package com.upc.security.controller;

import com.upc.coreentities.Resource.UserProfile.CreateUserProfileDto;
import com.upc.coreentities.Resource.UserProfile.UserProfileDto;
import com.upc.coreservice.Mapping.UserProfileMapper;
import com.upc.coreservice.Service.Interfaces.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@RestController
@CrossOrigin
@RequestMapping("")
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final UserProfileMapper mapper;

    public UserProfileController(UserProfileService userProfileService, UserProfileMapper mapper) {
        this.userProfileService = userProfileService;
        this.mapper = mapper;
    }
    @GetMapping("/user_profile")
    @Operation(tags = {"UserProfile"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<UserProfileDto> getAll(){
        return mapper.listToResource(userProfileService.findAll());
    }
    @GetMapping("/user/{userId}/user_profile")
    @Operation(tags = {"UserProfile"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public UserProfileDto getUserProfileByAccount(@PathVariable("userId") Long id){
        return mapper.toResource(userProfileService.findByUserId(id));
    }
    @PostMapping("/user/{userId}/user_profile")
    @Operation(tags = {"UserProfile"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public UserProfileDto createFavorite(@PathVariable("userId") Long accountId,@RequestBody CreateUserProfileDto resource){
        return mapper.toResource(userProfileService.create(accountId, mapper.toModel(resource)));
    }
    @PutMapping("/user_profile/{id}")
    @Operation(tags = {"UserProfile"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public UserProfileDto updateUserProfile(@PathVariable("id") Long id, @RequestBody CreateUserProfileDto createUserProfileDto){
        return mapper.toResource(userProfileService.update(id, mapper.toModel(createUserProfileDto)));
    }
}