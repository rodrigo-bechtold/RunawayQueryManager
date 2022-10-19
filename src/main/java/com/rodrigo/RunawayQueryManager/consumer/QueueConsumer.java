package com.rodrigo.RunawayQueryManager.consumer;

import com.rodrigo.RunawayQueryManager.dtos.QueryTimeoutEventDTO;
import com.rodrigo.RunawayQueryManager.models.QueryTimeoutEventModel;
import com.rodrigo.RunawayQueryManager.repositories.QueryTimeoutEventRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @Autowired
    QueryTimeoutEventRepository queryTimeoutEventRepository;


    @RabbitListener(queues = {"${spring.rabbitmq.queue}"})
    public void receive(@Payload QueryTimeoutEventDTO eventDTO) {
        QueryTimeoutEventModel eventModel = new QueryTimeoutEventModel();
        BeanUtils.copyProperties(eventDTO, eventModel);
        queryTimeoutEventRepository.save(eventModel);
    }
}
