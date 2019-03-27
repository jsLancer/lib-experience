package cn.westlife.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author zhuangchao
 * @date 2018-06-25 15:48
 */
public class FluxTest {

    public static void main(String[] args) {
//        simple();

//        generate();

//        create();

    }

    public static void create() {
        // create()方法与 generate()方法的不同之处在于所使用的是 FluxSink 对象
        // FluxSink 支持同步和异步的消息产生，并且可以在一次调用中产生多个元素。
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }

    public static void generate() {
        // generate()方法通过同步和逐一的方式来产生 Flux 序列
        // 序列的产生是通过调用所提供的 SynchronousSink 对象的 next()，complete()和 error(Throwable)方法来完成的
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);

        final Random random = new Random();

        // 状态对象会作为 generator 使用的第一个参数传入，可以在对应的逻辑中对该状态对象进行修改以供下一次生成时使用。
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }


    public static void simple() {
        // just()：可以指定序列中包含的全部元素。
        Flux.just("hello", "world").subscribe(System.out::println);
        // fromArray()，fromIterable()和 fromStream()：可以从一个数组、Iterable 对象或 Stream 对象中创建 Flux 对象。
        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::println);
        // empty()：创建一个不包含任何元素，只发布结束消息的序列。
        Flux.empty().subscribe(System.out::println);
        // error(Throwable error)：创建一个只包含错误消息的序列。
        // never()：创建一个不包含任何消息通知的序列
        // range(int start, int count)：创建包含从 start 起始的 count 个数量的 Integer 对象的序列。
        Flux.range(1, 10).subscribe(System.out::println);
        // interval(Duration period)和 interval(Duration delay, Duration period)：创建一个包含了从 0 开始递增的 Long 对象的序列。
        // 其中包含的元素按照指定的间隔来发布。除了间隔时间之外，还可以指定起始元素发布之前的延迟时间。
        // delay 表示延迟时间 period 表示间隔时间
        Flux.interval(Duration.of(1, ChronoUnit.SECONDS)).subscribe(System.out::println);

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
