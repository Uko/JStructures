/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package String;

/**
 *
 * @author uko
 */
public interface StringInterface
{
	public StringInterface concat(String param);
	public boolean equals(String param);
	public int size();
	public boolean isEmpty();
	public void replaceFirst(String what, String insteadOf) throws IllegalAccessException;
	public void insert(int after, String param) throws IllegalAccessException;
	public int indexOf(String param) throws IllegalAccessException;
	public int indexOf(char param)throws IllegalAccessException;
	public boolean contains(String param);
	public StringInterface clone();
	@Override
	public java.lang.String toString();
}