package org.siemac.metamac.access.control.web.client.model.ds;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class AppDS extends DataSource {

    public static final String CODE        = "code";
    public static final String TITLE       = "title";
    public static final String DESCRIPTION = "desc";
    public static final String APP_DTO     = "dto";

    public AppDS() {
        DataSourceTextField code = new DataSourceTextField(CODE);
        code.setPrimaryKey(true);
        addField(code);

        DataSourceTextField title = new DataSourceTextField(TITLE);
        addField(title);

        DataSourceTextField description = new DataSourceTextField(DESCRIPTION);
        addField(description);

        setClientOnly(true);
    }
}
