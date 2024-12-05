package com.kiran.ecommerce.payment;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;


@Data
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal amount;
    @Enumerated(STRING)
    private PaymentMethod paymentMethod;
    private Integer orderId;

    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;


}
