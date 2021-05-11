import java.util.ArrayList;

public interface Buffer <T> {
    public void put(T element, int num);

    public ArrayList<T> take(int num);

    public T look();

    public boolean isEmpty();

    public boolean isFull();

    public int size();

}
