package cs2321;

public class InfixToPostfix {
	/**
	 * Convert an infix expression and to a postfix expression
	 * infix expression : operator is between operands. Ex. 3 + 5
	 * postfix Expression: operator is after the operands. Ex. 3 5 +
	 * <p>
	 * The infixExp expression includes the following
	 * operands:  integer or decimal numbers
	 * and operators: + , - , * , /
	 * and parenthesis: ( , )
	 * <p>
	 * For easy parsing the expression, there is a space between operands and operators, parenthesis.
	 * Ex: "1 * ( 3 + 5 )"
	 * Notice there is no space before the first operand and after the last operand/parentheses.
	 * <p>
	 * The postExp includes the following
	 * operands:  integer or decimal numbers
	 * and operators: + , - , * , /
	 * <p>
	 * For easy parsing the expression, there should have a space between operands and operators.
	 * Ex: "1 3 5 + *"
	 * Notice there is space before the first operand and last operator.
	 * Notice that postExp does not have parenthesis.
	 */
	public static String convert(String infixExp) {

		DLLStack stack = new DLLStack();
		String[] string = infixExp.split("\\s+");
		String postfixExp = "";
		for (int i = 0; i < string.length; i++) {
			if (isInt(string[i])) {
				postfixExp = postfixExp + string[i] + " ";
			}
			if (string[i].equals("(")) stack.push(string[i]);
			if(string[i].equals("+") || string[i].equals("-") || string[i].equals("/") || string[i].equals("*")) {
				if(!stack.isEmpty()) {
					if (stack.top().equals("(")) stack.push(string[i]);
					else {
						//Test precedence to see if previous operater in stack needs to be done first
						while(!stack.isEmpty() && !stack.top().equals("(") && Precedence(string[i]) <= Precedence((String) stack.top())) {
							postfixExp = postfixExp + stack.pop() + " ";
						}
						stack.push(string[i]);
					}
				}
				else stack.push(string[i]);
			}
			// Pops off everything in side paranthesis when it reches "("
			if(string[i].equals(")")) {
				while(!stack.isEmpty() && !stack.top().equals("(")) {
					postfixExp = postfixExp + stack.pop() + " ";
				}
				//pops the "(" so it doesnt end up in output
				stack.pop();
			}
		}
		//Pops off the rest of the operators until stack is empty
		while(stack.size() > 1) postfixExp = postfixExp + stack.pop() + " ";
		postfixExp = postfixExp + stack.pop();
		return postfixExp;
	}

	//Test the precedence of an operater
	public static int Precedence(String s) {
		switch(s) {
			case "+":
			case "-":
				return 0;
			case "*":
			case "/":
				return 1;
			default: throw new IllegalArgumentException(s + " is not valid");
		}
	}

	//Test to see if String s is an integer by attemoing to Parse it
	public static boolean isInt(String s) {
		try {
			int integer = Integer.parseInt(s);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}
}
