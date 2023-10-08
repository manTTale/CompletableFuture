import java.util.concurrent.CompletableFuture;

public class Main2 {
    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        int value=7;
        getReady(completableFuture);
        getReady2(completableFuture);
        completableFuture.complete(value);

    }
    public static void getReady(CompletableFuture<Integer> future){
        future.thenApply(integer -> integer*5)
                .thenApply(integer -> integer + 20)
                .thenAccept(integer -> System.out.println(integer));
    }

    public static void getReady2(CompletableFuture<Integer> future){
        future.thenApply(integer -> integer*5)
                .thenApply(integer -> integer * 100)
                .thenAccept(integer -> System.out.println(integer));
    }
}
