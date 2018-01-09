import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Calculation {

	public static void main(String[] args){
		boolean b = true;
		do{
		System.out.print("Please, type expression and press Enter:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String expression = "";
		try {
			expression = br.readLine().replaceAll(" ", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Calculator calc = new Calculator();
		boolean check = calc.check(expression);
		if(check)
		System.out.println("Result: " + calc.getResult());
		
		System.out.print("For exit type '0'");
		try {
			if (br.read() == 48)
				b = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		}while(b);
	}
}
