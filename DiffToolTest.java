import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DiffToolTest {

    @Test
    public void testPropertyUpdate() {
        ExampleObject previous = new ExampleObject("James", "ACTIVE", Arrays.asList("Oil Change", "Tire Rotation"));
        ExampleObject current = new ExampleObject("Jim", "EXPIRED", Arrays.asList("Tire Rotation", "Alignment"));

        List<ChangeType> changes = DiffTool.diff(previous, current);

        assertEquals(2, changes.size());

        PropertyUpdate propertyUpdate = (PropertyUpdate) changes.get(0);
        assertEquals("firstName", propertyUpdate.getProperty());
        assertEquals("James", propertyUpdate.getPrevious());
        assertEquals("Jim", propertyUpdate.getCurrent());

        ListUpdate listUpdate = (ListUpdate) changes.get(1);
        assertEquals("services", listUpdate.getProperty());
        assertEquals(Arrays.asList("Alignment"), listUpdate.getAdded());
        assertEquals(Arrays.asList("Oil Change"), listUpdate.getRemoved());
    }

    @Test
    public void testMissingAuditKey() {
            ExampleObjectWithoutAuditKey previous = new ExampleObjectWithoutAuditKey("John", "ACTIVE", Arrays.asList("Oil Change"));
        ExampleObjectWithoutAuditKey current = new ExampleObjectWithoutAuditKey("Smith", "EXPIRED", Arrays.asList("Tire Rotation"));

        assertThrows(IllegalArgumentException.class, () -> DiffTool.diff(previous, current));
    }
}
