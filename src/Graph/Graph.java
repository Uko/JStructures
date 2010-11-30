/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.TreeMap;

/**
 * A simple graph featuring a creation of the graph with a fixed amount of vertexes and management of the connections between them.
 * @author uko
 */
public class Graph
{
	private Vertex[] vertexes;
	/**
	 * Creates a graph with a certain amount of vertexes
	 * @param amount number of vertexes to create.
	 */
	public Graph(int amount)
	{
		vertexes = new Vertex[amount];
		for (int i = 0; i < vertexes.length; i++)
		{
			vertexes[i] = new Vertex(i);
		}
	}
	/**
	 * returns a map of the connections of the specified vertex.
	 * @param id id of a vertex to return.
	 * @return map of the connections
	 */
	public TreeMap<Integer, Connection> getConnections(int id)
	{
		return vertexes[id].getConections();
	}
	/**
	 * adds a connections between specified vertexes
	 * @param weight weights to set on the connections
	 * @param ids array of vertex ids
	 */
	public void addConnections(double[] weight, int[] ids)
	{
		for (int i : ids)
		{
			for (int j : ids)
			{
				if (i != j)
				{
					vertexes[i].addConnection(weight[i], vertexes[j]);
					vertexes[j].addConnection(weight[i], vertexes[i]);
				}
			}
		}
	}
	/**
	 * adds a connections between specified vertexes
	 * @param weight a weight to set on the connections
	 * @param ids array of vertex ids
	 */
	public void addConnections(double weight, int[] ids)
	{
		for (int i : ids)
		{
			for (int j : ids)
			{
				if (i != j)
				{
					vertexes[i].addConnection(weight, vertexes[j]);
					vertexes[j].addConnection(weight, vertexes[i]);
				}
			}
		}
	}
	/**
	 * adds a connection between two vertexes
	 * @param weight a weight of the connection
	 * @param id1 first vertex id
	 * @param id2 second vertex id
	 */
	public void addConnection(double weight, int id1, int id2)
	{
		vertexes[id1].addConnection(weight, vertexes[id2]);
		vertexes[id2].addConnection(weight, vertexes[id1]);
	}
	/**
	 * removes connection between two vertexes
	 * @param id1 first vertex id
	 * @param id2 second vertex id
	 */
	public void removeConnection(int id1, int id2)
	{
		vertexes[id1].removeConnection(id2);
		vertexes[id2].removeConnection(id1);
	}

	private static class Vertex
	{
		private int id;
		private TreeMap<Integer, Connection> connections;
		public Vertex(int id)
		{
			this.id = id;
			this.connections = new TreeMap<Integer, Connection>();
		}
		/**
		 * @return the id
		 */
		public int getId()
		{
			return id;
		}
		/**
		 * @return the connections
		 */
		public TreeMap<Integer, Connection> getConections()
		{
			return connections;
		}
		public void addConnection(double weight, Vertex destination)
		{
			if (destination == this)
			{
				throw new UnsupportedOperationException();
			}
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

	/**
	 * a connection between vertexes
	 */
	public static class Connection
	{
		private double weight;
		private Vertex destination;
		/**
		 * creates a connection with a specified weight and destination
		 * @param weight a weight of a connection
		 * @param destination a destination of a connection
		 */
		public Connection(double weight, Vertex destination)
		{
			this.weight = weight;
			this.destination = destination;
		}
		/**
		 *
		 * @param destination
		 */
		public Connection(Vertex destination)
		{
			this(0, destination);
		}
		/**
		 * @return the weight
		 */
		public double getWeight()
		{
			return weight;
		}
		/**
		 * returns a map of the connections of the specified vertex.
		 * @param id id of a vertex to return.
		 * @return map of the connections
		 */
		public TreeMap<Integer, Connection> getConnections(int id)
		{
			return destination.getConections();
		}
	}

}
