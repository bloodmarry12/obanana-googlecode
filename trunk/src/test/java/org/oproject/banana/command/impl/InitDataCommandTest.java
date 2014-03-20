package org.oproject.banana.command.impl;

import org.junit.Test;
import org.oproject.AbstractBananaTester;

public class InitDataCommandTest extends AbstractBananaTester{

	@Test
	public void test_01(){
		InitDataCommand init = context.getInstance(InitDataCommand.class);
		init.initData("D:/微云网盘/286052056/开源项目/banana/code/banana/test/files/org/oproject/banana/command/impl/InitDataCommand/test_01.json", null);
	}
}
