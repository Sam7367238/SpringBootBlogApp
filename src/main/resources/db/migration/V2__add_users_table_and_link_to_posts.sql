create table users
(
    id       bigint auto_increment
        primary key,
    username varchar(20)  not null,
    password varchar(255) not null
);

alter table posts
    add user_id bigint not null;

alter table posts
    add constraint posts_users_id_fk
        foreign key (user_id) references users (id);