/**
 * 
 */
package org.oproject.banana.text.sql;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.oproject.AbstractBananaTester;
import org.oproject.banana.domain.db.data.ColumnData;
import org.oproject.banana.domain.db.data.ColumnData.Type;
import org.oproject.banana.domain.db.data.DataEntry;
import org.oproject.banana.domain.db.data.TableData;

/**
 * @author aohai.li
 *
 */
public class DataEntry2InsertSQLFormatorTest extends AbstractBananaTester {

	@Test
	public void test_01(){
		DataEntry2InsertSQLFormator formator = new DataEntry2InsertSQLFormator();
		
		DataEntry de = new DataEntry();
		TableData tb1 = new TableData("USER");
		tb1.getColumns().add(new ColumnData("ID", "1", Type.NUM));
		tb1.getColumns().add(new ColumnData("NAME", "AOHAI.LI", Type.STRING));
		tb1.getColumns().add(new ColumnData("FK", "SYSDATE", Type.FUNC));
		de.getTables().add(tb1);
		List<String> sqls = formator.format(de);
		
		Assert.assertEquals("insert into USER (ID, NAME, FK) values (1, 'AOHAI.LI', SYSDATE)", sqls.get(0));
	}
}
