import java.util.concurrent.CompletableFuture;

public class Main1 {
    public static void main(String[] args) {
        System.out.println("Starting process");
        System.out.println("In the thread in Main " + Thread.currentThread().getName());
        //supplyAsync = takes a supplier -> can be a method
        CompletableFuture.supplyAsync(() -> longNetworkProcess(5))
                //thenApply = to perform some operations on the object/objects
                            //i think its like a map in streams?
                .thenApply(integer -> performSomeOperations(integer))
                .thenApply(integer -> performSomeOperations(integer))
                //thenAccpet = after everything is done processing we call thenAccept to do something with the new object
                            //i think its like a forEach in streams?
                .thenAccept(integer -> {
                    System.out.println("Completed with value");
                    System.out.println(integer);
                })
                //thenRun = used to run something after the previous operations are completed
                .thenRun(() -> aVeryLongProcess())
                .thenRun(() -> aVeryLongProcess());
        //runAsync = if you want to do something async but don't care about the potential outcome
        CompletableFuture.runAsync(() -> aVeryLongProcess());
        System.out.println("Process called --->>> ");
        System.out.println("Sleeping for a little bit");
        sleepALittle();
        System.out.println("Done sleeping");

    }

    private static int longNetworkProcess(int i) {
        System.out.println("In the thread in longNetworkProcess " + Thread.currentThread().getName());
        sleepALittle();
        return  i*10;
    }

    public static void aVeryLongProcess(){
        System.out.println("In the thread in aVeryLongProcess " + Thread.currentThread().getName());
        sleepALittle();
        System.out.println("Done with the long processing");
    }

    public static void sleepALittle(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int performSomeOperations(int value){
        System.out.println("In the thread in performSomeOperations " + Thread.currentThread().getName());
        if(value % 2 == 0){
            value++;
        }else {
            value = value + 3;
        }
        return value;
    }
}
