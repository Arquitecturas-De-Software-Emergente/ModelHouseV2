package com.upc.servicemanagement.ServiceManagement;

import com.upc.coreentities.Resource.Request.ChangeStatusRequestDto;
import com.upc.coreentities.Resource.Request.CreateRequestDto;
import com.upc.coreentities.Resource.Request.RequestDto;
import com.upc.coreservice.Mapping.RequestMapper;
import com.upc.coreservice.Service.Interfaces.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@RestController
@CrossOrigin
@RequestMapping("")
public class RequestController {
    private final RequestService requestService;
    private final RequestMapper mapper;

    public RequestController(RequestService requestService, RequestMapper mapper) {
        this.requestService = requestService;
        this.mapper = mapper;
    }
    @GetMapping("/request")
    @Operation(tags = {"Request"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<RequestDto> getAll(){
        return mapper.listToResource(requestService.getAll());
    }


    @GetMapping("/businessProfile/{businessProfileId}/status/{status}/request")
    @Operation(tags = {"Request"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<RequestDto> getAllByBusinessIdAndStatus(@PathVariable("businessProfileId") Long id,
                                               @PathVariable("status") String status){
        return mapper.listToResource(requestService.findAllBusinessProfileIdAndStatus(id,status));
    }


    @GetMapping("/userProfile/{userProfileId}/status/{status}/request")
    @Operation(tags = {"Request"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<RequestDto> getAllByUserIdAndStatus(@PathVariable("userProfileId") Long id,
                                           @PathVariable("status") String status){
        return mapper.listToResource(requestService.findAllUserProfileIdAndStatus(id,status));
    }

    @PostMapping("/account/{accountId}/business/{businessId}/request")
    @Operation(tags = {"Request"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public RequestDto createProject(@PathVariable("accountId") Long userId,
                                    @PathVariable("businessId") Long businessId,
                                    @RequestBody CreateRequestDto createRequestDto){
        return mapper.toResource(requestService.create(userId, businessId, mapper.toModel(createRequestDto)));
    }


    @PutMapping("/request/{id}")
    @Operation(tags = {"Request"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public RequestDto updateRequest(@PathVariable("id") Long id, @RequestBody ChangeStatusRequestDto changeStatusRequestDto){
        return mapper.toResource(requestService.changeStatus(id, mapper.toModel(changeStatusRequestDto)));
    }



    @DeleteMapping("/request/{id}/status")
    @Operation(tags = {"Request"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        return  requestService.delete(id);
    }
}
