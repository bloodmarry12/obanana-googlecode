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
 * 数据初始化工具
 * <ul>
 * <li>生成原始模板</li>
 * <li>读取并解析文件</li>
 * <li>批量更新文件</li>
 * </ul>
 * @author aohai.li
 */
public class InitDataCommand implements Command{

	/**
	 * 日志对象
	 */
	private static final Logger logger = Logger.getLogger(InitDataCommand.class);
	
	@SuppressWarnings("rawtypes")
	@Inject
	@Named("Json2DataEntryParser")
	private Parser parser;
	
	@SuppressWarnings("rawtypes")
	@Inject
	@Named("DataEntry2InsertSQLFormator")
	private Formator formator;
	
	@Inject
	private SqlExecutor sqlExecutor;
	
	/**
	 * 构造测试数据
	 * 
	 * @param filePath
	 */
	@SuppressWarnings("unchecked")
	public void initData(String filePath, Map<String, Object> content){
		String fileContent = FileTool.readFileContent(filePath);
		
		DataEntry de = ParserAfterVelocityMerge.parse(fileContent, content, parser);
		
		List<String> sqls = (List<String>)formator.format(de);
		
		for(String sql : sqls){
			int ret = sqlExecutor.insert(sql);
			
			if(logger.isDebugEnabled()){
				logger.debug("插入[" + (ret == 1 ? "成功": "失败") + "]:" + sql);
			}
		}
	}
}
