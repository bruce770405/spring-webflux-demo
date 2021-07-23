package tw.com.bruce.springdemo.handler.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tw.com.bruce.springdemo.entity.UserEntity;
import tw.com.bruce.springdemo.handler.BasicHandler;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * basic handler impl
 *
 * @author BruceHsu
 * @version 2019-04-21
 * @see
 */
@Service(value = "BasicService")
public class BasicHandlerImpl implements BasicHandler {

    /**
     * demo db user data.
     */
    private final Map<String, UserEntity> data = new ConcurrentHashMap<>();

    /**
     * 收集data內的user entity 資料 flux.
     * @return
     */
    @Override
    public Flux<UserEntity> list() {
        return Flux.fromIterable(this.data.values());
    }

    /**
     * 尋找flux內傳入的所有id, 對應的userEntity包成flux往下流.
     * @param ids
     * @return
     */
    @Override
    public Flux<UserEntity> getById(Flux<String> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(this.data.get(id)));
    }

    /**
     * 尋找單一id是否有user資料
     * @param id
     * @return
     */
    @Override
    public Mono<UserEntity> getById(String id) {
        return Mono.justOrEmpty(this.data.get(id));
    }

    @Override
    public Mono<UserEntity> createOrUpdate(UserEntity user) {
        return Mono.defer(() -> Mono.just(Objects.requireNonNull(this.data.put(user.getId(), user))));
    }

    @Override
    public Mono<UserEntity> delete(String id) {
        return Mono.defer(() -> Mono.just(this.data.remove(id)));
    }
}
