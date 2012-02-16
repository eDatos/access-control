package org.siemac.metamac.access.control.web.client.model.ds;

import org.siemac.metamac.access.control.web.client.model.record.AccessRecord;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;


public class AccessDS extends DataSource {

    public AccessDS() {
        DataSourceIntegerField id = new DataSourceIntegerField(AccessRecord.ID);
        id.setPrimaryKey(true);
        addField(id);
        
        DataSourceTextField user = new DataSourceTextField(AccessRecord.USER);
        addField(user);
        
        DataSourceTextField role = new DataSourceTextField(AccessRecord.ROLE);
        addField(role);
        
        DataSourceTextField app = new DataSourceTextField(AccessRecord.APP);
        addField(app);
        
        DataSourceTextField operation = new DataSourceTextField(AccessRecord.OPERATION);
        addField(operation);
        
        setClientOnly(true);
    }
    
}
