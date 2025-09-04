package com.hospital.agendamento_service.controller;

import com.hospital.agendamento_service.dto.ConsultaRequestDTO;
import com.hospital.agendamento_service.dto.ConsultaResponseDTO;
import com.hospital.agendamento_service.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ConsultaResponseDTO agendar(@RequestBody ConsultaRequestDTO dto) {
        return consultaService.agendarConsulta(dto);
    }

    @GetMapping("/paciente/{id}")
    public List<ConsultaResponseDTO> listarPorPaciente(@PathVariable("id") String pacienteId) {
        return consultaService.listarPorPaciente(pacienteId);
    }

    @PutMapping("/{id}")
    public ConsultaResponseDTO atualizar(@PathVariable Long id, @RequestBody ConsultaRequestDTO dto) {
        return consultaService.atualizarConsulta(id, dto);
    }
}

