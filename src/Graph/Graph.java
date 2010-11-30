/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Graph;


import java.util.TreeMap;



/**
 *
 * @author uko
 */
public class Graph
{
	private Vertex[] vertexes;
	public Graph(int amount)
	{
		vertexes = new Vertex[amount];
		for(int i=0; i<vertexes.length; i++)
		{
			vertexes[i]=new Vertex(i);
		}
	}
	public TreeMap<Integer,Connection> getConnections(int id)
	{
		return vertexes[id].getConections();
	}
	public void addConnections(double weight,int[] ids)
	{
		for(int i: ids)
		{
			for(int j: ids)
			{
				if(i!=j)
				{
					vertexes[i].addConnection(weight, vertexes[j]);
					vertexes[j].addConnection(weight, vertexes[i]);
				}
			}
		}
	}
	public void addConnection(double weight, int id1, int id2)
	{
		vertexes[id1].addConnection(weight, vertexes[id2]);
		vertexes[id2].addConnection(weight, vertexes[id1]);
	}
	public void removeConnection(int id1, int id2)
	{
		vertexes[id1].removeConnection(id2);
		vertexes[id2].removeConnection(id1);
	}
	private static class Vertex
	{
		private int id;
		private TreeMap<Integer,Connection> connections;
		public Vertex(int id)
		{
			this.id = id;
			this.connections = new TreeMap<Integer,Connection>();
		}
		/**
		 * @return the id
		 */ public int getId()
		{
			return id;
		}
		/**
		 * @return the connections
		 */ public TreeMap<Integer,Connection> getConections()
		{
			return connections;
		}
		public void addConnection(double weight, Vertex destination)
		{
			if (destination==this)
				throw new UnsupportedOperationException();
			this.connections.put(destination.getId(), new Connection(weight, destination));
		}
		public void removeConnection(Vertex destination)
		{
			connections.remove(destination.getId());
		}
		public void removeConnection(int id)
		{
			connections.remove(id);
		}
	}
	public static class Connection
	{
		private double weight;
		private Vertex destination;
		public Connection(double weight, Vertex destination)
		{
			this.weight = weight;
			this.destination = destination;
		}
		public Connection(Vertex destination)
		{
			this(0,destination);
		}
		/**
		 * @return the weight
		 */ public double getWeight()
		{
			return weight;
		}
		public TreeMap<Integer,Connection> getConnections(int id)
		{
			return destination.getConections();
		}
	}
}
