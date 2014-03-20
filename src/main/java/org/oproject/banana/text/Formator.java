/**
 * 
 */
package org.oproject.banana.text;

/**
 * 文本格式化工具
 * @author aohai.li
 */
public interface Formator<T, E> {

	/**
	 * 格式化工具
	 * @param t
	 * @return
	 */
	E format(T t);
}
