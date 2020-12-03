package ua.edu.ucu.immutable;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    

    @Test (expected = IndexOutOfBoundsException.class)
    public void testAdd() {
        ImmutableList immutableLinkedList1 = new ImmutableLinkedList();

        ImmutableList immutableLinkedList2 = immutableLinkedList1.add(5);
        assertSame(5, immutableLinkedList2.get(0));

        immutableLinkedList1 = immutableLinkedList2.add(6);
        assertSame(5, immutableLinkedList1.get(0));
        assertSame(6, immutableLinkedList1.get(1));

        immutableLinkedList2 = immutableLinkedList1.add(7);
        assertSame(5, immutableLinkedList2.get(0));
        assertSame(6, immutableLinkedList2.get(1));
        assertSame(7, immutableLinkedList2.get(2));

        immutableLinkedList1 = immutableLinkedList2.add(1, 10);
        assertSame(10, immutableLinkedList1.get(1));

        immutableLinkedList2 = immutableLinkedList1.add(4, 7);
        assertSame(7, immutableLinkedList1.get(4));

        immutableLinkedList2.add(8, 10);

    }

    @Test
    public void testAddAll() {
        ImmutableList immutableLinkedList1 = new ImmutableLinkedList();

        Object[] array_to_add = {1, 2, 3};
        ImmutableList immutableLinkedList2 = immutableLinkedList1.addAll(array_to_add);

        assertSame(1, immutableLinkedList2.get(0));
        assertSame(2, immutableLinkedList2.get(1));
        assertSame(3, immutableLinkedList2.get(2));

        array_to_add = new Object[]{5, 6, 7};
        immutableLinkedList1 = immutableLinkedList2.addAll(1, array_to_add);
        assertSame(1, immutableLinkedList1.get(0));
        assertSame(5, immutableLinkedList1.get(1));
        assertSame(6, immutableLinkedList1.get(2));
        assertSame(7, immutableLinkedList1.get(3));

    }

    @Test
    public void testRemove() {
        ImmutableList immutableLinkedList1 = new ImmutableLinkedList();

        Object[] array_to_add = {1, 2, 3};
        ImmutableList immutableLinkedList2 = immutableLinkedList1.addAll(array_to_add);
        immutableLinkedList1 = immutableLinkedList2.remove(1);

        assertSame(1, immutableLinkedList1.get(0));
        assertSame(3, immutableLinkedList1.get(1));
        immutableLinkedList2 = immutableLinkedList1.remove(0);

        assertSame(3, immutableLinkedList2.get(0));
        immutableLinkedList1 = immutableLinkedList2.remove(0);

        assertTrue(immutableLinkedList1.isEmpty());

    }

    @Test
    public void testSet() {
        ImmutableList immutableLinkedList1 = new ImmutableLinkedList();

        Object[] array_to_add = {1, 2, 3};
        ImmutableList immutableLinkedList2 = immutableLinkedList1.addAll(array_to_add);
        immutableLinkedList1 = immutableLinkedList2.set(0, 9);

        assertSame(9, immutableLinkedList1.get(0));
        assertSame(2, immutableLinkedList1.get(1));
        assertSame(3, immutableLinkedList1.get(2));

        immutableLinkedList2 = immutableLinkedList1.set(1, 9);

        assertSame(9, immutableLinkedList2.get(0));
        assertSame(9, immutableLinkedList2.get(1));
        assertSame(3, immutableLinkedList2.get(2));

        immutableLinkedList1 = immutableLinkedList2.set(2, 9);
        assertSame(9, immutableLinkedList1.get(0));
        assertSame(9, immutableLinkedList1.get(1));
        assertSame(9, immutableLinkedList1.get(2));

    }

    @Test
    public void testIndex() {
        ImmutableList immutableLinkedList1 = new ImmutableLinkedList();

        Object[] array_to_add = {1, 2, 3, 1};
        ImmutableList immutableLinkedList2 = immutableLinkedList1.addAll(array_to_add);

        assertSame(0, immutableLinkedList2.indexOf(1));
        assertSame(1, immutableLinkedList2.indexOf(2));
        assertSame(2, immutableLinkedList2.indexOf(3));
        assertSame(-1, immutableLinkedList2.indexOf(4));
        immutableLinkedList1 = immutableLinkedList2.remove(0);
        assertSame(2, immutableLinkedList1.indexOf(1));

    }

    @Test
    public void testClear() {
        ImmutableList immutableLinkedList1 = new ImmutableLinkedList();

        Object[] array_to_add = {1, 2, 3, 1};
        ImmutableList immutableLinkedList2 = immutableLinkedList1.addAll(array_to_add);

        immutableLinkedList1 = immutableLinkedList2.clear();
        assertSame(0, immutableLinkedList1.size());

    }

    @Test
    public void testToArray() {
        ImmutableList immutableLinkedList1 = new ImmutableLinkedList();

        Object[] array_to_add = {1, 2, 3, 1};
        ImmutableList immutableLinkedList2 = immutableLinkedList1.addAll(array_to_add);

        Object arr[] = immutableLinkedList2.toArray();

        assertArrayEquals(arr, array_to_add);

    }

    @Test
    public void testGetLast() {
        ImmutableLinkedList immutableLinkedList1 = new ImmutableLinkedList();

        Object[] array_to_add = {1, 2, 3};
        ImmutableLinkedList immutableLinkedList2 = (ImmutableLinkedList) immutableLinkedList1.addAll(array_to_add);

        Node last = (Node) immutableLinkedList2.getLast();
        assertEquals(3, last.getObject());
    }

    @Test
    public void testGetFirst() {
        ImmutableLinkedList immutableLinkedList1 = new ImmutableLinkedList();

        Object[] array_to_add = {1, 2, 3};
        ImmutableLinkedList immutableLinkedList2 = (ImmutableLinkedList) immutableLinkedList1.addAll(array_to_add);

        Node first = (Node) immutableLinkedList2.getFirst();
        assertEquals(1, first.getObject());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyRemoveFirst() {
        ImmutableLinkedList immutableLinkedList = new ImmutableLinkedList();
        immutableLinkedList.removeFirst();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyRemoveLast() {
        ImmutableLinkedList immutableLinkedList = new ImmutableLinkedList();
        immutableLinkedList.removeLast();
    }

    @Test
    public void testRemoveLast() {
        ImmutableLinkedList immutableLinkedList1 = new ImmutableLinkedList();
        ImmutableLinkedList immutableLinkedList2 = (ImmutableLinkedList) immutableLinkedList1.add(1);
        assertTrue(immutableLinkedList2.removeLast().isEmpty());

        immutableLinkedList1 = (ImmutableLinkedList) immutableLinkedList2.add(2);
        assertEquals(1, immutableLinkedList1.size());
    }

}
