import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Java8 {

	private void myPrintFunction(String s) {
		System.out.println(s);
	}

	public static void main(String args[]) {

		// 1. Streams
		System.out.println("1. Stream examples:");
		Collection<String> collection = Arrays.asList("a", "b", "c");
		Stream<String> streamOfCollection = collection.stream();
		System.out.println(collection);

		streamOfCollection.forEach(x -> System.out.print(x));
		System.out.println();

		// ----------------------------

		Stream<String> streamBuilder = Stream.<String>builder().add("a1").add("b2").add("c3").build();
		streamBuilder.forEach(x -> System.out.print(x));
		System.out.println();

		// ----------------------------

		Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);
		streamGenerated.forEach(x -> {
			System.out.print(x + " ");
		});
		System.out.println();

		// ----------------------------

		Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(15);
		streamIterated.forEach(x -> System.out.print(x + ", "));
		System.out.println();

		// ----------------------------

		// 1.1. the reduce method (combines the elements of a Stream)
		System.out.println();
		System.out.println("1.1. reduce method:");
		List<Integer> numberList = Arrays.asList(2, 3, 4, 5);
		int even = numberList.stream().filter(x -> x % 2 == 0).reduce(0, (subtotal, element) -> subtotal + element);
		System.out.println("Sum of all even numbers: " + even);
		assert even == 6;

		// 1.2. the map method
		System.out.println();
		System.out.println("1.2. map method:");
		List<Integer> squaresList = numberList.stream().map(x -> x * x).collect(Collectors.toList());
		System.out.println(squaresList);

		// 1.3. the map method
		System.out.println();
		System.out.println("1.3. filter method:");
		List<String> input = Arrays.asList("cd", "an", "", "", "wr", "qy", "", "tq");
		List<String> result = input.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		result.forEach(s -> System.out.print(s + " "));
		System.out.println();
		System.out.println(result);

		// 1.4. the sort method
		System.out.println();
		System.out.println("1.4. sort method:");
		Random random = new Random();
		random.ints().limit(5).sorted().forEach(System.out::println);

		// 1.5. Double Colon Operator (especially useful in conjunction with Streams)
		System.out.println();
		System.out.println("1.5. Double Colon Operator:");
		Stream<String> stream = Stream.of("wc", "vb", "wc", "W5", "cm", "E3");
		stream.forEach(s -> System.out.print(s + ", "));
		// equivalent to: stream.forEach(System.out::println); // <Class name>::<method
		// name>

		List<String> list = new ArrayList<String>();
		list.add("Aa");
		list.add("Bb");
		list.add("Cc");
		// list.forEach(i -> myPrintFunction(i)); // if static function
		list.forEach((new Java8())::myPrintFunction);

		// 2. Function Interface
		System.out.println();
		System.out.println("2. Function Interface:");
		Function<Integer, Double> half = a -> a / 2.0;
		System.out.println(half.apply(10));
		assert half.apply(10) == 5.0;

		// 3. Optional (a type-level solution for representing optional values instead
		// of null references)
		System.out.println();
		System.out.println("3. Optional:");
		Optional<String> empty = Optional.empty();
		System.out.println(empty.isPresent());

		String name = "some string";
		Optional<String> opt1 = Optional.of(name); // will throw a NullPointerException in case of a null value
		System.out.println(opt1.isPresent());

		// In the case where we expect some null values, we can use the ofNullable()
		// method:
		Optional<String> opt2 = Optional.ofNullable(name);
		System.out.println(opt2.isPresent());

		Optional<String> opt3 = Optional.ofNullable(null);
		System.out.println(opt3.isPresent());

		String nullName = null;
		System.out.println(Optional.ofNullable(nullName).orElse("john"));
	}
}