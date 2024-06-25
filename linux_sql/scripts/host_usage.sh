#! /bin/sh

#Setup and validate arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#check # of args
if [ $# -ne 5 ]; then
  echo "Illegal number of parameters"
  exit 1
fi

#Save machine statistics in MB and current machine hostname to variables
#retrieve hardware specification variables
#xargs is a trick to trim leading and  trailing white spaces
vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)
memory_free=$(vmstat --unit M | tail -1 | awk -v col="4" '{print $col}')
cpu_idle=$(vmstat | tail -1 | awk '{print $15}')
cpu_kernel=$(vmstat | tail -1 | awk '{print $14}')
disk_io=$(vmstat --unit M -d | tail -1 | awk -v col="10" '{print $col}')
disk_available=$(df -BM / | tail -1 | awk '{print $4}' | sed 's/M//')
# Current time in `2019-11-26 14:40:19` UTC format
timestamp=$(date '+%Y-%m-%d %H:%M:%S')

#set up env var for pql cmd
export PGPASSWORD=$psql_password
# Subquery to find matching id in host_info table
host_id=$(psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -t -c "SELECT id FROM host_info WHERE hostname = '$hostname';")
#PSQL command: Inserts server usage data into host_usage table
#Note: be careful with double and single quotes
insert_stmt="INSERT INTO host_usage (\"timestamp\", host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available) VALUES ('$timestamp', $host_id, $memory_free, $cpu_idle, $cpu_kernel, $disk_io, $disk_available);"
#insert data into a database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?
