create table posts
(
    uuid       binary(16)   not null
        primary key,
    title      varchar(255) not null,
    content    text         not null,
    created_at datetime     null
);

