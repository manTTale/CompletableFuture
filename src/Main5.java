import java.util.concurrent.CompletableFuture;

public class Main5 {
    public static void main(String[] args) {

        int userId=5;
        //thenCombine is used to combine the completable future getAsyncValue and getAsyncValue2
        //if one of the completable future finishes processing before the other, it will wait for the other one
        getValueAsync(userId).thenCombine(getValueAsync2(userId),(a,b) -> a+b)
                .thenAccept(integer -> System.out.println(integer));
        //thenCompose is used when you want for example to run getValueAsync and with the result from there you want to
        //start another completable future (getValueAsync2 in this case)
        getValueAsync(userId).thenCompose(integer -> getValueAsync2(integer))
                        .thenAccept(System.out::println);
        sleepLong();
    }

    public static CompletableFuture<Integer> getValueAsync(int value){
        return CompletableFuture.supplyAsync(() -> process(value));
    }

    public static CompletableFuture<Integer> getValueAsync2(int value){
        return CompletableFuture.supplyAsync(() -> process2(value));
    }

    public static int process(int x){
        sleep();
        return x*4;
    }

    public static int process2(int y){
        sleep();
        return y*10;
    }
    public static void sleep(){
        try{
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sleepLong(){
        try{
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
