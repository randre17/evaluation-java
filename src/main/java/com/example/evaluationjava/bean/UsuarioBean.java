package com.example.evaluationjava.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioBean {
    private String name;
    private String email;
    private String password;
    private List<TelefonoBean> phones;
}
