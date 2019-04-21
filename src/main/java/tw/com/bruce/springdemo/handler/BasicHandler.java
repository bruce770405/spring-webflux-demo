package tw.com.bruce.springdemo.handler;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tw.com.bruce.springdemo.entity.UserEntity;

/**
 * handler 層 interface.
 *
 * @author: BruceHsu
 * @version: 2019-04-21
 * @see
 */
public interface BasicHandler {

    /**
     * @Description: 
     * @Param: []
     * @return: reactor.core.publisher.Flux<tw.com.bruce.springdemo.entity.UserEntity>
     */
    Flux<UserEntity> list();

    /**
     * @Description: 
     * @Param: [ids]
     * @return: reactor.core.publisher.Flux<tw.com.bruce.springdemo.entity.UserEntity>
     */
    Flux<UserEntity> getById(final Flux<String> ids);

    /**
     * @Description: 
     * @Param: [id]
     * @return: reactor.core.publisher.Mono<tw.com.bruce.springdemo.entity.UserEntity>
     */
    Mono<UserEntity> getById(final String id);

    /**
     * @Description: 
     * @Param: [user]
     * @return: reactor.core.publisher.Mono<tw.com.bruce.springdemo.entity.UserEntity>
     */
    Mono<UserEntity> createOrUpdate(final UserEntity user);

    /**
     * @Description: 
     * @Param: [id]
     * @return: reactor.core.publisher.Mono<tw.com.bruce.springdemo.entity.UserEntity>
     */
    Mono<UserEntity> delete(final String id);

}
