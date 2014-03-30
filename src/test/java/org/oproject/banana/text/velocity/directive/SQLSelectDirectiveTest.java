/**
 * 
 */
package org.oproject.banana.text.velocity.directive;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.oproject.AbstractBananaTester;
import org.oproject.banana.text.velocity.ParserAfterVelocityMerge;

/**
 * @author aohai.li
 *
 */
public class SQLSelectDirectiveTest extends AbstractBananaTester  {

	/**
	 * ≤‚ ‘
	 */
	@Test
	public void test_01(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("Count=");
		sb.append("#SQL_select()\n");
		sb.append("select count(*) from cs_cp_info t where t.id = '$cp_id'");
		sb.append("#end");
		
		Map<String, Object> contextMap = new HashMap<String, Object>();
		contextMap.put("cp_id", "123");
		ParserAfterVelocityMerge.parse(sb.toString(), contextMap, null);
	}
}
