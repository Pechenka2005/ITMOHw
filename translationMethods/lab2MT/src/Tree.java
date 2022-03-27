import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Tree {
    public String node;
    public int pos;
    public List<Tree> children = new ArrayList<>();

    public static Tree of(String name, Tree ...children) {
        return new Tree(-1, name, children);
    }

    public Tree(int pos, String node) {
        this.node = node;
        this.pos = pos;
    }

    public Tree(int pos, String node, Tree ...children) {
        this(pos, node);
        this.children = Arrays.asList(children);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return Objects.equals(node, tree.node) && Objects.equals(children, tree.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node, children);
    }

    public StringBuilder toStr() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("    ").append(this.pos).append(" [label = \"").append(this.node).append("\"]\n");
        this.children.forEach((ch) -> stringBuilder.append(ch.toStr()));
        this.children.forEach((ch) -> stringBuilder
                .append("    ")
                .append(this.pos)
                .append("->")
                .append(ch.pos)
                .append("\n"));
        return stringBuilder;
    }

}
