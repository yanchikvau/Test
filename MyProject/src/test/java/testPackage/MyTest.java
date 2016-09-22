package testPackage;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class MyTest {
	private String operand1;
	private String operand2;
	private String operation;
	private String result;
	@Test
	public void someTest(){
		try {
	        Integer op1 = Integer.valueOf(operand1);
	        Integer op2 = Integer.valueOf(operand2);
	        Float res = Float.valueOf(result);
	        float expRes=0f;
	        switch(operation){
	        case "+":
	        	expRes= op1 + op2;
	        	break;
	        case "-":
	        	expRes= op1 - op2;
	        	break;
	        case "*":
	        	expRes= op1 * op2;
	        	break;
	        case "/":
	        	try { 
	        		expRes= op1 / op2;
	        	} catch (ArithmeticException e) {
	        		fail("нельзя делить на ноль"); 
	        	}
	        	break;
	        default:
	        	fail("неверное арифметическое действие");
	        	break;
	        }
	        assertTrue("Ожидаемый результат(" + expRes + ") не соответствует фактическому("+ res+")", res.equals(expRes));
	    } catch (NumberFormatException e) {
	       fail("Неверный формат строки!");
	    }
		
	}
	
	public MyTest(String operand1, String operand2, String operation,String result){
		this.operand1 =operand1;
		this.operand2 = operand2;
		this.operation = operation;
		this.result = result;
	}
	@Parameters
	public static Collection <Object[]> testData(){
		String fileName = "data.txt";
		File file = new File(fileName);
		ArrayList<String[]> spisok = new ArrayList<String[]>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					spisok.add(s.split(";"));
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		Object[][]data = new Object [spisok.size()][4];
		data = spisok.toArray(new Object[spisok.size()][4]);
		return Arrays.asList(data);
	}
}
