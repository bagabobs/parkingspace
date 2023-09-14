create table car_park_entities (
    car_park_num varchar not null,
    address varchar not null,
    latitude double precision not null,
    longitude double precision not null,
    total_lots int,
    available_lots int,
    constraint car_park_pk primary key(car_park_num)
);