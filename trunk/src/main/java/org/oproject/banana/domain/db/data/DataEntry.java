/**
 * 
 */
package org.oproject.banana.domain.db.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ���ݼ���
 * @author aohai.li
 */
public class DataEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * �����ݼ���
	 */
	private List<TableData> tables = new ArrayList<TableData>();

	public List<TableData> getTables() {
		return tables;
	}

	public void setTables(List<TableData> tables) {
		this.tables = tables;
	}
}
