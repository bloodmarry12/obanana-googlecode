/**
 * 
 */
package org.oproject.banana.text.json;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.oproject.AbstractBananaTester;
import org.oproject.banana.domain.db.data.DataEntry;
import org.oproject.banana.util.FileTool;


/**
 * @author aohai.li
 *
 */
public class Json2DataEntryParserTest extends AbstractBananaTester {

	@Test
	public void t(){
		String fileContent = FileTool.readFileContent("D:/微云网盘/286052056/开源项目/banana/code/banana/test/files/org/oproject/banana/text/json/Json2DataEntryParserTest/test_01.json");
		Json2DataEntryParser j2dep = new Json2DataEntryParser();
		DataEntry dataEntry = j2dep.parse(fileContent);
		System.out.println(ToStringBuilder.reflectionToString(dataEntry));
	}
}
