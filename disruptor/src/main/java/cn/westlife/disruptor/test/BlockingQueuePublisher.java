package cn.westlife.disruptor.test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author westlife
 * @date 2018/6/6 23:31
 */
public class BlockingQueuePublisher implements EventPublisher {

    private ArrayBlockingQueue<TestEvent> queue;
    private TestHandler handler;

    public BlockingQueuePublisher(int maxEventSize, TestHandler handler) {
        this.queue = new ArrayBlockingQueue<TestEvent>(maxEventSize);
        this.handler = handler;
    }


    @Override
    public void start() {
        new Thread(() -> {
            TestEvent evt;
            while (true) {
                try {
                    evt = queue.take();
                    if (evt != null && handler.process(evt)) {
                        //完成后自动结束处理线程；
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void publish(Integer id, String message) throws InterruptedException {
        TestEvent evt = new TestEvent();
        evt.setId(id);
        evt.setMessage(message);
        queue.put(evt);
    }
}
