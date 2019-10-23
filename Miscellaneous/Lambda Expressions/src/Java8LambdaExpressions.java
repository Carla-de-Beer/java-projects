import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Java8LambdaExpressions {

    public static void main(String[] args) {

        // Functional interface
        System.out.println("Print text anonymous inner class:");
        Greeting myLambdaFunction = () -> System.out.println("Hello world!");
        myLambdaFunction.foo();

        printLambda(() -> System.out.println("Hello world!"));

        // ---------------------------------------------------------------
        // 1. Sort list, using a lambda expression (Java 8)
        // ---------------------------------------------------------------
        System.out.println("\n1. Sort list using a lambda expression:");

        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Brontë", 45),
                new Person("Matthew", "Arnold", 39)
        );

        Collections.sort(people, (o1, o2) -> o1.getLastName().compareTo(o2.getFirstName()));

        printConditionallyWithConsumer(people, p -> true, p -> System.out.println(p.toString()));

        // ---------------------------------------------------------------
        // 2. Print conditionally, using a Lambda expression (Java 8)
        // ---------------------------------------------------------------
        System.out.println("\n2. Print subset of people with surname starting with 'C', using lambda expression:");

        printConditionallyWithConsumer(people, p -> p.getLastName().startsWith("C"), p -> System.out.println(p.toString()));

        // ---------------------------------------------------------------
        // 3. Thread: Lambda expression (Java 8)
        // ---------------------------------------------------------------
        System.out.println("\n3. Start Thread with lambda expression:");

        Thread myLambdaThread = new Thread(() -> System.out.println("Printed lambda Runnable."));
        myLambdaThread.start();
    }

    private static void printLambda(Greeting greeting) {
        greeting.foo();
    }

    // Predicate<T> is an out-of-the-box interface to handle a test condition (no need to create own functional interface)
    private static void printConditionallyWithPredicate(List<Person> people, Predicate<Person> predicate) {
        for (Person p : people) {
            if (predicate.test(p))
                System.out.println(p.toString());
        }
    }

    private static void printConditionallyWithConsumer(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for (Person p : people) {
            if (predicate.test(p))
                consumer.accept(p);
        }
    }
}

@FunctionalInterface
interface Greeting {
    void foo();
}

@FunctionalInterface
interface Condition {
    boolean test(Person p);
}
