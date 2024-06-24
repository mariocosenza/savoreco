SET SCHEMA 'savoreco';

INSERT INTO address (street, city, country_code, zipcode, lat, lon)
VALUES
    ('Viale del Besento', 'Potenza', 'IT', '85100', 40.627233, 15.799091),
    ('Via Nicola Santangelo', 'Potenza', 'IT', '85100', 40.911374, 14.748329),
    ('Via Unità D''Italia', 'Potenza', 'IT', '85100', 40.641324, 15.789566),
    ('Via del Mulino', 'Potenza', 'IT', '85100', 40.261313, 16.254209),
    ('Viale Guglielmo Marconi', 'Potenza', 'IT', '85100', 40.630571, 15.805132),
    ('Via Principe Amedeo', 'Castellammare di Stabia', 'IT', '80053', 40.696917, 14.483807),
    ('Piazza Principe Umberto', 'Castellammare di Stabia', 'IT', '80053', 40.695831, 14.481782),
    ('Via Nazario Sauro', 'Potenza', 'IT', '85100', 40.632668, 15.806714),
    ('Viale Europa', 'Castellammare di Stabia', 'IT', '80053', 40.701004, 14.489221),
    ('Via Giuseppe Cosenza', 'Castellammare di Stabia', 'IT', '80053', 40.703854, 14.493116),
    ('Via Luigi Denza', 'Castellammare di Stabia', 'IT', '80053', 40.698449, 14.484570),
    ('Via Nicola Vaccaro', 'Potenza', 'IT', '85100', 40.632403, 15.801816),
    ('Corso Umberto I', 'Potenza', 'IT', '85100', 40.637166, 15.801601);

INSERT INTO user_account (email, name, surname, password, birthdate, deleted, expires, street, zipcode, country_code, avatar_image)
VALUES
    ('GiovanniBianchi@gmail.com', 'Giovanni', 'Bianchi', encode(sha512('Giovanni123*'::bytea), 'hex'), '1985-08-15', false, null, 'Via del Mulino', '85100', 'IT', null),
    ('SaraBlui@gmail.com', 'Sara', 'Blui', encode(sha512('Sara123*'::bytea), 'hex'), '1990-04-23', false, null, 'Viale Guglielmo Marconi', '85100', 'IT', null),
    ('MarcoVerdi@gmail.com', 'Marco', 'Verdi', encode(sha512('Marco123*'::bytea), 'hex'), '1992-05-11', false, null, 'Viale Europa', '80053', 'IT', null),
    ('FrancescaNeri@gmail.com', 'Francesca', 'Neri', encode(sha512('Francesca123*'::bytea), 'hex'), '1987-09-29', true, '2024-07-24', 'Via Giuseppe Cosenza', '80053', 'IT', null),
    ('RobertoVioli@gmail.com', 'Roberto', 'Violi', encode(sha512('Roberto123*'::bytea), 'hex'), '1990-09-11', false, null, null, null, 'IT', null);

INSERT INTO restaurant (name, street, zipcode, description, image_object, delivery_cost, category, deleted, creation_time)
VALUES
    ('McDonald', 'Viale del Besento', '85100', 'Fast Food Chain', 'https://t3.ftcdn.net/jpg/03/24/73/92/360_F_324739203_keeq8udvv0P2h1MLYJ0GLSlTBagoXS48.jpg', 2.00, 'Fast Food', '2024-06-18 13:48:56.867141'),
    ('KFC', 'Via Nicola Santangelo', '85100', 'Fast Food Chain', null, 2.50, 'Fast Food', false, '2024-06-18 13:48:56.867141'),
    ('Il ritrovo dei golosi', 'Via Unità D''Italia', '85100', 'Pasticceria', null, 4.00, 'Pasticceria', false, '2024-06-18 13:48:56.867141'),
    ('Buon Boccone', 'Piazza Principe Umberto', '80053', 'Buon Boccone, con troppa fila', null, 2.00, 'Rosticceria', false, '2024-06-22 17:44:16.785705'),
    ('Mare e sole', 'Via Nazario Sauro', '85100', 'Buon mare, buon sole, buonissima pizza', null, 1.50, 'Pizzeria', false, '2024-06-23 05:06:21.693811'),
    ('Sushi King', 'Via Luigi Denza', '80053', 'Ristorante Giapponese', null, 5.00, 'Giapponese', false);

