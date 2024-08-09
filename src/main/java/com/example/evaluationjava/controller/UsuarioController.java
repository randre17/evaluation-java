package com.example.evaluationjava.controller;

import com.example.evaluationjava.bean.ResponseBean;
import com.example.evaluationjava.bean.UsuarioBean;
import com.example.evaluationjava.exception.ErrorException;
import com.example.evaluationjava.exception.RecordNotFoundException;
import com.example.evaluationjava.model.UsuarioEntity;
import com.example.evaluationjava.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.example.evaluationjava.util.EvaluationJavaUtils.ESTADO_ACTIVO;

@RestController
@RequestMapping("/evaluation-java/v1")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> getUsers(){
        System.out.println("Start - getUsers");
        List<UsuarioEntity> list = service.getAllUsers();
        return new ResponseEntity<List<UsuarioEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UsuarioEntity>> getUsersActive(){
        System.out.println("Start - getUsersActive");
        List<UsuarioEntity> list = service.getActiveUsers();
        return new ResponseEntity<List<UsuarioEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/users-status/{estado}")
    public ResponseEntity<List<UsuarioEntity>> getUsersByStatus(@PathVariable String estado) {
        System.out.println("Start - getUsersByStatus");
        List<UsuarioEntity> list = service.getUsersByStatus(estado);
        return new ResponseEntity<List<UsuarioEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseBean> createUsuario(@Valid @RequestBody UsuarioBean usuarioBean) throws ErrorException {
        System.out.println("Inicio - createUsuario");

        UsuarioEntity usuario = service.createUser(usuarioBean);
        ResponseBean responseBean = new ResponseBean();
        responseBean.setId(String.valueOf(usuario.getId()));
        responseBean.setCreated(String.valueOf(usuario.getCreated()));
        responseBean.setModified(String.valueOf(usuario.getModified()));
        responseBean.setLastLogin(String.valueOf(usuario.getCreated()));
        responseBean.setToken("");
        responseBean.setStatus(String.valueOf(usuario.getActive()));
        return new ResponseEntity<ResponseBean>(responseBean, new HttpHeaders(), HttpStatus.OK);

    }
}
