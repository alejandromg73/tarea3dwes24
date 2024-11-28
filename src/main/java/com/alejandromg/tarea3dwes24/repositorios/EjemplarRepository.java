package com.alejandromg.tarea3dwes24.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alejandromg.tarea3dwes24.modelo.Ejemplar;

@Repository
public interface EjemplarRepository extends JpaRepository <Ejemplar, Long>{

}
