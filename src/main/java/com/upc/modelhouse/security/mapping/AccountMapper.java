package com.upc.modelhouse.security.mapping;

import com.upc.modelhouse.security.domain.model.entity.Account;
import com.upc.modelhouse.security.resource.Account.AccountDto;
import com.upc.modelhouse.security.resource.Account.CreateAccountDto;
import com.upc.modelhouse.shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AccountMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public AccountDto toResource(Account model){
        return mapper.map(model, AccountDto.class);
    }
    public List<AccountDto> listToResource(List<Account> model){
        return mapper.mapList(model, AccountDto.class);
    }
    public Account toModel(AccountDto resource) {
        return mapper.map(resource, Account.class);
    }

    public Account toModel(CreateAccountDto resource) {
        return mapper.map(resource, Account.class);
    }
}
