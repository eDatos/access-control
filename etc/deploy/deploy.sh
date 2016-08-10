#!/bin/sh

TMP_PATH=/servers/metamac/tmp
DEPLOY_TARGET_PATH=/servers/metamac/tomcats/metamac01/webapps
ENVIRONMENT_RELATIVE_PATH_FILE=WEB-INF/classes/metamac/environment.xml
LOGBACK_RELATIVE_PATH_FILE=WEB-INF/classes/logback.xml

scp -r etc/deploy deploy@estadisticas.arte-consultores.com:~/metamac-access-control-web:
scp metamac-access-control-web/target/access-control-internal-*.war deploy@estadisticas.arte-consultores.com:~/metamac-access-control-web/access-control-internal.war
ssh deploy@estadisticas.arte-consultores.com <<EOF

    chmod a+x deploy/*.sh;
    . deploy/utilities.sh
    
    sudo service metamac01 stop
    checkPROC "metamac"
    
    
    ###
    # ACCES-CONTROL-INTERNAL
    ###
    # Backup Configuration
    sudo mv $DEPLOY_TARGET_PATH/access-control-internal/$ENVIRONMENT_RELATIVE_PATH_FILE $TMP_PATH/environment.xml_access-control-internal_tmp
    sudo mv $DEPLOY_TARGET_PATH/access-control-internal/$LOGBACK_RELATIVE_PATH_FILE $TMP_PATH/logback.xml_access-control-internal_tmp
    
    # Update Process
    sudo mv metamac-access-control-web/access-control-internal.war $DEPLOY_TARGET_PATH/access-control-internal.war
    sudo unzip $DEPLOY_TARGET_PATH/access-control-internal.war -d $DEPLOY_TARGET_PATH
    sudo rm -rf $DEPLOY_TARGET_PATH/access-control-internal.war
    
    # Restore Configuration
    sudo mv $TMP_PATH/environment.xml_access-control-internal_tmp $DEPLOY_TARGET_PATH/access-control-internal/$ENVIRONMENT_RELATIVE_PATH_FILE
    sudo mv $TMP_PATH/logback.xml_access-control-internal_tmp $DEPLOY_TARGET_PATH/access-control-internal/$LOGBACK_RELATIVE_PATH_FILE
    
    
    sudo chown -R metamac.metamac /servers/metamac
    sudo service metamac01 start
    checkURL "http://estadisticas.arte-consultores.com/access-control-internal" "metamac01"

EOF
