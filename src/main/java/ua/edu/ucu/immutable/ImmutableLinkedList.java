package ua.edu.ucu.immutable;

public class ImmutableLinkedList implements ImmutableList {
    private Node firstElement = new Node(null);
    private Node lastElement = new Node(null);
    private int size = 0;


    public ImmutableLinkedList(Node firstElement, Node lastElement, int size) {
        this.firstElement = firstElement;
        this.lastElement = lastElement;
        this.size = size;
    }

    public ImmutableLinkedList() {

    }

    public ImmutableLinkedList copy() {
        ImmutableLinkedList newList = new ImmutableLinkedList();
        if (size == 0) {
            return new ImmutableLinkedList();
        }
        newList.firstElement = firstElement.singleCopy();
        Node curNewNode = newList.firstElement, curNode = firstElement;
        for (int i = 1; i < size; i++) {
            curNode = curNode.getNext();
            curNewNode.setNext(curNode.singleCopy());
            curNewNode.getNext().setPrevious(curNewNode);
            curNewNode = curNewNode.getNext();
        }
        newList.lastElement = curNewNode;
        newList.size = size;
        return newList;


    }
    
    public ImmutableLinkedList addFirst(Object e) {
        firstElement = new Node(e, (Node) null, firstElement);
        if (size != 0) {
            firstElement.getNext().setPrevious(firstElement);
        }
        else {
            lastElement = firstElement;
        }
        size++;
        return this;
    }

    public ImmutableLinkedList addLast(Object e) {
        lastElement = new Node(e, lastElement, (Node) null);
        if (size != 0) {
            lastElement.getPrevious().setNext(lastElement);
        }
        else {
            firstElement = lastElement;
        }
        size++;
        return this;
    }

    public Object getFirst() {
        return firstElement;
    }

    public Object getLast() {
        return lastElement;
    }

    public ImmutableLinkedList removeFirst() {
        if (size == 0) {
            throw new IllegalArgumentException("array is empty");
        }
        if (size == 1) {
            firstElement = null;
            lastElement = null;
            size = 0;
            return new ImmutableLinkedList();
        }
        firstElement = firstElement.getNext();
        firstElement.setPrevious((Node) null);
        size--;
        return this;
    }

    public ImmutableLinkedList removeLast() {
        if (size == 0) {
            throw new IllegalArgumentException("array is empty");
        }
        if (size == 1) {
            firstElement = null;
            lastElement = null;
            size = 0;
            return new ImmutableLinkedList();
        }
        lastElement = lastElement.getPrevious();
        lastElement.setNext((Node) null);
        size--;
        return this;
    }

    @Override
    public ImmutableList add(Object e) {
        ImmutableLinkedList newList = copy();
        newList.lastElement = new Node(e, newList.lastElement, (Node) null);
        if (size != 0) {
            newList.lastElement.getPrevious().setNext(newList.lastElement);
        }
        else {
            newList.firstElement = newList.lastElement;
        }
        newList.size++;
        return newList;
    }

    @Override
    public ImmutableList add(int index, Object e) {
        if (index < 0 | index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            return add(e);
        }
        ImmutableLinkedList newList = copy();
        Node curNode = newList.firstElement;
        for (int i = 0; i < index; i++) {
            curNode = curNode.getNext();
        }
        Node newNode = new Node(e, curNode.getPrevious(), curNode);
        curNode.getPrevious().setNext(newNode);
        curNode.setPrevious(newNode);
        newList.size++;
        return newList;
    }

    public Node[] fromObjectToNodeArray(Object[] arrayOfObjects) {
        if (arrayOfObjects.length == 0) {
            return new Node[0];
        }
        Node[] arrayOfNodes = new Node[arrayOfObjects.length];
        Node curNode = new Node(arrayOfObjects[0]);
        arrayOfNodes[0] = curNode;
        for (int i = 1; i < arrayOfObjects.length; i++) {
            curNode = new Node(arrayOfObjects[i]);
            arrayOfNodes[i] = curNode;
            arrayOfNodes[i - 1].setNext(curNode);
            curNode.setPrevious(arrayOfNodes[i-1]);
        }

        return arrayOfNodes;
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        if (c.length == 0) {
            return copy();
        }
        if (c.length == 1) {
            return add(c[0]);
        }

        Node[] arrayOfNodes = fromObjectToNodeArray(c);
        if (size == 0) {
            return new ImmutableLinkedList(arrayOfNodes[0],
                    arrayOfNodes[c.length - 1], c.length);
        }

        ImmutableLinkedList newList = copy();

        Node firstArrayNode = arrayOfNodes[0];
        newList.lastElement.setNext(firstArrayNode);
        newList.lastElement = arrayOfNodes[c.length - 1];

        newList.size = newList.size + c.length;
        return newList;
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index < 0 | index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            return addAll(c);
        }

        ImmutableLinkedList newList = copy();
        Node[] arrayOfNodes = fromObjectToNodeArray(c);

        Node curNode = newList.firstElement;
        for (int i = 0; i < index; i++) {
            curNode = curNode.getNext();
        }
        curNode.getPrevious().setNext(arrayOfNodes[0]);
        curNode.setPrevious(arrayOfNodes[c.length - 1]);

        newList.size = newList.size + c.length;
        return newList;
    }

    @Override
    public Object get(int index) {
        if (index < 0 | index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        Node node = firstElement;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node.getObject();
    }

    @Override
    public ImmutableList remove(int index) {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        if (index < 0 | index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList newList = copy();
        if (size == 1) {
            return new ImmutableLinkedList();
        }
        if (index == 0) {
            newList.firstElement = newList.firstElement.getNext();
            newList.firstElement.setPrevious((Node) null);
            newList.size--;
            return newList;
        }
        if (index == size - 1) {
            newList.lastElement = newList.lastElement.getPrevious();
            newList.firstElement.setNext((Node) null);
            newList.size--;
            return newList;
        }
        Node node = newList.firstElement;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        node.getPrevious().setNext(node.getNext());
        node.getNext().setPrevious(node.getPrevious());
        newList.size--;
        return newList;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (index < 0 | index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList newList = copy();
        Node node = newList.firstElement;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        node.setObject(e);
        return newList;
    }

    @Override
    public int indexOf(Object e) {
        Node curNode = firstElement;
        for (int i = 0; i < size; i++) {
            if (curNode.getObject() == e) {
                return i;
            }
            curNode = curNode.getNext();
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node curNode = firstElement;
        for (int i = 0; i < size; i++) {
                array[i] = curNode.getObject();
                curNode = curNode.getNext();
            }
        return array;
    }
}
