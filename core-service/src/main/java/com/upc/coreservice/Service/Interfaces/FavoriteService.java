package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.Resource.Favorite.CreateFavoriteDto;
import com.upc.coreentities.Security.Favorite;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FavoriteService {
    List<Favorite> getFavorites(Long userProfileId);
    Favorite addFavorite(Long userProfileId, Long businessProfileId, Favorite favorite);
    Favorite updateFavorite(CreateFavoriteDto createFavoriteDto);
    ResponseEntity<?> deleteFavorite(Long id);

}
