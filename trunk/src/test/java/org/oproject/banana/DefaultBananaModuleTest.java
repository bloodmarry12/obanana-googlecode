/**
 * 
 */
package org.oproject.banana;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.oproject.AbstractBananaTester;
import org.oproject.banana.db.SqlExecutor;

/**
 * @author aohai.li
 */
public class DefaultBananaModuleTest extends AbstractBananaTester{

	@Test
	public void start(){
		SqlExecutor sqlExecutor = context.getInstance(SqlExecutor.class);
		List<Map<String, Object>> records = sqlExecutor.select("SELECT SYSDATE FROM DUAL");
		System.out.println("->" + records);
	}
}
