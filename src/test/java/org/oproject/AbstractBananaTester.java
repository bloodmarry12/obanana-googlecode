/**
 * 
 */
package org.oproject;

import org.junit.BeforeClass;
import org.oproject.banana.test.config.BananaModelForOracle;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author aohai.li
 *
 */
public class AbstractBananaTester {

	protected static Injector context ;
	
	protected static String DIR_BASE = System.getProperty("user.dir");
	
	@BeforeClass
	public  static void beforeClass(){
		context = Guice.createInjector(new BananaModelForOracle());
	}
}
