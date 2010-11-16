/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package String;

/**
 *
 * @author uko
 */
public class Character
{
	public char value;
	public Character next;
	public Character(char value)
	{
		this.value = value;
		this.next = null;
	}
	public Character(char value, Character next)
	{
		this.value = value;
		this.next = next;
	}
}