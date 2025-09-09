package com.hospital.notificacao_service.listener;

import com.hospital.commons.dto.NotificacaoMessage;
import com.hospital.notificacao_service.config.RabbitMQConfig;
import com.hospital.notificacao_service.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificacaoListener {

    private final NotificacaoService notificacaoService;

    @RabbitListener(
            queues = RabbitMQConfig.CONSULTA_NOTIFICACAO_QUEUE,
            containerFactory = "rabbitListenerContainerFactory"
    )
    public void receberMensagem(NotificacaoMessage mensagem) {
        notificacaoService.processarNotificacao(mensagem);
    }
}
