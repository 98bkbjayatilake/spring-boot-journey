package com.bimsara.Spring_6_rest_mvc.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
@Data
public class Customer {
    private  String customerName;
    private UUID customerId;
    private Integer customerVersion;
    private LocalDateTime customerCreatedDate;
    private LocalDateTime lastModifiedDate;
}
