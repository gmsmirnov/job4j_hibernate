insert into body(name, serial_number) values('V10', 'ZFA000111222333444');
insert into body(name, serial_number) values('V11', 'ZFA111222333444555');
insert into body(name, serial_number) values('V12', 'ZFA222333444555666');
insert into body(name, serial_number) values('V13', 'ZFA333444555666777');
insert into body(name, serial_number) values('V14', 'ZFA444555666777888');
insert into body(name, serial_number) values('V15', 'ZFA555666777888999');
insert into body(name, serial_number) values('V16', 'ZFA666777888999000');
insert into body(name, serial_number) values('V17', 'ZFA777888999000111');

insert into engine(name, serial_number) values('VTEC-10', '21A530000');
insert into engine(name, serial_number) values('VTEC-20', '21A530001');
insert into engine(name, serial_number) values('VTEC-30', '21A530002');
insert into engine(name, serial_number) values('VTEC-40', '21A530003');
insert into engine(name, serial_number) values('VTEC-50', '21A530004');
insert into engine(name, serial_number) values('VTEC-60', '21A530005');
insert into engine(name, serial_number) values('VTEC-70', '21A530006');
insert into engine(name, serial_number) values('VTEC-80', '21A530007');

insert into transmission(name, serial_number) values('АКПП-1', 'A111111');
insert into transmission(name, serial_number) values('АКПП-2', 'A222222');
insert into transmission(name, serial_number) values('АКПП-3', 'A333333');
insert into transmission(name, serial_number) values('АКПП-4', 'A444444');
insert into transmission(name, serial_number) values('МКПП-1', 'M111111');
insert into transmission(name, serial_number) values('МКПП-2', 'M222222');
insert into transmission(name, serial_number) values('МКПП-3', 'M333333');
insert into transmission(name, serial_number) values('МКПП-4', 'M444444');

insert into car(name, body_id, engine_id, transmission_id) values('car-1', '1', '2', '3');
insert into car(name, body_id, engine_id, transmission_id) values('car-2', '2', '3', '4');
insert into car(name, body_id, engine_id, transmission_id) values('car-3', '4', '5', '6');
insert into car(name, body_id, engine_id, transmission_id) values('car-4', '1', '5', '6');
insert into car(name, body_id, engine_id, transmission_id) values('car-5', '2', '2', '3');