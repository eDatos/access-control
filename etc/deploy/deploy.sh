#!/bin/bash

HOME_PATH=metamac-access-control-web
TRANSFER_PATH=$HOME_PATH/tmp
DEPLOY_TARGET_PATH=/servers/edatos-internal/tomcats/edatos-internal01/webapps
ENVIRONMENT_RELATIVE_PATH_FILE=WEB-INF/classes/metamac/environment.xml
LOGBACK_RELATIVE_PATH_FILE=WEB-INF/classes/logback.xml
RESTART=1

if [ "$1" == "--no-restart" ]; then
    RESTART=0
fi

scp -o ProxyCommand="ssh -W %h:%p deploy@estadisticas.arte-consultores.com" -r etc/deploy deploy@192.168.10.16:$TRANSFER_PATH
scp -o ProxyCommand="ssh -W %h:%p deploy@estadisticas.arte-consultores.com" metamac-access-control-web/target/access-control-internal-*.war deploy@192.168.10.16:$TRANSFER_PATH/access-control-internal.war
ssh -o ProxyCommand="ssh -W %h:%p deploy@estadisticas.arte-consultores.com" deploy@192.168.10.16 <<EOF

    chmod a+x $TRANSFER_PATH/deploy/*.sh;
    . $TRANSFER_PATH/deploy/utilities.sh

    ###
    # ACCESS-CONTROL-INTERNAL
    ###

    if [ $RESTART -eq 1 ]; then
        sudo service edatos-internal01 stop
        checkPROC "edatos-internal"
    fi

    # Update Process
    sudo rm -rf $DEPLOY_TARGET_PATH/access-control-internal
    sudo mv $TRANSFER_PATH/access-control-internal.war $DEPLOY_TARGET_PATH/access-control-internal.war
    sudo unzip $DEPLOY_TARGET_PATH/access-control-internal.war -d $DEPLOY_TARGET_PATH/access-control-internal
    sudo rm -rf $DEPLOY_TARGET_PATH/access-control-internal.war

    # Restore Configuration
    sudo cp $HOME_PATH/environment.xml $DEPLOY_TARGET_PATH/access-control-internal/$ENVIRONMENT_RELATIVE_PATH_FILE
    # Take care!, it's not necessary to restore the logback.xml file, it's externally configured in the applicationContext.xml file
    # sudo cp $HOME_PATH/logback.xml $DEPLOY_TARGET_PATH/access-control-internal/$LOGBACK_RELATIVE_PATH_FILE

    if [ $RESTART -eq 1 ]; then
        sudo chown -R edatos-internal.edatos-internal /servers/edatos-internal
        sudo service edatos-internal01 start
    fi

    echo "Finished deploy"

EOF