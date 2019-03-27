package cn.westlife.disruptor.test;

/**
 * @author westlife
 * @date 2018/6/6 23:21
 */
public class ArrayBlockingQueueTest {

    public static final int COUNT = 1024 * 1024;

    public static void main(String[] args) {

        CounterTracer tracer = new SimpleTracer(COUNT);

        TestHandler handler = new TestHandler(tracer);

        EventPublisher publisher = new BlockingQueuePublisher(COUNT, handler);

        publisher.start();
        tracer.start();

        try {

            for (int i = 0; i < COUNT; i++) {
                publisher.publish(i, i + "");
            }
        } catch (Exception e) {
            e.getMessage();
        }

        try {
            tracer.waitForReached();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(tracer.getMilliTimeSpan());
    }

}


