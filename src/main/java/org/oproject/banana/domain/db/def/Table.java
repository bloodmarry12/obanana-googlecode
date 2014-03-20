/**
 * 
 */
package org.oproject.banana.domain.db.def;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 表定义
 * @author aohai.li
 */
public class Table implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1495220317845574513L;

	/**
	 * 默认空构造函数
	 */
	public Table() {
		super();
	}

	/**
	 * 构造函数
	 * @param name
	 */
	public Table(String name) {
		super();
		this.name = name;
	}

	/**
	 * 表名称
	 */
	private String name;
	
	/**
	 * 表备注
	 */
	private String comment;
	
	/**
	 * 列集合
	 */
	List<Column> columns = new ArrayList<Column>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
}
