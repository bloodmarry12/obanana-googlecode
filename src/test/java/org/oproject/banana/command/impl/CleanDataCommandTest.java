package org.oproject.banana.command.impl;

import org.junit.Test;
import org.oproject.AbstractBananaTester;

public class CleanDataCommandTest extends AbstractBananaTester{

	/**
	 * 
	 */
	@Test
	public void test_01(){
		CleanDataCommand clean = context.getInstance(CleanDataCommand.class);
		clean.clean(DIR_BASE + "/test/files/org/oproject/banana/command/impl/CleanDataCommand/test_01.json", null);
	}
	
	/**
	 * µ¥Ôª²âÊÔ
	 */
	@Test
	public void test_empty(){
		CleanDataCommand clean = context.getInstance(CleanDataCommand.class);
		clean.clean(DIR_BASE + "/test/files/org/oproject/banana/command/impl/CleanDataCommand/test_empty.json", null);
	}
}
