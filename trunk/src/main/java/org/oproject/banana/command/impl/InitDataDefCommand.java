/**
 * 
 */
package org.oproject.banana.command.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.oproject.banana.command.Command;
import org.oproject.banana.db.TableDefinition;
import org.oproject.banana.domain.db.def.Table;
import org.oproject.banana.text.Formator;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * ���ݳ�ʼ������
 * <ul>
 * <li>����ԭʼģ��</li>
 * <li>��ȡ�������ļ�</li>
 * <li>���������ļ�</li>
 * </ul>
 * @author aohai.li
 */
public class InitDataDefCommand implements Command{

	/**
	 * ��־����
	 */
	private static final Logger log = Logger.getLogger(InitDataDefCommand.class);
	
	@Inject
	private TableDefinition tableDefinition;
	
	/**
	 * �ַ���������
	 */
	@SuppressWarnings("rawtypes")
	@Inject
	@Named("Table2JsonFormator")
	private Formator  formator;
	
	/**
	 * �ں�̨��ӡģ���ַ���
	 * @param tableNames
	 */
	@SuppressWarnings("unchecked")
	public void sysoutDataTemplate(String[] tableNames){
		List<Table> list = new ArrayList<Table>();
		for(String tableName : tableNames){
			list.add(tableDefinition.getDefinition(tableName));
		}
		
		String str = (String)formator.format(list);
		log.info(str);
	}
}
