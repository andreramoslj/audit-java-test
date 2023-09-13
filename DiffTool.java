import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DiffTool {
    public static List<ChangeType> diff(Object previous, Object current) {
        List<ChangeType> changes = new ArrayList<>();
        diffObjects("", previous, current, changes);
        return changes;
    }

    private static void diffObjects(String path, Object previous, Object current, List<ChangeType> changes) {
        if (previous == null && current == null) {
            return;
        }

        if (previous == null || current == null) {
            changes.add(new PropertyUpdate(path, previous, current));
            return;
        }

        if (previous.getClass() != current.getClass()) {
            throw new IllegalArgumentException("Objects must be of the same type.");
        }

        Class<?> clazz = previous.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String fullPath = path.isEmpty() ? fieldName : path + "." + fieldName;

            try {
                Object prevFieldValue = field.get(previous);
                Object currFieldValue = field.get(current);

                if (!areEqual(prevFieldValue, currFieldValue)) {
                    if (field.isAnnotationPresent(AuditKey.class) || fieldName.equals("id")) {
                        changes.add(new PropertyUpdate(fullPath, prevFieldValue, currFieldValue));
                    } else {
                        throw new IllegalArgumentException("AuditKey annotation or 'id' field not found.");
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field: " + fullPath, e);
            }
        }
    }

    private static boolean areEqual(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }
}
