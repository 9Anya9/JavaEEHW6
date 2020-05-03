create table if not exists books
(
    id int primary key auto_increment,
    title varchar(50) not null,
    author varchar(50) not null,
    isbn varchar(50) not null
);

create table if not exists users
(
    id       int primary key auto_increment,
    login    varchar(30) not null,
    password varchar(40) not null,
    custom_auth_field varchar(50) not null,
    unique uniq_login (login)
);

create table if not exists permissions
(
    id         int primary key auto_increment,
    permission varchar(30) not null,
    unique uniq_permission (permission)
);

create table if not exists user_to_permissions (
    user_id int not null,
    permission_id int not null,
    constraint fk_user_to_permission_user foreign key (user_id) references users(id),
    constraint fk_user_to_permission_permission foreign key (permission_id) references permissions(id)
);

create table if not exists user_to_favbooks (
    book_id int not null,
    user_id int not null,
    constraint fk_user_to_liked_book_user foreign key (user_id) references users (id),
    constraint fk_user_to_liked_book_book foreign key (book_id) references books (id)
);

insert into users (login, password, custom_auth_field) values
    ('admin', 'password', 'admin_caf'),
    ('user', 'password', 'user_caf');

insert into permissions (permission) values
    ('ADD_BOOK'),
    ('ADD_TO_FAVORITES'),
    ('VIEW_BOOKS');

insert into books (title, author, isbn) values
    ('JavaEE', 'AS', '1'),
    ('Android', 'GT', '2'),
    ('IOS', 'FK', '3');

insert into user_to_permissions (user_id, permission_id) values
    ((select id from users where login = 'admin'), (select id from permissions where permission = 'ADD_BOOK')),
    ((select id from users where login = 'admin'), (select id from permissions where permission = 'VIEW_BOOKS')),
    ((select id from users where login = 'user'), (select id from permissions where permission = 'VIEW_BOOKS')),
    ((select id from users where login = 'user'), (select id from permissions where permission = 'ADD_TO_FAVORITES'));