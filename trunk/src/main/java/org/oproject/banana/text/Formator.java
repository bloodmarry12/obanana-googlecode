/**
 * 
 */
package org.oproject.banana.text;

/**
 * �ı���ʽ������
 * @author aohai.li
 */
public interface Formator<T, E> {

	/**
	 * ��ʽ������
	 * @param t
	 * @return
	 */
	E format(T t);
}
