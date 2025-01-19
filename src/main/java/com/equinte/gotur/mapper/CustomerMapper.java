package com.equinte.gotur.mapper;

import com.equinte.gotur.dao.customer.CustomerDTO;
import com.equinte.gotur.dao.customer.request.CreateCustomerRequest;
import com.equinte.gotur.dao.customer.response.CreateCustomerResponse;
import com.equinte.gotur.dao.customer.response.GetCustomerResponse;
import com.equinte.gotur.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toDto(Customer customer);

    @Mapping(target = "orderCount", expression = "java(customer.getOrders() != null ? customer.getOrders().size() : null)")
    @Mapping(target = "customerTierName", source = "tier.name")
    GetCustomerResponse toResponseElement(Customer customer);


    @Mapping(target = "tier", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "id", ignore = true)
    Customer toEntity(CreateCustomerRequest request);

    @Mapping(target = "orderCount", expression = "java(customer.getOrders() != null ? customer.getOrders().size() : null)")
    @Mapping(target = "customerTierName", source = "tier.name")
    CreateCustomerResponse createResponse(Customer customer);
}
