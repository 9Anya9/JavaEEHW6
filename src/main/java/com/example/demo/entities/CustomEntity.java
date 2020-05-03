package com.example.demo.entities;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Set;

@Getter
@ToString(callSuper = true)
public class CustomEntity extends User {

    private final String customAuthField;
    private final Set<BookEntity> favBooks;

    public CustomEntity (
            final String username,
            final String password,
            final List<? extends GrantedAuthority> authorities,
            final String customAuthField,
            final Set<BookEntity> favBooks) {
        super(username, password, authorities);
        this.customAuthField = customAuthField;
        this.favBooks = favBooks;
    }
}
