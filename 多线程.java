import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class 多线程 {
    public static void main(String[] args) throws Exception{
        MyThread thread1=new MyThread();
        thread1.start();


        Task task=new Task();
        Thread thread2=new Thread(task);
        thread2.start();

        MyCallable mycallable=new MyCallable();
        FutureTask<String> futureTask=new FutureTask<>(mycallable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());



        for(int i=0;i<10;i++){
            System.out.println("主线程：" + Thread.currentThread().getName());
        }
     }

}

class MyCallable implements Callable<String>{
    @Override
    public String call() throws Exception{
        for(int i=0;i<10;i++) {
            System.out.println("主线程：" + Thread.currentThread().getName());
        }
        return "我是返回值";
    }
}

class  Task implements Runnable{
    @Override
    public void run(){
        for(int i=0;i<10;i++) {
            System.out.println("runnable任务正在跑->主线程：" + Thread.currentThread().getName());
        }
    }

}

class MyThread extends Thread{
    @Override
    public void  run(){
        for(int i=0;i<10;i++) {
            System.out.println("主线程：" + Thread.currentThread().getName());
        }
    }
}
