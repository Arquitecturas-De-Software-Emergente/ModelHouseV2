package com.upc.coreentities.ServiceManagement;

import com.upc.coreentities.Security.Project;
import lombok.*;

import javax.persistence.*;
import java.awt.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProjectResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Number quantity;
    private String resourceType;
    private String state;
    private String image;
    private String projectId;

    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "project_id")
    private Project project;
*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

}
