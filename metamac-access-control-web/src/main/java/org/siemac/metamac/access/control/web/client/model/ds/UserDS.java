package org.siemac.metamac.access.control.web.client.model.ds;

import org.siemac.metamac.access.control.web.client.model.record.UserRecord;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;


public class UserDS extends DataSource {

    public UserDS() {
        DataSourceIntegerField id = new DataSourceIntegerField(UserRecord.ID, "Id");
        id.setPrimaryKey(true);
        addField(id);
        
        DataSourceTextField username = new DataSourceTextField(UserRecord.USERNAME, "Username");
        addField(username);
        
        DataSourceTextField name = new DataSourceTextField(UserRecord.NAME, "Title");
        addField(name);
        
        DataSourceTextField surname = new DataSourceTextField(UserRecord.SURNAME, "Surname");
        addField(surname);
        
        DataSourceTextField mail = new DataSourceTextField(UserRecord.MAIL, "mail");
        addField(mail);
        
    }
    
}
