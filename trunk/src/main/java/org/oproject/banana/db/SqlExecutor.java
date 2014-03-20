/**
 * 
 */
package org.oproject.banana.db;

import java.util.List;
import java.util.Map;

/**
 * ��װ���SQLִ����
 * @author aohai.li
 */
public interface SqlExecutor {

	/**
	 * ��ѯ
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> select(String sql);
	
	/**
	 * ��ѯ
	 * @param sql
	 * @param params
	 * @return
	 */
	Map<String, Object> select(String sql, Object... params);
	
	/**
	 * ɾ��
	 * @param sql
	 * @return
	 */
	int delete(String sql);
	
	/**
	 * ɾ��
	 * @param sql
	 * @param params
	 * @return
	 */
	int delete(String sql, Object... params);
	
	/**
	 * ����
	 * @param sql
	 * @return
	 */
	int insert(String sql);
	
	/**
	 * ����
	 * @param sql
	 * @param params
	 * @return
	 */
	int insert(String sql, Object... params);
	
	/**
	 * ����
	 * @param sql
	 * @return
	 */
	int update(String sql);
	
	/**
	 * ����
	 * @param sql
	 * @param params
	 * @return
	 */
	int update(String sql, Object... params);
}
