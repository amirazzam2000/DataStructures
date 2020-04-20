package MapObjects;

public class Queue<T> {
    private T object;
    private Queue<T> next;
    private static int size = 1;


    public Queue(T object) {
        this.object = object;
        next = null;
    }

    public Queue() {
        this.object = null;
        next = null;
        size = 0;
    }

    public T peek() {
        return object;
    }

    public T pop(){
        T aux = object;
        if (next != null) {
            object = next.peek();
            next = next.next;
        }else{
            object = null;
        }

        size--;
        return aux;
    }

    public void add(T object) {
        if(this.object == null) {
            this.object = object;
            size++;
        }
        else if(next == null){
            this.next = new Queue<>(object);
            size++;
        }
        else
            this.next.add(object);


    }

    public void add(Queue<T> q) {
        if(this.object == null) {
            this.object = q.object;
            this.next = q.next;

        }
        else if(next == null){
            this.next = new Queue<>(q.object);

        }
        else
            this.next.add(object);

    }

    public static int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return object == null;
    }

    public T popObjectByValue(T object){
        if(this.object == object){
           return this.pop();
        }
        else{
            if (this.next != null)
                this.next.popObjectByValue(object);
        }

        return null;
    }

}
