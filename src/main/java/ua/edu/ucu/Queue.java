package ua.edu.ucu;


import ua.edu.ucu.immutable.ImmutableLinkedList;
import ua.edu.ucu.immutable.Node;

public class Queue {
    private ImmutableLinkedList queue = new ImmutableLinkedList();

    public Queue(ImmutableLinkedList queue) {
        this.queue = queue;
    }

    public Object peek() {
        if (queue.isEmpty()) {
            throw new IllegalArgumentException("empty queue");
        }
        Node first = (Node) queue.getFirst();
        return first.getObject();
    }

    public Object dequeue() {
        if (queue.isEmpty()) {
            throw new IllegalArgumentException("empty queue");
        }
        Node first = (Node) queue.getFirst();
        queue.removeFirst();
        return first.getObject();
    }

    public void enqueue(Object e) {
        queue.addLast(e);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
}
