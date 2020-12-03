package ua.edu.ucu.immutable;

public class ImmutableArrayList implements ImmutableList {

    private Object[] elements = {0};
    private int size = 0;


    @Override
    public ImmutableList add(Object e) {
        return add(this.size, e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        Object[] singleElList = {e};
        return addAll(index, singleElList);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(this.size, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        ImmutableArrayList newArray = new ImmutableArrayList();

        Object[] newElements = new Object[size + c.length];
        System.arraycopy(this.elements, 0, newElements, 0, index);
        System.arraycopy(c, 0, newElements, index, c.length);
        System.arraycopy(this.elements, index, newElements,
                index + c.length, this.size - index);

        newArray.size = this.size + c.length;
        newArray.elements = newElements;
        return newArray;

    }

    @Override
    public Object get(int index) {
        if (index < 0 | index > size - 1) {
            throw new IllegalArgumentException();
        }
        return elements[index];
    }

    @Override
    public ImmutableList remove(int index) {
        if (index < 0 | index > size - 1) {
            throw new IllegalArgumentException();
        }
        ImmutableArrayList newArray = new ImmutableArrayList();
        Object[] newElements = elements;
        System.arraycopy(newElements, index + 1, newElements,
                index, size - 1 - index);
        newArray.elements = newElements;
        newArray.size = size - 1;
        return newArray;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (index < 0 | index > size - 1) {
            throw new IllegalArgumentException();
        }
        ImmutableArrayList newArray = new ImmutableArrayList();
        newArray.elements = elements;
        newArray.elements[index] = e;
        newArray.size = size;
        return newArray;
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++) {
            if (this.elements[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        return elements;
    }

}
