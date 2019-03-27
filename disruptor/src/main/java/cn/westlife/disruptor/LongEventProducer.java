package cn.westlife.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * @author westlife
 * @date 2018/6/3 17:04
 */
public class LongEventProducer {

    private RingBuffer<LongEvent> ringBuffer;

    private static Translator TRANSLATOR = new Translator();

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(Long data) {
        //先从 RingBuffer 获取下一个可以写入的事件的序号
        long sequence = ringBuffer.next();
        try {
            //获取对应的事件对象，将数据写入事件对象
            LongEvent event = ringBuffer.get(sequence);
            event.setValue(data);
        } finally {
            //发布事件
            ringBuffer.publish(sequence);
        }
    }

    public void publish(Long data) {
        ringBuffer.publishEvent(TRANSLATOR, data);
    }

    //数据 --> 事件
    static class Translator implements EventTranslatorOneArg<LongEvent, Long> {
        @Override
        public void translateTo(LongEvent longEvent, long l, Long aLong) {
            longEvent.setValue(aLong);
        }
    }
}
