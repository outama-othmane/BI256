package me.outama.BI256.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Random;

import org.junit.Test;

import me.outama.BI256.main.BI256;
import me.outama.BI256.main.exceptions.OverflowException;

public class BI256Test 
{
	
	private Integer random() 
	{
		Random rand = new Random(); 
		int x = rand.nextInt(Integer.MAX_VALUE / 100);
		return x > 0 ? x : -x;
	}
	
	@Test
	public void test_it_can_create_a_signed_number_using_a_string() throws Exception
	{
		String m = random().toString();
		
		BI256 number = new BI256("-" + m);
		
		assertEquals(number.toString(), "-" + m);
	}
	
	@Test
	public void test_it_can_convert_a_negative_bi256_to_a_string() throws Exception
	{
		BI256 number = new BI256(true, "4345332480");
		
		assertEquals(number.toString(), "-4345332480");
	}
	
	@Test
	public void test_it_can_convert_a_bi256_to_a_string() throws Exception
	{
		BI256 number = new BI256("303485998230283498");
		
		assertEquals(number.toString(), "303485998230283498");
	}
	
	@Test
	public void test_it_can_add_two_positive_big256_numbers() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(false, n2.toString());
		
		Long sum = (long) (n1+n2);
		
		assertEquals(sum.toString(), number1.add(number2).toString());
	}
	
	@Test
	public void test_it_can_add_one_negative_big256_number_and_a_positive_big256() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		
		BI256 number1 = new BI256(true, n1.toString());
		BI256 number2 = new BI256(false, n2.toString());
		
		Long sum = (long) (-n1+n2);
		
		assertEquals(sum.toString(), number1.add(number2).toString());
	}
	
	@Test
	public void test_it_can_add_one_positive_big256_number_and_a_negative_big256() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(true, n2.toString());
		
		Long sum = (long) (n1-n2);
		
		assertEquals(sum.toString(), number1.add(number2).toString());
	}
	
	@Test
	public void test_it_can_add_two_negative_numbers() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		
		BI256 number1 = new BI256(true, n1.toString());
		BI256 number2 = new BI256(true, n2.toString());
		
		Long sum = (long) -(n1+n2);
		
		assertEquals(sum.toString(), number1.add(number2).toString());
	}
	
	@Test
	public void test_it_can_subtract_two_positive_numbers() throws Exception
	{
		BI256 number1 = new BI256(false, "1908");
		BI256 number2 = new BI256(false, "2495");
		
		Integer sum = 1908-2495;
		
		assertEquals(sum.toString(), number1.subtract(number2).toString());
	}
	
	@Test
	public void test_it_can_subtract_one_positive_and_one_negative_number() throws Exception
	{
		BI256 number1 = new BI256(false, "1908");
		BI256 number2 = new BI256(true, "2495");
		
		Integer sum = 1908- (-2495);
		
		assertEquals(sum.toString(), number1.subtract(number2).toString());
	}
	
	@Test
	public void test_it_can_subtract_one_negative_number_and_one_positive_number() throws Exception
	{
		BI256 number1 = new BI256(true, "1908");
		BI256 number2 = new BI256(false, "2495");
		
		Integer sum = -1908 - 2495;
		
		assertEquals(sum.toString(), number1.subtract(number2).toString());
	}
	
	@Test
	public void test_it_can_subtract_two_negative_numbers() throws Exception
	{
		BI256 number1 = new BI256(true, "1908");
		BI256 number2 = new BI256(true, "2495");
		
		Integer sum = -1908 - (-2495);
		
		assertEquals(sum.toString(), number1.subtract(number2).toString());
	}
	
	@Test
	public void test_it_multiply_two_positive_numbers() throws Exception
	{
		Integer n1 = 45345;
		Integer n2 = 13938;
		
		BI256 number1 = new BI256(true, n1.toString());
		BI256 number2 = new BI256(true, n2.toString());
		
		Long multi = (long) (n1*n2);
		
		
		assertEquals(multi.toString(), number1.multiply(number2).toString());
	}
	
	@Test
	public void test_it_can_create_a_BI256_using_a_long_number() throws Exception
	{
		Long x = (long) -1*random();
		assertEquals(new BI256(x).toString(), x.toString());
	}
	
	@Test
	public void test_it_can_create_a_BI256_using_a_integer() throws Exception
	{
		Integer x = -1*random();
		assertEquals(new BI256(x).toString(), x.toString());
	}
	
	@Test
	public void test_if_a_number_is_smaller_than_another() throws Exception
	{
		BI256 num1 = new BI256(5);
		BI256 num2 = new BI256(10);
		
		assertTrue(num1.isSmallerThan(num2) == true);
	}
	
	@Test
	public void test_if_a_number_is_smaller_than_another_signed_number() throws Exception
	{
		BI256 num1 = new BI256(5);
		BI256 num2 = new BI256(-10);
		
		assertTrue(num1.isSmallerThan(num2) == false);
	}
	
	@Test
	public void test_if_a_signed_number_is_smaller_than_another_signed_number() throws Exception
	{
		BI256 num1 = new BI256(-9);
		BI256 num2 = new BI256(-3);
		
		assertTrue(num1.isSmallerThan(num2) == true);
	}
	
	@Test
	public void test_if_a_number_is_not_smaller_than_another_number() throws Exception
	{
		BI256 num1 = new BI256(9);
		BI256 num2 = new BI256(9);
		
		assertTrue(num1.isSmallerThan(num2) == false);
	}
	
	@Test
	public void test_if_a_signed_number_is_not_smaller_than_another_signed_number() throws Exception
	{
		BI256 num1 = new BI256(-9);
		BI256 num2 = new BI256(-9);
		
		assertTrue(num1.isSmallerThan(num2) == false);
	}
	
	@Test
	public void test_if_a_number_is_greater_than_another() throws Exception
	{
		BI256 num1 = new BI256(5);
		BI256 num2 = new BI256(10);
		
		assertTrue(num1.isGreaterThan(num2) == false);
	}
	
	@Test
	public void test_if_a_number_is_greater_than_another_signed_number() throws Exception
	{
		BI256 num1 = new BI256(5);
		BI256 num2 = new BI256(-10);
		
		assertTrue(num1.isGreaterThan(num2) == true);
	}
	
	@Test
	public void test_if_a_signed_number_is_greater_than_another_signed_number() throws Exception
	{
		BI256 num1 = new BI256(-9);
		BI256 num2 = new BI256(-3);
		
		assertTrue(num1.isGreaterThan(num2) == false);
	}
	
	@Test
	public void test_if_a_signed_number_is_not_greater_than_another_signed_number() throws Exception
	{
		BI256 num1 = new BI256(-9);
		BI256 num2 = new BI256(-9);
		
		assertTrue(num1.isGreaterThan(num2) == false);
	}
	
	@Test
	public void test_if_a_number_is_not_greater_than_another_number() throws Exception
	{
		BI256 num1 = new BI256(9);
		BI256 num2 = new BI256(9);
		
		assertTrue(num1.isGreaterThan(num2) == false);
	}
	
	@Test
	public void test_if_a_signed_number_is_equal_to_unsigned_number() throws Exception
	{
		BI256 num1 = new BI256(-9);
		BI256 num2 = new BI256(9);
		
		assertTrue(num1.isEqualTo(num2) == false);
	}
	
	@Test
	public void test_if_a_number_is_equal_to_another() throws Exception
	{
		BI256 num1 = new BI256(9);
		BI256 num2 = new BI256(9);
		
		assertTrue(num1.isEqualTo(num2) == true);
	}
	
	@Test
	public void test_it_a_number_is_smaller_than_zero() throws Exception
	{
		BI256 num1 = new BI256(0);
		BI256 num2 = new BI256(9);
		
		assertTrue(num1.isSmallerThan(num2) == true);
	}
	
	@Test
	public void test_it_a_number_is_greater_than_zero() throws Exception
	{
		BI256 num1 = new BI256(9);
		BI256 num2 = new BI256(0);
		
		assertTrue(num1.isGreaterThan(num2) == true);
	}
	
	@Test
	public void test_it_returns_zero_if_subtract_of_equal_numbers() throws Exception
	{
		BI256 num1 = new BI256(9);
		BI256 num2 = new BI256(9);
		
		assertEquals(num1.subtract(num2).toString(), "0");
	}
	
	@Test
	public void test_it_returns_zero_if_addition_of_two_opposite_numbers() throws Exception
	{
		BI256 num1 = new BI256("9");
		BI256 num2 = new BI256(true, "9");
		
		assertEquals(num1.add(num2).toString(), "0");
	}
	
	@Test
	public void test_it_can_divide_two_positive_numbers() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(false, n2.toString());
		
		Long division = (long) (n1/n2);
		
		assertEquals(division.toString(), number1.divide(number2).toString());
	}
	
	@Test
	public void test_it_can_divide_two_negative_numbers() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		
		BI256 number1 = new BI256(true, n1.toString());
		BI256 number2 = new BI256(true, n2.toString());
		
		Long division = (long) (n1/n2);
		
		assertEquals(division.toString(), number1.divide(number2).toString());
	}
	
	@Test
	public void test_it_can_divide_one_negative_number_and_one_positive_number() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		
		BI256 number1 = new BI256(true, n1.toString());
		BI256 number2 = new BI256(false, n2.toString());
		
		Long division = (long) (n1/n2);
	
		String m = "";
		if (division != 0) {
			m = "-";
		}
		
		m += division.toString();
		
		assertEquals(m, number1.divide(number2).toString());
	}
	
	@Test
	public void test_it_can_divide_one_positive_number_and_one_negative_number() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(true, n2.toString());
		
		Long division = (long) (n1/n2);
		
		String m = "";
		if (division != 0) {
			m = "-";
		}
		
		m += division.toString();
		
		assertEquals(m, number1.divide(number2).toString());
	}
	
	@Test
	public void test_it_can_divide_one_number_by_one() throws Exception
	{
		Integer n1 = random();
		Integer n2 = 1;
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(false, n2.toString());
		
		assertEquals(n1.toString(), number1.divide(number2).toString());
	}
	
	@Test
	public void test_it_can_divide_one_negative_number_by_one() throws Exception
	{
		Integer n1 = random();
		Integer n2 = 1;
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(true, n2.toString());
		
		assertEquals("-" + n1.toString(), number1.divide(number2).toString());
	}
	
	@Test
	public void test_it_returns_zero_if_the_numerator_equals_to_zero() throws Exception
	{
		Integer n1 = 0;
		Integer n2 = random();
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(true, n2.toString());
		
		assertEquals("0", number1.divide(number2).toString());
	}
	
	
	@Test
	public void test_it_fails_if_the_dividor_equals_to_zero() throws Exception
	{
		Integer n1 = random();
		Integer n2 = 0;
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(true, n2.toString());
		
		assertThrows(Exception.class, () -> {
			number1.divide(number2);
		});
	}
	
	@Test
	public void test_it_returns_modulo_two_numbers() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(false, n2.toString());
		
		Long modulo = (long) (n1%n2);
		
		assertEquals(modulo.toString(), number1.mod(number2).toString());
	}
	
	@Test
	public void test_it_returns_modulo_two_mixed_numbers() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		
		BI256 number1 = new BI256(true, n1.toString());
		BI256 number2 = new BI256(false, n2.toString());
		
		Long modulo = (long) (-n1%n2);
		
		assertEquals(modulo.toString(), number1.mod(number2).toString());
	}
	
	@Test
	public void test_it_returns_modulo_two_mixed_numbers2() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(true, n2.toString());
		
		Long modulo = (long) (n1%-n2);
		
		assertEquals(modulo.toString(), number1.mod(number2).toString());
	}
	
	@Test
	public void test_it_can_addMod() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		Integer n3 = random();
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(false, n2.toString());
		BI256 number3 = new BI256(false, n3.toString());
		
		Long modulo = (long) (n1+n2);
		modulo = modulo % n3;
		
		assertEquals(modulo.toString(), number1.addMod(number2, number3).toString());
	}
	
	@Test
	public void test_it_can_substractMod() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		Integer n3 = random();
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(false, n2.toString());
		BI256 number3 = new BI256(false, n3.toString());
		
		Long modulo = (long) (n1-n2)%n3;
		
		assertEquals(modulo.toString(), number1.subtractMod(number2, number3).toString());
	}
	
	@Test
	public void test_it_can_multiplyMod() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		Integer n3 = random();
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(false, n2.toString());
		BI256 number3 = new BI256(false, n3.toString());
		
		Long modulo = (long) (n1*n2);
		modulo = modulo % n3;
		
		assertEquals(modulo.toString(), number1.multiplyMod(number2, number3).toString());
	}
	
	@Test
	public void test_it_can_divideMod() throws Exception
	{
		Integer n1 = random();
		Integer n2 = random();
		Integer n3 = random();
		
		BI256 number1 = new BI256(false, n1.toString());
		BI256 number2 = new BI256(false, n2.toString());
		BI256 number3 = new BI256(false, n3.toString());
		
		Long modulo = (long) ((long)(n1/n2))%n3;
		
		assertEquals(modulo.toString(), number1.divideMod(number2, number3).toString());
	}
	
	@Test
	public void test_it_throws_an_error_if_the_number_cant_be_represented_on_256bits()
	{
		assertThrows(OverflowException.class, () -> {
			new BI256(new BigInteger("2").pow(255).toString());
		});
	}
	
	@Test
	public void test_it_throws_an_error_if_the_negative_number_cant_be_represented_on_256bits()
	{
		assertThrows(OverflowException.class, () -> {
			new BI256(new BigInteger("2").pow(255).multiply(new BigInteger("-1")).subtract(new BigInteger("1")).toString());
		});
	}
	
	@Test
	public void test_it_can_represent_the_biggest_number_in_256_bits() throws Exception
	{
		String big = new BigInteger("2").pow(255).subtract(new BigInteger("1")).toString();
		
		assertEquals(big, new BI256(big).toString());
	}
	
	@Test
	public void test_it_can_represent_the_smaller_number_in_256_bits() throws Exception
	{
		String big = new BigInteger("2").pow(255).multiply(new BigInteger("-1")).toString();
		
		assertEquals(big, new BI256(big).toString());
	}

}
