package com.upc.security.controller;

import com.upc.coreentities.Resource.BusinessProfile.BusinessProfileDto;
import com.upc.coreentities.Resource.BusinessProfile.CreateBusinessProfileDto;
import com.upc.coreentities.Resource.BusinessProfile.UpdateBusinessProfileDto;
import com.upc.coreservice.Mapping.BusinessProfileMapper;
import com.upc.coreservice.Service.Interfaces.BusinessProfileService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@SecurityRequirement(name = "acme")
@RestController
@CrossOrigin
@RequestMapping("")
public class BusinessProfileController {
    private final BusinessProfileService businessProfileService;
    private final BusinessProfileMapper mapper;

    public BusinessProfileController(BusinessProfileService businessProfileService, BusinessProfileMapper mapper) {
        this.businessProfileService = businessProfileService;
        this.mapper = mapper;
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
    @PutMapping("/business_profile/{id}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"BusinessProfile"})
    public BusinessProfileDto updateBusinessProfile(@PathVariable("id") Long id, @RequestBody UpdateBusinessProfileDto updateBusinessProfileDto){
        return mapper.toResource(businessProfileService.update(id, mapper.toModel(updateBusinessProfileDto)));
    }
}
