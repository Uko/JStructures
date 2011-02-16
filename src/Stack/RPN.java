/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Parser
 * @author uko
 */
public class RPN
{
	private String stringResult = new String();
	private double result;
	/**
	 * A constructor that takes a string to parse, and sets results to a fields of a class
	 * @param source string to parse
	 * @throws Stack.RPN.WrongFormulaException an exception is thrown
	 */
	RPN(String source) throws WrongFormulaException
	{
		LinkedList<Node> segmentedSource = new LinkedList<Node>();
		LinkedList<Node> segmentedResult = new LinkedList<Node>();
		Stack<Node> parseHelper = new Stack<Node>();
		boolean wasSymbol = false;
		int amountOfBrackets = 0;
		int buffer = 0;
		for (int i = 0; i < source.length(); i++)
		{
			switch (source.charAt(i))
			{
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					wasSymbol = false;
					buffer = buffer * 10 + source.charAt(i) - 48;
					break;
				case '(':
					wasSymbol = false;
					amountOfBrackets++;
					if (buffer != 0)
					{
						segmentedSource.add(new Node(-1, new Integer(buffer)));
						buffer = 0;
					}
					segmentedSource.add(new Node(0, new Character('(')));
					break;
				case ')':
					wasSymbol = false;
					amountOfBrackets--;
					if (amountOfBrackets < 0)
					{
						throw new WrongFormulaException("Missing opening bracket");
					}
					if (buffer != 0)
					{
						segmentedSource.add(new Node(-1, new Integer(buffer)));
						buffer = 0;
					}
					segmentedSource.add(new Node(0, new Character(')')));
					break;
				case '+':
					if (wasSymbol)
					{
						throw new WrongFormulaException("Two operators in a row");
					}
					wasSymbol = true;
					if(i==0)
					{
						break;
					}
					if (buffer != 0)
					{
						segmentedSource.add(new Node(-1, new Integer(buffer)));
						buffer = 0;
					}
					segmentedSource.add(new Node(1, new Character('+')));
					break;
				case '-':
					if (wasSymbol)
					{
						throw new WrongFormulaException("Two operators in a row");
					}
					wasSymbol = true;
					if(i==0)
					{
						segmentedSource.add(new Node(-1, new Integer(0)));
						segmentedSource.add(new Node(1, new Character('-')));
						break;
					}
					if (buffer != 0)
					{
						segmentedSource.add(new Node(-1, new Integer(buffer)));
						buffer = 0;
					}
					segmentedSource.add(new Node(1, new Character('-')));
					break;
				case '*':
					if (wasSymbol)
					{
						throw new WrongFormulaException("Two operators in a row");
					}
					wasSymbol = true;
					if (buffer != 0)
					{
						segmentedSource.add(new Node(-1, new Integer(buffer)));
						buffer = 0;
					}
					segmentedSource.add(new Node(2, new Character('*')));
					break;
				case '/':
					if (wasSymbol)
					{
						throw new WrongFormulaException("Two operators in a row");
					}
					wasSymbol = true;
					if (buffer != 0)
					{
						segmentedSource.add(new Node(-1, new Integer(buffer)));
						buffer = 0;
					}
					segmentedSource.add(new Node(2, new Character('/')));
					break;
				case '^':
					if (wasSymbol)
					{
						throw new WrongFormulaException("Two operators in a row");
					}
					wasSymbol = true;
					if (buffer != 0)
					{
						segmentedSource.add(new Node(-1, new Integer(buffer)));
						buffer = 0;
					}
					segmentedSource.add(new Node(3, new Character('^')));
					break;
				default:
					throw new WrongFormulaException("Unsupported character");
			}
		}
		if (amountOfBrackets != 0 || wasSymbol || source.charAt(0)=='*' || source.charAt(0)=='/' || source.charAt(0)=='^')
		{
			throw new WrongFormulaException("Some sh*t is going on with that formula");
		}
		if (buffer != 0)
			segmentedSource.add(new Node(-1, new Integer(buffer)));
		for (Node in : segmentedSource)
		{
			switch (in.getWeight())
			{
				case -1:
					segmentedResult.add(in);
					stringResult += ((Integer) in.getData()).toString() + " ";
					break;
				case 0:

					if ((Character) in.getData() == '(')
					{
						parseHelper.add(in);
					}
					else
					{
						Node temp = parseHelper.pop();
						while ((Character) temp.getData() != '(')
						{
							segmentedResult.add(temp);
							stringResult += ((Character) temp.getData()).toString() + " ";
							temp = parseHelper.pop();
						}
					}
					break;
				case 1:
				case 2:
				case 3:
					for (;;)
					{
						if (parseHelper.isEmpty() || in.getWeight() > parseHelper.peek().getWeight())
						{
							parseHelper.add(in);
							break;
						}
						Node temp = parseHelper.pop();
						segmentedResult.add(temp);
						stringResult += ((Character) temp.getData()).toString() + " ";
					}
			}

		}
		while (!parseHelper.empty())
		{
			Node temp = parseHelper.pop();
			segmentedResult.add(temp);
			stringResult += ((Character) temp.getData()).toString()+" ";
		}
		stringResult=stringResult.trim();
		Stack<Double> resultHelper = new Stack<Double>();
		for(Node in:segmentedResult)
		{
			if(in.weight==-1)
				resultHelper.add(((Integer)in.getData()).doubleValue());
			else
				switch((Character)in.getData())
				{
					case '+':
						resultHelper.add(resultHelper.pop()+resultHelper.pop());
						break;
					case '-':
						resultHelper.add(-resultHelper.pop()+resultHelper.pop());
						break;
					case '*':
						resultHelper.add(resultHelper.pop()*resultHelper.pop());
						break;
					case '/':
						Double temp = resultHelper.pop();
						resultHelper.add(resultHelper.pop()/temp);
						break;
					case '^':
						temp = resultHelper.pop();
						resultHelper.add(Math.pow(resultHelper.pop(), temp));
				}
		}
		result=resultHelper.pop();
	}
	/**
	 * @return the stringResult
	 */
	public String getStringResult()
	{
		return stringResult;
	}
	/**
	 * @return the result
	 */ public double getResult()
	{
		return result;
	}

	private static class Node
	{
		private int weight;
		private Object data;
		public Node(int weight, Object data)
		{
			this.weight = weight;
			this.data = data;
		}
		/**
		 * @return the weight
		 */
		public int getWeight()
		{
			return weight;
		}
		/**
		 * @return the data
		 */
		public Object getData()
		{
			return data;
		}
	}

	/**
	 * An exception, that is thrown in case the bad formula is passed for parsing.
	 */
	public static class WrongFormulaException extends Exception
	{
		private String problem;
		/**
		 * Default constructor.
		 */
		public WrongFormulaException()
		{

		}
		/**
		 * Constructor, that allows to set a problem of the exception.
		 * @param problem a string with the explanation of the problem
		 */
		public WrongFormulaException(String problem)
		{
			this.problem = problem;
		}
		/**
		 * @return the problem
		 */
		public String getProblem()
		{
			return problem;
		}
	}
}
