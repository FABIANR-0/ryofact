package com.ryo.ryofact.billing.repository;

import com.ryo.ryofact.billing.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long> {

    Boolean existsBillingByBillingIdentifierWisphub(String billingIdentifierWisphub);
}
