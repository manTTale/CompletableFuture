import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Main4 {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> getValue())
                //if this completable future takes more than 1 second then we return 99 and forget about it
//                .completeOnTimeout(subValue(),1, TimeUnit.SECONDS)
                //alternatively you can use orTimeout
                //if timelimit of 1 second is reached without getting a value the whole completable future will crash
                .orTimeout(1, TimeUnit.SECONDS)
                .thenAccept(System.out::println).join();
        sleepALittle();
        System.out.println("Main done sleeping...");



    }

    public static int getValue(){
        sleepALittleMore();
        return 5;
    }
    public static void sleepALittle() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleepALittleMore() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int subValue(){
        return 99;
    }
}
