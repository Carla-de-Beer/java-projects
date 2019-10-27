public class ClosuresExample {

    public static void main(String[] args) {
        int a = 20;
        int b = 20; // final by default when used inside closure

        doProcess(a, new Process() {
            @Override
            public void process(int i) {
                // compile expects b to be effectively final
                System.out.println(i + b);
            }
        });

        doProcess(a, i -> System.out.println(i + b));
    }

    private static void doProcess(int i, Process p) {
        p.process(i);
    }
}

interface Process {
    void process(int i);
}
