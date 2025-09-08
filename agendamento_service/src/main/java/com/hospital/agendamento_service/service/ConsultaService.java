package com.hospital.agendamento_service.service;

import com.hospital.agendamento_service.dto.ConsultaRequestDTO;
import com.hospital.agendamento_service.dto.ConsultaResponseDTO;
import com.hospital.agendamento_service.mapper.ConsultaMapper;
import com.hospital.agendamento_service.model.Consulta;
import com.hospital.agendamento_service.repository.ConsultaRepository;
import com.hospital.agendamento_service.security.SecurityUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    public ConsultaResponseDTO agendarConsulta(ConsultaRequestDTO dto) {
        Consulta consulta = ConsultaMapper.toEntity(dto);
        return ConsultaMapper.toDTO(consultaRepository.save(consulta));
    }

    public List<ConsultaResponseDTO> listarPorPaciente(String pacienteId) {
        String usuarioLogado = SecurityUtils.getUsuarioLogado();

        if (SecurityUtils.isPaciente() && !usuarioLogado.equals(pacienteId)) {
            throw new AccessDeniedException("Você não pode acessar as consultas de outro paciente.");
        }

        List<Consulta> consultas = consultaRepository.findByPacienteId(pacienteId);

        if (consultas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado ou sem consultas.");
        }

        return consultas.stream()
                .map(ConsultaMapper::toDTO)
                .collect(Collectors.toList());
    }


    public ConsultaResponseDTO atualizarConsulta(Long id, ConsultaRequestDTO novaConsulta) {
        Consulta existente = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));


        if (novaConsulta.getMedicoId() != null) {
            existente.setMedicoId(novaConsulta.getMedicoId());
        }

        if (novaConsulta.getPacienteId() != null) {
            existente.setPacienteId(novaConsulta.getPacienteId());
        }

        if (novaConsulta.getDataHora() != null) {
            existente.setDataHora(novaConsulta.getDataHora());
        }

        if (novaConsulta.getObservacoes() != null) {
            existente.setObservacoes(novaConsulta.getObservacoes());
        }

        return ConsultaMapper.toDTO(consultaRepository.save(existente));
    }

}
