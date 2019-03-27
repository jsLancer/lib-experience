package cn.westlife.disruptor.test;

/**
 * @author westlife
 * @date 2018/6/6 23:32
 */
public class TestHandler {

    private CounterTracer tracer;

    public TestHandler(CounterTracer counterTracer) {
        this.tracer = counterTracer;
    }

    public boolean process(TestEvent event){
        return tracer.count();
    }

}
