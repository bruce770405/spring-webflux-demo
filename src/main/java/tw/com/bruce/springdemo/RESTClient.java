package tw.com.bruce.springdemo;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tw.com.bruce.springdemo.entity.UserEntity;

/**
 * client 測試
 *
 * @author: BruceHsu
 * @version: 2019-04-21
 * @see
 */
public class RESTClient {

    /**
     * 調用createdUser.block 方法的作用是等待請求完成並得到所產生的類User 的對象。
     * @Description: test.
     * @Param: [args]
     * @return: void
     */
    public static void main(final String[] args) {

        UserEntity user = new UserEntity();
        user.setId("1");
        user.setUserName("bruce");
        user.setMail("test@example.org");

        final WebClient client = WebClient.create("http://localhost:8080/basic");
        final Mono<UserEntity> createdUser = client.post()
                .uri("")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), UserEntity.class)
                .exchange()
                .flatMap(response -> response.bodyToMono(UserEntity.class));

        System.out.println(createdUser.block());
    }

}
