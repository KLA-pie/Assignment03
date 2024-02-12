package org.example

/**
 * `dijkstraShortestPath` finds the shortest path between a start node and an end
 * node of a graph using Dijkstra's algorithm.
 *
 * @param graph A graph which has a set of vertices and edges.
 * @param start The source node.
 * @param end The sink node.
 *
 * @return A list containing the path of vertices to get from the starting node to
 * the end node if a path exists, or null if a path does not exist.
 */
fun <VertexType> dijkstraShortestPath(graph: DirectedWeightedGraph<VertexType>, start: VertexType, end: VertexType): List<VertexType>? {

    val distances = mutableMapOf<VertexType, Double>().apply { put(start, 0.0) } // Map to keep track of the shortest distance from start to each vertex
    val previous = mutableMapOf<VertexType, VertexType>() // Map to keep track of previous vertex in the shortest path

    val queue = MinimumPriorityQueue<VertexType>() // Initialize priority queue for vertices based on their distances from the start node
    queue.addWithPriority(start, 0.0)

    while (!queue.isEmpty()) { // Start the algorithm
        val current = queue.next() ?: break // Use the next vertex with the minimum distance or break if the queue is empty

        if (current == end) { // If the current node is the end node, reconstruct and return the shortest path
            val path = mutableListOf<VertexType>()
            var vertex: VertexType? = current
            while (vertex != null) { // Reconstruct the path by backtracking from the end node to the start node
                path.add(vertex)
                vertex = previous[vertex]
            }
            return path.reversed() // Return the reversed path
        }

        val currentDistance = distances[current] ?: continue // Get the shortest distance to the current node from the start node
        val neighbors = graph.getEdges(current)
        for ((neighbor, cost) in neighbors) { // Iterate over neighbors and update distances and previous vertices if a shorter path is found
            val newDistance = currentDistance + cost // Calculate the distance to the neighbor through the current node
            if (!distances.containsKey(neighbor) || newDistance < distances[neighbor]!!) {
                // If the new distance is shorter than the previously recorded distance, update the distance and node

                distances[neighbor] = newDistance
                previous[neighbor] = current
                queue.addWithPriority(neighbor, newDistance)
            }
        }
    }

    return null // If the loop exits, then a pathway does not exist for the graph from the start node to the end node
}
