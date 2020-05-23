package Hash;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class List<T> {

    private Object[] object;
    private int pos = 0;


    public List() {
        this.object = new Object[100];

    }


    public void add(T object) {
        if (pos < getSize() ) {
            this.object[pos++] =  object;
        }
        else{
            this.object = Arrays.copyOf(this.object,
                    getSize() + 10);
        }
    }

    public void delete(int index){
        Object[] aux = new Object[getSize()];
        if (index > 0) {
            System.arraycopy(this.object, 0, aux, 0, index - 1);
            System.arraycopy(this.object, index, aux, index, getSize() - index);
        }
        else{
            System.arraycopy(this.object, 1, aux, 0, getSize() - 1);
        }
        pos--;
        this.object = aux;
    }


    private int getSize() {
        return object.length;
    }

    public  int size(){
        return pos;
    }



    public T get(int i ){
        return (T) object[i];
    }


    public static void main(String[] args) {
        List<Integer> test = new List<>();

        test.add(1);
        test.add(2);
        test.add(3);
        for (int i = 0; i < 100; i++) {
            test.add(i);
        }
        System.out.println(test.get(1));

        test.delete(14);

        System.out.println();

    }
}
