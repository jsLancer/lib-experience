package cn.westlife.disruptor.test;

/**
 * @author westlife
 * @date 2018/6/6 23:33
 */
public interface CounterTracer {

    boolean count();

    void start();

    long getMilliTimeSpan();

    void waitForReached() throws InterruptedException;
}
