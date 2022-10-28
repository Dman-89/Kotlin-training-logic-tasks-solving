package codewars.any_match_all_match;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;
import static org.junit.jupiter.api.Assertions.*;

//https://www.codewars.com/kata/581e50555f59405743001813/java
public class AnyMatchAllMatch {

    public static void main(String[] args) {
        assertEquals(true, anyMatch(Helpers.listFromArray("bla", "ble", "blo"), x -> x.equals("blo")));
        assertEquals(false, allMatch(Helpers.listFromArray("bla", "ble", "blo"), x -> x.equals("blo")));
        assertEquals(true, anyMatch(Helpers.listFromArray("bla", "blo", "blob"), x -> x.equals("blo")));
        assertEquals(true, allMatch(Helpers.listFromArray("blo", "blo", "blo"), x -> x.equals("blo")));
        assertEquals(false, allMatch(Helpers.listFromArray("blo", "blo", "blob"), x -> x.equals("blo")));
    }


    static <T> boolean anyMatch(Node<T> head, Predicate<T> p) {
        if (head == null) return false;
        return p.test(head.data) || anyMatch(head.next, p);
    }

    static <T> boolean allMatch(Node<T> head, Predicate<T> p) {
        if (head == null) return true;
        return p.test(head.data) && allMatch(head.next, p);
    }
}

class Node<T> {
    public T data;
    public Node<T> next;

    Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }

    Node(T data) {
        this(data, null);
    }
}

class Helpers {
    public static <T> Node<T> listFromArray(T... items) {
        Iterator<T> iterator = Arrays.stream(items).iterator();
        Node<T> head = new Node(iterator.next());
        Node<T> curr = head;
        while(iterator.hasNext()) {
            curr.next = new Node<>(iterator.next());
            curr = curr.next;
        }
        return head;
    }
}