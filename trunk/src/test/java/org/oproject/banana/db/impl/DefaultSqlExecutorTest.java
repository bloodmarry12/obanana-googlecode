/**
 * 
 */
package org.oproject.banana.db.impl;

import org.junit.Test;
import org.oproject.AbstractBananaTester;

/**
 * ≤‚ ‘”√¿˝
 * @author aohai.li
 */
public class DefaultSqlExecutorTest extends AbstractBananaTester {

	@Test
	public void query(){
		DefaultSqlExecutor executor = context.getInstance(DefaultSqlExecutor.class);
		System.out.println(executor.select("SELECT SYSDATE FROM DUAL"));
	}
	
	@Test
	public void querya(){
		DefaultSqlExecutor executor = context.getInstance(DefaultSqlExecutor.class);
		System.out.println(executor.select("SELECT COUNT(*) FROM CS_CP_INFO_TRACE"));
	}
}
