package com.example.pruebalab6.controller;

import com.example.pruebalab6.entity.Technician;
import com.example.pruebalab6.entity.Ticket;
import com.example.pruebalab6.repository.TechnicianRepository;
import com.example.pruebalab6.repository.TicketRepository;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class TechnicianController {

    private final TechnicianRepository technicianRepository;
    private final TicketRepository ticketRepository;
    public TechnicianController(TechnicianRepository technicianRepository, TicketRepository ticketRepository) {
        this.technicianRepository = technicianRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/Tecnicos")
    public String listarTecnicos(Model model){
        List<Technician> listaTecnicos = technicianRepository.listarTecnicos();
        model.addAttribute("listaTecnicos",listaTecnicos);
        return "technician/list";
    }

    @GetMapping("/tecniconew")
    public String nuevoProductoFrm(Model model, @ModelAttribute("tecnico") Technician technician) {
        return "technician/newFrm";
    }

    @PostMapping("/save")
    public String guardarProducto(RedirectAttributes attr,
                                  Model model,
                                  @ModelAttribute("tecnico") @Valid Technician technician,
                                  BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "technician/newFrm";
        } else {
            if (technician.getId() == null) {
                Technician Encontradotechnician = technicianRepository.buscarXdni(technician.getDni());
                if (Encontradotechnician != null) {
                    System.out.println("El técnico ya existe");
                    return "technician/newFrm";
                } else {
                    attr.addFlashAttribute("msg", "Técnico creado exitosamente");
                    technicianRepository.save(technician);
                    return "redirect:/Tecnicos";
                }
            } else {
                attr.addFlashAttribute("msg", "Técnico actualizado exitosamente");
                technicianRepository.save(technician);
                return "redirect:/Tecnicos";
            }
        }
    }

    @GetMapping("/editarTecnico")
    public String editarTransportista(Model model, @RequestParam("id") int id) {

        Optional<Technician> optProduct = technicianRepository.findById(id);

        if (optProduct.isPresent()) {
            Technician technician = optProduct.get();
            model.addAttribute("tecnico", technician);
            return "technician/newFrm";
        } else {
            return "redirect:/Tecnicos";
        }
    }

    @GetMapping("/borrarTecnico")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {
        List<Ticket> tickets = ticketRepository.listaticketsportecnico(id);
        if (!tickets.isEmpty()) {
            ticketRepository.deleteAll(tickets);
        }

        Optional<Technician> optTechnician = technicianRepository.findById(id);
        if (optTechnician.isPresent()) {
            technicianRepository.delete(optTechnician.get());
            attr.addFlashAttribute("msg", "Técnico eliminado exitosamente");
        }
        return "redirect:/Tecnicos";

    }



}
