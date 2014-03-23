/**
 * 
 */
package org.oproject.banana.text.velocity;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.oproject.banana.text.Parser;

/**
 * Velocityǰ����Ⱦ��
 * @author aohai.li
 */
public class ParserAfterVelocityMerge{
	
	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(ParserAfterVelocityMerge.class);
	
	/*
	 * ��ʼ��velocity
	 */
	static{
			Properties pros = new Properties();
			pros.put("userdirective", "org.oproject.banana.text.velocity.directive.SQLExecutoryDirective");
			Velocity.init(pros);
//		Velocity.addProperty("executeSQL", "org.oproject.banana.text.velocity.directive.SQLExecutoryDirective");
	}

	public static <T> T parse(String str, Parser<T> parser) {
		return parse(str, null, parser);
	}
	
	public static <T> T parse(String str, Map<String, Object> context, Parser<T> parser) {
		
		if(StringUtils.isBlank(str)){
			return null;
		}
		
		// TODO velocity����merge
		VelocityContext velocityContext = new VelocityContext();
		
		if(null != context && !context.isEmpty()){
			for(Map.Entry<String, Object> entry : context.entrySet()){
				velocityContext.put(entry.getKey(), entry.getValue());
			}
		}
//		SQLExecutoryDirective
		// �����ӽ��������д���
		StringWriter writer = new StringWriter();
		Velocity.evaluate(velocityContext, writer, "velocityEvaluate", str);
		String mergedStr = writer.toString();
		
		if(log.isDebugEnabled()){
			log.debug("Velocity��Ⱦ�����: " + mergedStr);
		}
		
		if(null == parser){
			return null;
		}
		
		return parser.parse(mergedStr);
	}
}
