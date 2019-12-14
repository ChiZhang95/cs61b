/* Attributed by Chi Zhang at UC Berkeley CS 61B class, 2018/2/12 */

public class LinkedListDeque<T> {

    private class LinkedNode {

        private T item;
        private LinkedNode next;
        private LinkedNode prev;

        private LinkedNode(LinkedNode p, T i, LinkedNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /* The first item is at sentinel.next*/
    private LinkedNode sentinel;
    private int size;

    /* Create an empty LinkedListDeque */
    public LinkedListDeque(){
        sentinel = new LinkedNode(sentinel, null,sentinel);
        size = 0;
    }

    /* Create a LinkedListDequeue*/
    public LinkedListDeque(T x){
        sentinel = new LinkedNode(null,null, null);
        sentinel.next = new LinkedNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /*Add an item as the first item*/
    public void addFirst(T item){
        if(isEmpty()){
            sentinel.next = new LinkedNode(sentinel,item,sentinel);
            sentinel.prev = sentinel.next;
        }
        else {
            sentinel.next = new LinkedNode(sentinel, item, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
        }
        size += 1;
    }

    /* Add an item to the last*/
    public void addLast(T item){
        sentinel.prev = new LinkedNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /* Returns ture if deque is empty, false otherwise */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        else {
            return false;
        }
    }

    /* Return the number of items in the deque */
    public int size(){
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space */
    public void printDeque(){
        LinkedNode p = sentinel.next;
        while(p != sentinel){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.print(p.item + " ");
    }

    /* Removes and returns the item at the front of the deque*/
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        else {
            T first = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next.item = null;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return first;
        }
    }

    /* Removes and returns the item at the back of the deque0 */
    public T removeLast(){
        if(size == 0){
            return null;
        }

        T last = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = null;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return last;
    }

    /* Gets the item at the given index */
    public T get(int index){
        LinkedNode p = sentinel.next;

        if(size == 0 || index+1 > size){
            return null;
        }

        while(index != 0){
            p = p.next;
            index -= 1;
        }

        return p.item;
    }

    public T recursivehelper(int index, LinkedNode n){
        if(index == 0){
            return n.item;
        }
        else{
            return recursivehelper(index-1,n.next);
        }
    }

    public T getRecursive(int index){
        if(index >= size){
            return null;
        }
        return recursivehelper(index,sentinel.next);
    }

}