INSERT INTO seller_account (email, name, surname, password, restaurant_id)
VALUES
    ('MarioRossi@gmail.com', 'Mario', 'Rossi', encode(sha512('Mario123*'::bytea), 'hex'), 1),
    ('LuigiRossi@gmail.com', 'Luigi', 'Rossi', encode(sha512('Luigi123*'::bytea), 'hex'), 2),
    ('CarlaVerdi@gmail.com', 'Carla', 'Verdi', encode(sha512('Carla123*'::bytea), 'hex'), 3),
    ('VeraGialli@gmail.com', 'Vera', 'Gialli', encode(sha512('Vera123*'::bytea), 'hex'), 4),
    ('FrancoArancio@gmail.com', 'Franco', 'Arancio', encode(sha512('Franco123*'::bytea), 'hex'), 5),
    ('LucaBianchi@gmail.com', 'Luca', 'Bianchi', encode(sha512('Luca123*'::bytea), 'hex'), 6),
    ('AnnaBlui@gmail.com', 'Anna', 'Blui', encode(sha512('Anna123*'::bytea), 'hex'), null);

INSERT INTO moderator_account (email, name, surname, password)
VALUES
    ('MarioCosenza@gmail.com', 'Mario', 'Cosenza', encode(sha512('Mario123*'::bytea), 'hex')),
    ('GiuseppeCavallaro@gmail.com', 'Giuseppe', 'Cavallaro', encode(sha512('Giuseppe123*'::bytea), 'hex')),
    ('MarioFasolino@gmail.com', 'Mario', 'Fasolino', encode(sha512('Mario123*'::bytea), 'hex'));

INSERT INTO food (restaurant_id, image_object, name, description, available, green_point, category, allergens, price, quantity)
VALUES
    (1, null, 'Big Mac', 'Due fette di manzo 100% con formaggio, lattuga, cipolle e cetriolini, tutto su un panino con semi di sesamo.', true, 10, 'Panino', 'Glutine, Latte, Uova', 10.00, 100),
    (1, null, 'Chicken McNuggets', 'Bocconcini di pollo croccanti serviti con la salsa a scelta.', true, 8, 'Pollo', 'Glutine', 6.00, 200),
    (1, null, 'Cheeseburger', 'Un classico con carne 100% bovina, formaggio, cetriolini, cipolle, ketchup e senape.', true, 5, 'Panino', 'Glutine, Latte', 4.00, 150),
    (1, null, 'Filet-O-Fish', 'Pesce panato con formaggio e salsa tartara su un panino morbido.', true, 7, 'Panino', 'Glutine, Latte, Pesce', 5.00, 100),
    (1, null, 'McSundae', 'Gelato alla vaniglia con topping a scelta tra caramello, cioccolato o fragola.', false, 3, 'Dolce', 'Latte', 2.50, 0),
    (2, null, 'Original Recipe Chicken', 'Pollo fritto con la ricetta segreta di 11 erbe e spezie.', true, 10, 'Pollo', 'Glutine', 8.00, 150),
    (2, null, 'Zinger Burger', 'Panino piccante con filetto di pollo croccante, lattuga e maionese.', true, 5, 'Panino', 'Glutine, Latte, Uova', 7.50, 100),
    (2, null, 'Popcorn Chicken', 'Bocconcini di pollo fritto croccanti e gustosi.', true, 8, 'Pollo', 'Glutine', 6.00, 200),
    (2, null, 'Twister Wrap', 'Wrap con pollo croccante, lattuga, pomodoro e salsa speciale.', false, 7, 'Panino', 'Glutine, Latte, Uova', 5.50, 0),
    (3, null, 'Tiramisù', 'Dessert classico italiano con savoiardi, mascarpone, caffè e cacao.', true, 20, 'Dolce', 'Glutine, Latte, Uova', 5.00, 80),
    (3, null, 'Cannoli Siciliani', 'Dolce tipico siciliano con ricotta dolce e canditi.', true, 15, 'Dolce', 'Glutine, Latte, Uova', 4.50, 100),
    (3, null, 'Sfogliatella', 'Pasticcino napoletano croccante con ripieno di ricotta e canditi.', true, 18, 'Dolce', 'Glutine, Latte, Uova', 3.50, 120),
    (3, null, 'Pastiera Napoletana', 'Dolce napoletano con grano cotto, ricotta e arancia candita.', false, 25, 'Dolce', 'Glutine, Latte, Uova', 6.00, 0),
    (4, null, 'Arancini', 'Riso ripieno di ragù, piselli e mozzarella, impanato e fritto.', true, 12, 'Antipasto', 'Glutine, Latte, Uova', 2.50, 150),
    (4, null, 'Frittatina di Maccheroni', 'Pasta con besciamella, piselli e prosciutto cotto, impanata e fritta.', true, 15, 'Antipasto', 'Glutine, Latte, Uova', 3.00, 120),
    (4, null, 'Panino con la Milza', 'Panino farcito con milza di vitello soffritta, con o senza ricotta.', true, 8, 'Panino', 'Glutine, Latte', 5.00, 100),
    (5, null, 'Pizza Margherita', 'Classica pizza con pomodoro, mozzarella e basilico.', true, 15, 'Pizza', 'Glutine, Latte', 6.00, 100),
    (5, null, 'Pizza Diavola', 'Pizza piccante con salame piccante e mozzarella.', true, 18, 'Pizza', 'Glutine, Latte', 7.00, 80),
    (5, null, 'Pizza Capricciosa', 'Pizza con pomodoro, mozzarella, funghi, prosciutto, carciofi e olive.', false, 20, 'Pizza', 'Glutine, Latte', 8.00, 0),
    (5, null, 'Pizza Napoletana', 'Pizza con pomodoro, mozzarella, acciughe e capperi.', true, 22, 'Pizza', 'Glutine, Latte, Pesce', 7.50, 60),
    (6, null, 'Nigiri di Salmone', 'Nigiri con riso e fettine di salmone fresco.', true, 25, 'Nigiri', 'Pesce, Soia', 12.00, 50),
    (6, null, 'Nigiri di Tonno', 'Nigiri con riso e fettine di tonno fresco.', true, 20, 'Nigiri', 'Pesce, Soia', 10.00, 60),
    (6, null, 'Ramen', 'Zuppa tradizionale giapponese con uova, alghe e carne.', true, 18, 'Zuppa', 'Glutine, Soia, Uova', 15.00, 70),
    (6, null, 'Uramaki California Roll', 'Uramaki con polpa di granchio, avocado e cetriolo.', false, 22, 'Uramaki', 'Glutine, Pesce, Soia', 14.00, 0),
    (6, null, 'Zuppa di Miso', 'Zuppa tradizionale giapponese con tofu, alga wakame e miso.', true, 12, 'Zuppa', 'Soia', 5.00, 90);

