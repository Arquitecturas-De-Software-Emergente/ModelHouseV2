package com.upc.modelhouse.security.domain.service;

import com.upc.modelhouse.security.domain.model.entity.User;
import com.upc.modelhouse.security.resource.UserCredentialsResource;
import com.upc.modelhouse.security.resource.UserResource;

public interface UserService {
    UserResource login (UserCredentialsResource credentials);
    User register(UserCredentialsResource credentialsResource);
}
