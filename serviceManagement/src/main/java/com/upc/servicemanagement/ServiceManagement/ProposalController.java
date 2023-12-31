package com.upc.servicemanagement.ServiceManagement;

import com.upc.coreentities.Resource.Proposal.ChangeStatusProposalDto;
import com.upc.coreentities.Resource.Proposal.CreateProposalDto;
import com.upc.coreentities.Resource.Proposal.ProposalDto;
import com.upc.coreentities.Resource.Proposal.UpdateProposalDto;
import com.upc.coreentities.Resource.Request.ChangeStatusRequestDto;
import com.upc.coreentities.Resource.Request.RequestDto;
import com.upc.coreentities.Security.Project;
import com.upc.coreentities.ServiceManagement.Proposal;
import com.upc.coreservice.Mapping.ProposalMapper;
import com.upc.coreservice.Service.Interfaces.ProjectService;
import com.upc.coreservice.Service.Interfaces.ProposalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@RestController
@CrossOrigin
@RequestMapping("")
public class ProposalController {
    private final ProposalService proposalService;
    private final ProjectService projectService;
    private final ProposalMapper mapper;

    @Autowired
    public ProposalController(ProposalService proposalService, ProjectService projectService, ProposalMapper mapper) {
        this.proposalService = proposalService;
        this.projectService = projectService;
        this.mapper = mapper;
    }
    @GetMapping("/proposal/{id}/business_profile")
    @Operation(tags = {"Proposal"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<ProposalDto> getAllByBusinessProfileId(@PathVariable("id") Long id){
        return mapper.listToResource(proposalService.getAllByBusinessProfileId(id));
    }
    @GetMapping("/proposal/{id}/user_profile")
    @Operation(tags = {"Proposal"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<ProposalDto> getAllByUserProfileId(@PathVariable("id") Long id){
        return mapper.listToResource(proposalService.getAllByUserProfileId(id));
    }
    @GetMapping("/proposal")
    @Operation(tags = {"Proposal"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<ProposalDto> getAll(){
        return mapper.listToResource(proposalService.getAll());
    }
    @GetMapping("/request/{requestId}/proposal")
    @Operation(tags = {"Proposal"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProposalDto getAllByRequestId(@PathVariable("requestId") Long id){
        return mapper.toResource(proposalService.findAllRequestId(id));
    }

    @PutMapping("/proposal/{id}")
    @Operation(tags = {"Proposal"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProposalDto updateProposal(@PathVariable("id") Long id, @RequestBody UpdateProposalDto updateProposalDto){
        return mapper.toResource(proposalService.update(id, mapper.toModel(updateProposalDto)));
    }

    @PutMapping("/request/{id}/status")
    @Operation(tags = {"Proposal"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProposalDto updateStatusProposal(@PathVariable("id") Long id, @RequestBody ChangeStatusProposalDto changeStatusProposalDto){
        return mapper.toResource(proposalService.changeStatus(id, mapper.toModel(changeStatusProposalDto)));
    }

    @DeleteMapping("/proposal/{id}")
    @Operation(tags = {"Proposal"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        return  proposalService.delete(id);
    }
}
