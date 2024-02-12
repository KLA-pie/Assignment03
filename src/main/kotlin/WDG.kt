package org.example
/**
 * A class representation of a directed graph
 * @param VertexType the representation of a vertex in the graph
 * @property adjacencyMap a map with the keys being a source vertex mapped to
 * the destination vertex
 */
class DirectedWeightedGraph<VertexType> : Graph<VertexType> {

    private val adjacencyMap: MutableMap<VertexType, MutableMap<VertexType, Double>> = mutableMapOf()

    /**
     * @return the vertices in the graph
     */
    override fun getVertices(): Set<VertexType> {
        return adjacencyMap.keys
    }

    /**
     * Add an edge between two vertices of a given weight
     * @param from The source vertex
     * @param to The sink vertex
     * @param cost The weight of the connection
     */
    override fun addEdge(from: VertexType, to: VertexType, cost: Double) {
        // Ensure that all vertices are in the map
        adjacencyMap.getOrPut(from) { mutableMapOf() }
        adjacencyMap.getOrPut(to) { mutableMapOf() }
        adjacencyMap[from]!![to] = cost
    }

    /**
     * Obtain relevant edges in the graph
     * @return All connections determined by all source nodes
     */
    override fun getEdges(from: VertexType): Map<VertexType, Double> {
        return adjacencyMap[from] ?: emptyMap()
    }

    /**
     * Remove all edges and vertices from the graph
     */
    override fun clear() {
        adjacencyMap.clear()
    }
}
