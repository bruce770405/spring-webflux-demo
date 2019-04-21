package tw.com.bruce.springdemo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * User 資料 實體.
 *
 * @author: BruceHsu
 * @version: 2019-04-21
 * @see
 */
@Data
public class UserEntity implements Serializable {

    /** id. */
    private String id;

    /** user name. */
    private String userName;

    /** mail. */
    private String mail;
}
