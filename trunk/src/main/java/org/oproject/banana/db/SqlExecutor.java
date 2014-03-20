/**
 * 
 */
package org.oproject.banana.db;

import java.util.List;
import java.util.Map;

/**
 * 封装后的SQL执行器
 * @author aohai.li
 */
public interface SqlExecutor {

	/**
	 * 查询
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> select(String sql);
	
	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return
	 */
	Map<String, Object> select(String sql, Object... params);
	
	/**
	 * 删除
	 * @param sql
	 * @return
	 */
	int delete(String sql);
	
	/**
	 * 删除
	 * @param sql
	 * @param params
	 * @return
	 */
	int delete(String sql, Object... params);
	
	/**
	 * 插入
	 * @param sql
	 * @return
	 */
	int insert(String sql);
	
	/**
	 * 插入
	 * @param sql
	 * @param params
	 * @return
	 */
	int insert(String sql, Object... params);
	
	/**
	 * 更新
	 * @param sql
	 * @return
	 */
	int update(String sql);
	
	/**
	 * 更新
	 * @param sql
	 * @param params
	 * @return
	 */
	int update(String sql, Object... params);
}
