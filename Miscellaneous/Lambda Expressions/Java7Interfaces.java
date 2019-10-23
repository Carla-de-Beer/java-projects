import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Java7Interfaces {

    public static void main(String[] args) {

        // ---------------------------------------------------------------
        // 1. Sort list, using an anonymous inner class (Java 7)
        // ---------------------------------------------------------------
        System.out.println("\n1. Sort list using anonymous inner class:");

        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Brontë", 45),
                new Person("Matthew", "Arnold", 39)
        );

        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getFirstName());
            }
        });

        printConditionally(people, p -> true);

        // ---------------------------------------------------------------
        // 2. Print conditionally, using an anonymous inner class (Java 7)
        // ---------------------------------------------------------------
        System.out.println("\n2. Print subset of people with surname starting with 'C', using anonymous inner class:");

        printConditionally(people, new Condition() {
            @Override
            public boolean test(Person p) {
                return p.getLastName().startsWith("C");
            }
        });

        // ---------------------------------------------------------------
        // 3. Thread: Anonymous inner class (Java 7)
        // ---------------------------------------------------------------
        System.out.println("\n3. Start Thread with anonymous inner class:");

        Thread myTread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printed inside Runnable.");
            }
        });

        myTread.start();
    }

    private static void printLambda(Greeting greeting) {
        greeting.foo();
    }

    private static void printConditionally(List<Person> people, Condition condition) {
        for (Person p : people) {
            if (condition.test(p))
                System.out.println(p.toString());
        }
    }
}