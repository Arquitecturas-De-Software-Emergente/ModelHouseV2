package com.upc.coreentities.Security;

import com.upc.coreentities.Security.BusinessProfile;
import lombok.*;

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
    private int score;
    private String comment;
    private Date reviewDate;
    @OneToOne
    @JoinColumn(name = "business_id", nullable = false)
    private BusinessProfile businessProfile;
}
