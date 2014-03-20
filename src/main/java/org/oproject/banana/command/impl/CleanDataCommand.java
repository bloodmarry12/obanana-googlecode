/**
 * 
 */
package org.oproject.banana.command.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.oproject.banana.command.Command;
import org.oproject.banana.db.SqlExecutor;
import org.oproject.banana.domain.db.data.DataEntry;
import org.oproject.banana.text.Formator;
import org.oproject.banana.text.Parser;
import org.oproject.banana.text.velocity.ParserAfterVelocityMerge;
import org.oproject.banana.util.FileTool;

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
public class CleanDataCommand implements Command{

	/**
	 * ��־����
	 */
	private static final Logger logger = Logger.getLogger(CleanDataCommand.class);
	
	@SuppressWarnings("rawtypes")
	@Inject
	@Named("Json2DataEntryParser")
	private Parser parser;
	
	@SuppressWarnings("rawtypes")
	@Inject
	@Named("DataEntry2DeleteSQLFormator")
	private Formator formator;
	
	@Inject
	private SqlExecutor sqlExecutor;
	
	/**
	 * �����������
	 * 
	 * @param filePath
	 */
	@SuppressWarnings("unchecked")
	public void clean(String filePath, Map<String, Object> content){
		String fileContent = FileTool.readFileContent(filePath);
		
		DataEntry de = ParserAfterVelocityMerge.parse(fileContent, content, parser);
		
		List<String> sqls = (List<String>)formator.format(de);
		
		for(String sql : sqls){
			int ret = sqlExecutor.delete(sql);
			
			if(logger.isDebugEnabled()){
				logger.debug("ɾ��[" + (ret == 1 ? "�ɹ�": "ʧ��") + "]:" + sql);
			}
		}
	}
}