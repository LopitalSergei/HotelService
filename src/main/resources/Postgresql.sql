select "Person_id" from "Persons" where "Name" = 'Austin Boswell'
insert into "Persons" ("Name") values ('')
insert into "Aplications" ("Client_name", "Places", "Room_class", "Duration_hours") values ('', '', '', '')
select * from "Aplications"
insert into "Rooms" ("Room_number","Places", "Room_class", "Status") values ('29', '4', 'SECOND_CLASS', 'IN_PROGRESS')
select * from "Rooms" where "Places">=3 and "Room_class" = 'SECOND_CLASS'
truncate "Aplications"
select * from "Aplications"
select count(*) from "Aplications"
select * from  "Rooms" where "Status" is null
select * from  "Rooms"
update "Rooms" set "Status" = 'BOOKED' where "Room_number" = '1'
select * from "Requests"
insert into "Persons" ("Name") values ('Laptev Sergei')
select * from "Aplications"
delete from "Aplications" where "Aplication_id" = '1'
insert into "Requests" ("Room_number", "Places", "Room_class", "Duration_hours", "Request_status", "Person_id") values ('', '', '', '', '', '')