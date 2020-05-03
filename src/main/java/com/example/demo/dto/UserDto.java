package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@ToString
public class UserDto {

    private Integer id;

    @NotNull(message = "Empty Custom Authentication!")
    private String customAuthField;

    @NotEmpty(message = "Empty login!")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
             message = "Login: at least 8 characters (min 1 letter and min 1 number).")
    private String login;

    @NotEmpty(message = "Empty password!")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
             message = "Password: at least 8 characters (min 1 letter and min 1 number).")
    private String password;


}
