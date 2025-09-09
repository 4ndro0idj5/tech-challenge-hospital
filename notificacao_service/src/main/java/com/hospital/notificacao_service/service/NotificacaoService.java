package com.hospital.notificacao_service.service;

import com.hospital.commons.dto.NotificacaoMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificacaoService {

    public void processarNotificacao(NotificacaoMessage mensagem) {

        log.info("[NOTIFICAÇÃO] Enviando notificação para o paciente: {}", mensagem.pacienteId());
        log.info("Mensagem: {}", mensagem.mensagem());
        log.info("Data da consulta: {}", mensagem.dataHora());
        log.info("Médico responsável: {}", mensagem.medicoId());
    }
}
