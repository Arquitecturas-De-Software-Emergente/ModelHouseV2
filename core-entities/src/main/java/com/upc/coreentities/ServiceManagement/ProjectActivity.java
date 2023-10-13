package com.upc.coreentities.ServiceManagement;

import com.upc.coreentities.Security.Project;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProjectActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String status;
    private Date startedAt;


    @ManyToOne(optional = true)
    @JoinColumn(name = "project_id", nullable = true)
    private Project project;


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "proposal_id", nullable = true)
    private Proposal proposal;

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String description;
    private String status;
    private Date startedAt;
    private Date completedAt;
    @Column(name = "completion_percent")
    private Float completionPercent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

     */
}
