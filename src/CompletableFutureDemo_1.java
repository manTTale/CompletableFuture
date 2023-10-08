import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class CompletableFutureDemo_1 {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = calculateAsync();
        try {
            String result = completableFuture.get();
            System.out.println("Thread in main " + Thread.currentThread().getName());
            System.out.println(result);
            System.out.println("------------------");
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static CompletableFuture<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture();

        List<String> list = new ArrayList<>();
        list.add("Roberto");
        list.add("Stefan");
        list.add("Justin");

        Executors.newCachedThreadPool().submit(() -> {
            try {
                System.out.println("Thread in method " +  Thread.currentThread().getName());
                Thread.sleep(20);
                String joinedString = String.join(",", list);
                completableFuture.complete(joinedString);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
        return completableFuture;
    }
}