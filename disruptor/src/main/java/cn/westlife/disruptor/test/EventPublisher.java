package cn.westlife.disruptor.test;

/**
 * @author westlife
 * @date 2018/6/6 23:29
 */
public interface EventPublisher {

    void start();

    void publish(Integer id, String message) throws InterruptedException;
}
