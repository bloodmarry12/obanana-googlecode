package org.oproject.banana.command.impl;

import org.junit.Test;
import org.oproject.AbstractBananaTester;

public class CheckDataCommandTest extends AbstractBananaTester{

	@Test
	public void test_01(){
		CheckDataCommand init = context.getInstance(CheckDataCommand.class);
		init.exists(DIR_BASE + "/test/files/org/oproject/banana/command/impl/CheckDataCommandTest/test_01.json", null);
	}
}
