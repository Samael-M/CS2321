package cs2321;

public class PostfixExpression {
	
	/**
	 * Evaluate a postfix expression. 
	 * Postfix expression notation has operands first, following by the operations.
	 * For example:
	 *    13 5 *           is same as 13 * 5 
	 *    4 20 5 + * 6 -   is same as 4 * (20 + 5) - 6  
	 *    
	 * In this homework, expression in the argument only contains
	 *     integer, +, -, *, / and a space between every number and operation. 
	 * You may assume the result will be integer as well. 
	 * 
	 * @param exp The postfix expression
	 * @return the result of the expression
	 */

	public static int evaluate(String exp) {
		DLLStack stack = new DLLStack();
		String[] string = exp.split("\\s+");
		for(int i = 0; i <= string.length -1; i++) {
			if (isInt(string[i])) {
				stack.push(Integer.parseInt(string[i]));
			}
			else if (string[i].charAt(0) == '+') {
				stack.push((int) stack.pop() + (int) stack.pop());
			}
			else if (string[i].charAt(0) == '-') {
				int temp = (int) stack.pop();
				stack.push((int) stack.pop() - temp);
			}
			else if (string[i].charAt(0) == '/') {
				int temp = (int) stack.pop();
				stack.push((int) stack.pop() / temp);
			}
			else if (string[i].charAt(0) == '*') {
				stack.push((int) stack.pop() * (int) stack.pop());
			}
			else throw new IllegalArgumentException("Invalid postfix expression");
		}
		return (int) stack.pop();
	}

	public static boolean isInt(String s) {
		try {
			int integer = Integer.parseInt(s);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}
}
