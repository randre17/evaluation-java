package com.example.evaluationjava.repository;

import com.example.evaluationjava.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    @Query(value = "SELECT * FROM USUARIO WHERE active=?",nativeQuery = true)
    public List<UsuarioEntity> findByEstado(int estado);

    @Query(value = "SELECT * FROM USUARIO WHERE mail=?",nativeQuery = true)
    public UsuarioEntity findByEmail(String email);

}
