package com.equinte.gotur.mapper;

import com.equinte.gotur.dao.customer_tier.request.UpdateCustomerTierRequest;
import com.equinte.gotur.dao.customer_tier.response.CustomerTierDTO;
import com.equinte.gotur.entity.CustomerTier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerTierMapper {
    CustomerTierMapper INSTANCE = Mappers.getMapper(CustomerTierMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    CustomerTier updateEntity(@MappingTarget CustomerTier customerTier, UpdateCustomerTierRequest request);

    CustomerTierDTO toDto(CustomerTier customerTier);
}
