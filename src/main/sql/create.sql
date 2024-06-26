DROP SCHEMA IF EXISTS savoreco CASCADE;
CREATE SCHEMA savoreco;
SET SCHEMA 'savoreco';
CREATE EXTENSION postgis;
CREATE EXTENSION pg_trgm;

CREATE TABLE address
(
    street       varchar(256),
    city         varchar(512),
    country_code char(2) default 'IT' not null,
    zipcode      varchar(16),
    lat           double precision default 0 not null,
    lon           double precision default 0 not null,

    CONSTRAINT pk_address PRIMARY KEY (street, zipcode)
);

CREATE TYPE order_status AS ENUM ('delivered', 'pending', 'payed', 'confirmed', 'delivering', 'canceled');
CREATE TYPE payment_type AS ENUM ('google', 'paypal', 'visa', 'mastercard');

CREATE TABLE user_account
(
    user_id      bigint generated by default as identity,
    email        varchar(128)           not null,
    name         varchar(48)            not null,
    surname      varchar(48)            not null,
    password     varchar(512)           not null,
    birthdate    date                   not null,
    deleted      boolean  default false not null,
    eco_point     int      default 0     not null,
    expires      timestamp,
    street       text,
    zipcode      varchar(16),
    country_code varchar(2)             default 'IT',
    avatar_image varchar(1024)          not null, --default do be added

    CONSTRAINT pk_user_account PRIMARY KEY (user_id),
    CONSTRAINT uk_user_account UNIQUE (email),
    CONSTRAINT ck_user_account CHECK (length(password) >= 64),
    CONSTRAINT fk_user_account_address FOREIGN KEY (street, zipcode) REFERENCES address (street, zipcode)
);



CREATE TABLE restaurant
(
    restaurant_id int generated by default as identity,
    name          varchar(128) not null,
    street        varchar(256) not null,
    zipcode       varchar(16)  not null,
    description   text                  default '',
    image_object  varchar(2048)         default '/assets/images/savoreco-logo.svg',
    delivery_cost decimal(16, 8)        default 0 not null,
    category      varchar(128),
    deleted       boolean      not null default false,
    creation_time timestamp    not null default now(),

    CONSTRAINT pk_restaurant PRIMARY KEY (restaurant_id),
    CONSTRAINT fk_restaurant_address FOREIGN KEY (street, zipcode) REFERENCES address (street, zipcode) ON UPDATE CASCADE ON DELETE RESTRICT
);
CREATE INDEX ON restaurant USING gin (name gin_trgm_ops);

CREATE TABLE seller_account
(
    seller_id     int generated by default as identity,
    email         varchar(128) not null,
    name          varchar(48)  not null,
    surname       varchar(48)  not null,
    password      varchar(512) not null,
    restaurant_id int,

    CONSTRAINT pk_seller_account PRIMARY KEY (seller_id),
    CONSTRAINT uk_seller_account UNIQUE (email),
    CONSTRAINT ck_seller_account CHECK (length(password) >= 64),
    CONSTRAINT fk_seller_account_restaurant FOREIGN KEY (restaurant_id) references restaurant (restaurant_id)
);

CREATE TABLE moderator_account
(
    moderator_id int generated by default as identity,
    email         varchar(128) not null,
    name         varchar(48)  not null,
    surname      varchar(48)  not null,
    password     varchar(512) not null,

    CONSTRAINT pk_moderator_account PRIMARY KEY (moderator_id),
    CONSTRAINT uk_moderator_account UNIQUE (email),
    CONSTRAINT ck_moderator_account CHECK (length(password) >= 64)
);

CREATE TABLE food
(
    food_id       int generated by default as identity,
    restaurant_id int          not null,
    image_object  varchar(2048), --default da aggiungere
    name          varchar(128) not null,
    description   text                  default '',
    available     boolean               default true not null,
    green_point   int                   default 0 not null,
    category      varchar(128) not null default 'other',
    allergens     text,
    price         double precision default 10 not null,
    quantity      smallint     not null default 1 check (quantity >= 0),

    CONSTRAINT pk_food PRIMARY KEY (food_id),
    CONSTRAINT fk_food_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant (restaurant_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT uk_food UNIQUE (restaurant_id, name, category)
);

CREATE TABLE purchase
(
    user_id        bigint                           not null,
    purchase_id    bigint generated by default as identity, --possible attack
    delivery_cost  decimal(16, 8) default 0         not null,
    time           timestamp                        not null default now(),
    iva            smallint       default 10,
    status         order_status   default 'pending' not null,
    total_cost     decimal(16, 8) default 0         not null,
    payment_method payment_type                     not null default 'google',
    pick_up        boolean                          not null default false,


    CONSTRAINT fk_purchase_user_account FOREIGN KEY (user_id) REFERENCES user_account (user_id) on update restrict on delete cascade,
    CONSTRAINT pk_purchase PRIMARY KEY (purchase_id)
);

CREATE TABLE basket
(
    basket_id bigint generated by default as identity, -- primary
    user_id   bigint not null,

    CONSTRAINT pk_basket PRIMARY KEY (basket_id),
    CONSTRAINT uk_basket UNIQUE (user_id),
    CONSTRAINT fk_basket_user_account FOREIGN KEY (user_id) REFERENCES user_account (user_id) on update cascade on delete cascade
);

CREATE TABLE bought_food
(
    bought_food_id int generated by default as identity,
    purchase_id bigint,
    name        varchar(128)                 not null,
    green_point int            default 0     not null,
    price       decimal(16, 8) default 0     not null,
    quantity    smallint                     not null default 1 check (quantity >= 1),
    restaurant_id  integer                   default 1,

    CONSTRAINT pk_bought_food PRIMARY KEY (bought_food_id),
    CONSTRAINT fk_bought_food_purchase FOREIGN KEY (purchase_id) REFERENCES purchase (purchase_id) on update cascade on delete cascade,
    CONSTRAINT fk_bought_food_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id) on update no action on delete set null
);

CREATE TABLE basket_contains
(
    basket_id bigint,
    food_id   int,
    quantity  int check (quantity >= 1),

    CONSTRAINT pk_basket_contains PRIMARY KEY (basket_id, food_id),
    CONSTRAINT fk_basket_contains_food FOREIGN KEY (food_id) REFERENCES food (food_id) on update cascade on delete cascade,
    CONSTRAINT fk_basket_contains_basket FOREIGN KEY (basket_id) REFERENCES basket (basket_id) on update cascade on delete cascade
);
