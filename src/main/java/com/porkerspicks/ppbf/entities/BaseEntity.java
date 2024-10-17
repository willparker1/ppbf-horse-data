package com.porkerspicks.ppbf.entities;

import lombok.Data;

import jakarta.persistence.*;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    private Long id;

    @Version
    private Integer version;
}
