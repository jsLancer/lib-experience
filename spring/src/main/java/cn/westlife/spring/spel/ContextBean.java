package cn.westlife.spring.spel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zhuangchao
 * @date 2019/4/3 15:24
 */
public class ContextBean implements Serializable {

    private Long id;

    private String name;

    private List<Integer> list;

    private Map<Integer, String> map;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }
}
