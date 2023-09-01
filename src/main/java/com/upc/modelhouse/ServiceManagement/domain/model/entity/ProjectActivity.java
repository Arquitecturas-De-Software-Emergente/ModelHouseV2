package com.upc.modelhouse.ServiceManagement.domain.model.entity;

import com.upc.modelhouse.security.domain.model.entity.Project;
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
