/**
 * 
 */
package org.oproject.banana.db.impl;


import org.junit.Assert;
import org.junit.Test;
import org.oproject.AbstractBananaTester;
import org.oproject.banana.db.TableDefinition;
import org.oproject.banana.domain.db.def.Table;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @author aohai.li
 */
public class OracleTableDefinitionTest extends AbstractBananaTester {

	@Test
	public void genTable(){
		TableDefinition td = context.getInstance(TableDefinition.class);
		Table table = td.getDefinition("cs_cp_info");
		
		Assert.assertNotNull(table);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(table));
	}
}
