#! /bin/sh

#Setup and validate arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#check # of args
if [ $# -ne 5 ]; then
  echo 'Illegal number of parameters'
  exit 1
fi

#retrieve hardware info and storing them into vars
# hardware info
hostname=$(hostname -f)
cpu_number=$(nproc)
cpu_architecture=$(uname -p)
cpu_model=$(grep "model name" /proc/cpuinfo | head -n 1 | awk -F: '{print $2}')
cpu_mhz=$(grep "cpu MHz" /proc/cpuinfo | head -n 1 | awk -F: '{print $2}')
l2_cache=$(lscpu| grep "L2 cache"|awk -F: '{print $2}'| xargs|awk '{print $1}')
total_mem=$(vmstat --unit M | tail -1 | awk '{print $4}')
timestamp=$(date '+%Y-%m-%d %H:%M:%S')
#PSQL command: Inserts host info data into host_info table
#Note: be careful with double and single quotes

insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, \"timestamp\", total_mem) VALUES ('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $l2_cache, '$timestamp', $total_mem);"

#set up env var for pql cmd
export PGPASSWORD=$psql_password
#insert data into a database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?
