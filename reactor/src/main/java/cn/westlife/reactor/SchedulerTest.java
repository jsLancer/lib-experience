package cn.westlife.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author zhuangchao
 * @date 2018-06-25 18:15
 */
public class SchedulerTest {


    public static void main(String[] args) {
        // 当前线程，通过 Schedulers.immediate()方法来创建。
        // 单一的可复用的线程，通过 Schedulers.single()方法来创建。
        // 使用弹性的线程池，通过 Schedulers.elastic()方法来创建。线程池中的线程是可以复用的。当所需要时，新的线程会被创建。如果一个线程闲置太长时间，则会被销毁。该调度器适用于 I/O 操作相关的流的处理。
        // 使用对并行操作优化的线程池，通过 Schedulers.parallel()方法来创建。其中的线程数量取决于 CPU 的核的数量。该调度器适用于计算密集型的流的处理。
        // 使用支持任务调度的调度器，通过 Schedulers.timer()方法来创建。
        // 已有的 ExecutorService 对象中创建调度器，通过 Schedulers.fromExecutorService()方法来创建。

        // 通过 publishOn()和 subscribeOn()方法可以切换执行操作的调度器。
        // 其中 publishOn()方法切换的是操作符的执行方式，而 subscribeOn()方法切换的是产生流中元素时的执行方式。

        Flux.create(sink -> {
            // parallel-1
            sink.next(Thread.currentThread().getName());
            sink.complete();
        }).publishOn(Schedulers.single())
                // [single-1] parallel-1
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                // [elastic-2] [single-1] parallel-1
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);
        test();

    }


    public static void test() {
        Flux.range(1, 10)
                .map(e -> {
                    System.out.println(Thread.currentThread().getName());
                    return e.toString();
                })
                .publishOn(Schedulers.parallel())
                .filter(e -> {
                    System.out.println(Thread.currentThread().getName());
                    return !e.equals("5");
                })
                .subscribeOn(Schedulers.elastic())
                .subscribe(e -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(e);
                });
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
