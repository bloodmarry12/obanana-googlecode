/**
 * 
 */
package org.oproject.banana.text.json;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.oproject.banana.domain.db.def.Column;
import org.oproject.banana.domain.db.def.Table;
import org.oproject.banana.text.Formator;

/**
 * Table对象转换为json格式
 * @author aohai.li
 */
public class Table2JsonFormator implements Formator<List<Table>, String> {

	public static final String CONS_LINESEP = System
			.getProperty("line.separator");

	public static final String CONS_BLANK2 = "  ";

	public static final String CONS_BLANK4 = "    ";
	
	public static final String CONS_BLANK8 = "        ";

	@Override
	public String format(List<Table> tableList) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		for (int tableIndex = 0; tableIndex < tableList.size(); tableIndex++) {
			Table table = tableList.get(tableIndex);
			sb.append(CONS_LINESEP).append(CONS_BLANK4).append("\"")
					.append(table.getName().toUpperCase()).append("\"")
					.append(" : [{");
			List<Column> columnList = table.getColumns();

			for (int columnIndex = 0 ; columnIndex < columnList.size(); columnIndex++) {
				Column column = columnList.get(columnIndex);
				sb.append(CONS_LINESEP);
				StringBuilder columnStringBuilder = new StringBuilder();
				columnStringBuilder.append(CONS_BLANK8).append("\"")
				.append(column.getName())
				.append("\"").append(" : null");
				
				if (columnIndex < columnList.size() - 1) {
					columnStringBuilder.append(",");
				}
				
				/*
				 *  为了整齐，做注释的对其
				 */
				if(columnStringBuilder.length() < 45){
					columnStringBuilder.append(StringUtils.leftPad("", 60 - columnStringBuilder.length()));
				} else { 
					columnStringBuilder.append(CONS_BLANK4);
				}
				columnStringBuilder.append("/*")
				.append(column.getType())
				.append(" ")
				.append(column.getComments())
				.append("*/");
				
				sb.append(columnStringBuilder.toString());
			}
			sb.append(CONS_LINESEP).append(CONS_BLANK4).append("}]");
			
			if (tableIndex < tableList.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(CONS_LINESEP).append("}");
		return sb.toString();
	}
}
