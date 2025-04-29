package com.mycom.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrdersCustomerDto {
    private int orderId;
    private String customerName;
    private String customerPhone;
    private LocalDate orderDate;

    public OrdersCustomerDto(int orderId, String customerName, LocalDate orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDate = orderDate;
    }
}
