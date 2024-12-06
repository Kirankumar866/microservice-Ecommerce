package com.kiran.ecommerce.kafka;

import com.kiran.ecommerce.kafka.order.OrderConfirmation;
import com.kiran.ecommerce.kafka.payment.PaymentConfirmation;
import com.kiran.ecommerce.notification.Notification;
import com.kiran.ecommerce.notification.NotificationRepository;
import com.kiran.ecommerce.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepo;
    //private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation){
        log.info(String.format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        notificationRepo.save(
                Notification.builder().type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        //TO DO send email




    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation){
        log.info(String.format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        notificationRepo.save(
                Notification.builder().type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        //TO DO send email




    }



}
