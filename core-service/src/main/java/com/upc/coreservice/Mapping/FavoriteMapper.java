package com.upc.coreservice.Mapping;

import com.upc.coreentities.Resource.Favorite.CreateFavoriteDto;
import com.upc.coreentities.Resource.Favorite.FavoriteDto;
import com.upc.coreentities.Security.Favorite;
import com.upc.coreentities.Util.Shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@AllArgsConstructor
public class FavoriteMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public FavoriteDto toResource(Favorite model){
        return mapper.map(model, FavoriteDto.class);
    }
    public Favorite toModel(FavoriteDto resource) {
        return mapper.map(resource, Favorite.class);
    }

    public Favorite toModel(CreateFavoriteDto resource) {
        mapper.addMappings(new PropertyMap<CreateFavoriteDto, Favorite>() {
            @Override
            protected void configure() {
                map().setId(null);
            }
        });

        return mapper.map(resource, Favorite.class);
    }

    public List<FavoriteDto> listToResource(List<Favorite> model) {
        return mapper.mapList(model, FavoriteDto.class);
    }
}
