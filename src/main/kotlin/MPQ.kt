package org.example

/**
 * ``MinPriorityQueue`` maintains a priority queue where the lower
 *  the priority value, the sooner the element will be removed from
 *  the queue.
 *  @param T the representation of the items in the queue
 *  @property queue A queue that is organized by priority
 */
class MinimumPriorityQueue<T>: MinPriorityQueue<T> {
    
    private var queue = MinHeap<T>()

    /**
     * @return true if the queue is empty, false otherwise
     */
    override fun isEmpty(): Boolean {
        return queue.isEmpty()
    }

    /**
     * Add [elem] with at level [priority]
     */
    override fun next(): T?{
        return queue.getMin()
    }

    /**
     * Get the next (lowest priority) element.
     * @return the next element in terms of priority.  If empty, return null.
     */
    override fun addWithPriority(elem: T, priority: Double) {
        queue.insert(elem, priority)
    }

    /**
     * Adjust the priority of the given element
     * @param elem whose priority should change
     * @param newPriority the priority to use for the element
     *   the lower the priority the earlier the element int
     *   the order.
     */
    override fun adjustPriority(elem: T, newPriority: Double) {
        queue.adjustHeapNumber(elem, newPriority)
    }
}