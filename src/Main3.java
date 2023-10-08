import java.util.concurrent.CompletableFuture;

public class Main3 {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() ->getValue())
                .exceptionally(throwable -> handleError(throwable))
                .thenApply(integer -> getAnotherValue())
                .exceptionally(throwable -> 91)
                .thenAccept(integer -> System.out.println(integer)).join();
    }

    public static int getValue() {
        return 5;
    }

    public static int getAnotherValue() {
        blowUp();
        return 500;
    }
    public static void blowUp(){
        throw new RuntimeException();
    }

    public static int handleError(Throwable throwable){
        return 100;
    }
}
