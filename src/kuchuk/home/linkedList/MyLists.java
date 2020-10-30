package kuchuk.home.linkedList;

public interface MyLists<T> {

    /**
     * Appends the specified element to the end of this list
     *
     * @param element to be appended to this list
     */
    void add(T element);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    T get(int index) throws IndexOutOfBoundsException;

    /**
     * Removes the element at the specified position in this list
     *
     * @param index the index of the element to be removed
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    void remove(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    int size();
}
