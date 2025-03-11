--modifying data
INSERT INTO cd.facilities VALUES (9, 'Spa', 20, 30, 100000, 800);

INSERT into cd.facilities
(facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
(SELECT max(facid) FROM cd.facilities)+1, 'Spa', 20, 30, 100000, 800;

UPDATE cd.facilities SET initialoutlay=10000 WHERE name='Tennis Court 2';

UPDATE cd.facilities facs
SET
membercost = facs2.membercost * 1.1,
guestcost = facs2.guestcost * 1.1
FROM (SELECT * FROM cd.facilities WHERE facid = 0) facs2
WHERE facs.facid

DELETE FROM cd.bookings;

DELETE from cd.members
WHERE memid=37;

--Baiscs
SELECT facid, name, membercost, monthlymaintenance FROM cd.facilities
WHERE membercost<monthlymaintenance/50;

SELECT name FROM cd.facilities
WHERE name LIKE '%Tennis%';

SELECT * FROM cd.facilities
WHERE facid in (1,5)

SELECT memid, surname, firstname, joindate
FROM cd.members
WHERE joindate >= '2012-09-01';

SELECT surname FROM cd.members
UNION SELECT name FROM cd.facilities;

--join
SELECT starttime FROM cd.bookings
INNER JOIN cd.members ON cd.bookings.memid=cd.members.memid
WHERE surname='Farrell' AND firstname='David';

SELECT starttime, name FROM cd.bookings
INNER JOIN cd.facilities ON cd.bookings.facid=cd.facilities.facid
WHERE starttime >= '2012-09-21%'
and starttime < '2012-09-22%'
and name in ('Tennis Court 2','Tennis Court 1')
ORDER BY starttime;

SELECT mems.firstname AS memfname, mems.surname AS memsname, recs.firstname AS recfname, recs.surname AS recsname
FROM cd.members mems
LEFT OUTER JOIN cd.members recs
ON recs.memid = mems.recommendedby
ORDER BY memsname, memfname;

select DISTINCT recs.firstname as firstname, recs.surname as surname
from cd.members mems
inner join cd.members recs on recs.memid = mems.recommendedby
order by surname, firstname;

SELECT firstname || ' '||surname as member,(
SELECT  firstname || ' '||surname as member from cd.members mem2
WHERE mem1.recommendedby=mem2.memid)
FROM cd.members mem1;

--Aggregation
SELECT recommendedby, COUNT(recommendedby) as count
FROM cd.members
WHERE recommendedby!=0
GROUP BY recommendedby
ORDER BY recommendedby;

select facid, sum(slots) as "Total Slots"
from cd.bookings
group by facid
order by facid;
select facid, sum(slots) as "Total Slots"
from cd.bookings
WHERE starttime >= '2012-09-01'
and starttime < '2012-10-01'
GROUP by facid
order by sum(slots);

select facid, extract(month from starttime) as month, sum(slots) as "Total Slots"
from cd.bookings
where extract(year from starttime) = 2012
group by facid, month
order by facid, month;

SELECT COUNT(*) FROM (SELECT memid, COUNT(memid) AS booking_count
FROM cd.bookings
GROUP BY memid
HAVING COUNT(memid) >= 1) as newtable;

select mems.surname, mems.firstname, mems.memid, min(bks.starttime) as starttime
from cd.bookings bks
inner join cd.members mems on
mems.memid = bks.memid
where starttime >= '2012-09-01'
group by mems.surname, mems.firstname, mems.memid
order by mems.memid;

SELECT  (SELECT COUNT(*) AS total_rows
FROM cd.members), firstname, surname FROM cd.members
ORDER BY joindate;

SELECT ROW_NUMBER() OVER (ORDER BY joindate) AS counter,firstname, surname FROM cd.members
ORDER BY joindate;

SELECT facid, total from (
select facid, total, rank() over (order by total desc) rank from (
select facid, sum(slots) total
from cd.bookings
group by facid
) as sumslots
) as ranked
where rank = 1

--STRING
select surname || ', ' || firstname as name from cd.members;

SELECT memid, telephone
FROM cd.members
WHERE telephone ~ '[()]';

SELECT substr (mems.surname,1,1) AS letter, count(*) AS COUNT
FROM cd.members mems
GROUP BY letter ORDER BY letter;