/**
 * 
 */
package org.oproject.banana.domain.db.data;

/**
 * @author aohai.li
 *
 */
public class ColumnData {

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 值
	 */
	private String value;
	
	/**
	 * 类型
	 */
	private Type type = Type.STRING;

	public ColumnData() {
		super();
	}

	public ColumnData(String name, String value, Type type) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * 类型
	 * @author aohai.li
	 */
	public enum Type {
		STRING, NUM, FUNC
	}

	@Override
	public String toString() {
		return "ColumnData [name=" + name + ", value=" + value + ", type="
				+ type + "]";
	}
}
