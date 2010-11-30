/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author uko
 */
public class DijkstraAlgorithm
{
	private double length;
	private int[] path;
	public DijkstraAlgorithm(Graph graph, int from, int to)
	{
		TreeMap<Double, Integer> map = new TreeMap<Double, Integer>();
		int step = 0;
		map.put(0.0, from);
		while (((Entry<Double, Integer>) map.entrySet().toArray()[step]).getValue() != to)
		{
			Object[] archi = map.entrySet().toArray();
			for (Entry<Integer, Graph.Connection> i : graph.getConnections(((Entry<Double, Integer>) archi[step]).getValue()).entrySet())
			{
				boolean was = false;
				for (int j = 0; j < step; j++)
				{
					if (i.getKey() == ((Entry<Double, Integer>) map.entrySet().toArray()[j]).getValue())
					{
						was = true;
						break;
					}
				}
				if (!was)
				{
					if (map.containsValue(i.getKey()))
					{
						for (int k = 0; k <= archi.length; k++)
						{
							if (((Entry<Double, Integer>) archi[k]).getValue() == i.getKey())
							{
								if (((Entry<Double, Integer>) archi[k]).getKey() > ((Entry<Double, Integer>) map.entrySet().toArray()[step]).getKey() + i.getValue().getWeight())
								{
									map.remove(((Entry<Double, Integer>) archi[k]).getKey());
									map.put(((Entry<Double, Integer>) map.entrySet().toArray()[step]).getKey() + i.getValue().getWeight(), i.getKey());
								}
								break;
							}
						}
					}
					else
					{
						map.put(((Entry<Double, Integer>) map.entrySet().toArray()[step]).getKey() + i.getValue().getWeight(), i.getKey());
					}
				}
			}
			step++;
		}
		length = ((Entry<Double, Integer>) map.entrySet().toArray()[step]).getKey();
		path = new int[step + 1];
		Object[] archi = map.entrySet().toArray();
		for (int i = 0; i <= step; i++)
		{
			path[i] = ((Entry<Double, Integer>) archi[i]).getValue();
		}

	}
	/**
	 * @return the length
	 */
	public double getLength()
	{
		return length;
	}
	/**
	 * @return the path
	 */
	public int[] getPath()
	{
		return path;
	}
}
