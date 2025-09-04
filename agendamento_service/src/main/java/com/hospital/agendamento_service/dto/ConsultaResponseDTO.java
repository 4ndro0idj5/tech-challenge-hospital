package com.hospital.agendamento_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaResponseDTO {
    private Long id;
    private String pacienteId;
    private String medicoId;
    private LocalDateTime dataHora;
    private String observacoes;
}

