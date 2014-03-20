/**
 * 
 */
package org.oproject.banana.text.sql;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.oproject.banana.domain.db.data.ColumnData;
import org.oproject.banana.domain.db.data.DataEntry;
import org.oproject.banana.domain.db.data.TableData;
import org.oproject.banana.text.Formator;

/**
 * 根据数据集创建Insert语句
 * @author aohai.li
 *
 */
public class DataEntry2DeleteSQLFormator implements Formator<DataEntry, List<String>> {
	
	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(DataEntry2DeleteSQLFormator.class);
	
	@Override
	public List<String> format(DataEntry t) {
		List<String> ret = new ArrayList<String>();
		
		if(null == t || t.getTables().isEmpty()){
			return ret;
		}
		
		for(TableData tableData : t.getTables()){
			StringBuilder deleteStatement = new StringBuilder();
			StringBuilder conditionStatement = new StringBuilder();
	        String tableName = tableData.getName();
	        for(ColumnData columnData : tableData.getColumns()){
	            String columnName = columnData.getName();
	            String sqlValue = columnData.getValue();
	            ColumnData.Type columnType = columnData.getType();

	            if(conditionStatement.length() > 0){
	            	conditionStatement.append("and ");
	            }

	            if(null == sqlValue){
	            	conditionStatement.append(columnName).append(" is null ");
	            } else {
	            	if(ColumnData.Type.STRING == columnType){
	            		sqlValue = "'" + sqlValue + "'";
	            	}
	            	
	            	conditionStatement.append(columnName).append(" = ").append(sqlValue).append(" ");
	            }
	            
	            
	        }
	        deleteStatement.append("delete from ").append(tableName).append(" where ").append(conditionStatement);
	       
	        if(log.isDebugEnabled()){
	        	log.debug("构建delStatement: " + deleteStatement);
	        }
	        
	        ret.add(deleteStatement.toString());
		}
		return ret;
	}
}
