package com.upc.coreentities.Security;

import com.upc.coreentities.ServiceManagement.ProjectActivity;
import com.upc.coreentities.ServiceManagement.ProjectResource;
import com.upc.coreentities.ServiceManagement.Proposal;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "business_profile_id", nullable = false)
    private BusinessProfile businessProfile;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proposal_id", nullable = false)
    private Proposal proposal;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;
}
