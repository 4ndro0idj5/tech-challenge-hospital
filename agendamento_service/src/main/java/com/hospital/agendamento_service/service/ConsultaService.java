package com.hospital.agendamento_service.service;

import com.hospital.agendamento_service.dto.ConsultaRequestDTO;
import com.hospital.agendamento_service.dto.ConsultaResponseDTO;
import com.hospital.agendamento_service.mapper.ConsultaMapper;
import com.hospital.agendamento_service.model.Consulta;
import com.hospital.agendamento_service.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return consultaRepository.findByPacienteId(pacienteId).stream()
                .map(ConsultaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ConsultaResponseDTO atualizarConsulta(Long id, ConsultaRequestDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consulta.setDataHora(dto.getDataHora());
        consulta.setObservacoes(dto.getObservacoes());
        return ConsultaMapper.toDTO(consultaRepository.save(consulta));
    }
}
