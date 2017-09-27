package test;
import bench_threads.Threadone;

public class MostThreadsTest {
    public void run() {
        for(int i = 0; i < 10000; i++) {
            new Threadone().start();
        }
    }
    public static void main(String[] argc) {
        new MostThreadsTest().run();
    }

}
