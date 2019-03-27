package cn.westlife.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author westlife
 * @date 2018/6/3 13:44
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        EventFactory<LongEvent> factory = new LongEventFactory();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        int ringBufferSize = 1024 * 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, executorService,
                ProducerType.SINGLE, new YieldingWaitStrategy());


        EventHandler<LongEvent> eventHandler = new LongEventHandler();

        disruptor.handleEventsWith(eventHandler);

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer eventProducer = new LongEventProducer(ringBuffer);

        for (int i = 0; i < 100; i++) {
//            eventProducer.onData((long) i);
            eventProducer.publish((long) i);
        }

        Thread.sleep(1000);

        disruptor.shutdown();
        executorService.shutdown();
    }

}
