package com.forrequia.oauth2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Permission extends BaseIdEntity {

    @Column
    private String name;
}
