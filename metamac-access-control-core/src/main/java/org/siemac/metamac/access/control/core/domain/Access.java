package org.siemac.metamac.access.control.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Access
 */
@Entity
@Table(name = "TB_ACCESS", uniqueConstraints = {@UniqueConstraint(columnNames = {"ROLE_FK", "APP_FK", "USER_FK", "OPERATION_FK", "REMOVAL_DATE"})})
public class Access extends AccessBase {

    private static final long serialVersionUID = 1L;

    public Access() {
    }
}
