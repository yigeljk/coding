
    import java.util.concurrent.locks.LockSupport;

    public class 两个线程交替打印Alibaba {
        public static  final String s="alibaba";
        public static  int index=0;
        public static final  Object lock=new Object();

        public static boolean printA=true;
        public static void main(String[] args) {
            new Thread(()->{
                synchronized (lock){
                    while(index<s.length()){
                        System.out.println(Thread.currentThread().getName()+":"+s.charAt(index));
                        index++;
                        lock.notify();  //唤醒另外一个线程
                        if(index<s.length()){
                            try{
                                lock.wait();
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            },"线程1").start();

            new Thread(()->{
                synchronized (lock){
                    while(index<s.length()){
                        System.out.println(Thread.currentThread().getName()+":"+s.charAt(index));
                        index++;
                        lock.notify();
                        if(index<s.length()){
                            try{
                                lock.wait();
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            },"线程2").start();



        }
    }

