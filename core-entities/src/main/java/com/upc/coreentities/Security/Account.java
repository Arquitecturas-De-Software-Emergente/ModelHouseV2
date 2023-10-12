package com.upc.coreentities.Security;

import com.upc.coreentities.Util.Shared.domain.model.AuditModel;
import javax.persistence.*;
import javax.validation.constraints.Email;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(name = "email_address")
    private String emailAddress;
    private String password;
    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private BusinessProfile businessProfile;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private UserProfile userProfile;



    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private BusinessProfile businessProfile;

     */
}
