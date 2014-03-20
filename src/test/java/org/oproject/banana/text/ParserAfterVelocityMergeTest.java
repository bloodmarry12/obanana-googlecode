/**
 * 
 */
package org.oproject.banana.text;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.oproject.AbstractBananaTester;
import org.oproject.banana.text.velocity.ParserAfterVelocityMerge;

/**
 * @author aohai.li
 *
 */
public class ParserAfterVelocityMergeTest extends AbstractBananaTester{

	@Test
	public void evl_01(){
		String str = "We are using $project $name $none to render this.";
		Map<String, Object> contextMap = new HashMap<String, Object>();
		contextMap.put("project", "test");
		contextMap.put("name", "ADSD");
		ParserAfterVelocityMerge.parse(str, contextMap, null);
	}
	
	@Test
	public void evl_02(){
		String str = "We are using $project $name $!none to render this. #executeSQL(\"select SYSDATE FROM DUAL\")";
		Map<String, Object> contextMap = new HashMap<String, Object>();
		contextMap.put("project", "test");
		contextMap.put("name", "ADSD");
		ParserAfterVelocityMerge.parse(str, contextMap, null);
	}
	
	@Test
	public void evl_03(){
		String str = "#executeSQL(\"select SYSDATE FROM DUAL\")";
		Map<String, Object> contextMap = new HashMap<String, Object>();
		contextMap.put("project", "test");
		contextMap.put("name", "ADSD");
		ParserAfterVelocityMerge.parse(str, contextMap, null);
	}
}
