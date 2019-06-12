package cn.westlife.spring.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuangchao
 * @date 2019/4/3 11:45
 */
public class SpelExpression {

    public static void main(String[] args) {
//        simpleSpel();
//        contextSpel();
//        mapSpel();
        testSpel();
    }

    private static void testSpel() {
        ExpressionParser parser = new SpelExpressionParser();

        Expression hello = parser.parseExpression("'Hello'.startsWith('H') ? 'Hello' : 'hi'");
        String value = hello.getValue(String.class);
        System.out.println(value);

    }

    public static void mapSpel() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(map);

        Expression expression = parser.parseExpression("[1]");
        System.out.println(expression.getValue(context, String.class));
    }


    public static void contextSpel() {
        ContextBean bean = new ContextBean();
        bean.setId(1L);
        bean.setName("context test");
        bean.setList(Arrays.asList(1, 2, 3));
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        bean.setMap(map);

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(bean);

        Expression id = parser.parseExpression("id");
        System.out.println(id.getValue(context, Long.class));

        Expression name = parser.parseExpression("name");
        System.out.println(name.getValue(context, String.class));

        Expression list = parser.parseExpression("list[1]");
        System.out.println(list.getValue(context, Integer.class));

        Expression mapValue = parser.parseExpression("map[1]");
        System.out.println(mapValue.getValue(context, String.class));

        Expression obj = parser.parseExpression("map");
        System.out.println(obj.getValue(context, Map.class));

        Expression expression = parser.parseExpression("'http:'.concat(map[1])");
        System.out.println(expression.getValue(context, String.class));

    }


    private static void simpleSpel() {
        ExpressionParser parser = new SpelExpressionParser();

        Expression hello = parser.parseExpression("'Hello'");
        String value = hello.getValue(String.class);
        System.out.println(value);

        Expression concat = parser.parseExpression("'Hello '.concat('SpEL')");
        String concatValue = concat.getValue(String.class);
        System.out.println(concatValue);

        Expression plus = parser.parseExpression("2 * 5");
        System.out.println(plus.getValue(Integer.class));

        Expression bool = parser.parseExpression("5 < 9");
        System.out.println(bool.getValue(Boolean.class));

        Expression op = parser.parseExpression("'' != null ? 'true' : 'false'");
        System.out.println(op.getValue(String.class));
    }

}
