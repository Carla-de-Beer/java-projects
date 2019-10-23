import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MethodReferenceExample2 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Brontë", 45),
                new Person("Matthew", "Arnold", 39)
        );

        Collections.sort(people, (o1, o2) -> o1.getLastName().compareTo(o2.getFirstName()));

        // Method reference takes the place of a consumer
        printConditionallyWithConsumer(people, p -> true, System.out::println); // p -> method(p)
    }

    private static void printConditionallyWithConsumer(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for (Person p : people) {
            if (predicate.test(p))
                consumer.accept(p);
        }
    }
}

