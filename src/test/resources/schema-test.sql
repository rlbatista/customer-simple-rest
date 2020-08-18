create table customers
(
   id bigint not null,
   cpf varchar (11) not null,
   name varchar (50) not null,
   
   street varchar (60) not null,
   number varchar (10) not null,
   complement varchar (50),
   neighborhood varchar (30) not null,
   city varchar (30) not null,
   state_code varchar (2) not null,
   primary key (id)
);

alter table customers add constraint uk_customer_cpf unique (cpf);
create sequence sq_customer start 1 increment 1;
