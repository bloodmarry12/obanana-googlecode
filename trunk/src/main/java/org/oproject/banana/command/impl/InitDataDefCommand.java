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
 * 数据初始化工具
 * <ul>
 * <li>生成原始模板</li>
 * <li>读取并解析文件</li>
 * <li>批量更新文件</li>
 * </ul>
 * @author aohai.li
 */
public class InitDataDefCommand implements Command{

	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(InitDataDefCommand.class);
	
	@Inject
	private TableDefinition tableDefinition;
	
	/**
	 * 字符串生成器
	 */
	@SuppressWarnings("rawtypes")
	@Inject
	@Named("Table2JsonFormator")
	private Formator  formator;
	
	/**
	 * 在后台打印模板字符串
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
