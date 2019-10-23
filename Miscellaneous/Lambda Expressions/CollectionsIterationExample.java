import java.util.Arrays;
import java.util.List;

public class CollectionsIterationExample {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Brontë", 45),
                new Person("Matthew", "Arnold", 39)
        );

        // Loop with a for-in loop
        for (Person p : people) {
            System.out.println(p);
        }

        // OR:

        // makes it easier for the processor to run in multiple threads because order is not guaranteed
        people.forEach(System.out::println);
    }
}
