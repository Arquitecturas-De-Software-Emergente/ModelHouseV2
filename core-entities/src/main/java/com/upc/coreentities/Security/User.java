package com.upc.coreentities.Security;

import com.upc.coreentities.Util.Shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "is_active")
    private Boolean isActive;
    private String role;
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile userProfile;

}