package com.hospital.agendamento_service.mapper;

import com.hospital.agendamento_service.dto.ConsultaRequestDTO;
import com.hospital.agendamento_service.dto.ConsultaResponseDTO;
import com.hospital.agendamento_service.model.Consulta;

public class ConsultaMapper {

    public static Consulta toEntity(ConsultaRequestDTO dto) {
        return Consulta.builder()
                .pacienteId(dto.getPacienteId())
                .medicoId(dto.getMedicoId())
                .dataHora(dto.getDataHora())
                .observacoes(dto.getObservacoes())
                .build();
    }

    public static ConsultaResponseDTO toDTO(Consulta consulta) {
        ConsultaResponseDTO dto = new ConsultaResponseDTO();
        dto.setId(consulta.getId());
        dto.setPacienteId(consulta.getPacienteId());
        dto.setMedicoId(consulta.getMedicoId());
        dto.setDataHora(consulta.getDataHora());
        dto.setObservacoes(consulta.getObservacoes());
        return dto;
    }
}
