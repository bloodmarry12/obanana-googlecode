/**
 * 
 */
package org.oproject.banana.domain.db.def;

import java.io.Serializable;

/**
 * �ж���
 * @author aohai.li
 *
 */
public class Column implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Column() {
		super();
	}

	public Column(String name) {
		super();
		this.name = name;
	}

	public Column(String name, String comments) {
		super();
		this.name = name;
		this.comments = comments;
	}

	/**
	 * ����
	 */
	private String name;
	
	/**
	 * �б�ע
	 */
	private String comments;
	
	/**
	 * �ж���
	 */
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
