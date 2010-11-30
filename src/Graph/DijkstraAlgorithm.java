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
	Graph graph;
	int from;
	int to;
	public DijkstraAlgorithm(Graph graph, int from, int to)
	{
		this.graph = graph;
		this.from = from;
		this.to = to;
	}
	public double solve()
	{
		TreeMap<Double, Integer> map = new TreeMap<Double, Integer>();
		int step=0;
		map.put(0.0, from);
		while(((Entry<Double, Integer>)map.entrySet().toArray()[step]).getValue()!=to)
		{
			Object[] archi=map.entrySet().toArray();
			for(Entry<Integer, Graph.Connection> i:graph.getConnections(((Entry<Double, Integer>)archi[step]).getValue()).entrySet())
			{
				boolean was=false;
				for(int j=0; j<step; j++)
				{
					if(i.getKey()==((Entry<Double, Integer>)map.entrySet().toArray()[j]).getValue())
					{
						was=true;
						break;
					}
				}
				if(!was)
				{
					map.put(((Entry<Double, Integer>)map.entrySet().toArray()[step]).getKey()+i.getValue().getWeight(), i.getKey());
				}
			}
			step++;
		}
		return ((Entry<Double, Integer>) map.entrySet().toArray()[step]).getKey();
	}
}
