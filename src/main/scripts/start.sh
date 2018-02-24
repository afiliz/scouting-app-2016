#!/bin/sh
source stop.sh

function install_war {
    mkdir www
    cd www
    jar xf ../$1
}

war=$1
mode=$2
export war
export mode

if [ ! -d www ]
then
    echo "installing $war"
    install_war ${war}
else
    eval $(stat -s www)
    www_mod=$st_mtime
    eval $(stat -s $war)
    war_mod=$st_mtime
    if [ $war_mod -gt $www_mod ]
    then
        echo "re-installing $war"
        rm -rf www
        install_war $war
    else
        echo "www and $war are up to date"
        cd www
    fi
fi
CP="WEB-INF/classes"
for entry in WEB-INF/lib/*.jar
do
    CP=$CP:$entry
done

date=`date "+%m-%d-%Y-%H-%M-%S"`
log_file="/Users/adminstrator/logs/${date}.log"
touch $log_file
echo "creating log_file in $log_file"
if [ ${mode} = "" ]; then
     java -cp "$CP" com.scouting2016.tko.Main production > ${log_file} &
else
    java -cp "$CP" com.scouting2016.tko.Main ${mode} > ${log_file} &
fi