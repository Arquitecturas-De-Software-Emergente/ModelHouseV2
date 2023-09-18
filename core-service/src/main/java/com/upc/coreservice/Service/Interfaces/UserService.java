package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.Resource.UserCredentialsResource;
import com.upc.coreentities.Resource.UserResource;
import com.upc.coreentities.Security.User;

public interface UserService {
    UserResource login (UserCredentialsResource credentials);
    User register(UserCredentialsResource credentialsResource);
}
