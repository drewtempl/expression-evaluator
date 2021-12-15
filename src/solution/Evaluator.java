package solution;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class Evaluator
{
    public static final Pattern UNSIGNED_DOUBLE =
        Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?");
    public static final Pattern CHARACTER = Pattern.compile("\\S.*?");
    
    /**
     * Takes an infix expression and converts it to postfix.
     * 
     * @param expression
     *            The infix expression.
     * @return the postfix expression.
     */
    public String toPostfix(String expression)
    {
        Stack<Character> stack = new Stack<>();
        Scanner input = new Scanner(expression);
        String next;
        char symbol;
        boolean prevParentheses = false;
        String postfixExpression = "";
    
        while (input.hasNext())
        {
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                next = input.findInLine(UNSIGNED_DOUBLE);
                
                postfixExpression += next + " ";
                
                prevParentheses = false;
            }
            else
            {
                next = input.findInLine(CHARACTER);
                symbol = next.charAt(0);
                   
                if(symbol == '(' && prevParentheses)
                    throw new IllegalArgumentException("Invalid expression: " + symbol);
                
                else if (symbol == '(')
                {
                    stack.push(symbol);
                    prevParentheses = false;
                }
                    
                
                else if (symbol == '*' || symbol == '/' || 
                         symbol == '+' || symbol == '-')
                {
                    while(!stack.empty() && stack.peek() != '(' && 
                        precedence(stack.peek()) >= precedence(symbol))
                    {
                        postfixExpression += stack.pop() + " ";
                    }
                    
                    prevParentheses = false;
                    
                    stack.push(symbol);
                }
                
                else if(symbol == ')')
                {
                    while(!stack.empty() && stack.peek() != '(')
                    {
                        postfixExpression += stack.pop() + " ";
                    }
                    prevParentheses = true;
                    
                    stack.pop();
                }
                
                else
                    throw new IllegalArgumentException("Invalid expression: " + symbol);
            }
            
            
        }
        
        while(!stack.empty())
        {
            postfixExpression += stack.pop() + " ";
        }
        
        
        return postfixExpression;
    }
    
    /**
     * Evaluates a postfix expression and returns the result.
     * 
     * @param postfixExpression
     *            The postfix expression.
     * @return The result of the expression.
     */
    public double evaluate(String postfixExpression)
    {
        Stack<Double> stack = new Stack<>();
        Scanner input = new Scanner(postfixExpression);
        String next;
        char operator;
        Double op1, op2;
        double answer = Double.NaN;
    
        while (input.hasNext())
        {
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                next = input.findInLine(UNSIGNED_DOUBLE);
                
                op1 = Double.parseDouble(next);
                stack.push(op1);
                
            }
            else
            {
                next = input.findInLine(CHARACTER);
                operator = next.charAt(0);
                
                switch (operator)
                {
                    case '+':
                        op2 = stack.pop();
                        op1 = stack.pop();
                        stack.push(op1 + op2);
                    break;
                    
                    case '-':
                        op2 = stack.pop();
                        op1 = stack.pop();
                        stack.push(op1 - op2);
                    break;
                    
                    case '*':
                        op2 = stack.pop();
                        op1 = stack.pop();
                        stack.push(op1 * op2);
                    break;
                    
                    case '/':
                        op2 = stack.pop();
                        op1 = stack.pop();
                        stack.push(op1 / op2);
                    break;
                
                }
            }
        }
        return stack.pop();
    
    }
    
    /**
     * Takes a Character and returns its level of precedence .
     * 
     * @param Character to be evaluated
     *            
     * @return level of precedence
     */
    public int precedence(Character input)
    {
        if(input == '+' || input == '-')
            return 0;
        
        else 
            return 1;
    }
    

    public double calculate(String expression)
    {   
        return evaluate(toPostfix(expression));
    }
}

