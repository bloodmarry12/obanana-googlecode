package org.oproject.banana.db;

import org.oproject.banana.domain.db.def.Table;

/**
 * @author aohai.li
 */
public interface TableDefinition {

	/**
	 * ��ȡ����
	 * @param tableName
	 * @return
	 */
	Table getDefinition(String tableName);
}
