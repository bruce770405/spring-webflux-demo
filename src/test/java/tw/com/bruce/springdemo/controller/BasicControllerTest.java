package tw.com.bruce.springdemo.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import tw.com.bruce.springdemo.entity.UserEntity;

/**
 * 測試 controller 狀況
 *
 * @author: BruceHsu
 * @version: 2019-04-21
 * @see
 */
public class BasicControllerTest {

    private final WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();

    @Test
    public void testCreateUser() throws Exception {
        final UserEntity user = new UserEntity();
        user.setUserName("bruce");
        user.setMail("test@example.org");
        WebTestClient.BodyContentSpec spec = client.post().uri("/basic")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), UserEntity.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody().jsonPath("userName").isEqualTo("bruce");
    }

    /**
     * 調用createdUser.block 方法的作用是等待請求完成並得到所產生的類User 的對象。
     *
     * @Description: test.
     * @Param: [args]
     * @return: void
     */
    @Test
    public void testBasic(final String[] args) {

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
