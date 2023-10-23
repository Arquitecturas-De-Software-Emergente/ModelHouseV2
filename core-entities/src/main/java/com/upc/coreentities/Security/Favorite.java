package com.upc.coreentities.Security;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "business_profile_id")
    private BusinessProfile businessProfile;

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;
}
