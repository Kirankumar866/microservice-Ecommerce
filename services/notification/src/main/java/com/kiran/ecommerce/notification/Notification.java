package com.kiran.ecommerce.notification;

import com.kiran.ecommerce.kafka.order.OrderConfirmation;
import com.kiran.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;

    public static class Builder {
        private final Notification notification;

        public Builder() {
            this.notification = new Notification();
        }

        public Builder id(String id) {
            notification.id = id;
            return this;
        }

        public Builder type(NotificationType type) {
            notification.type = type;
            return this;
        }

        public Builder notificationDate(LocalDateTime notificationDate) {
            notification.notificationDate = notificationDate;
            return this;
        }

        public Builder orderConfirmation(OrderConfirmation orderConfirmation) {
            notification.orderConfirmation = orderConfirmation;
            return this;
        }

        public Builder paymentConfirmation(PaymentConfirmation paymentConfirmation) {
            notification.paymentConfirmation = paymentConfirmation;
            return this;
        }

        public Notification build() {
            return notification;
        }
    }

    // Static method to create builder
    public static Builder builder() {
        return new Builder();
    }
}
