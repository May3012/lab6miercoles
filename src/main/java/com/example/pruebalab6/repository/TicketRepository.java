package com.example.pruebalab6.repository;

import com.example.pruebalab6.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    @Query(nativeQuery = true,value = "select * from ticket where TechnicianID = ?1;")
    List<Ticket> listaticketsportecnico(int id);



}