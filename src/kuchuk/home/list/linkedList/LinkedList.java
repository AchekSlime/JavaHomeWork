package kuchuk.home.list.linkedList;

import jdk.jshell.spi.ExecutionControl;
import kuchuk.home.list.MyLists;

/**
 * LinkedList is a doubly linked list
 *
 * @author Achek
 * @see MyLists
 * @param <T> the type of elements held in this collection
 */
public class LinkedList<T> implements MyLists<T> {

    /**
     * Pointer to first node.
     */
    private Node<T> head;

    /**
     * Pointer to last node.
     */
    private Node<T> tail;

    private int size = 0;

    /**
     * Constructs an empty list.
     */
    public LinkedList() {
    }

    /**
     * Appends the specified element into the empty list
     *
     * @param newNode - element o be appended to this list
     */
    private void creation(Node<T> newNode) {
        head = newNode;
        tail = head;
        size++;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element to be appended to this list
     */
    @Override
    public void add(T element) {
        if (tail == null) {
            creation(new Node<>(element));
        } else {
            Node<T> newTail = new Node<>(element);
            tail.setNext(newTail);
            newTail.setPrev(tail);
            tail = newTail;
            size++;
        }
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        else return getNodeByIndex(index).getValue();
    }

    /**
     * Returns the index of the searched element in list
     *
     * @param value the element whose index will be found
     * @return the index of the searched element or -1 if there is no such element
     */
    public int get(T value) {
        int index = 0;
        if (value == null) {
            return -1;
        } else {
            for (Node<T> curNode = head; curNode != null; curNode = curNode.getNext()) {
                if (value.equals(curNode.getValue()))
                    return index;
                index++;
            }
        }
        return -1;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void remove(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        else {
            Node<T> removable = getNodeByIndex(index);

            if (removable.getPrev() != null)
                removable.getPrev().setNext(removable.getNext());
            else if (removable.getNext() != null)
                head = removable.getNext();
            else
                head = null;
            if (removable.getNext() != null)
                removable.getNext().setPrev(removable.getPrev());
            else if (removable.getPrev() != null)
                tail = removable.getNext();
            else tail = null;

            size--;
        }
    }

    /**
     *
     * Returns the (non-null) Node at the specified element index.
     */
    private Node<T> getNodeByIndex(int index) {
        int i = 0;
        Node<T> curNode = head;
        while (curNode != null) {
            if (i == index)
                return curNode;
            i++;
            curNode = curNode.getNext();
        }

        return new Node<>();
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
    }

    @Override
    public boolean contains(Object index) {
        return false;
    }


}
