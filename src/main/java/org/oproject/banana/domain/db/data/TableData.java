/**
 * 
 */
package org.oproject.banana.domain.db.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aohai.li
 *
 */
public class TableData {

	private String name;

	private List<ColumnData> columns = new ArrayList<ColumnData>();
	
	public TableData() {
		super();
	}

	public TableData(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ColumnData> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnData> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "TableData [name=" + name + ", columns=" + columns + "]";
	}
}
