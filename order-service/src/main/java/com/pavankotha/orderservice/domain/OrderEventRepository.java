package com.pavankotha.orderservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OrderEventRepository extends JpaRepository<OrderEventEntity, Long> {}
