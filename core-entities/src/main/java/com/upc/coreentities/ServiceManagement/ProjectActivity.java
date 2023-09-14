package com.upc.coreentities.ServiceManagement;

import com.upc.coreentities.Security.Project;
import lombok.*;

import jakarta.persistence.*;
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
    private String name;
    private String description;
    private String status;
    private Date startedAt;
    private Date completedAt;
    @Column(name = "completion_percent")
    private Float completionPercent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
