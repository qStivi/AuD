package julian;

public class List<T extends Comparable<T>> {

    ListElement<T> head;
    ListElement<T> tail;

    public List() {
        this.tail = new ListElement<>(null, null);
        this.head = new ListElement<>(tail, null);
    }

    public void add(T value) { // head -> tail

        ListElement<T> element = new ListElement<>(value);

        ListElement<T> temp = this.head;
        while (temp.next != this.tail) {
            temp = temp.next;
        }
        temp.next = element;
        element.next = tail;
    }

    public void deleteSmallerThanLast() {

        ListElement<T> lastElement = this.head;

        while (!lastElement.next.equals(this.tail)) {
            lastElement = lastElement.next;
        }

        ListElement<T> temp = this.head;

        while (!temp.next.equals(this.tail)) { //temp = aktuelles Element

            if (temp.next.key.compareTo(lastElement.key) < 0) { //Wenn Schlüssel von nächstem Element
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }

    }

    public void cutSublist(int a, int b) {

        if(!(a > b || a > 6)) {
            int c = 0;
            ListElement<T> x = this.head;

            while (c < a - 1) {
                x = x.next;
                c++;
            }

            if (b > 6) {
                x.next = this.tail;

            } else {
                ListElement<T> y = x.next;
                c = 0;
                while (c < b - a + 1) {
                    y = y.next;
                    c++;
                }
                x.next = y;
            }
        }
    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();
        ListElement<T> element = this.head;

        while (true) {
            if (element.equals(this.tail)) {
                output.append(element.key);
                break;
            }
            output.append(element.key).append(" -> ");
            element = element.next;
        }
        return output.toString();
    }


    public static void main(String[] args) {
        List<Integer> list = new List<>();
        list.add(5); // 1
        list.add(7); // 2
        list.add(17); // 3
        list.add(19); // 4
        list.add(20); // 5
        list.add(25); // 6
        System.out.println(list);
        //list.deleteSmallerThanLast();
        list.cutSublist(2, 9);
        System.out.println(list);
    }

}
