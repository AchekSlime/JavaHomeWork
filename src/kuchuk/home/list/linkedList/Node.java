package kuchuk.home.list.linkedList;

public class Node<T> {
    private final T value;
    private Node<T> nextNode;
    private Node<T> prevNode;

    public Node() {
        value = null;
    }

    public Node(T value) {
        this.value = value;
    }

    public T getValue(){
        return value;
    }

    public Node<T> getNext(){
        return nextNode;
    }

    public Node<T> getPrev(){
        return prevNode;
    }

    public void setNext(Node<T> nextNode){
        this.nextNode = nextNode;
    }

    public void setPrev(Node<T> prevNode){
        this.prevNode = prevNode;
    }


}
