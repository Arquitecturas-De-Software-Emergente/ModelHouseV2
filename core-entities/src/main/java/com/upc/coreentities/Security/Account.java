package com.upc.coreentities.Security;

import com.upc.coreentities.Util.Shared.domain.model.AuditModel;
import jakarta.persistence.*;
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

    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private BusinessProfile businessProfile;
}
