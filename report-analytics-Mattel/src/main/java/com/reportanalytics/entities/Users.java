package com.reportanalytics.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "users", indexes = {
        @Index(name = "loginId", columnList = "login_id")
})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;
    
    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;
    
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "contact")
    private String contact;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "emp_id", nullable = false)
    private String empId;

    @Column(name = "org_email_id", nullable = false)
    private String orgEmailId;

    @Column(name = "profile_photo")
    private String profilePhoto;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "created_on")
    @CreationTimestamp
    private Date createdOn;

    @Column(name = "last_modified_on")
    @UpdateTimestamp
    private Date lastModifiedOn;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "deleted_on")
    private Date deletedOn;

    @Column(name = "is_locked", nullable = false)
    private boolean isLocked = false;
    
    @Column(name = "is_password_changed", nullable = false)
    private boolean isPasswordChanged = false;
    
    @Column(name = "login_attempt_counter")
    private Integer loginAttemptCounter = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id_fk")
    private Role role;
}
