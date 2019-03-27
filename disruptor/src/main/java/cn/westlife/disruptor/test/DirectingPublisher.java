package cn.westlife.disruptor.test;

/**
 * @author westlife
 * @date 2018/6/6 23:36
 */
public class DirectingPublisher implements EventPublisher {

    private TestEvent event = new TestEvent();

    private TestHandler handler;

    public DirectingPublisher(TestHandler handler) {
        this.handler = handler;
    }

    @Override
    public void start() {

    }

    @Override
    public void publish(Integer id, String message) {
        event.setId(id);
        event.setMessage(message);
        handler.process(event);
    }
}
