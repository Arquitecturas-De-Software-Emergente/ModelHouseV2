package com.upc.modelhouse.security.domain.model.entity;

import com.upc.modelhouse.ServiceManagement.domain.model.entity.Proposal;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "business_profile_id", nullable = false)
    private BusinessProfile businessProfile;

    @OneToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;
}
