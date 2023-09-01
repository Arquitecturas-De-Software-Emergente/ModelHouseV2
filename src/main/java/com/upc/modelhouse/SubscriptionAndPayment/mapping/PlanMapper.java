package com.upc.modelhouse.SubscriptionAndPayment.mapping;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Plan;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Plan.CreatePlanDto;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Plan.PlanDto;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Plan.UpdatePlanDto;
import com.upc.modelhouse.shared.mapping.EnhancedModelMapper;
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
