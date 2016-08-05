package org.siemac.metamac.access.control.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Applications
 */
@Entity
@Table(name = "TB_APPS", uniqueConstraints = {@UniqueConstraint(columnNames = {"CODE"})})
public class App extends AppBase {

    private static final long serialVersionUID = 1L;

    public App() {
    }
}