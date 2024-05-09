package com.example.pruebalab6.repository;

import com.example.pruebalab6.dto.EquiposPorPaísDto;
import com.example.pruebalab6.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,Integer> {

    @Query(nativeQuery = true,value = "SELECT l.Country, COUNT(d.DeviceID) AS Cantidad_Equipos FROM device d\n" +
            "inner JOIN site s ON d.SiteID = s.SiteID\n" +
            "inner JOIN location l ON s.LocationID = l.LocationID\n" +
            "GROUP BY l.Country;")
    List<EquiposPorPaísDto> listaEquiposPorPais();
}
