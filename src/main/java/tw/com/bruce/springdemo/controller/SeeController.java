package tw.com.bruce.springdemo.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 服務器推播事件（Server-Sent Events，SSE）controller
 *
 * @author: BruceHsu
 * @version: 2019-04-21
 * @see
 */
@RestController
@RequestMapping("/sse")
public class SeeController {

    /**
     * Duration的內部實現與Instant類似，也是包含兩部分：seconds表示秒，nanos表示納秒。
     * 兩者的區別是
     * Instant用於表示一個時間戳（或者說是一個時間點），
     * 而Duration表示一個時間段，
     * 所以Duration類中不包含now()靜態方法。可以通過Duration.between()方法創建
     *
     * @Description:
     * @Param: []
     * @return: reactor.core.publisher.Flux<org.springframework.http.codec.ServerSentEvent   <   java.lang.Integer>>
     */
    @GetMapping("/randomNumbers")
    public Flux<ServerSentEvent<Integer>> randomNumbers() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(Long.toString(data.getT1()))
                        .data(data.getT2())
                        .build());
    }
}
