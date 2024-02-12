package org.example

/**
 * ``minPathSum`` finds the shortest distance in an n by n matrix of different
 * integer values to get from the top left to the bottom right in the least amount
 * of weight by moving only to the right and downwards
 *
 *  @param matrix an n by n array of integers that correspont to graph weights
 *
 *  @return the minimum sum after using Dijkstra's algorithm
 */
fun minPathSum(matrix: Array<IntArray>): Int {
    val n = matrix.size // Define the graph and get the matrix length
    val graph = DirectedWeightedGraph<Pair<Int, Int>>()

    // Add all elements as vertices in the graph
    for (i in 0 until n) {
        for (j in 0 until n) {
            graph.addEdge(Pair(i, j), Pair(i + 1, j), matrix[i][j].toDouble()) // Down edge
            graph.addEdge(Pair(i, j), Pair(i, j + 1), matrix[i][j].toDouble()) // Right edge
        }
    }

    // Use Dijkstra's algorithm to find shortest path between the first and final
    // matrix element
    val shortestPath = dijkstraShortestPath(graph, Pair(0, 0), Pair(n - 1, n - 1))

    var total = 0 // Calculate the and return the minimum total
    shortestPath?.forEach { vertex ->
        total += matrix[vertex.first][vertex.second]
    }
    return total
}
