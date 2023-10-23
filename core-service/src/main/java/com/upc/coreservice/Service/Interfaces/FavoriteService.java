package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.Security.Favorite;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FavoriteService {
    List<Favorite> getFavorites(Long userProfileId);
    Favorite addFavorite(Long userProfileId, Long businessProfileId, Favorite favorite);
    ResponseEntity<?> deleteFavorite(Long id);

}
