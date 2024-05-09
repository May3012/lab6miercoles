package com.example.pruebalab6.repository;

import com.example.pruebalab6.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TechnicianRepository extends JpaRepository<Technician,Integer> {
    @Query(nativeQuery = true,value = "SELECT * FROM technician where Dni=?1")
    Technician buscarXdni(String dni);
    @Query(nativeQuery = true,value = "select * from technician")
    List<Technician> listarTecnicos();
}
