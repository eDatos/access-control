package org.siemac.metamac.access.control.web.client.model.ds;

import org.siemac.metamac.access.control.web.client.model.record.AppRecord;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;


public class AppDS extends DataSource {
    
    public AppDS() {
        DataSourceTextField code = new DataSourceTextField(AppRecord.CODE);
        code.setPrimaryKey(true);
        addField(code);
        
        DataSourceTextField title = new DataSourceTextField(AppRecord.TITLE);
        addField(title);
        
        DataSourceTextField description = new DataSourceTextField(AppRecord.DESCRIPTION);
        addField(description);
        
        setClientOnly(true);
    }

}
