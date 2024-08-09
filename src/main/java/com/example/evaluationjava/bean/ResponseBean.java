package com.example.evaluationjava.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBean {
    private String id;
    private String created;
    private String modified;
    private String lastLogin;
    private String token;
    private String status;

}
