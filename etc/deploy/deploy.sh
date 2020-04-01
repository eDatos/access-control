#!/bin/bash

HOME_PATH=metamac-access-control-web
TRANSFER_PATH=$HOME_PATH/tmp
DEPLOY_TARGET_PATH_INTERNAL=/servers/edatos-internal/tomcats/edatos-internal01/webapps
ENVIRONMENT_RELATIVE_PATH_FILE=WEB-INF/classes/metamac/environment.xml
LOGBACK_RELATIVE_PATH_FILE=WEB-INF/classes/logback.xml
RESTART=1

if [ "$1" == "--no-restart" ]; then
    RESTART=0
fi


scp -r etc/deploy deploy@estadisticas.arte-consultores.com:$TRANSFER_PATH
scp metamac-access-control-web/target/access-control-internal-*.war deploy@estadisticas.arte-consultores.com:$TRANSFER_PATH/access-control-internal.war
ssh deploy@estadisticas.arte-consultores.com <<EOF

    chmod a+x $TRANSFER_PATH/deploy/*.sh;
    . $TRANSFER_PATH/deploy/utilities.sh

    ###
    # ACCES-CONTROL-INTERNAL
    ###

    if [ $RESTART -eq 1 ]; then
        sudo service edatos-internal01 stop
        checkPROC "edatos-internal"
    fi

    # Update Process
    sudo rm -rf $DEPLOY_TARGET_PATH_INTERNAL/access-control-internal
    sudo mv $TRANSFER_PATH/access-control-internal.war $DEPLOY_TARGET_PATH_INTERNAL/access-control-internal.war
    sudo unzip $DEPLOY_TARGET_PATH_INTERNAL/access-control-internal.war -d $DEPLOY_TARGET_PATH_INTERNAL/access-control-internal
    sudo rm -rf $DEPLOY_TARGET_PATH_INTERNAL/access-control-internal.war

    # Restore Configuration
    sudo cp $HOME_PATH/environment.xml $DEPLOY_TARGET_PATH_INTERNAL/access-control-internal/$ENVIRONMENT_RELATIVE_PATH_FILE
    sudo cp $HOME_PATH/logback.xml $DEPLOY_TARGET_PATH_INTERNAL/access-control-internal/$LOGBACK_RELATIVE_PATH_FILE

    if [ $RESTART -eq 1 ]; then
        sudo chown -R edatos-internal.edatos-internal /servers/edatos-internal
        sudo service edatos-internal01 start
    fi

    # checkURL "http://estadisticas.arte-consultores.com/access-control-internal" "metamac01"
    echo "Finished deploy"

EOF