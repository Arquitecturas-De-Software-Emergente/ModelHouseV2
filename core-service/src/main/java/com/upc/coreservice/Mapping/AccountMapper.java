package com.upc.coreservice.Mapping;

import com.upc.coreentities.Resource.AccountResource;
import com.upc.coreentities.Resource.CreateAccountResource;
import com.upc.coreentities.Security.Account;
import com.upc.coreentities.Util.Shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AccountMapper {

    private static final long serialVersionUID = 1L;

    @Autowired
    private EnhancedModelMapper modelMapper;

    public AccountMapper accountMapper() {
        return new AccountMapper(modelMapper);
    }

    public Page<AccountResource> modelListPage(List<AccountResource> modelList, Pageable pageable) {
        return new PageImpl<>(modelMapper.mapList(modelList, AccountResource.class), pageable, modelList.size());
    }

    public Account toModel(CreateAccountResource resource) {
        return modelMapper.map(resource, Account.class);
    }

    public AccountResource toResource(Account account) {
        return modelMapper.map(account, AccountResource.class);
    }

    /*
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

     */
}
