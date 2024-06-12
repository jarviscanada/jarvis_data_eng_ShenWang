# Introduction
This two-part SQL Introduction Project aims to familiarize trainees with the basics of SQL, specifically DML and DDL. The first part focused on learning through an interactive exercise, where I mastered DML and DDL statements and various clause syntax. The second part involved a three-table database for a booking system. I set up the PSQL server using Docker and loaded sample data using a SQL file. I then wrote queries to simulate real-life usage of the table. Through this project, I can now create a simple booking database system and write complex constraints and queries to retrieve specific data.


# SQL Quries

#### Table Setup (DDL)
For each table, we specified the column names, data types, and constraints. For example, the column "memid" is an integer and cannot be NULL.

We defined two constraints: "members_pk" and "fk_members_recommendedby." The PRIMARY KEY constraint "members_pk" ensures that "memid" is not null and is unique. The FOREIGN KEY constraint "fk_members_recommendedby" specifies that the "recommendedby" column references the "memid" from the "cd.members" table. This ensures that an entry cannot be created if "recommendedby" does not match an existing "memid." The "ON DELETE SET NULL" clause ensures that if the referenced "memid" is deleted, the "recommendedby" column is set to NULL, to maintain data integrity.

The other two tables follow similar principles, with slight variations in constraints. Table two has one PRIMARY KEY constraint, and two FOREIGN KEY constraints without the "ON DELETE SET NULL" clause.
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

#### Question 1-29
Solutions in queries.sql

Reference:
1. https://pgexercises.com/gettingstarted.html
2. https://pgexercises.com/questions
