package com.ups.auth_server.model.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pierinamgm
 */

@Data @Getter @Setter
public class User {

    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String status;

}
