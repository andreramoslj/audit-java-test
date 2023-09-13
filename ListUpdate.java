import java.util.List;

public class ListUpdate extends ChangeType {
    private final String property;
    private final List<Object> added;
    private final List<Object> removed;

    public ListUpdate(String property, List<Object> added, List<Object> removed) {
        this.property = property;
        this.added = added;
        this.removed = removed;
    }

    public String getProperty() {
        return property;
    }

    public List<Object> getAdded() {
        return added;
    }

    public List<Object> getRemoved() {
        return removed;
    }
}