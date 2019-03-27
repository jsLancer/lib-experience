package cn.westlife.disruptor.test;

/**
 * @author westlife
 * @date 2018/6/6 23:29
 */
public class TestEvent {
    private Integer id;
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TestEvent{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}