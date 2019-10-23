public class ThisReferenceExample {

    public static void main(String[] args) {
        ThisReferenceExample thisReferenceExample = new ThisReferenceExample();
        // System.out.println(this); // can't use this inside a static context // this will not work

        thisReferenceExample.doProcess(10, new Process() {
            @Override
            public void process(int i) {
                System.out.println("Value of i is: " + i);
                // working on an instance of this inside object
                // this-value changes inside the anonymous inner class
                System.out.println(this);
            }

            @Override
            public String toString() {
                return "This is an anonymous inner class.";
            }
        });

        // This is where lambdas differ from anonymous inner classes
        thisReferenceExample.doProcess(10, i -> {
            System.out.println("Value of i is: " + i);
            // System.out.println(this); // can't use this inside a static context // this will not work
        });

        thisReferenceExample.execute();
    }

    private void doProcess(int i, Process p) {
        p.process(i);
    }

    private void execute() {
        doProcess(10, i -> {
            System.out.println("Value of i is: " + i);
            // this = instance on which execute method has been called => ThisReferenceExample
            // this-value not changed by lambda expression
            System.out.println(this);
        });
    }

    @Override
    public String toString() {
        return "This is the main ThisReferenceExample class instance.";
    }
}
