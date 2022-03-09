create sequence seq_blog increment by 1 start with 1;
create table blog
(
    id      number(11)   not null primary key,
    name    varchar2(30) not null,
    email   varchar2(30) not null unique,
    title   varchar2(50),
    content varchar2(100)
);
drop sequence seq_blog;
drop table blog;


select * from blog;