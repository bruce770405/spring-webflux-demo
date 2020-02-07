
package tw.com.bruce.springdemo.controller;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tw.com.bruce.springdemo.entity.UserEntity;
import tw.com.bruce.springdemo.handler.BasicHandler;

import java.util.Objects;

/**
 * @program: reactiveSpringDemo
 * @description: 基本demo controller
 * @author: BruceHsu
 * @create: 2019-04-21 15:31
 */
@RestController
@RequestMapping("/basic")
public class BasicController {

    private final BasicHandler handler;

    @Autowired
    public BasicController(final BasicHandler handler) {
        this.handler = handler;
    }

    /**
     * @Description:
     * @return: void
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
    @ExceptionHandler(ResourceNotFoundException.class)
    public void notFound() {
    }

    /**
     * @Description: 測試單點是否錯誤.
     * @return: reactor.core.publisher.Mono<java.lang.String>
     */
    @GetMapping("")
    public Mono<String> heath() {
        return Mono.just("Hello project");
    }

    /**
     * @Description: api 回傳 user list.
     * @return: reactor.core.publisher.Flux<tw.com.bruce.springdemo.entity.UserEntity>
     */
    @GetMapping("/list")
    public Flux<UserEntity> list() {
        return this.handler.list();
    }

    /**
     * @Description: 搜尋 id 的使用者資料.
     * @Param: [id]
     * @return: reactor.core.publisher.Mono<tw.com.bruce.springdemo.entity.UserEntity>
     */
    @GetMapping("/{id}")
    public Mono<UserEntity> getById(@PathVariable("id") final String id) {
        return this.handler.getById(id);
    }

    /**
     * @Description: 
     * @Param: [user]
     * @return: reactor.core.publisher.Mono<tw.com.bruce.springdemo.entity.UserEntity>
     */
    @PostMapping("/crate")
    public Mono<UserEntity> create(@RequestBody final UserEntity user) {
        return this.handler.createOrUpdate(user);
    }

    /**
     * @Description: 
     * @Param: [id, user]
     * @return: reactor.core.publisher.Mono<tw.com.bruce.springdemo.entity.UserEntity>
     */
    @PutMapping("/{id}")
    public Mono<UserEntity> update(@PathVariable("id") final String id, @RequestBody final UserEntity user) {
        Objects.requireNonNull(user);
        user.setId(id);
        return this.handler.createOrUpdate(user);
    }

    /**
     * @Description: 刪除 user id 的資料.
     * @Param: [id]
     * @return: reactor.core.publisher.Mono<tw.com.bruce.springdemo.entity.UserEntity>
     */
    @DeleteMapping("/{id}")
    public Mono<UserEntity> delete(@PathVariable("id") final String id) {
        return this.handler.delete(id);
    }
}
