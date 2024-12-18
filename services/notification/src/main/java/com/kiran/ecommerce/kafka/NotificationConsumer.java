package com.kiran.ecommerce.kafka;

import com.kiran.ecommerce.email.EmailService;
import com.kiran.ecommerce.kafka.order.OrderConfirmation;
import com.kiran.ecommerce.kafka.payment.PaymentConfirmation;
import com.kiran.ecommerce.notification.Notification;
import com.kiran.ecommerce.notification.NotificationRepository;
import com.kiran.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.kiran.ecommerce.notification.NotificationType.ORDER_CONFIRMATION;
import static com.kiran.ecommerce.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service

public class NotificationConsumer {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final NotificationRepository notificationRepo;
    private final EmailService emailService;

    public NotificationConsumer(NotificationRepository notificationRepo, EmailService emailService) {
        this.notificationRepo = notificationRepo;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        notificationRepo.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        //send email
        var customerName = paymentConfirmation.customerFirstname()+" "+ paymentConfirmation.customerLastname();
        emailService.sentPaymentSuccessEmail(paymentConfirmation.customerEmail(),customerName,paymentConfirmation.amount(), paymentConfirmation.orderReference());




    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        notificationRepo.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        //send email
        var customerName = orderConfirmation.customer().firstName()+" "+ orderConfirmation.customer().lastName();
        emailService.sentOrderConfirmationEmail(orderConfirmation.customer().email(),customerName,orderConfirmation.totalAmount(), orderConfirmation.orderReference(), orderConfirmation.products());




    }



}
