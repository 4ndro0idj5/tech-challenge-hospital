package com.hospital.commons.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record NotificacaoMessage(
        String pacienteId,
        String medicoId,
        LocalDateTime dataHora,
        String mensagem
) implements Serializable {}
