package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InfixToPostfixTest {


	InfixToPostfix infPos;
	@Before
	public void setUp() throws Exception {
		infPos = new InfixToPostfix();
	}

	@Test
	public void testConvert1() {

		org.junit.Assert.assertEquals((Object) "5 3 +", (Object) infPos.convert("5 + 3"));
	}
	
	@Test
	public void testConvert2() {
		org.junit.Assert.assertEquals((Object) "3 -5 +", (Object) infPos.convert("3 + -5"));
	}

	@Test
	public void testConvert3() {
		org.junit.Assert.assertEquals((Object) "5 3 -", (Object) infPos.convert("5 - 3"));
	}
	
	@Test
	public void testConvert4() {
		org.junit.Assert.assertEquals((Object) "5 7 9 + *", (Object) infPos.convert("5 * ( 7 + 9 )"));
	}
	
	@Test
	public void testConvert5() {
		org.junit.Assert.assertEquals((Object) "5 -7 * 3 9 9 * + *", (Object) infPos.convert("5 * -7 * ( 3 + 9 * 9 )"));
	}
	
	@Test
	public void testConvert6() {
		org.junit.Assert.assertEquals((Object) "4 4 + 4 4 + +", (Object) infPos.convert("( 4 + 4 ) + ( 4 + 4 )"));
	}
	
	@Test
	public void testConvert7() {
		org.junit.Assert.assertEquals((Object) "9 3 1 - -3 * + 10 2 / +", (Object) infPos.convert("9 + ( 3 - 1 ) * -3 + 10 / 2"));
	}
	
	@Test
	public void testConvert8() {
		org.junit.Assert.assertEquals((Object) "1 2 3 4 6 7 8 / + * 9 * + * -", (Object) infPos.convert("1 - 2 * ( 3 + 4 * ( 6 + 7 / 8 ) * 9 )"));
	}
}
