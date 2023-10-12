package com.upc.coreentities.Security;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BusinessProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String address;
    @Column(name = "web_site")
    private String webSite;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String description;
    @Column(name = "foundation_date")
    private String foundationDate;


    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String image;
    private String address;
    @Column(name = "web_site")
    private String webSite;
    @Column(name = "phone_business")
    private String phoneBusiness;
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "last_login")
    private Date lastLogin;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

     */
}
