package com.upc.coreservice.Mapping;

import com.upc.coreentities.Resource.Request.ChangeStatusRequestDto;
import com.upc.coreentities.Resource.Request.CreateRequestDto;
import com.upc.coreentities.Resource.Request.RequestDto;
import com.upc.coreentities.ServiceManagement.Request;
import com.upc.coreentities.Util.Shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@AllArgsConstructor
public class RequestMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public RequestDto toResource(Request model){
        return mapper.map(model, RequestDto.class);
    }
    public List<RequestDto> listToResource(List<Request> model){
        return mapper.mapList(model, RequestDto.class);
    }
    public Request toModel(RequestDto resource) {
        return mapper.map(resource, Request.class);
    }

    public Request toModel(CreateRequestDto resource) {
        return mapper.map(resource, Request.class);
    }

    public Request toModel(ChangeStatusRequestDto resource) {
        return mapper.map(resource, Request.class);
    }

}
