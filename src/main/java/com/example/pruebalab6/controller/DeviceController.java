package com.example.pruebalab6.controller;

import com.example.pruebalab6.repository.DeviceRepository;
import com.example.pruebalab6.repository.TechnicianRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeviceController {
    final DeviceRepository deviceRepository;
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }
    @GetMapping("/Estadisticas")
    public String estadisticas(Model model){
        model.addAttribute("listaEquiposPorPais",deviceRepository.listaEquiposPorPais());
        return "estadisticas";
    }
}
