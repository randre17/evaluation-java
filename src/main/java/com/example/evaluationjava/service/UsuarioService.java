package com.example.evaluationjava.service;

import com.example.evaluationjava.bean.UsuarioBean;
import com.example.evaluationjava.exception.ErrorException;
import com.example.evaluationjava.model.UsuarioEntity;
import com.example.evaluationjava.repository.UsuarioRepository;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.evaluationjava.util.EvaluationJavaUtils.ESTADO_ACTIVO;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    public List<UsuarioEntity> getAllUsers(){
        List<UsuarioEntity> lst = repository.findAll();
        if(!lst.isEmpty()){
            return lst;
        }else{
            return new ArrayList<UsuarioEntity>();
        }
    }

    public List<UsuarioEntity> getActiveUsers(){
        List<UsuarioEntity> lst = repository.findByEstado(ESTADO_ACTIVO);
        if(!lst.isEmpty()){
            return lst;
        }else{
            return new ArrayList<UsuarioEntity>();
        }
    }

    public List<UsuarioEntity> getUsersByStatus(String estado){
        List<UsuarioEntity> lst = repository.findByEstado(Integer.parseInt(estado));
        if(!lst.isEmpty()){
            return lst;
        }else{
            return new ArrayList<UsuarioEntity>();
        }
    }

    public UsuarioEntity createUser(UsuarioBean obj) throws ErrorException {
        UsuarioEntity usuario = repository.findByEmail(obj.getEmail());
        if(usuario!=null){
            throw new ErrorException("Ya existe usuario con el correo ingresado: " + obj.getEmail());
        }else{
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity.setName(obj.getName());
            usuarioEntity.setMail(obj.getEmail());
            usuarioEntity.setActive(1);
            if(obj.getPhones().isEmpty()){
                usuarioEntity.setPhones("no-phone");
            }else{
                List<String> phones = new ArrayList<>();
                obj.getPhones().forEach(x-> {
                    phones.add(x.getCountryCode()+x.getCityCode()+x.getNumber());
                });
                usuarioEntity.setPhones(StringUtils.join(phones, ','));
            }
            usuarioEntity.setPassword(obj.getPassword());
            usuarioEntity.setCreated(LocalDateTime.now());
            usuarioEntity.setModified(LocalDateTime.now());
            usuarioEntity.setLast_login(LocalDateTime.now());
            return repository.save(usuarioEntity);
        }
    }
}
