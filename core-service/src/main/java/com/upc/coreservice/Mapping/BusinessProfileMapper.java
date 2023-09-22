package com.upc.coreservice.Mapping;

import com.upc.coreentities.Resource.BusinessProfile.BusinessProfileDto;
import com.upc.coreentities.Resource.BusinessProfile.CreateBusinessProfileDto;
import com.upc.coreentities.Resource.BusinessProfile.UpdateBusinessProfileDto;
import com.upc.coreentities.Security.BusinessProfile;
import com.upc.coreentities.Util.Shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@AllArgsConstructor
public class BusinessProfileMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public BusinessProfileDto toResource(BusinessProfile model){
        return mapper.map(model, BusinessProfileDto.class);
    }
    public List<BusinessProfileDto> listToResource(List<BusinessProfile> model){
        return mapper.mapList(model, BusinessProfileDto.class);
    }
    public BusinessProfile toModel(BusinessProfileDto resource) {
        return mapper.map(resource, BusinessProfile.class);
    }

    public BusinessProfile toModel(CreateBusinessProfileDto resource) {
        return mapper.map(resource, BusinessProfile.class);
    }

    public BusinessProfile toModel(UpdateBusinessProfileDto resource) {
        return mapper.map(resource, BusinessProfile.class);
    }
}
