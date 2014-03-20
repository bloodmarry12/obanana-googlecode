/**
 * 
 */
package org.oproject.banana.text;

/**
 * 解析器，用于将字符串解析为java对象
 * @author aohai.li
 */
public interface Parser<T> {

	T parse(String str);
}
