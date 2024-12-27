import java.util.ArrayList;
import java.util.Iterator;

class NameRepository implements Iterable<String> {
    private ArrayList<String> names = new ArrayList<>();

    public void addName(String name) {
        names.add(name);
    }

    public Iterator<String> iterator() {
        return names.iterator();
    }
}

public class IteratorPattern {
    public static void main(String[] args) {
        NameRepository names = new NameRepository();
        names.addName("Kuldeep");
        names.addName("Vikash");
        names.addName("Rajender");
        for (String name : names) {
            System.out.println(name);
        }
    }
}