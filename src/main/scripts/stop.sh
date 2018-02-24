#!/bin/sh

function stop_jetty {
    jetty_process=`jps | grep Main | cut -d ' ' -f1`
    kill -9 ${jetty_process}
}

stop_jetty