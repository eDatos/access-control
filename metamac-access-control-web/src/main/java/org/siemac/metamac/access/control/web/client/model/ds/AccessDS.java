package org.siemac.metamac.access.control.web.client.model.ds;

import org.siemac.metamac.access.control.web.client.model.record.AccessRecord;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class AccessDS extends DataSource {

    public AccessDS() {
        DataSourceIntegerField id = new DataSourceIntegerField(AccessRecord.ID, "Id");
        id.setPrimaryKey(true);
        addField(id);

        DataSourceTextField user = new DataSourceTextField(AccessRecord.USER, "User");
        addField(user);

        DataSourceTextField role = new DataSourceTextField(AccessRecord.ROLE, "Role");
        addField(role);

        DataSourceTextField app = new DataSourceTextField(AccessRecord.APP, "Application");
        addField(app);

        DataSourceTextField date = new DataSourceTextField(AccessRecord.REMOVAL_DATE, "Dischared date");
        addField(date);

        DataSourceTextField operation = new DataSourceTextField(AccessRecord.OPERATION, "Operation");
        addField(operation);

        setClientOnly(true);
    }

}
