package tw.com.bruce.springdemo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tw.com.bruce.springdemo.entity.UserEntity;
import tw.com.bruce.springdemo.handler.BasicHandler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * 測試 controller 狀況
 *
 * @author BruceHsu
 * @version 2019-04-21
 * @see
 */
@RunWith(SpringRunner.class)
@WebFluxTest(controllers = BasicController.class)
public class BasicControllerTests {

	@MockBean
	private BasicHandler basicHandler;

	@Autowired
	private WebTestClient webClient;

	@Test
	public void testCreateUser() {
		final UserEntity user = new UserEntity();
		user.setUserName("bruce");
		user.setMail("test@example.org");

		doReturn(Mono.just(user)).when(basicHandler).createOrUpdate(any(UserEntity.class));

		WebTestClient.BodyContentSpec spec = webClient.post().uri("/basic/create")
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(user), UserEntity.class)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody().jsonPath("userName").isEqualTo("bruce");

		verify(basicHandler, times(1)).createOrUpdate(any(UserEntity.class));
	}

}
