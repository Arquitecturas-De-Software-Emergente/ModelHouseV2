package com.upc.coreservice.Mapping;

import com.upc.coreentities.Resource.Proposal.CreateProposalDto;
import com.upc.coreentities.Resource.Proposal.ProposalDto;
import com.upc.coreentities.Resource.Proposal.UpdateProposalDto;
import com.upc.coreentities.ServiceManagement.Proposal;
import com.upc.coreentities.Util.Shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class ProposalMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ProposalMapper(EnhancedModelMapper mapper) {
        this.mapper = mapper;
    }

    public ProposalDto toResource(Proposal model){
        return mapper.map(model, ProposalDto.class);
    }
    public List<ProposalDto> listToResource(List<Proposal> model){
        return mapper.mapList(model, ProposalDto.class);
    }
    public Proposal toModel(ProposalDto resource) {
        return mapper.map(resource, Proposal.class);
    }

    public Proposal toModel(CreateProposalDto resource) {
        return mapper.map(resource, Proposal.class);
    }

    public Proposal toModel(UpdateProposalDto resource) {
        return mapper.map(resource, Proposal.class);
    }
}
