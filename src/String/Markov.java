/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package String;

import java.util.ArrayList;

/**
 *
 * @author uko
 */
public class Markov
{
	ArrayList<java.lang.String> pLeft;
	ArrayList<java.lang.String> pRight;
	java.lang.String workString;
	java.lang.String resultString;

	int endProduction = -1;

	ArrayList<String> mPLeft;
	ArrayList<String> mPRight;
	String workMarkovString;
	String resultMarkovString;

	public Markov()
	{
		pLeft = new ArrayList<java.lang.String>();
		pRight = new ArrayList<java.lang.String>();
		workString = new java.lang.String();

		mPLeft = new ArrayList<String>();
		mPRight = new ArrayList<String>();
		workMarkovString = new String();
	}

	public Markov(ArrayList<java.lang.String> pLeft, ArrayList<java.lang.String> pRight, int endProduction, java.lang.String string)
	{
		this.pLeft = new ArrayList<java.lang.String>();
		this.pLeft.addAll(pLeft);

		this.pRight = new ArrayList<java.lang.String>();
		this.pRight.addAll(pRight);

		this.workString = new java.lang.String();
		this.workString = this.workString.concat(string);

		this.endProduction = endProduction;

		mPLeft = new ArrayList<String>();
		mPRight = new ArrayList<String>();
		for(int i = 0, n = pLeft.size(); i < n; i++)
		{
			mPLeft.add(new String(pLeft.get(i)));
			mPRight.add(new String(pRight.get(i)));
		}
		workMarkovString = new String(string);
	}

	boolean addProduction(java.lang.String left, java.lang.String right)
	{
		pLeft.add(left);
		pRight.add(right);

		mPLeft.add(new String(left));
		mPRight.add(new String(right));
		return true;
	}

	boolean addProduction(int index, java.lang.String left, java.lang.String right)
	{
		pLeft.add(index, left);
		pRight.add(index, right);

		mPLeft.add(index, new String(left));
		mPRight.add(index, new String(right));
		return true;
	}

	boolean removeProduction(int index)
	{
		pLeft.remove(index);
		pRight.remove(index);

		mPLeft.remove(index);
		mPRight.remove(index);
		return true;
	}

	void setworkString(java.lang.String string)
	{
		workString = new java.lang.String();
		workString = workString.concat(string);

		workMarkovString = new String(string);
	}

	void setEndProduction(int index)
	{
		if((index >= 0) && (index < pLeft.size()))
			endProduction = index;
		else
			throw new RuntimeException();
	}

	java.lang.String ProcessString()
	{
		resultString = new java.lang.String();
		resultString = resultString.concat(workString);

		int iter = 0;
		for(int i = 0, n = pLeft.size(); (i < n) && (iter < 20000); i++)
		{
			iter++;
			if(resultString.indexOf(pLeft.get(i)) >= 0)
			{
				resultString = resultString.replaceFirst(pLeft.get(i)/*	.replace("|", "\\|")
																		.replace("]", "\\]")
																		.replace("*", "\\*")
																		.replace("^", "\\^")*/, pRight.get(i));
				if(i == endProduction)
					break;
				i=-1;
			}
		}

		return resultString;
	}

	String ProcessMarkovString() throws IllegalAccessException
	{
		resultMarkovString = new String();
		resultMarkovString = resultMarkovString.concat(workMarkovString);

		int iter = 0;
		for(int i = 0, n = mPLeft.size(); (i < n) && (iter < 20000); i++)
		{
			iter++;
			if(resultMarkovString.contains(mPLeft.get(i)))
			{
				resultMarkovString.replaceFirst(mPLeft.get(i), mPRight.get(i).clone());
				if(i == endProduction)
					break;
				i=-1;
			}
		}

		return resultMarkovString;
	}
}
