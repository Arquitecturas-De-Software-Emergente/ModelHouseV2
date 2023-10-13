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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "business_profile_id", nullable = false)
    private BusinessProfile businessProfile;
    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
    private List<ProjectActivity> projectActivity;
    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
    private List<ProjectResource> projectResource;
    @OneToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;
}
