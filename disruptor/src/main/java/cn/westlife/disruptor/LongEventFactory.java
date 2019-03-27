package cn.westlife.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author westlife
 * @date 2018/6/3 13:41
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
