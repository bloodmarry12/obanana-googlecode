package org.oproject.banana;

import java.util.Map;

import org.oproject.banana.command.impl.CheckDataCommand;
import org.oproject.banana.command.impl.CleanDataCommand;
import org.oproject.banana.command.impl.InitDataCommand;
import org.oproject.banana.command.impl.InvokeCommand;

import com.google.inject.Inject;

/**
 * �㽶
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
	 * ִ����֤�ű�Ŀ¼
	 * </p>
	 * <ul>
	 * <li>1������INIT.json�ļ���ִ��</li>
	 * <li>2��ִ��INFOKE.groovy�ļ���ִ��</li>
	 * <li>3������CHECK.json�ļ���ִ��</li>
	 * <li>4������CLEAN.json�ļ���ִ��</li>
	 * </ul>
	 * @param dir
	 * @return
	 */
	public static boolean run(String dirPath, Map<String, Object> content){
		
		// 1�������������
		clean.clean(dirPath + "/clean.json", content);
		// 2����ʼ����������
		init.initData(dirPath + "/init.json", content);
		// 3��ִ�нű�
		invoke.invoke(dirPath + "/invoke.groovy", content);
		// 4����ִ֤�к�����
		check.exists(dirPath + "/check.json", content);
		return false;
	}
}
