/**
 * 
 */
package org.oproject.banana.text.json;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.oproject.banana.domain.db.data.ColumnData;
import org.oproject.banana.domain.db.data.DataEntry;
import org.oproject.banana.domain.db.data.TableData;
import org.oproject.banana.text.Parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

/**
 * @author aohai.li
 * 
 */
public class Json2DataEntryParser implements Parser<DataEntry> {

	@Override
	public DataEntry parse(String str) {
		Gson gson = new GsonBuilder().registerTypeAdapter(DataEntry.class,
				new JsonDeserializer<DataEntry>() {

			@Override
			public DataEntry deserialize(JsonElement arg0, Type arg1,
					JsonDeserializationContext arg2) throws JsonParseException {
				// TODO Auto-generated method stub
				if (null == arg0){
					return null;
				}
				
				DataEntry de = new DataEntry();
				
				Set<Entry<String, JsonElement>> jsonElementSet = arg0.getAsJsonObject().entrySet();
				for(Entry<String, JsonElement> entry : jsonElementSet){
					JsonArray tables = entry.getValue().getAsJsonArray();
					Iterator<JsonElement> tablesIterator = tables.iterator();
					while(tablesIterator.hasNext()){
						JsonElement jsonElement = tablesIterator.next();
						TableData td = new TableData();
						td.setName(entry.getKey());
						Set<Entry<String, JsonElement>> columnJsonSet = jsonElement.getAsJsonObject().entrySet();
						for(Entry<String, JsonElement> columnJsonEntry : columnJsonSet){
							ColumnData columnData = new ColumnData();
							columnData.setName(columnJsonEntry.getKey());
							
							JsonElement valueJson = columnJsonEntry.getValue();
							
							// …Ë÷√÷µ
							String columnValueStr = null;
							ColumnData.Type columnValueType = ColumnData.Type.STRING;
							
							if( !valueJson.isJsonNull()){
								columnValueStr = valueJson.getAsString();
								
								if (valueJson.isJsonPrimitive()){
									JsonPrimitive jsonPrivPrimitive = valueJson.getAsJsonPrimitive();
									if(jsonPrivPrimitive.isNumber()){
										columnValueType = ColumnData.Type.NUM;
									} else if(columnValueStr.startsWith("FUNC(") && columnValueStr.endsWith(")")){
										columnValueStr = columnValueStr.substring(5, columnValueStr.length() - 1);
										columnValueType = ColumnData.Type.FUNC;
									}
								}
							}
							
						    columnData.setValue(columnValueStr);
							columnData.setType(columnValueType);
							td.getColumns().add(columnData);
						}
						de.getTables().add(td);
					}
				}
				return de;
			}}).create();
		DataEntry dataEntry = gson.fromJson(str, DataEntry.class);
		return dataEntry;
	}
}
