package cn.westlife.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author westlife
 * @date 2018/6/3 13:43
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("Event:" + longEvent.getValue());
    }
}
