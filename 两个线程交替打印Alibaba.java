
    import java.util.concurrent.locks.LockSupport;

    public class 两个线程交替打印Alibaba {

        static Thread t1, t2;

        static String a = "hloaiaa";
        static String b = "el,lbb";

        static StringBuilder result = new StringBuilder();

        static int i = 0, j = 0;

        public static void main(String[] args) {

            t1 = new Thread(() -> {
                while (i < a.length()) {
                    result.append(a.charAt(i++));
                    sleep1s();

                    LockSupport.unpark(t2); // 唤醒 t2

                    if (j < b.length()) {  // 避免最后死锁
                        LockSupport.park();
                    }
                }
            });

            t2 = new Thread(() -> {
                while (j < b.length()) {
                    LockSupport.park();   // 等待 t1

                    result.append(b.charAt(j++));
                    sleep1s();

                    LockSupport.unpark(t1); // 唤醒 t1
                }
            });

            t1.start();
            t2.start();

            // 等待线程结束（简单处理）
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(result.toString());
        }

        private static void sleep1s() {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

