/**
 * 
 */
package org.oproject.banana.db.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.oproject.banana.db.SqlExecutor;
import org.oproject.banana.db.TableDefinition;
import org.oproject.banana.domain.db.def.Column;
import org.oproject.banana.domain.db.def.Table;

import com.google.inject.Inject;

/**
 * @author aohai.li
 */
public class OracleTableDefinition implements TableDefinition {

	private static final Logger log = Logger
			.getLogger(OracleTableDefinition.class);

	/**
	 * 
	 */
	@Inject
	private SqlExecutor sqlExecutor;

	@Override
	public Table getDefinition(String tableName) {

		if (null == tableName || "".equals(tableName.trim())) {
			return null;
		}

		StringBuilder sqlsb = new StringBuilder();
		sqlsb.append(
				"select c.COLUMN_NAME, c.DATA_TYPE||'('||c.DATA_LENGTH||') '|| c.NULLABLE DS, cc.COMMENTS")
				.append(" from USER_TAB_COLUMNS c inner join user_col_comments cc")
				.append(" on c.TABLE_NAME = cc.TABLE_NAME and c.COLUMN_NAME = cc.COLUMN_NAME")
				.append(" where c.TABLE_NAME = '")
				.append(tableName.toUpperCase()).append("'");

		List<Map<String, Object>> list = sqlExecutor.select(sqlsb.toString());

		if (null == list || list.isEmpty()) {
			return null;
		}

		Table table = new Table();
		table.setName(tableName.toUpperCase());

		for (Map<String, Object> record : list) {
			Column column = new Column();
			column.setName(String.valueOf(record.get("COLUMN_NAME")));
			column.setComments(String.valueOf(record.get("COMMENTS")));
			column.setType(String.valueOf(record.get("DS")));
			table.getColumns().add(column);
		}

		return table;
	}
}
