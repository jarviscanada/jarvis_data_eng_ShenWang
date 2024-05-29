# Introduction

# SQL Quries

###### Table Setup (DDL)

For each table. We stated the column names and the data type that will be stored, and also the constraints (ex. for memid it will be integer and it cannot be NULL)
We have two constraints, one called members_pk, and second fk_members_recommendedby. For the PRIMARY KEY constraints, this will ensure the memid is not null and is unique. 
The second constraints is a FOREIGN KEY constraint where the recommendedby col will reference to the memid from cd.member. This means a entry cannot be created if the recommendedby doesn't match a pre existing memid. The ON DELETE SET NULL means if the referenced value,memid, is deleted, then the recommendedby will be set to NULL to ensure data integrity. 

Same case for the other two tables is just the contraints are a little different. Table two has one primary key constraints. Table two has two foreign key constraints without the delete set to null part. 
CREATE TABLE cd.members
    (
       memid integer NOT NULL, 
       surname character varying(200) NOT NULL, 
       firstname character varying(200) NOT NULL, 
       address character varying(300) NOT NULL, 
       zipcode integer NOT NULL, 
       telephone character varying(20) NOT NULL, 
       recommendedby integer,
       joindate timestamp NOT NULL,
       CONSTRAINT members_pk PRIMARY KEY (memid),
       CONSTRAINT fk_members_recommendedby FOREIGN KEY (recommendedby)
            REFERENCES cd.members(memid) ON DELETE SET NULL
    );

      CREATE TABLE cd.facilities
    (
       facid integer NOT NULL, 
       name character varying(100) NOT NULL, 
       membercost numeric NOT NULL, 
       guestcost numeric NOT NULL, 
       initialoutlay numeric NOT NULL, 
       monthlymaintenance numeric NOT NULL, 
       CONSTRAINT facilities_pk PRIMARY KEY (facid)
    );

        CREATE TABLE cd.bookings
    (
       bookid integer NOT NULL, 
       facid integer NOT NULL, 
       memid integer NOT NULL, 
       starttime timestamp NOT NULL,
       slots integer NOT NULL,
       CONSTRAINT bookings_pk PRIMARY KEY (bookid),
       CONSTRAINT fk_bookings_facid FOREIGN KEY (facid) REFERENCES cd.facilities(facid),
       CONSTRAINT fk_bookings_memid FOREIGN KEY (memid) REFERENCES cd.members(memid)
    );
Reference:
1. https://pgexercises.com/gettingstarted.html
2. https://pgexercises.com/questions
