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
    private Integer quantity;
    private Boolean isChecked;
    private String image;
    private String projectId;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

}
