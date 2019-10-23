public class MethodReferenceExample1 {

    public static void main(String[] args) {
        Thread t = new Thread(MethodReferenceExample1::printMessage); // new Thread(() -> printMessage());
        t.start();
    }

    private static void printMessage() {
        System.out.println("Hello");
    }
}
