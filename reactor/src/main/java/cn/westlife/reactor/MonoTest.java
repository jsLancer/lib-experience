package cn.westlife.reactor;

import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author zhuangchao
 * @date 2018-06-25 16:53
 */
public class MonoTest {

    public static void main(String[] args) {

        // fromCallable()、fromCompletionStage()、fromFuture()、fromRunnable()和 fromSupplier()：
        // 分别从 Callable、CompletionStage、CompletableFuture、Runnable 和 Supplier 中创建 Mono。

        // delay(Duration duration)和 delayMillis(long duration)：创建一个 Mono 序列，在指定的延迟时间之后，产生数字 0 作为唯一值。

        // ignoreElements(Publisher<T> source)：创建一个 Mono 序列，忽略作为源的 Publisher 中的所有元素，只产生结束消息。

        // justOrEmpty(Optional<? extends T> data)和 justOrEmpty(T data)：从一个 Optional 对象或可能为 null 的对象中创建 Mono。
        // 只有 Optional 对象中包含值或对象不为 null 时，Mono 序列才产生对应的元素。
        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);

    }
}
