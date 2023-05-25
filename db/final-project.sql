create database bookstore;
use bookstore;

create table user_type(
    id int auto_increment primary key ,
    type varchar(255)
);
alter table user_type add index idx_type(type);

create table user(
    id int auto_increment primary key ,
    email varchar(255) unique ,
    password varchar(255),
    name varchar(255),
    user_type_id int,
    constraint fk_user_type_id foreign key (user_type_id) references user_type(id)
);
alter table user add index idx_email(email);
alter table user add index idx_password(password);

create table user_contact(
    id int auto_increment primary key ,
    phone varchar(255),
    address varchar(255),
    user_id int,
    constraint fk_user_id foreign key (user_id) references user(id)
);

create table book_genre(
    id int auto_increment primary key ,
    name varchar(255)
);

create table author(
    id int auto_increment primary key ,
    name varchar(255),
    address varchar(255),
    book_id int,
    constraint fk_author_book_id foreign key (book_id) references book(id)
);

create table book(
    id int auto_increment primary key ,
    title varchar(255),
    description varchar(255),
    price int,
    quantity int,
    genre_id int,
    constraint fk_genre_id foreign key (genre_id) references book_genre(id)
);
alter table book add index idx_title(title);

create table status(
    id int auto_increment primary key ,
    name varchar(255)
);

create table cart(
    id int auto_increment primary key ,
    book_id int,
    constraint fk_cart_book_id foreign key (book_id) references book(id),
    quantity int,
    status_id int,
    constraint fk_cart_status_id foreign key (status_id) references status(id),
    user_id int,
    constraint fk_cart_user_id foreign key (user_id) references user(id)
);

create table orders(
    id int auto_increment primary key ,
    status_id int,
    constraint fk_order_status_id foreign key (status_id) references status(id),
    user_id int,
    constraint fk_order_user_id foreign key (user_id) references user(id),
    book_id int,
    constraint fk_order_book_id foreign key (book_id) references book(id),
    quantity int,
    order_date datetime
);

alter table orders add column cost int default 0;

-- Dummy data for user_type table
INSERT INTO user_type (type) VALUES
('Admin'),
('Employee'),
('Customer');

-- Dummy data for user table
INSERT INTO user (email, password, name, user_type_id) VALUES
('john@example.com', 'password123', 'John Doe', 3),
('jane@example.com', 'pass456', 'Jane Smith', 2),
('admin@example.com', 'adminpass', 'Admin User', 1);

-- Dummy data for user_contact table
INSERT INTO user_contact (phone, address, user_id) VALUES
('123-456-7890', '123 Main St', 1),
('987-654-3210', '456 Elm St', 2),
('555-123-4567', '789 Oak St', 3);

-- Dummy data for book_genre table
INSERT INTO book_genre (name) VALUES
('Fiction'),
('Non-Fiction'),
('Mystery');

-- Dummy data for book table
INSERT INTO book (title, description, price, quantity, genre_id) VALUES
('Book 1', 'Description for Book 1', 10, 5, 1),
('Book 2', 'Description for Book 2', 15, 3, 2),
('Book 3', 'Description for Book 3', 20, 8, 1);

-- Dummy data for author table
INSERT INTO author (name, address, book_id) VALUES
('John Smith', '123 Author St', 1),
('Jane Johnson', '456 Writer Ave', 2),
('Robert Roberts', '789 Novelist Rd', 3);

-- Dummy data for status table
INSERT INTO status (name) VALUES
('Pending'),
('Processing'),
('Shipped'),
('Delivered');

-- Dummy data for cart table
INSERT INTO cart (book_id, quantity, status_id, user_id) VALUES
(1, 2, 1, 2),
(2, 1, 2, 3),
(3, 3, 1, 1);

-- Dummy data for orders table
INSERT INTO orders (status_id, user_id, book_id, quantity, order_date) VALUES
(3, 1, 2, 1, NOW()),
(4, 3, 3, 2, NOW()),
(2, 2, 1, 3, NOW());


Select * from user where password='password123';

select b.id, b.title, b.description, b.price, b.quantity, bg.name genre, a.name author
from book b
join book_genre bg on bg.id = b.genre_id
join author a on a.book_id = b.id
order by b.id desc ;

select b.id, b.title, b.description, b.price, b.quantity, bg.name genre, a.name author
from book b
join book_genre bg on bg.id = b.genre_id
join author a on a.book_id = b.id
where b.id = 1;

select  b.id, b.title, b.description, b.price, b.quantity, bg.name genre, a.name author
from book b
join book_genre bg on bg.id = b.genre_id
join author a on b.id = a.book_id
where b.title like '%F%' or b.description like '%F%' or bg.name like '%F%' or a.name like '%F%'
order by b.id desc ;

select u.id, u.email, u.name, ut.type, uc.phone, uc.address
from user u
join user_type ut on ut.id = u.user_type_id
join user_contact uc on u.id = uc.user_id
where u.id = 1;

select u.id, u.email, u.name, ut.type
from user u
join user_type ut on ut.id = u.user_type_id
where ut.type = 'Customer';

INSERT INTO user (email, password, name, user_type_id) VALUES
('debrah@example.com', 'password123', 'Emmanuel Debrah', 3);

select c.id, b.title, b.description, c.quantity, s.name status, (b.price * c.quantity) cost
from cart c
join book b on b.id = c.book_id
join status s on s.id = c.status_id
where c.id = 1;

select *
from cart;

update cart set quantity = quantity + 1 where id = 1;


select o.cost, o.quantity, o.id, u.name, u.email, b.title, b.description
from orders o
join book b on b.id = o.book_id
join user u on o.user_id = u.id
where user_id = 1;

select o.cost, o.quantity, o.id, u.name, u.email, b.title, b.description
from orders o
join book b on b.id = o.book_id
join user u on o.user_id = u.id
where o.id = 1;


