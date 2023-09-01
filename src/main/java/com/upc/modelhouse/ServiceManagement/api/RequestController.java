package com.upc.modelhouse.ServiceManagement.api;

import com.upc.modelhouse.ServiceManagement.domain.service.RequestService;
import com.upc.modelhouse.ServiceManagement.mapping.RequestMapper;
import com.upc.modelhouse.ServiceManagement.resource.Request.ChangeStatusRequestDto;
import com.upc.modelhouse.ServiceManagement.resource.Request.CreateRequestDto;
import com.upc.modelhouse.ServiceManagement.resource.Request.RequestDto;
import com.upc.modelhouse.security.resource.Project.CreateProjectDto;
import com.upc.modelhouse.security.resource.Project.ProjectDto;
import com.upc.modelhouse.security.resource.Project.UpdateProjectDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
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
    @GetMapping("/business/{businessId}/status/{status}/request")
    @Operation(tags = {"Request"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<RequestDto> getAllByBusinessIdAndStatus(@PathVariable("businessId") Long id,
                                               @PathVariable("status") String status){
        return mapper.listToResource(requestService.findAllBusinessProfileIdAndStatus(id,status));
    }
    @GetMapping("/user/{userId}/status/{status}/request")
    @Operation(tags = {"Request"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<RequestDto> getAllByUserIdAndStatus(@PathVariable("userId") Long id,
                                           @PathVariable("status") String status){
        return mapper.listToResource(requestService.findAllUserProfileIdAndStatus(id,status));
    }
    @PostMapping("/user/{userId}/business/{businessId}/request")
    @Operation(tags = {"Request"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public RequestDto createProject(@PathVariable("userId") Long userId,
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
    @DeleteMapping("/request/{id}")
    @Operation(tags = {"Request"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        return  requestService.delete(id);
    }
}
