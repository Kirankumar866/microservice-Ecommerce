package com.kiran.ecommerce.payment;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private Integer orderId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    public Integer getId() {
        return id;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Payment payment;

        private Builder() {
            this.payment = new Payment();
        }

        public Builder id(Integer id) {
            payment.id = id;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            payment.amount = amount;
            return this;
        }

        public Builder paymentMethod(PaymentMethod paymentMethod) {
            payment.paymentMethod = paymentMethod;
            return this;
        }

        public Builder orderId(Integer orderId) {
            payment.orderId = orderId;
            return this;
        }

        public Payment build() {
            validatePayment();
            return payment;
        }

        private void validatePayment() {
            if (payment.amount == null || payment.amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalStateException("Amount must be positive");
            }
            if (payment.paymentMethod == null) {
                throw new IllegalStateException("Payment method is required");
            }
            if (payment.orderId == null) {
                throw new IllegalStateException("Order ID is required");
            }
        }
    }
}