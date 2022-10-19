package com.rodrigo.RunawayQueryManager.sender;

import com.rabbitmq.client.AMQP;
import com.rodrigo.RunawayQueryManager.dtos.QueryTimeoutEventDTO;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(QueryTimeoutEventDTO event) {
        rabbitTemplate.convertAndSend(this.queue.getName(), event);
    }
}
