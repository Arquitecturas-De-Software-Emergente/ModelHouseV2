package com.upc.coreservice.Repository.Security;

import com.upc.coreentities.Security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAddress(String emailAddress);
}