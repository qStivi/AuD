package julian;

public class ListElement<T> {

    ListElement<T> next;
    T key;

    public ListElement(ListElement<T> next, T key) {
        this.next = next;
        this.key = key;
    }

    public ListElement(T key) {
        this.next = null;
        this.key = key;
    }
}
