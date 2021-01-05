package dateStructure.chapt02;

import java.util.function.Predicate;

public class Assert {
    public static void assertThat(Predicate predicate, Object obj, String errorMsg) {
        boolean test = predicate.test(obj);
        if (!test)
            throw new RuntimeException(errorMsg);
    }

    public static void assertThat(Predicate predicate, Object obj) {
        boolean test = predicate.test(obj);
        if (!test)
            throw new RuntimeException("assert error");
    }

    public static void assertEqual(Object expected, Object given) {
        boolean test = expected.equals(given);
        if (!test)
            throw new RuntimeException("assert error expected is: " + expected + " but given: " + given);
    }
}
