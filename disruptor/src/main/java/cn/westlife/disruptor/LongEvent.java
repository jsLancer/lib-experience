package cn.westlife.disruptor;

/**
 * @author westlife
 * @date 2018/6/3 13:41
 */
public class LongEvent {

    private Long value;

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
