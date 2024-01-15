create database recipebook;

create table users (
id int not null primary key auto_increment,
username varchar(32),
password varchar(32),
created timestamp,
is_active boolean
);

create table recipes (
id int not null primary key auto_increment,
recipeName varchar(32),
cookingSteps varchar(333),
imageName varchar(32),
difficulty varchar(32),
cookingTime varchar(32),
ingredients varchar(132);
);