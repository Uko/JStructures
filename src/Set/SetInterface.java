/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Set;

/**
 *
 * @author uko
 */
public interface SetInterface<type>
{
	public void add(type element);
	public boolean contains(type element);

	public SetInterface<type> union(SetInterface<type> set);
	public SetInterface<type> intersection(SetInterface<type> set);
	//public SetInterface<type> intersection(SetInterface<type> set);
//	public SetInterface<type> intersection(SetInterface<type> set);
}
