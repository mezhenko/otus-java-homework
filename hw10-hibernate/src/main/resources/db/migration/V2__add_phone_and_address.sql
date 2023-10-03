create table phone
(
    id   bigserial not null primary key,
    number varchar(50),
    client_id bigint
);

create table address
(
    id   bigserial not null primary key,
    street varchar(50)
);

alter table client
    add column address_id bigint;


alter table client add constraint FKb137u2cl2ec0otae32lk5pcl2 foreign key (address_id) references address;
alter table phone add constraint FKtbfp4gd90boxgx6wfbqeeq6si foreign key (client_id) references client;