import java.util.ArrayList;
import java.util.List;

interface Component {
    void showDetails();
}

class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    public void showDetails() {
        System.out.println("Leaf: " + name);
    }
}

class Composite implements Component {
    private List<Component> children = new ArrayList<>();

    public void add(Component component) {
        children.add(component);
    }

    public void showDetails() {
        for (Component child : children) {
            child.showDetails();
        }
    }
}

public class CompositePattern {
    public static void main(String[] args) {
        Leaf leaf1 = new Leaf("Leaf 1");
        Leaf leaf2 = new Leaf("Leaf 2");

        Composite composite = new Composite();
        composite.add(leaf1);
        composite.add(leaf2);

        composite.showDetails();
    }
}