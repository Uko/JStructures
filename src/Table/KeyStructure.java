/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Table;

/**
 * A key structure containing a key and a pointer to the related body.
 * @param <keyType> type of key.
 * @param <bodyType> type of body.
 * @author uko
 */
public class KeyStructure<keyType,bodyType>
{
	/**
	 * A value of key.
	 */
	public keyType value;
	/**
	 * Pointer to body
	 */
	public bodyType body;

	/**
	 * Default constructor which sets null values of both fields
	 */
	public KeyStructure()
	{
		this(null,null);
	}

	/**
	 * Constructor that sets fields of from the passed parameters.
	 * @param value a value of key
	 * @param body a body that is linked to the key.
	 */
	public KeyStructure(keyType value, bodyType body)
	{
		this.value = value;
		this.body = body;
	}

	/**
	 * Compares values of 2 keys
	 * @param temp 2nd key data structure
	 * @return true if key values of equal
	 */
	public boolean equals(KeyStructure<keyType,bodyType> temp)
	{
		return this.value.equals(temp.value);
	}
}
