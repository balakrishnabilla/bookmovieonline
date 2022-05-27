
insert into theatre (theatre_id, theatre_name, city_id, movie_id) values
(1, 'Aries Plex', 1,1000),
(2, 'Mall of travancore' , 1,1001) , 
(3, 'Centrum mall', 1, 1002);

insert into show (theatre_id, show_date, show_time, total_seats, available_seats) values
(1, '2018-12-26', '11:30', 100, 100 ),
(1, '2018-12-26', '16:30', 100, 100 ),
(2, '2018-12-26', '11:30', 100, 100 ),
(2, '2018-12-26', '16:30', 100, 76 ),
(2, '2018-12-27', '20:30', 100, 0 );


insert into seat (show_id, seat_num, status,price) values
(1, 'a1',0,100.00),
(1, 'a2',1,100.00),
(1, 'a3',1,100.00),
(1, 'b1',0,300.00),
(2, 'b2',1,100.00),
(2, 'b3',1,100.00),
(3, 'c1',0,200.00),
(3, 'c2',1,300.00);