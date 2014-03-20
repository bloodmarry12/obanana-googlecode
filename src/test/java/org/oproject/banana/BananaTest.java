/**
 * 
 */
package org.oproject.banana;

import org.junit.Test;
import org.oproject.AbstractBananaTester;

/**
 * @author aohai.li
 *
 */
public class BananaTest extends AbstractBananaTester {

	/**
	 * 
	 */
	@Test
	public void test_01(){
		Banana.run(DIR_BASE + "/tester/compensation/demo/test_01/", null);
	}
}
