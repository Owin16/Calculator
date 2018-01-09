import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator implements Calculatorable {

	private ArrayList<Double> list = new ArrayList<>();
	private static final String REGEX1 = "[*/+-]";
	private static final String REGEX2 = "[*/]";
	private static final String REGEX3 = "[+-]";
	private static Pattern pat1;
	private static Pattern pat2;
	private String expression;

	public Calculator() {
		pat1 = Pattern.compile(REGEX2);
		pat2 = Pattern.compile(REGEX3);
	}

	public double plus(double a, double b) {
		return a + b;
	}

	public double minus(double a, double b) {
		return a - b;
	}

	public double devision(double a, double b) {
		return a / b;
	}

	public double multiplication(double a, double b) {
		return a * b;
	}

	public boolean check(String expression) {
		this.expression = expression;
		String[] mass = expression.split(REGEX1);
		for (String str : mass) {
			try {
				Double.parseDouble(expression.substring(0,1));
			 	Double.parseDouble(expression.substring(expression.length()-1, expression.length()));
				list.add(Double.parseDouble(str));
			} catch (NumberFormatException e) {
				System.out.println("please verivy your expression");
				return false;
			} catch (IndexOutOfBoundsException e){
				System.out.println("please verivy your expression");
				return false;
			}
		}
		return true;
	}

	public double getResult() {
		double resultOperation;
		char operation;
		Matcher mat1 = pat1.matcher(expression);
		while (mat1.find()) {
			operation = expression.charAt(mat1.start());
			int count = 0;
			Matcher mat2 = pat2.matcher(expression.substring(0, mat1.start()));
			while (mat2.find()) {
				count++;
			}
			switch (operation) {
			case '*':
				resultOperation = multiplication(list.get(count), list.get(count + 1));
				list.set(count, resultOperation);
				list.remove(count + 1);
				break;
			case '/':
				resultOperation = devision(list.get(count), list.get(count + 1));
				list.set(count, resultOperation);
				list.remove(count + 1);
				break;
			}
		}
		Matcher mat2 = pat2.matcher(expression);
		while (mat2.find()) {
			operation = expression.charAt(mat2.start());
			switch (operation) {
			case '+':
				resultOperation = plus(list.get(0), list.get(1));
				list.set(0, resultOperation);
				list.remove(1);
				break;
			case '-':
				resultOperation = minus(list.get(0), list.get(1));
				list.set(0, resultOperation);
				list.remove(1);
				break;
			}
		}
		return list.get(0);
	}
}
