/**
 * 
 */
package org.oproject.banana;

import org.apache.log4j.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * @author aohai.li
 * 
 */
public class DefaultBananaModule extends AbstractModule {

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(DefaultBananaModule.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		if(log.isInfoEnabled()){
			log.info("环境配置------开始");
		}
		bind(Integer.class) .annotatedWith(Names.named("server_port")).toInstance(12);
//		bind(ScriptProvider.class).to(DefaultFileScriptProviderImpl.class);
		
		
		
		if(log.isInfoEnabled()){
			log.info("服务配置------完成");
		}
	}

}