INSERT INTO purchase (user_id, delivery_cost, time, iva, status, total_cost, payment_method, pick_up, street, zipcode)
VALUES
    (1, 2.50, '2024-06-18 13:48:58.062140', 10, 'payed', 25.50, 'paypal', true, null, null),
    (2, 5.50, '2024-06-18 13:48:58.062140', 10, 'payed', 41.00, 'visa', false, 'Via Nicola Vaccaro', '85100'),
    (1, 2.00, '2024-06-22 18:29:56.889459', 10, 'payed', 16.00, 'paypal', true, null, null),
    (3, 5.00, '2024-06-22 18:33:55.860079', 10, 'payed', 22.00, 'google', true, null, null),
    (4, 2.00, '2024-06-22 19:11:34.392978', 10, 'payed', 10.50, 'visa', true, null, null),
    (5, 4.00, '2024-06-22 19:23:14.961214', 10, 'payed', 7.00, 'visa', false, 'Corso Umberto I', '85100'),
    (3, 7.00, '2024-06-23 16:37:56.829330', 10, 'payed', 10.00, 'google', true, null, null),
    (2, 2.00, '2024-06-23 17:09:57.037974', 10, 'payed', 14.00, 'google', true, null, null),
    (1, 6.50, '2024-06-23 20:35:52.483929', 10, 'payed', 10.50, 'google', true, null, null),



INSERT INTO basket (user_id)
VALUES
    (1),
    (2),
    (3),
    (4),
    (5);

INSERT INTO bought_food (purchase_id, name, green_point, price, quantity, restaurant_id)
VALUES
    (1, 'Original Recipe Chicken', 10, 8.00, 1, 2),
    (1, 'Zinger Burger', 5, 7.50, 2, 2),
    (2, 'Pizza Diavola', 18, 7.00, 3, 5),
    (2, 'Pizza Napoletana', 22, 7.50, 2, 5),
    (2, 'Tiramisù', 20, 5.00, 1, 3),
    (3, 'Big Mac', 10, 10.00, 1, 1),
    (3, 'Chicken McNuggets', 8, 6.00, 1, 1),
    (4, 'Nigiri di Salmone', 25, 12.00, 1, 6),
    (4, 'Nigiri di Tonno', 20, 10.00, 1, 6),
    (5, 'Arancini', 12, 2.50, 3, 4),
    (5, 'Frittatina di Maccheroni', 15, 3.00, 2, 4),
    (6, 'Sfogliatella', 18, 3.50, 2, 3),
    (7, 'Arancini', 12, 2.50, 2, 4),
    (7, 'Zuppa di Miso', 12, 5.00, 1, 6),
    (8, 'Cheeseburger', 5, 4.00, 2, 1),
    (8, 'Chicken McNuggets', 8, 6.00, 1, 1),
    (9, 'Popcorn Chicken', 8, 6.00, 1, 2),
    (9, 'Cannoli Siciliani', 15, 4.50, 1, 3);

INSERT INTO basket_contains (basket_id, food_id, quantity)
VALUES
    (1, 1, 1),
    (1, 2, 1),
    (1, 6, 2),
    (2, 11, 1),
    (2, 12, 3),
    (3, 16, 1),
    (4, 23, 1),
    (5, 18, 2);
