package com.upc.security.controller;

import com.upc.coreentities.Resource.BusinessProfile.BusinessProfileDto;
import com.upc.coreentities.Resource.UserProfile.CreateUserProfileDto;
import com.upc.coreentities.Resource.UserProfile.UserProfileDto;
import com.upc.coreentities.Security.UserProfile;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreservice.Mapping.UserProfileMapper;
import com.upc.coreservice.Service.Interfaces.StorageService;
import com.upc.coreservice.Service.Interfaces.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SecurityRequirement(name = "acme")
@RestController
@CrossOrigin
@RequestMapping("")
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final UserProfileMapper mapper;
    private final StorageService storageService;
    private final HttpServletRequest request;

    public UserProfileController(UserProfileService userProfileService, UserProfileMapper mapper, StorageService storageService, HttpServletRequest request) throws IOException {
        this.userProfileService = userProfileService;
        this.mapper = mapper;
        this.storageService = storageService;
        this.request = request;
        this.storageService.init("/UserProfile");
    }
    private String uploadDir = "/Images/UserProfile";
    @GetMapping("/user_profile")
    @Operation(tags = {"UserProfile"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<UserProfileDto> getAll(){
        return mapper.listToResource(userProfileService.findAll());
    }
    @GetMapping("/account/{accountId}/user_profile")
    @Operation(tags = {"UserProfile"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public UserProfileDto getUserProfileByAccount(@PathVariable("accountId") Long id){
        return mapper.toResource(userProfileService.findByUserId(id));
    }
    @PostMapping("/account/{accountId}/user_profile")
    @Operation(tags = {"UserProfile"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public UserProfileDto createFavorite(@PathVariable("accountId") Long accountId,@RequestBody CreateUserProfileDto resource){
        return mapper.toResource(userProfileService.create(accountId, mapper.toModel(resource)));
    }
    @GetMapping("/user_profile/{id}")
    @Operation(tags = {"UserProfile"})
    public UserProfileDto getUserProfileById(@PathVariable("id") Long id){
        return mapper.toResource(userProfileService.findByUserId(id));
    }
    @PutMapping("/user_profile/{id}")
    @Operation(tags = {"UserProfile"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public UserProfileDto updateUserProfile(@PathVariable("id") Long id, @RequestBody CreateUserProfileDto resource){
        return mapper.toResource(userProfileService.update(id, mapper.toModel(resource)));
    }
    @PostMapping("/user_profile/upload/{id}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"UserProfile"})
    public Map<String, String> upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable("id") Long id){
        UserProfile userProfile = userProfileService.findByUserId(id);
        String url;
        if(userProfile == null){
            throw new RuntimeException("User Profile is null");
        }
        String path = storageService.store(multipartFile, "/UserProfile");
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/security/user_profile/media/")
                .path(path)
                .toUriString();
        userProfile.setImage(url);
        userProfileService.update(id, userProfile);
        return Map.of("Url", url);
    }
    @GetMapping("/user_profile/media/{filename:.+}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"UserProfile"})
    public ResponseEntity<Resource> upload(@PathVariable String filename) throws IOException {
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }
    @GetMapping("/user_profile/{id}/deviceId")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"UserProfile"})
    public String getDeviceIdByUserProfile(@PathVariable("id") Long id){
        return userProfileService.getDeviceId(id);
    }
}
