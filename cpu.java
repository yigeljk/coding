public class cpu {
    public static void main(String[] args) {
//        // 开 4 个线程，占满 4 核 CPU
        for (int i = 0; i < 16; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                }
            },"线程"+i).start();
        }
//        double a=0.1;
//        double b=0.2;
//        double c=0.3;
//        System.out.println(c*10);
//        System.out.println(a+b+a+b);
    }
}