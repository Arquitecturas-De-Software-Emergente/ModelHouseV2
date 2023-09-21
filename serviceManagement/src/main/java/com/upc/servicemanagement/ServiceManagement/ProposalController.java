package com.upc.servicemanagement.ServiceManagement;

import com.upc.coreentities.Resource.Proposal.CreateProposalDto;
import com.upc.coreentities.Resource.Proposal.ProposalDto;
import com.upc.coreentities.Resource.Proposal.UpdateProposalDto;
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
    @PostMapping("/request/{requestId}/proposal")
    @Operation(tags = {"Proposal"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProposalDto createProject(@PathVariable("requestId") Long requestId,
                                    @RequestBody CreateProposalDto createProposalDto){
        Project resource = new Project();
        Proposal proposal = proposalService.create(requestId, mapper.toModel(createProposalDto));
        if(proposal != null){
            Project project = projectService.createProject(proposal.getRequest().getBusinessProfile().getId(), resource, proposal);
            if(project != null){
                return mapper.toResource(proposal);
            }
        }
        return null;
    }
    @PutMapping("/proposal/{id}")
    @Operation(tags = {"Proposal"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProposalDto updateProposal(@PathVariable("id") Long id, @RequestBody UpdateProposalDto updateProposalDto){
        return mapper.toResource(proposalService.update(id, mapper.toModel(updateProposalDto)));
    }
    @DeleteMapping("/proposal/{id}")
    @Operation(tags = {"Proposal"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        return  proposalService.delete(id);
    }
}
