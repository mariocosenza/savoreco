SET SCHEMA 'savoreco';

INSERT INTO address (street, city, country_code, zipcode, gps_point)
VALUES ('Viale del Besento', 'Potenza', 'IT', '85100', ST_GeomFromText('POINT(40.627233 15.799091)', 4326)),
       ('Via Nicola Sant angelo', 'Potenza', 'IT', '85100', ST_GeomFromText('POINT(40.911374 14.748329)', 4326)),
       ('Viale Italia', 'Potenza', 'IT', '85100', ST_GeomFromText('POINT(40.911988 14.774473)', 4326)),
       ('Via del Mulino', 'Potenza', 'IT', '85100', ST_GeomFromText('POINT(40.261313 16.254209)', 4326)),
       ('Viale guglielmo', 'Potenza', 'IT', '85100', ST_GeomFromText('POINT(40.630571 15.805132)', 4326));

INSERT INTO user_account (email, name, surname, password, age, street, zipcode, country_code, avatar_image)
VALUES ('GiovanniBianchi@gmail.com', 'Giovanni', 'Bianchi', encode(sha512('Giovanni123*'::bytea), 'hex'), '1985-08-15', 'Via del Mulino',
        '85100', 'IT', 'https://example.com/avatar/john.jpg'),
       ('SaraBlui@gmail.com', 'Sara', 'Blui', encode(sha512('Sara123*'::bytea), 'hex'), '1990-04-23',
        'Viale guglielmo', '85100', 'IT', 'https://example.com/avatar/jane.jpg');

INSERT INTO restaurant (name, street, zipcode, description, image_object, delivery_cost, category, deleted)
VALUES ('McDonald', 'Viale del Besento', '85100', 'Fast Food Chain', 'https://example.com/images/bistro.jpg',
        2.00, 'Fast Food', false),
       ('KFC', 'Via Nicola Sant angelo', '85100', 'Fast Food Chain', 'https://example.com/images/cafe.jpg',
        2.50, 'Fast Food', false),
       ('Il ritrovo dei golosi', 'Viale Italia', '85100', 'Pasticceria', 'https://example.com/images/burger.jpg',
        4.00, 'Pasticceria', false);

INSERT INTO seller_account (email, name, surname, password, restaurant_id)
VALUES ('MarioRossi@gmail.com', 'Mario', 'Rossi', encode(sha512('Mario123*'::bytea), 'hex'), 1),
       ('LuigiRossi@gmail.com', 'Luigi', 'Rossi', encode(sha512('Luigi123*'::bytea), 'hex'), 2),
       ('CarlaVerdi@gmail.com', 'Carla', 'Verdi', encode(sha512('Carla123*'::bytea), 'hex'), 3);

INSERT INTO moderator_account (email, name, surname, password)
VALUES ('MarioCosenza@gmail.com', 'Mario', 'Cosenza', encode(sha512('Mario123*'::bytea), 'hex')),
       ('GiuseppeCavallaro@gmail.com', 'Giuseppe', 'Cavallaro', encode(sha512('Giuseppe123*'::bytea), 'hex'));

INSERT INTO food (restaurant_id, image_object, name, description, available, green_point, category, allergens, quantity)
VALUES (1, 'https://example.com/images/pizza.jpg', 'Big Mac', 'Carne 100% bovina',
        true, 10, 'Panino', 'Glutine, Latte, Uova', 100),
       (1, 'https://example.com/images/croissant.jpg', 'Crispy McBacon', 'Bacon Croccante',
        true, 5, 'Panino', 'Glutine, Latte, Uova', 80),
       (2, 'https://example.com/images/burger.jpg', 'Bucket Pollo', '2 Tender Crispy, 2 Tender Original Recipe',
        true, 10, 'Pollo', 'Glutine, Latte', 75),
       (2, 'https://example.com/images/burger.jpg', 'Double Krispy', '2 Tender Crispy Maionese',
        true, 10, 'Panino', 'Glutine, Latte, Uova', 25),
       (3, 'https://example.com/images/burger.jpg', 'Profiteroles', 'bignè ripieni di chantilly alla gianduia',
        true, 5, 'Dolce', 'Latte, Uova', 80),
       (3, 'https://example.com/images/burger.jpg', 'Crostata', 'Crostata con Fragoline di Bosco',
        true, 20, 'Dolce', 'Glutine, Latte, Uova', 50);

INSERT INTO purchase (user_id, delivery_cost, iva, status, total_cost, payment_method)
VALUES (1, 4.00, 5, 'pending', 26.00, 'paypal'),
       (2, 2.00, 5, 'payed', 10.00, 'visa');

INSERT INTO basket (user_id)
VALUES (1),
       (2);

INSERT INTO bought_food (purchase_id, food_id, name, green_point, price, quantity)
VALUES (1, 6, 'Crostata', 20, 22.00, 1),
       (2, 2, 'Crispy McBacon', 5, 8.00, 1);

INSERT INTO basket_contains (basket_id, food_id)
VALUES (1, 1),
       (2, 3);