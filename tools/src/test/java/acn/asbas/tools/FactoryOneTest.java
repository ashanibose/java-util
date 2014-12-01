/**
 * 
 */
package acn.asbas.tools;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import acn.asbas.tools.dp.factory.impl.FactoryOne;
import acn.asbas.tools.dp.shared.impl.ProductOne;

/**
 * @author ASHANI BASU
 * 
 */
public class FactoryOneTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(((new FactoryOne()).getInstance()) instanceof ProductOne);
	}

}
