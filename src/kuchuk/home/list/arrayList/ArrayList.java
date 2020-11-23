package kuchuk.home.list.arrayList;

import kuchuk.home.list.MyLists;

import java.util.Arrays;

public class ArrayList<T> implements MyLists<T> {
    private static final int DEFAULT_ARRAY_CAPACITY = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENTS = {};
    //FIX: почему оно не private?
    Object[] elements;
    int size;

    public ArrayList() {
        elements = new Object[DEFAULT_ARRAY_CAPACITY];
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param newElement to be appended to this list
     */
    @Override
    public void add(T newElement) {
        if (size == elements.length)
            elements = increaseCapacity();
        elements[size] = newElement;
        size++;
    }

    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     *
     * @throws OutOfMemoryError if minCapacity is less than zero
     */
    // FIXME:каждый раз при добавлении элемента придется писать try catch. Так не надо.
    private Object[] increaseCapacity() throws OutOfMemoryError {
        int minCapacity = size + 1;
        return elements = Arrays.copyOf(elements,
                newCapacity(minCapacity));
    }

    /**
     * Returns a capacity at least as large as the given minimum capacity.
     * Returns the current capacity increased by 50% if that suffices.
     * Will not return a capacity greater than MAX_ARRAY_SIZE unless
     * the given minimum capacity is greater than MAX_ARRAY_SIZE.
     *
     * @param minCapacity the desired minimum capacity
     * @throws OutOfMemoryError if minCapacity is less than zero
     */
    private int newCapacity(int minCapacity) throws OutOfMemoryError{
        // check overflow
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (elements == DEFAULT_CAPACITY_EMPTY_ELEMENTS)
                return Math.max(DEFAULT_ARRAY_CAPACITY, minCapacity);
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
                ? newCapacity
                : hugeCapacity(minCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
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
        checkIndex(index);
        return elements(index);
    }


    /**
     * Checks whether the index is within the bounds of the array
     *
     * @param index index of the element to return
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    /**
     * Сhecks whether the index is within the bounds of the array
     *
     * @return the T element at the specified position in this list
     * @param index index of the element to return
     */
    @SuppressWarnings("unchecked")
    private T elements(int index){
        return (T) elements[index];
    }

    /**
     * Returns an element at the specified index converting it to the desired type
     *
     * @param index the index of the element to be removed
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        remove(elements, index);
    }

    /**
     * Private remove method that skips bounds checking and does not
     * return the value removed.
     */
    private void remove(Object[] array, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(elements, i + 1, elements, i, newSize - i);
        elements[size = newSize] = null;
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

    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    @Override
    public void clear() {
        for (int to = size, i = size = 0; i < to; i++)
            elements[i] = null;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param element element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    @Override
    public boolean contains(Object element) {
        return tryFind(element, 0, size) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    //FIX: Почему он не private?
    int tryFind(Object o, int start, int end) {
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
