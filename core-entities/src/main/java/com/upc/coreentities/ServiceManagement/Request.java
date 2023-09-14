package com.upc.coreentities.ServiceManagement;

import com.upc.coreentities.Security.BusinessProfile;
import com.upc.coreentities.Security.UserProfile;
import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date requestAt;
    private String status;
    private String description;
    private Boolean accepted;
    private Date acceptedAt;
    private Date updateAt;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "business_profile_id", nullable = false)
    private BusinessProfile businessProfile;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @OneToOne(mappedBy = "request", cascade = CascadeType.ALL)
    private Proposal proposal;
}
