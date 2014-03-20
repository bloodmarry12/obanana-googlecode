/**
 * 
 */
package org.oproject.banana.test.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.oproject.banana.db.SqlExecutor;
import org.oproject.banana.db.impl.DefaultSqlExecutor;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 基于ORACLE的环境配置
 * @author aohai.li
 */
public class BananaModelForMysql extends AbstractModule {

	private static final Logger log = Logger.getLogger(BananaModelForMysql.class);
	
	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(Integer.class) .annotatedWith(Names.named("server_port")).toInstance(12);
		
	    setDataSource();
	    
	    bind(SqlExecutor.class).to(DefaultSqlExecutor.class);
		
	}

	private void setDataSource() {
		try{
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver            
			cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/pmc_shine" );
			cpds.setUser("admin");                                  
//			cpds.setPassword("");                                  
			
			// the settings below are optional -- c3p0 can work with defaults
			cpds.setMinPoolSize(5);                                     
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(20);
			
			bind(DataSource.class).toInstance(cpds);
		} catch (PropertyVetoException pve){
			log.error("初始化连接池失败，", pve);
			throw new RuntimeException(pve.toString());
		}
	}
}
