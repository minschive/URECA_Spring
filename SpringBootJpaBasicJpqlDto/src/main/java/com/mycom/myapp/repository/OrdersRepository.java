package com.mycom.myapp.repository;

import java.util.List;

import com.mycom.myapp.dto.OrdersCustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mycom.myapp.dto.OrdersDto;
import com.mycom.myapp.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

    // #0, #1
    @Query("select o from Orders o")
    List<Orders> listOrder();

    // #2
//	@Query("select o from Orders o")
    @Query("select o from Orders o join fetch o.customer join fetch o.product")
    List<Orders> listOrdersServiceDto();

    // #3
    // Spring Data Jpa 가 자동으로 만들어 주는 select 관련 메소드 외에,
    // 직접 select 한 결과를 원하는 구조와 값으로 만들기 위해서는 resository 에서 직접 Dto 를 return 할 수 있도록 해야 한다.
    // jpql with dto
//	@Query(
//		"""
//			select new com.mycom.myapp.dto.OrdersDto(
//				o.id,
//				null,
//				null,
//				o.orderQuantity,
//				o.orderDate
//			)
//			from Orders o
//		""")
    @Query(
            """
                select new com.mycom.myapp.dto.OrdersDto(
                    o.id,
                    new com.mycom.myapp.dto.CustomerDto(
                        o.customer.id, o.customer.name, o.customer.gender, o.customer.phone, null
                    ),
                    new com.mycom.myapp.dto.ProductDto(
                        o.product.id, o.product.name, o.product.price, o.product.quantity, o.product.country, null
                    ),
                    o.orderQuantity,
                    o.orderDate
                )
                from Orders o join o.customer join o.product
            """)

    List<OrdersDto> listOrdersRepositoryDto();

    // #4
    @Query(
            """
                select new com.mycom.myapp.dto.OrdersCustomerDto(
                    o.id as orderId,
                    c.name as customerName,
                    c.phone as customerPhone,
                    o.orderDate
                )
                from Orders o join o.customer c
            """)

    List<OrdersCustomerDto> listOrdersCustomerRepositoryDto();
}