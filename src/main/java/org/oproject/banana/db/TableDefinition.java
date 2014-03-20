package org.oproject.banana.db;

import org.oproject.banana.domain.db.def.Table;

/**
 * @author aohai.li
 */
public interface TableDefinition {

	/**
	 * 获取定义
	 * @param tableName
	 * @return
	 */
	Table getDefinition(String tableName);
}
