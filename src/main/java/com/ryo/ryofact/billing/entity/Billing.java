package com.ryo.ryofact.billing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "billing")
public class Billing {

    @Id
    @GeneratedValue
    @Column(name = "billing_id", nullable = false, unique = true)
    private Long billingId;

    @Column(name = "payment_reference", nullable = false, unique = true)
    private String paymentReference;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "billing_identifier_wisphub", nullable = false, unique = true)
    private String billingIdentifierWisphub;

    @Column(name = "is_save_wisphub", nullable = false )
    private Boolean isSaveWisphub;

    @Column(name = "object_wisphub_response", nullable = false, columnDefinition = "TEXT")
    private String objectWisphubResponse;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Billing(String paymentReference, BigDecimal total, String billingIdentifierWisphub, Boolean isSaveWisphub, String objectWisphubResponse) {
        this.paymentReference = paymentReference;
        this.total = total;
        this.billingIdentifierWisphub = billingIdentifierWisphub;
        this.isSaveWisphub = isSaveWisphub;
        this.objectWisphubResponse = objectWisphubResponse;
    }

    public static Billing create(String paymentReference, BigDecimal total, String billingIdentifierWisphub, Boolean isSaveWisphub, String objectWisphubResponse) {
        return new Billing(paymentReference, total, billingIdentifierWisphub, isSaveWisphub, objectWisphubResponse);
    }
}
