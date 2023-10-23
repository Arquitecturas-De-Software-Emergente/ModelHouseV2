package com.upc.security.controller;

import com.upc.coreentities.Resource.Favorite.CreateFavoriteDto;
import com.upc.coreentities.Resource.Favorite.FavoriteDto;
import com.upc.coreservice.Mapping.FavoriteMapper;
import com.upc.coreservice.Service.Interfaces.FavoriteService;
import com.upc.coreservice.Service.Interfaces.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("")
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final FavoriteMapper mapper;

    @Autowired
    public FavoriteController(FavoriteService favoriteService, FavoriteMapper mapper) {
        this.favoriteService = favoriteService;
        this.mapper = mapper;
    }

    @GetMapping("/favorite/{userProfileId}")
    @Operation(tags = {"Favorite"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<FavoriteDto> getAllByUserProfileId(Long userProfileId){
        return mapper.listToResource(favoriteService.getFavorites(userProfileId));
    }

    @PostMapping("/userProfile/{userProfileId}/businessProfile/{businessProfileId}")
    @Operation(tags = {"Favorite"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public FavoriteDto createFavorite(@PathVariable("userProfileId") Long userId,
                                      @PathVariable("businessProfileId") Long businessId,
                                      @RequestBody CreateFavoriteDto favoriteDto){
        return mapper.toResource(favoriteService.addFavorite(userId, businessId, mapper.toModel(favoriteDto)));
    }

    @DeleteMapping("/favorite/{id}")
    @Operation(tags = {"Favorite"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public void deleteFavorite(@PathVariable("id") Long id){
        favoriteService.deleteFavorite(id);
    }
}
