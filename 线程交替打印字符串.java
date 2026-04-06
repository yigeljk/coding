import java.util.concurrent.locks.LockSupport;

public class 线程交替打印字符串 {
    static Thread t1, t2;
    static int num = 1;

    public static void main(String[] args) {

        t1 = new Thread(() -> {
            while (num <= 10) {
                System.out.println("线程1：" + num++);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {
            while (num <= 10) {
                LockSupport.park();
                System.out.println("线程2：" + num++);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
    }
}