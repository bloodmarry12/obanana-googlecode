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
public class DataEntry2InsertSQLFormator implements Formator<DataEntry, List<String>> {
	
	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(DataEntry2InsertSQLFormator.class);
	
	@Override
	public List<String> format(DataEntry t) {
		List<String> ret = new ArrayList<String>();
		
		if(null == t || t.getTables().isEmpty()){
			return ret;
		}
		
		for(TableData tableData : t.getTables()){
			StringBuilder insertStatement = new StringBuilder();
	        String tableName = tableData.getName();
	        insertStatement.append("insert into ").append(tableName).append(" ");
	        StringBuilder colsStatement = new StringBuilder();
	        StringBuilder valsStatement = new StringBuilder();
	        for(ColumnData columnData : tableData.getColumns()){
	        	
	            String columnName = columnData.getName();
	            String sqlValue = columnData.getValue();
	            ColumnData.Type columnType = columnData.getType();
	            // null值，在构建insert语句的时候，直接忽略掉就可以了
	            if(null == sqlValue){
	                continue;
	            } 
	            
	            if(ColumnData.Type.STRING == columnType){
	            	sqlValue = "'" + sqlValue + "'";
	            }
	            
	            if(colsStatement.length() > 0){
	                colsStatement.append(", ");
	                valsStatement.append(", ");
	            }
	            colsStatement.append(columnName);
	            valsStatement.append(sqlValue);
	        }
	        insertStatement.append("(").append(colsStatement).append(") values (");
	        insertStatement.append(valsStatement).append(")");
	        if(log.isDebugEnabled()){
	        	log.debug("构建insertStatement: " + insertStatement);
	        }
	        
	        ret.add(insertStatement.toString());
		}
		return ret;
	}
}
