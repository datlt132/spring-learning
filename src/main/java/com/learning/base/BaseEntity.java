package com.learning.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected boolean active;
    protected boolean deleted;
    protected Date createdTime;
    protected Date updatedTime;
    protected Long createdBy;
    protected Long updatedBy;

    @Transient
    protected String creator;
    @Transient
    protected String updater;
}
