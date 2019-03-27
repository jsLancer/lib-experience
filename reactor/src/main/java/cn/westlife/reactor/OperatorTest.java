package cn.westlife.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

/**
 * @author zhuangchao
 * @date 2018-06-25 16:58
 */
public class OperatorTest {

    public static void main(String[] args) {

//        buffer();
//
//        filter();
//
//        window();

//        zipWith();

//        take();

//        reduce();

//        merge();

        flatMap();

//        combineLatest();

    }


    public static void buffer() {
        // buffer 和 bufferTimeout
        // 这两个操作符的作用是把当前流中的元素收集到集合中，并把集合对象作为流中的新元素。在进行收集时可以指定不同的条件：所包含的元素的最大数量或收集的时间间隔。
        // 方法 buffer()仅使用一个条件，而 bufferTimeout()可以同时指定两个条件。指定时间间隔时可以使用 Duration 对象或毫秒数

        Flux.range(1, 100).buffer(20)
                .subscribe(System.out::println);

        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
    }

    public static void filter() {
        // 对流中包含的元素进行过滤，只留下满足 Predicate 指定条件的元素
        Flux.range(1, 10).filter(i -> i % 2 == 0)
                .subscribe(System.out::println);
    }


    public static void window() {
        // window 操作符是把当前流中的元素收集到另外的 Flux 序列中，因此返回值类型是 Flux<Flux<T>>
        Flux.range(1, 100).window(20).subscribe(System.out::println);
        Flux.interval(Duration.ofMillis(100)).window(Duration.ofMillis(1001)).take(2).toStream().forEach(System.out::println);
    }

    public static void zipWith() {
        // zipWith 操作符把当前流中的元素与另外一个流中的元素按照一对一的方式进行合并。在合并时可以不做任何处理，由此得到的是一个元素类型为 Tuple2 的流；
        // 也可以通过一个 BiFunction 函数对合并的元素进行处理，所得到的流的元素类型为该函数的返回值。

        Flux.just("a", "b", "e").zipWith(Flux.just("c", "d", "f"))
                .subscribe(System.out::println);

        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
                .subscribe(System.out::println);
    }

    public static void take() {
        // take 系列操作符用来从当前流中提取元素
        Flux.range(1, 1000).take(10).subscribe(System.out::println);
        Flux.range(1, 1000).takeLast(10).subscribe(System.out::println);
        Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println);
        Flux.range(1, 1000).takeUntil(i -> i == 10).subscribe(System.out::println);
        Flux.range(1, 1000).take(Duration.ofMillis(10)).subscribe(System.out::println);
    }


    public static void reduce() {
        // reduce 和 reduceWith 操作符对流中包含的所有元素进行累积操作，得到一个包含计算结果的 Mono 序列。累积操作是通过一个 BiFunction 来表示的。
        // 在操作时可以指定一个初始值。如果没有初始值，则序列的第一个元素作为初始值。
        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);
    }

    public static void merge() {
        // merge 和 mergeSequential 操作符用来把多个流合并成一个 Flux 序列。
        // 不同之处在于 merge 按照所有流中元素的实际产生顺序来合并，
        // 而 mergeSequential 则按照所有流被订阅的顺序，以流为单位进行合并。
        Flux.merge(Flux.interval(Duration.ofMillis(0), Duration.ofMillis(1000)).take(5),
                Flux.interval(Duration.ofMillis(500), Duration.ofMillis(1000)).take(5))
                .toStream()
                .forEach(System.out::println);

        Flux.mergeSequential(Flux.interval(Duration.ofMillis(0), Duration.ofMillis(1000)).take(5),
                Flux.interval(Duration.ofMillis(500), Duration.ofMillis(1000)).take(5))
                .toStream()
                .forEach(System.out::println);

    }

    public static void flatMap() {
        // flatMap 和 flatMapSequential 操作符把流中的每个元素转换成一个流，再把所有流中的元素进行合并。

        Flux.just(5, 10).flatMap(x -> Flux.interval(Duration.ofMillis(x * 100), Duration.ofMillis(100)).take(x))
                .toStream()
                .forEach(System.out::println);
    }

    public static void combineLatest() {
        // combineLatest 操作符把所有流中的最新产生的元素合并成一个新的元素，作为返回结果流中的元素。
        // 只要其中任何一个流中产生了新的元素，合并操作就会被执行一次，结果流中就会产生新的元素。
        Flux.combineLatest(
                Arrays::toString,
                Flux.interval(Duration.ofMillis(100)).take(5),
                Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5)
        ).toStream().forEach(System.out::println);

    }

}
