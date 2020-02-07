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
@RequestMapping("/see")
public class SeeController {

    /**
     * @Description:
     * @Param: []
     * @return: reactor.core.publisher.Flux<org.springframework.http.codec.ServerSentEvent < java.lang.Integer>>
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
