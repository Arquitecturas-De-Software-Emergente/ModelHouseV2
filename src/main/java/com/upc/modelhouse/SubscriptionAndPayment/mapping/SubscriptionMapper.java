package com.upc.modelhouse.SubscriptionAndPayment.mapping;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Subscription;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Subscription.CreateSubscriptionDto;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Subscription.SubscriptionDto;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Subscription.UpdateSubscriptionDto;
import com.upc.modelhouse.shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SubscriptionMapper {
    @Autowired
    EnhancedModelMapper mapper;
    public SubscriptionDto toResource(Subscription model) { return mapper.map(model, SubscriptionDto.class); }
    public List<SubscriptionDto> listToResource(List<Subscription> model) { return mapper.mapList(model, SubscriptionDto.class); }
    public Subscription toModel(SubscriptionDto resource) { return mapper.map(resource, Subscription.class); }
    public Subscription toModel(CreateSubscriptionDto resource) { return mapper.map(resource, Subscription.class); }
    public Subscription toModel(UpdateSubscriptionDto resource) { return mapper.map(resource, Subscription.class); }
}
