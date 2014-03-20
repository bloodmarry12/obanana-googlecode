package org.oproject.banana;

import java.util.Map;

import org.oproject.banana.command.impl.CheckDataCommand;
import org.oproject.banana.command.impl.CleanDataCommand;
import org.oproject.banana.command.impl.InitDataCommand;
import org.oproject.banana.command.impl.InvokeCommand;

import com.google.inject.Inject;

/**
 * 香蕉
 * @author aohai.li
 */
public class Banana {

	@Inject
	private static CleanDataCommand clean;
	
	@Inject
	private static CheckDataCommand check;
	
	@Inject
	private static InitDataCommand init;
	
	@Inject
	private static InvokeCommand invoke;
	
	/**
	 * run
	 * <p>
	 * 执行验证脚本目录
	 * </p>
	 * <ul>
	 * <li>1、加载INIT.json文件，执行</li>
	 * <li>2、执行INFOKE.groovy文件，执行</li>
	 * <li>3、加载CHECK.json文件，执行</li>
	 * <li>4、加载CLEAN.json文件，执行</li>
	 * </ul>
	 * @param dir
	 * @return
	 */
	public static boolean run(String dirPath, Map<String, Object> content){
		
		// 1、清理测试数据
		clean.clean(dirPath + "/clean.json", content);
		// 2、初始化测试数据
		init.initData(dirPath + "/init.json", content);
		// 3、执行脚本
		invoke.invoke(dirPath + "/invoke.groovy", content);
		// 4、验证执行后数据
		check.exists(dirPath + "/check.json", content);
		return false;
	}
}
