package ua.edu.ucu.immutable;


public class Node {
    private Node next;
    private Node previous;
    private Object object;

    public Node(Object object, Node previous, Node next) {
        this.previous = previous;
        this.next = next;
        this.object = object;
    }

    public Node() {

    }

    public Node(Object object) {
        this.object = object;
    }

    public Node getPrevious() {
        return previous;
    }

    public Node getNext() {
        return next;
    }

    public Object getObject() {
        return object;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node singleCopy() {
        return new Node(object, previous, next);
    }

}
