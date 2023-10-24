package com.upc.security.controller;

import com.upc.coreentities.Resource.BusinessProfile.BusinessProfileDto;
import com.upc.coreentities.Resource.BusinessProfile.CreateBusinessProfileDto;
import com.upc.coreentities.Resource.BusinessProfile.UpdateBusinessProfileDto;
import com.upc.coreentities.Security.BusinessProfile;
import com.upc.coreservice.Mapping.BusinessProfileMapper;
import com.upc.coreservice.Service.Interfaces.BusinessProfileService;
import com.upc.coreservice.Service.Interfaces.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.Map;

//@SecurityRequirement(name = "acme")
@RestController
@CrossOrigin
@RequestMapping("")
public class BusinessProfileController {
    private final BusinessProfileService businessProfileService;
    private final BusinessProfileMapper mapper;
    private final StorageService storageService;
    private final HttpServletRequest request;

    @Autowired
    public BusinessProfileController(BusinessProfileService businessProfileService, BusinessProfileMapper mapper, StorageService storageService, HttpServletRequest request) throws IOException {
        this.businessProfileService = businessProfileService;
        this.mapper = mapper;
        this.storageService = storageService;
        this.request = request;

        this.storageService.init("/BusinessProfile");
    }
    @GetMapping("/business_profile")
    @Operation(tags = {"BusinessProfile"})
    public List<BusinessProfileDto> getAll(){
        return mapper.listToResource(businessProfileService.findAll());
    }
    @GetMapping("/account/{accountId}/business_profile")
    @Operation(tags = {"BusinessProfile"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public BusinessProfileDto getUserProfileByAccountId(@PathVariable("accountId") Long id){
        return mapper.toResource(businessProfileService.findByAccountId(id));
    }
    @GetMapping("/business_profile/{id}")
    @Operation(tags = {"BusinessProfile"})
    public BusinessProfileDto getUserProfileById(@PathVariable("id") Long id){
        return mapper.toResource(businessProfileService.findById(id));
    }
    @PostMapping("/account/{accountId}/business_profile")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"BusinessProfile"})
    public BusinessProfileDto createBusinessProfile(@PathVariable("accountId") Long userId,@RequestBody CreateBusinessProfileDto resource){
        return mapper.toResource(businessProfileService.create(userId, mapper.toModel(resource)));
    }
    @PostMapping("/business_profile/upload/{id}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"BusinessProfile"})
    public Map<String, String> upload(@RequestParam("file")MultipartFile multipartFile, @PathVariable("id") Long id){
        BusinessProfile businessProfile = businessProfileService.findById(id);
        if(businessProfile == null){
            throw new RuntimeException("Business Profile is null");
        }
        String path = storageService.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/security/business_profile/media/")
                .path(path)
                .toUriString();
        businessProfile.setImage(url);
        businessProfileService.update(id, businessProfile);
        return Map.of("Url", url);
    }
    @GetMapping("/business_profile/media/{filename:.+}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"BusinessProfile"})
    public ResponseEntity<Resource> upload(@PathVariable String filename) throws IOException {
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }
    @PutMapping("/business_profile/{id}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"BusinessProfile"})
    public BusinessProfileDto updateBusinessProfile(@PathVariable("id") Long id, @RequestBody UpdateBusinessProfileDto updateBusinessProfileDto){
        return mapper.toResource(businessProfileService.update(id, mapper.toModel(updateBusinessProfileDto)));
    }
}
