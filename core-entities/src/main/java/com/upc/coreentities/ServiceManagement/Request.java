package com.upc.coreentities.ServiceManagement;

import com.upc.coreentities.Resource.BusinessProfile.BusinessProfileDto;
import com.upc.coreentities.Resource.UserProfile.UserProfileDto;
import com.upc.coreentities.Security.BusinessProfile;
import com.upc.coreentities.Security.UserProfile;
import lombok.*;

import javax.persistence.*;
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
    private String title;
    @Column(name = "request_date")
    private Date requestDate;
    @Column(name = "request_status")
    private String requestStatus;
    private String description;
    @Column(name = "is_response")
    private boolean isResponse;
    @Column(name = "response_date")
    private Date responseDate;
    private String category;
    private String estimatedBudget;
    private int area;
    private String location;
    private String file;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "business_profile_id", nullable = false)
    private BusinessProfile businessProfile;

    @OneToOne(mappedBy = "request", cascade = CascadeType.ALL)
    private Proposal proposal;



    /*
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

     */
}
