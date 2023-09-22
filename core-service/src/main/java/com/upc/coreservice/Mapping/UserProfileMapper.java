package com.upc.coreservice.Mapping;

import com.upc.coreentities.Resource.UserProfile.CreateUserProfileDto;
import com.upc.coreentities.Resource.UserProfile.UpdateUserProfileDto;
import com.upc.coreentities.Resource.UserProfile.UserProfileDto;
import com.upc.coreentities.Security.UserProfile;
import com.upc.coreentities.Util.Shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@AllArgsConstructor
public class UserProfileMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public UserProfileDto toResource(UserProfile model){
        return mapper.map(model, UserProfileDto.class);
    }
    public List<UserProfileDto> listToResource(List<UserProfile> model){
        return mapper.mapList(model, UserProfileDto.class);
    }

    public UserProfile toModel(UserProfileDto resource) {
        return mapper.map(resource, UserProfile.class);
    }

    public UserProfile toModel(CreateUserProfileDto resource) {
        return mapper.map(resource, UserProfile.class);
    }

    public UserProfile toModel(UpdateUserProfileDto resource) {
        return mapper.map(resource, UserProfile.class);
    }
}
