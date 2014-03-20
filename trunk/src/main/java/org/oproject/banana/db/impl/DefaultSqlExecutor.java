/**
 * 
 */
package org.oproject.banana.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.oproject.banana.db.SqlExecutor;
import org.oproject.banana.exception.DAOException;

import com.google.inject.Inject;

/**
 * 默认的SQL执行器
 * 
 * @author aohai.li
 */
public class DefaultSqlExecutor implements SqlExecutor {

	private static final Logger log = Logger
			.getLogger(DefaultSqlExecutor.class);

	/**
	 * 数据源
	 */
	@Inject
	private DataSource dataSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.oproject.banana.db.SqlExecutor#select(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> select(String sql) {
		if (log.isDebugEnabled()) {
			log.debug("执行SQL:" + sql);
		}
		List<Map<String, Object>> ret = executeWithoutTransation(
				new ExecutorForQueryStmt(sql), new ResultSet2ListMapWrapper());

		if (log.isDebugEnabled()) {
			log.debug("返回结果:" + ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.oproject.banana.db.SqlExecutor#select(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public Map<String, Object> select(String sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.oproject.banana.db.SqlExecutor#delete(java.lang.String)
	 */
	@Override
	public int delete(String sql) {
		if (log.isDebugEnabled()) {
			log.debug("执行SQL:" + sql);
		}

		int ret = executeWithTransation(new ExecutorForUpdateStmt(sql),
				new DefaultResultWrapper<Integer>());

		if (log.isDebugEnabled()) {
			log.debug("返回结果:" + ret);
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.oproject.banana.db.SqlExecutor#delete(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public int delete(final String sql, Object... params) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.oproject.banana.db.SqlExecutor#insert(java.lang.String)
	 */
	@Override
	public int insert(String sql) {
		if (log.isDebugEnabled()) {
			log.debug("执行SQL:" + sql);
		}
		
		int ret = executeWithTransation(new ExecutorForUpdateStmt(sql),
				new DefaultResultWrapper<Integer>());
		
		if (log.isDebugEnabled()) {
			log.debug("返回结果:" + ret);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.oproject.banana.db.SqlExecutor#insert(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public int insert(String sql, Object... params) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.oproject.banana.db.SqlExecutor#update(java.lang.String)
	 */
	@Override
	public int update(String sql) {
		return executeWithTransation(new ExecutorForUpdateStmt(sql),
				new DefaultResultWrapper<Integer>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.oproject.banana.db.SqlExecutor#update(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public int update(String sql, Object... params) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 在事务中执行
	 * 
	 * @param execute
	 * @return
	 */
	protected <T, R> T executeWithTransation(Executor<R> executor,
			ResultWrapper<T, R> wrapper) {
		T ret = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			R t = executor.execute(conn);
			conn.commit();
			ret = wrapper.wrap(t);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e.toString(), e1);
			}
			throw new DAOException(e.toString(), e);
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}
		return ret;
	}

	/**
	 * 在事务中执行
	 * 
	 * @param execute
	 * @return
	 */
	protected <T, R> T executeWithoutTransation(Executor<R> executor,
			ResultWrapper<T, R> wrapper) {
		T ret = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			R r = executor.execute(conn);
			ret = wrapper.wrap(r);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e.toString(), e1);
			}
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}
		return ret;
	}

	/**
	 * 执行器
	 * 
	 * @author aohai.li
	 * 
	 */
	interface Executor<T> {
		T execute(Connection conn) throws SQLException;
	}

	/**
	 * 执行更新语句
	 * 
	 * @author aohai.li
	 */
	class ExecutorForUpdateStmt implements Executor<Integer> {

		/**
		 * 要执行的SQL
		 */
		private String sql;

		/**
		 * @param sql
		 */
		public ExecutorForUpdateStmt(String sql) {
			super();
			this.sql = sql;
		}

		@Override
		public Integer execute(Connection conn) throws SQLException {
			Statement stmt = conn.createStatement();
			return stmt.executeUpdate(sql);
		}
	}

	/**
	 * 执行更新语句
	 * 
	 * @author aohai.li
	 */
	class ExecutorForQueryStmt implements Executor<ResultSet> {

		/**
		 * 要执行的SQL
		 */
		private String sql;

		/**
		 * @param sql
		 */
		public ExecutorForQueryStmt(String sql) {
			super();
			this.sql = sql;
		}

		@Override
		public ResultSet execute(Connection conn) throws SQLException {
			Statement stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		}
	}

	/**
	 * @author aohai.li
	 * @param <R>
	 * @param <T>
	 */
	interface ResultWrapper<T, R> {
		T wrap(R r);
	}

	class DefaultResultWrapper<T> implements ResultWrapper<T, T> {

		@Override
		public T wrap(T r) {
			return r;
		}
	}

	class ResultSet2ListMapWrapper implements
			ResultWrapper<List<Map<String, Object>>, ResultSet> {

		@Override
		public List<Map<String, Object>> wrap(ResultSet rs) {
			List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
			try {
				while (rs.next()) {
					Map<String, Object> record = new HashMap<String, Object>();
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();
					for (int i = 1; i < columnCount + 1; i++) {
						String columnName = rsmd.getColumnLabel(i);
						Object columnValue = rs.getObject(i);
						record.put(columnName, columnValue);
					}
					retList.add(record);
				}
			} catch (SQLException e) {
				throw new DAOException("返回结果转换失败，", e);
			}
			return retList;
		}
	}
}
