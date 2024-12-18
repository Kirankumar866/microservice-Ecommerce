package com.kiran.ecommerce.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service

public class NotificationProducer {

    private static final Logger log = LoggerFactory.getLogger(NotificationProducer.class);
    private final KafkaTemplate<String,PaymentNotificationRequest> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //Sending notification
    public void sendNotification(PaymentNotificationRequest request){
        log.info("Sending notification with body <{}>", request);
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC,"payment-topic")
                .build();
        kafkaTemplate.send(message);
    }


}
