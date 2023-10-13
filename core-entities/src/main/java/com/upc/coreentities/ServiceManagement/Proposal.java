package com.upc.coreentities.ServiceManagement;

import com.upc.coreentities.Resource.ProjectActivity.CreateProjectActivityDto;
import com.upc.coreentities.Resource.ProjectResource.CreateProjectResourceDto;
import com.upc.coreentities.Security.BusinessProfile;
import com.upc.coreentities.Security.Project;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "proposal_date")
    private Date proposalDate;
    @Column(name = "proposal_status")
    private String proposalStatus;
    private String title;
    private String description;
    private String file;
    @Column(name = "estimated_time")
    private Date estimatedTime;
    @Column(name = "is_response")
    private Boolean isResponse;
    @Column(name = "response_date")
    private Date responseDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "businessprofile_id", nullable = false)
    private BusinessProfile businessProfile;

    @OneToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
    private List<ProjectActivity> projectActivity;

    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
    private List<ProjectResource> projectResource;


    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "proposal_date")
    private Date proposalDate;
    private String description;
    private Float price;
    private String status;
    @Column(name = "is_response")
    private Boolean isResponse;
    @Column(name = "response_date")
    private Date responseDate;

    @OneToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @OneToOne(mappedBy = "proposal", cascade = CascadeType.ALL)
    private Project project;

     */
}
