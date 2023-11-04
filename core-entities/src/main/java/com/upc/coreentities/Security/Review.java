package com.upc.coreentities.Security;

import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float score;
    private String comment;
    private Date reviewDate;

    @OneToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile userProfile;
}
