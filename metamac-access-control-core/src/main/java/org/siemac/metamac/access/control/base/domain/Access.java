package org.siemac.metamac.access.control.base.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Access
 */
@Entity
@Table(name = "TBL_ACCESS", uniqueConstraints = {@UniqueConstraint(columnNames = {"ROLE_FK", "APP_FK", "USER_FK", "OPERATION_CODE_ID", "REMOVAL_DATE"})})
public class Access extends AccessBase {

    private static final long serialVersionUID = 1L;

    public Access() {
    }
}
