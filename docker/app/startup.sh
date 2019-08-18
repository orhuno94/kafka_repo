#!/bin/bash

mongod &

sleep 10;

# Start the first process
./zookeeper_start.sh -D
status=$?
if [ $status -ne 0 ]; then
	echo "Failed to start zookeeper_start: $status"
	exit $status
fi

# Start the second process
./kafka_start.sh -D
status=$?
if [ $status -ne 0 ]; then
	echo "Failed to start kafka_start: $status"
	exit $status
fi

./run_logapp.sh -D


#	while /bin/true; do
#	  ps aux |grep zookeeper_start|grep -q -v grep
#	    PROCESS_1_STATUS=$?
#		ps aux |grep kafka_start|grep -q -v grep
#		PROCESS_2_STATUS=$?
#		  # If the greps above find anything, they will exit with 0 status
#		    # If they are not both 0, then something is wrong
#	   	    if [ $PROCESS_1_STATUS -ne 0 -o $PROCESS_2_STATUS -ne 0 ]; then
#				  echo "One of the processes has already exited."
#				      exit -1
#		    fi
#		    sleep 60
#		    done
while /bin/true; do
	
	ps aux | grep java |grep -q -v grep
	PROCESS_STATUS=$?
	
	# If the greps above find anything, they will exit with 0 status
	# If they are not both 0, then something is wrong
	if [ $PROCESS_STATUS -ne 0 ]; then
		echo "One of the processes has already exited."
		exit -1
	fi
	sleep 60
done
