create sequence seq_member increment by 1 start with 1;
create table member
(
    id      number(11)   not null primary key,
    email   varchar2(30) not null unique,
    pw      varchar2(20) not null,
    name    varchar2(30) not null,
    phone   varchar2(50),
    address varchar2(100)
);
drop sequence seq_member;
drop table member;

select * from member;


