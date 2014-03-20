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
public class DataEntry2SelectSQLFormatorTest extends AbstractBananaTester {

	@Test
	public void test_01(){
		DataEntry2SelectSQLFormator formator = new DataEntry2SelectSQLFormator();
		
		DataEntry de = new DataEntry();
		TableData tb1 = new TableData("USER");
		tb1.getColumns().add(new ColumnData("ID", "1", Type.NUM));
		tb1.getColumns().add(new ColumnData("NAME", "AOHAI.LI", Type.STRING));
		tb1.getColumns().add(new ColumnData("FK", "SYSDATE", Type.FUNC));
		de.getTables().add(tb1);
		List<String> sqls = formator.format(de);
		Assert.assertEquals("select * from USER where ID = 1 and NAME = 'AOHAI.LI' and FK = SYSDATE ", sqls.get(0));
	}
}
