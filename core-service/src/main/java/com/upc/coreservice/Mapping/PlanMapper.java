package com.upc.coreservice.Mapping;

import com.upc.coreentities.Resource.Plan.CreatePlanDto;
import com.upc.coreentities.Resource.Plan.PlanDto;
import com.upc.coreentities.Resource.Plan.UpdatePlanDto;
import com.upc.coreentities.SubscriptionAndPayment.Plan;
import com.upc.coreentities.Util.Shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PlanMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public PlanDto toResource(Plan model) { return mapper.map(model, PlanDto.class); }
    public List<PlanDto> listToResource(List<Plan> model) { return mapper.mapList(model, PlanDto.class); }
    public Plan toModel(PlanDto resource) { return mapper.map(resource, Plan.class); }
    public Plan toModel(CreatePlanDto resource) { return mapper.map(resource, Plan.class); }
    public Plan toModel(UpdatePlanDto resource) { return mapper.map(resource, Plan.class); }

}
