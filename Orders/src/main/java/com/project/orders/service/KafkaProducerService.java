package com.project.orders.service;

import com.project.orders.model.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {
@Autowired
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void sendOrderEvent(OrderPlacedEvent event) {

        kafkaTemplate.send("order-topic", event);
        log.info(kafkaTemplate.getProducerFactory().getConfigurationProperties().toString());
        log.info("Order Event Published");
    }
}
