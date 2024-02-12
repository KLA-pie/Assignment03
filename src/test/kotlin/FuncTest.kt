import org.example.DirectedWeightedGraph
import org.example.MinimumPriorityQueue
import org.example.dijkstraShortestPath
import org.example.minPathSum
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class FuncTest {

    @Test
    fun return_connected_vertex() {
        val graph = DirectedWeightedGraph<String>()
        graph.addEdge("A", "B", 1.0)
        graph.addEdge("A", "C", 2.0)
        graph.addEdge("B", "C", 3.0)

        assertEquals(setOf("A", "B", "C"), graph.getVertices())
    }

    @Test
    fun return_weighted_edge() {
        val graph = DirectedWeightedGraph<String>()
        graph.addEdge("A", "B", 1.0)
        graph.addEdge("A", "C", 2.0)

        assertEquals(mapOf("B" to 1.0, "C" to 2.0), graph.getEdges("A"))
    }

    @Test
    fun return_no_sink_edge() {
        val graph = DirectedWeightedGraph<String>()
        graph.addEdge("A", "B", 1.0)
        graph.addEdge("A", "C", 2.0)
        graph.addEdge("B", "C", 3.0)

        assertEquals(mapOf("B" to 1.0, "C" to 2.0), graph.getEdges("A"))
        assertEquals(mapOf("C" to 3.0), graph.getEdges("B"))
        assertTrue(graph.getEdges("C").isEmpty())
    }

    @Test
    fun test_clear() {
        val graph = DirectedWeightedGraph<String>()
        graph.addEdge("A", "B", 1.0)
        graph.addEdge("A", "C", 2.0)
        graph.addEdge("B", "C", 3.0)

        graph.clear()

        assertTrue(graph.getVertices().isEmpty())
        assertTrue(graph.getEdges("A").isEmpty())
        assertTrue(graph.getEdges("B").isEmpty())
        assertTrue(graph.getEdges("C").isEmpty())
    }

    @Test
    fun test_empty() {
        val queue = MinimumPriorityQueue<Int>()
        assertTrue(queue.isEmpty())

        queue.addWithPriority(1, 10.0)
        assertFalse(queue.isEmpty())

        queue.next()
        assertTrue(queue.isEmpty())
    }

    @Test
    fun test_next() {
        val queue = MinimumPriorityQueue<Int>()
        assertNull(queue.next())

        queue.addWithPriority(3, 30.0)
        queue.addWithPriority(1, 10.0)
        queue.addWithPriority(2, 20.0)

        assertEquals(1, queue.next())
        assertEquals(2, queue.next())
        assertEquals(3, queue.next())
        assertNull(queue.next())
    }

    @Test
    fun test_add() {
        val queue = MinimumPriorityQueue<Int>()
        queue.addWithPriority(1, 10.0)
        queue.addWithPriority(2, 20.0)

        assertEquals(1, queue.next())
        assertEquals(2, queue.next())
    }

    @Test
    fun test_adjust() {
        val queue = MinimumPriorityQueue<Int>()
        queue.addWithPriority(1, 10.0)
        queue.addWithPriority(2, 20.0)
        queue.addWithPriority(3, 30.0)

        queue.adjustPriority(2, 5.0) // Decrease priority of 2
        assertEquals(2, queue.next()) // 2 should be returned first now

        queue.addWithPriority(4, 15.0) // Add a new element
        assertEquals(1, queue.next()) // New element should be returned next
    }
    
    @Test
    fun test_shortest_path() {
        val graph = DirectedWeightedGraph<String>()
        graph.addEdge("A", "B", 5.0)
        graph.addEdge("A", "C", 3.0)
        graph.addEdge("B", "D", 4.0)
        graph.addEdge("C", "D", 7.0)
        graph.addEdge("C", "E", 10.0)
        graph.addEdge("D", "E", 2.0)

        val shortestPath = dijkstraShortestPath(graph, "A", "E")
        assert(shortestPath == listOf("A", "B", "D", "E"))
    }

    @Test
    fun test_no_path() {
        val graph = DirectedWeightedGraph<Char>()
        graph.addEdge('A', 'B', 1.0)
        graph.addEdge('C', 'D', 2.0)
        graph.addEdge('E', 'F', 3.0)

        val shortestPath = dijkstraShortestPath(graph, 'A', 'E')
        assert(shortestPath == null)
    }

    @Test
    fun test_path_sum() {
        val matrix1 = arrayOf(
            intArrayOf(1, 3, 1),
            intArrayOf(1, 5, 1),
            intArrayOf(4, 2, 1)
        )
        assertEquals(7, minPathSum(matrix1))

        val matrix2 = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        )
        assertEquals(21, minPathSum(matrix2))

        val matrix3 = arrayOf(
            intArrayOf(1)
        )
        assertEquals(1, minPathSum(matrix3))

        val matrix4 = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        )
        assertEquals(7, minPathSum(matrix4))
    }
}