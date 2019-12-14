/* Attributed by Chi Zhang at UC Berkeley CS 61B class, 2018/2/12 */
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int sizeahead;

    /* Create an empty ArrayDeque */
    public ArrayDeque(){
        items = (T []) new Object[8];
        size = 0;
        sizeahead = 0;
    }

    /* Adds an item to the front */
    public void addFirst(T item){
        if(size == 0){
            items[0] = item;
        }
        if(size == items.length - sizeahead){
            T[] a = (T []) new Object[items.length*2];
            System.arraycopy(items,0,a,0,size);
            System.arraycopy(items,size,a,a.length-sizeahead,sizeahead);
            items = a;
        }
        else{
            items[items.length - sizeahead - 1] = item;
        }
        size += 1;
    }

    /* Adds an item to the back */
    public void addLast(T item){
        if(size == items.length-sizeahead){
            T[] a = (T []) new Object[size*2];
            System.arraycopy(items,0,a,0,size);
            System.arraycopy(items,size,a,a.length-sizeahead,sizeahead);
            items = a;
        }
        items[size] = item;
        size += 1;
    }

    /* Return true if empty */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    /* return size */
    public int size(){
        return size;
    }

    /* prints items in the deque */
    public void printDeque(){
        for(int i = 0; i<sizeahead; i++){
            System.out.print(items[items.length - sizeahead + i]+" ");
        }
        for(int i = 0; i<size; i++){
            System.out.print(items[i]+" ");
        }
    }

    /* remove and returns theitem at the front */
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        if(sizeahead > 0){
            T first = items[items.length - sizeahead];
            items[items.length - sizeahead] = null;
            size -= 1;
            return first;
        }
        else {
            T[] a = (T[]) new Object[size];
            System.arraycopy(items, 1, a, 0, size - 1);
            T first = items[0];
            items = a;
            size -= 1;
            return first;
        }
    }

    /* Removes and returns the item in the back */
    public T removeLast(){
        T last = items[size - 1];
        items[size-1] = null;
        return last;
    }

    /* Gets the item at the given index */
    public T get(int index){
        if(index >= size+sizeahead){
            return null;
        }
        else{
            if(sizeahead > 0){
                if(index < sizeahead){
                    return items[items.length - sizeahead + sizeahead];
                }
                else{
                    return items[index - sizeahead];
                }
            }
            else{
                return items[index];
            }
        }
    }
}