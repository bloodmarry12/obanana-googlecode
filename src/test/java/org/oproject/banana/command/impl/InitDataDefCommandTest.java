package org.oproject.banana.command.impl;

import org.junit.Test;
import org.oproject.AbstractBananaTester;

public class InitDataDefCommandTest extends AbstractBananaTester{

	@Test
	public void test_01(){
		InitDataDefCommand init = context.getInstance(InitDataDefCommand.class);
		init.sysoutDataTemplate(new String[]{"CS_CP_INFO"});
	}
}
