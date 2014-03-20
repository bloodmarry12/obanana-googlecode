/**
 * 
 */
package org.oproject.banana.text.json;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.oproject.AbstractBananaTester;
import org.oproject.banana.domain.db.def.Column;
import org.oproject.banana.domain.db.def.Table;
import org.oproject.banana.text.Formator;

/**
 * 
 * @author aohai.li
 */
public class Table2JsonFormatorTest extends AbstractBananaTester {

	
	@Test
	public void test_01(){
		Formator<List<Table>, String> formator = new Table2JsonFormator();
		
		Table table = new Table("TEST");
		table.getColumns().add(new Column("COL_1", "COMMENTS_1"));
		table.getColumns().add(new Column("COL_2", "COMMENTS_2"));
		table.getColumns().add(new Column("COL_3", "COMMENTS_3"));
		table.getColumns().add(new Column("COL_4", "COMMENTS_4"));
		
		Table userTable = new Table("USER");
		userTable.getColumns().add(new Column("ID", "Á÷Ë®ºÅ"));
		userTable.getColumns().add(new Column("NAME", "COMMENTS_2"));
		userTable.getColumns().add(new Column("TYPE", "COMMENTS_3"));
		userTable.getColumns().add(new Column("GMT_CREATE", "COMMENTS_4"));
		
		List<Table> tableList= new ArrayList<Table>();
		tableList.add(table);
		tableList.add(userTable);
		
		String str = formator.format(tableList);
		
		System.out.println(str);
	}
}
