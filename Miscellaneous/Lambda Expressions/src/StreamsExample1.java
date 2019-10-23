import java.util.Arrays;
import java.util.List;

public class StreamsExample1 {

    // Streams: "assembly line" strategy to lists, rather than sequential operations.
    // For loops are still more efficient. Operate sequentially.

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Brontë", 45),
                new Person("Matthew", "Arnold", 39)
        );

        // forEach takes a consumer
        people.stream()
                .filter(p -> p.getLastName().startsWith("C"))
                .forEach(p -> System.out.println(p.getFirstName()));

        long count1 = people.stream()
                .filter(p -> p.getLastName().startsWith("C"))
                .count(); // terminal operation

        long count2 = people.parallelStream()
                .filter(p -> p.getLastName().startsWith("C"))
                .count(); // terminal operation


        System.out.println("Number of items in list that start with the letter 'C': " + count1);
        System.out.println("Number of items in list that start with the letter 'C': " + count2);
    }
}
