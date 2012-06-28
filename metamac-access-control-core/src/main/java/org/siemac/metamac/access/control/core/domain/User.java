package org.siemac.metamac.access.control.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Users
 */
@Entity
@Table(name = "TB_USERS", uniqueConstraints = {@UniqueConstraint(columnNames = {"USERNAME"})})
public class User extends UserBase {

    private static final long serialVersionUID = 1L;

    public User() {
    }
}
