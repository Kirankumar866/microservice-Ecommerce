package com.kiran.ecommerce.payment;

import com.kiran.ecommerce.notification.NotificationProducer;
import com.kiran.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepo;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public PaymentService(PaymentRepository paymentRepo, PaymentMapper mapper, NotificationProducer notificationProducer) {
        this.paymentRepo = paymentRepo;
        this.mapper = mapper;
        this.notificationProducer = notificationProducer;
    }

    public Integer createPayment(PaymentRequest request) {
        var payment = paymentRepo.save(mapper.toPayment(request));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );
        System.out.print(payment);



        return payment.getId();
    }
}
