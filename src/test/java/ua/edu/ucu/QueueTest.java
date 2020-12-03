package ua.edu.ucu;

import org.junit.Test;
import ua.edu.ucu.immutable.ImmutableLinkedList;

import static org.junit.Assert.*;

public class QueueTest {
    
    @Test
    public void testQueue() {
        ImmutableLinkedList immutableLinkedList = new ImmutableLinkedList();
        Queue queue = new Queue(immutableLinkedList);
        queue.enqueue(2);
        assertEquals(2, queue.peek());
        queue.enqueue(3);
        assertEquals(2, queue.peek());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.peek());
        assertEquals(3, queue.dequeue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPeek() {
        ImmutableLinkedList immutableLinkedList = new ImmutableLinkedList();
        Queue queue = new Queue(immutableLinkedList);
        queue.peek();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyDequeue() {
        ImmutableLinkedList immutableLinkedList = new ImmutableLinkedList();
        Queue queue = new Queue(immutableLinkedList);
        queue.dequeue();
    }
    
}
