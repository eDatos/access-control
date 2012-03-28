package org.siemac.metamac.access.control.base.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Roles definitions
 */
@Entity
@Table(name = "TB_ROLES", uniqueConstraints = {@UniqueConstraint(columnNames = {"CODE"})})
public class Role extends RoleBase {

    private static final long serialVersionUID = 1L;

    public Role() {
    }
}
