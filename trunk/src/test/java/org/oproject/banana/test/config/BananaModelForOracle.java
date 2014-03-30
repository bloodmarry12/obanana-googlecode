/**
 * 
 */
package org.oproject.banana.test.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.oproject.banana.Banana;
import org.oproject.banana.command.impl.CleanDataCommand;
import org.oproject.banana.command.impl.InitDataCommand;
import org.oproject.banana.command.impl.InitDataDefCommand;
import org.oproject.banana.command.impl.InvokeCommand;
import org.oproject.banana.db.SqlExecutor;
import org.oproject.banana.db.TableDefinition;
import org.oproject.banana.db.impl.DefaultSqlExecutor;
import org.oproject.banana.db.impl.OracleTableDefinition;
import org.oproject.banana.text.Formator;
import org.oproject.banana.text.Parser;
import org.oproject.banana.text.json.Json2DataEntryParser;
import org.oproject.banana.text.json.Table2JsonFormator;
import org.oproject.banana.text.sql.DataEntry2DeleteSQLFormator;
import org.oproject.banana.text.sql.DataEntry2InsertSQLFormator;
import org.oproject.banana.text.sql.DataEntry2SelectSQLFormator;
import org.oproject.banana.text.velocity.directive.SQLDeleteDirective;
import org.oproject.banana.text.velocity.directive.SQLExecutoryDirective;
import org.oproject.banana.text.velocity.directive.SQLSelectDirective;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * 基于ORACLE的环境配置
 * @author aohai.li
 */
public class BananaModelForOracle extends AbstractModule {

	private static final Logger log = Logger.getLogger(BananaModelForOracle.class);
	
	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		
	    setDataSource();
	    bind(SqlExecutor.class).to(DefaultSqlExecutor.class);
		bind(TableDefinition.class).to(OracleTableDefinition.class);
		
		bind(InitDataCommand.class);
		bind(InitDataDefCommand.class);
		bind(CleanDataCommand.class);
		bind(InvokeCommand.class);
		
		bind(Formator.class).annotatedWith(Names.named("Table2JsonFormator")).to(Table2JsonFormator.class);
		bind(Formator.class).annotatedWith(Names.named("DataEntry2InsertSQLFormator")).to(DataEntry2InsertSQLFormator.class);
		bind(Formator.class).annotatedWith(Names.named("DataEntry2SelectSQLFormator")).to(DataEntry2SelectSQLFormator.class);
		bind(Formator.class).annotatedWith(Names.named("DataEntry2DeleteSQLFormator")).to(DataEntry2DeleteSQLFormator.class);
		
		bind(Parser.class).annotatedWith(Names.named("Json2DataEntryParser")).to(Json2DataEntryParser.class);
		
		requestStaticInjection(SQLExecutoryDirective.class);
		requestStaticInjection(SQLSelectDirective.class);
		requestStaticInjection(SQLDeleteDirective.class);
		requestStaticInjection(Banana.class);
		requestStaticInjection(InvokeCommand.class);
	}

	private void setDataSource() {
		try{
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			cpds.setDriverClass( "oracle.jdbc.OracleDriver" ); //loads the jdbc driver            
			cpds.setJdbcUrl( "jdbc:oracle:thin:@public2.devdb.alipay.net:1521:public2" );
			cpds.setUser("apaycrm");                                  
			cpds.setPassword("ali99");                                  
			
			// the settings below are optional -- c3p0 can work with defaults
			cpds.setMinPoolSize(5);                                     
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(5);
			
			bind(DataSource.class).toInstance(cpds);
		} catch (PropertyVetoException pve){
			log.error("初始化连接池失败，", pve);
			throw new RuntimeException(pve.toString());
		}
	}
}
