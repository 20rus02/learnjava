package su.zhaik.memory;


import java.lang.management.ManagementFactory;

/**
 * VM options -Xmx512m -Xms512m
 * <p>
 * Runtime runtime = Runtime.getRuntime();
 * long mem = runtime.totalMemory() - runtime.freeMemory();
 * <p>
 * System.gc()
 * <p>
 * jconsole, connect to pid
 */
@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class Main {
    public static void main(String... args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int size = 20_000_000;

        System.out.println("Starting the loop");
        while (true) {
            System.gc();
            Thread.sleep(1000);
            Runtime runtime = Runtime.getRuntime();
            long mem = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Memory in use: " + mem);

            Object[] array = new Object[size];
            System.out.println("New array of size: " + array.length + " created");
            for (int i = 0; i < size; i++) {
//                array[i] = new Object();
                array[i] = new String(""); //String pool
//                array[i] = new String(new char[0]); //without String pool
//                array[i] = new MyClass();
//                array[i] = new Integer(11111);
            }
            System.out.println("Created " + size + " objects.");
            Thread.sleep(1000); //wait for 1 sec
        }
    }

    private static class MyClass {
        private int i = 0;
        private long l = 1;
    }
}
