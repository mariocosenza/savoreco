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
    ('Corso Umberto I', 'Potenza', 'IT', '85100', 40.637166, 15.801601),
    ('Via Ugo Foscolo', 'Gragnano', 'IT', '80054', 40.707835, 14.500239),
    ('Via Roma', 'Gragnano', 'IT', '80054', 40.695765, 14.504542),
    ('Via Castellammare', 'Gragnano', 'IT', '80054', 40.696872, 14.506493),
    ('Via Vittorio Veneto', 'Gragnano', 'IT', '80054', 40.698327, 14.503017),
    ('Piazza San Leone', 'Gragnano', 'IT', '80054', 40.700415, 14.502802),
    ('Via Quarantola', 'Gragnano', 'IT', '80054', 40.703933, 14.507402),
    ('Via del Presepe', 'Gragnano', 'IT', '80054', 40.705482, 14.505034),
    ('Via Plinio il Vecchio', 'Castellammare di Stabia', 'IT', '80053', 40.696102, 14.484805),
    ('Via Annunziatella', 'Castellammare di Stabia', 'IT', '80053', 40.699876, 14.487245),
    ('Via Nocera', 'Castellammare di Stabia', 'IT', '80053', 40.702435, 14.488209),
    ('Via Salvador Allende', 'Castellammare di Stabia', 'IT', '80053', 40.704230, 14.489887),
    ('Via Giuseppe Bonito', 'Castellammare di Stabia', 'IT', '80053', 40.703124, 14.487945),
    ('Via Regina Margherita', 'Castellammare di Stabia', 'IT', '80053', 40.697289, 14.485986),
    ('Via Roma', 'Castellammare di Stabia', 'IT', '80053', 40.694812, 14.483277);

INSERT INTO user_account (email, name, surname, password, birthdate, deleted, expires, street, zipcode, country_code, avatar_image)
VALUES
    ('GiovanniBianchi@gmail.com', 'Giovanni', 'Bianchi', encode(sha512('Giovanni123*'::bytea), 'hex'), '1985-08-15', false, null, 'Via del Mulino', '85100', 'IT', null),
    ('SaraBlui@gmail.com', 'Sara', 'Blui', encode(sha512('Sara123*'::bytea), 'hex'), '1990-04-23', false, null, 'Viale Guglielmo Marconi', '85100', 'IT', null),
    ('MarcoVerdi@gmail.com', 'Marco', 'Verdi', encode(sha512('Marco123*'::bytea), 'hex'), '1992-05-11', false, null, 'Viale Europa', '80053', 'IT', null),
    ('FrancescaNeri@gmail.com', 'Francesca', 'Neri', encode(sha512('Francesca123*'::bytea), 'hex'), '1987-09-29', true, '2024-07-24', 'Via Giuseppe Cosenza', '80053', 'IT', null),
    ('RobertoVioli@gmail.com', 'Roberto', 'Violi', encode(sha512('Roberto123*'::bytea), 'hex'), '1990-09-11', false, null, null, null, 'IT', null),
    ('ElenaBianchi@gmail.com', 'Elena', 'Bianchi', encode(sha512('Elena123*'::bytea), 'hex'), '1995-07-21', false, null, 'Via Nazario Sauro', '85100', 'IT', null),
    ('MatteoVerdi@gmail.com', 'Matteo', 'Verdi', encode(sha512('Matteo123*'::bytea), 'hex'), '1993-03-15', false, null, 'Corso Umberto I', '85100', 'IT', null),
    ('AnnaRossi@gmail.com', 'Anna', 'Rossi', encode(sha512('Anna123*'::bytea), 'hex'), '1991-11-30', false, null, 'Via Vittorio Veneto', '80054', 'IT', null),
    ('PaoloNeri@gmail.com', 'Paolo', 'Neri', encode(sha512('Paolo123*'::bytea), 'hex'), '1986-10-25', false, null, 'Piazza San Leone', '80054', 'IT', null),
    ('GiuliaGialli@gmail.com', 'Giulia', 'Gialli', encode(sha512('Giulia123*'::bytea), 'hex'), '1994-06-17', false, null, 'Via Via Plinio il Vecchio', '80053', 'IT', null),
    ('StefanoBlu@gmail.com', 'Stefano', 'Blu', encode(sha512('Stefano123*'::bytea), 'hex'), '1983-11-20', false, null, 'Via Giuseppe Bonito', '80053', 'IT', null),
    ('ValentinaRossi@gmail.com', 'Valentina', 'Rossi', encode(sha512('Valentina123*'::bytea), 'hex'), '1989-03-10', false, null, null, null, 'IT', null),
    ('DavideNeri@gmail.com', 'Davide', 'Neri', encode(sha512('Davide123*'::bytea), 'hex'), '1996-07-08', false, null, 'Via Roma', '80053', 'IT', null),
    ('AlessandraVerdi@gmail.com', 'Alessandra', 'Verdi', encode(sha512('Alessandra123*'::bytea), 'hex'), '1981-01-22', '2024-07-26', null, null, null, 'IT', null),
    ('FabioRossi@gmail.com', 'Fabio', 'Rossi', encode(sha512('Fabio123*'::bytea), 'hex'), '1997-02-14', false, null, 'Via Nocera', '80053', 'IT', null);
   


INSERT INTO restaurant (name, street, zipcode, image_object, delivery_cost, category, deleted, creation_time, description)
VALUES
    ('McDonald', 'Viale del Besento', '85100', 'https://t3.ftcdn.net/jpg/03/24/73/92/360_F_324739203_keeq8udvv0P2h1MLYJ0GLSlTBagoXS48.jpg', 2.00, 'Fast Food', false, '2024-06-18 13:48:56.867141', 'McDonald è sinonimo di gusto veloce e conveniente. Offriamo un ampia gamma di panini iconici come il Big Mac e i Chicken McNuggets, preparati con ingredienti freschi e di alta qualità. Goditi i nostri menu in un ambiente accogliente e moderno, perfetto per pranzi veloci o serate in famiglia.'),
    ('KFC', 'Via Nicola Santangelo', '85100', null, 2.50, 'Fast Food', false, '2024-06-18 13:48:56.867141', 'KFC è il leader mondiale del pollo fritto, celebre per la sua croccantezza unica e le 11 spezie segrete. Da noi puoi trovare gustosi panini, deliziosi wrap e irresistibili bucket di pollo che faranno la gioia di tutta la famiglia. Un viaggio di sapori avvolti in un’atmosfera amichevole e dinamica.'),
    ('Il ritrovo dei golosi', 'Via Unità D''Italia', '85100', null, 4.00, 'Pasticceria', false, '2024-06-18 13:48:56.867141', 'Il Ritrovo dei Golosi è un paradiso per gli amanti dei dolci. Offriamo una varietà di pasticceria italiana classica, dalle sfogliatelle croccanti ai tiramisù vellutati. Ogni creazione è preparata con ingredienti di alta qualità e una passione per la tradizione che si riflette in ogni morso.'),
    ('Buon Boccone', 'Piazza Principe Umberto', '80053', null, 2.00, 'Rosticceria', false, '2024-06-22 17:44:16.785705',  'Buon Boccone è il tuo angolo di gusto dedicato alla cucina di rosticceria tradizionale. Con piatti preparati come una volta, da arancini dorati a frittatine cremose, il nostro menu è pensato per portare sulla tua tavola il sapore genuino dei piatti caserecci, perfetti per ogni occasione.'),
    ('Mare e sole', 'Via Nazario Sauro', '85100', null, 1.50, 'Pizzeria', false, '2024-06-23 05:06:21.693811', 'Mare e Sole ti invita a gustare l’autentica pizza napoletana, preparata con passione e ingredienti freschi. Il nostro menu varia dalle classiche Margherita e Diavola alle creazioni più innovative, tutte cotte nel nostro forno a legna per un’esperienza culinaria indimenticabile.'),
    ('Sushi King', 'Via Luigi Denza', '80053', null, 5.00, 'Giapponese', false, '2024-06-24 11:38:51.867141', 'Sushi King è il ristorante giapponese che offre un’autentica esperienza di sushi e sashimi. Scopri la freschezza dei nostri nigiri, uramaki creativi e tempura leggera. Ogni piatto è preparato con maestria e attenzione ai dettagli per portare sulla tua tavola il meglio della cucina nipponica.'),
    ('Marcos Pizza Imperiale', 'Via Ugo Foscolo', '80054', null, 3.00, 'Pizzeria', false, '2024-06-25 11:38:51.867141', 'Situata in Via Ugo Foscolo, Marcos Pizza Imperiale è una pizzeria rinomata nel cuore della città di Gragnano. Questo locale è noto per la sua autenticità. Specializzata in una vasta gamma di pizze tradizionali, offre un ambiente accogliente e informale dove poter gustare i sapori autentici della cucina italiana.');


INSERT INTO seller_account (email, name, surname, password, restaurant_id)
VALUES
    ('MarioRossi@gmail.com', 'Mario', 'Rossi', encode(sha512('Mario123*'::bytea), 'hex'), 1),
    ('LuigiRossi@gmail.com', 'Luigi', 'Rossi', encode(sha512('Luigi123*'::bytea), 'hex'), 2),
    ('CarlaVerdi@gmail.com', 'Carla', 'Verdi', encode(sha512('Carla123*'::bytea), 'hex'), 3),
    ('VeraGialli@gmail.com', 'Vera', 'Gialli', encode(sha512('Vera123*'::bytea), 'hex'), 4),
    ('FrancoArancio@gmail.com', 'Franco', 'Arancio', encode(sha512('Franco123*'::bytea), 'hex'), 5),
    ('LucaBianchi@gmail.com', 'Luca', 'Bianchi', encode(sha512('Luca123*'::bytea), 'hex'), 6),
    ('AnnaBlui@gmail.com', 'Anna', 'Blui', encode(sha512('Anna123*'::bytea), 'hex'), null),
    ('MarcoBordeaux@gmail.com', 'Marco', 'Bordeaux', encode(sha512('Marco123*'::bytea), 'hex'), 7);


INSERT INTO moderator_account (email, name, surname, password)
VALUES
    ('MarioCosenza@gmail.com', 'Mario', 'Cosenza', encode(sha512('Mario123*'::bytea), 'hex')),
    ('GiuseppeCavallaro@gmail.com', 'Giuseppe', 'Cavallaro', encode(sha512('Giuseppe123*'::bytea), 'hex')),
    ('MarioFasolino@gmail.com', 'Mario', 'Fasolino', encode(sha512('Mario123*'::bytea), 'hex'));

INSERT INTO food (restaurant_id, image_object, name, available, green_point, category, allergens, price, quantity, description)
VALUES
    (1, null, 'Big Mac', true, 10, 'Panino', 'Glutine, Latte, Uova', 10.00, 100, 'Due fette di manzo 100% con formaggio, lattuga, cipolle e cetriolini, tutto su un panino con semi di sesamo.'),
    (1, null, 'Chicken McNuggets', true, 8, 'Pollo', 'Glutine', 6.00, 200, 'Bocconcini di pollo croccanti serviti con la salsa a scelta.'),
    (1, null, 'Cheeseburger', true, 5, 'Panino', 'Glutine, Latte', 4.00, 150, 'Un classico con carne 100% bovina, formaggio, cetriolini, cipolle, ketchup e senape.'),
    (1, null, 'Filet-O-Fish', 'Pesce panato con formaggio e salsa tartara su un panino morbido.', true, 7, 'Panino', 'Glutine, Latte, Pesce', 5.00, 100),
    (1, null, 'McSundae', false, 3, 'Dolce', 'Latte', 2.50, 0, 'Gelato alla vaniglia con topping a scelta tra caramello, cioccolato o fragola.'),
    (2, null, 'Original Recipe Chicken', true, 10, 'Pollo', 'Glutine', 8.00, 150, 'Pollo fritto con la ricetta segreta di 11 erbe e spezie.'),
    (2, null, 'Zinger Burger', 'Panino piccante con filetto di pollo croccante, lattuga e maionese.', true, 5, 'Panino', 'Glutine, Latte, Uova', 7.50, 100),
    (2, null, 'Popcorn Chicken', true, 8, 'Pollo', 'Glutine', 6.00, 200, 'Bocconcini di pollo fritto croccanti e gustosi.'),
    (2, null, 'Twister Wrap', false, 7, 'Panino', 'Glutine, Latte, Uova', 5.50, 0, 'Wrap con pollo croccante, lattuga, pomodoro e salsa speciale.'),
    (3, null, 'Tiramisù', true, 20, 'Dolce', 'Glutine, Latte, Uova', 5.00, 80, 'Dessert classico italiano con savoiardi, mascarpone, caffè e cacao.'),
    (3, null, 'Cannoli Siciliani', true, 15, 'Dolce', 'Glutine, Latte, Uova', 4.50, 100, 'Dolce tipico siciliano con ricotta dolce e canditi.'),
    (3, null, 'Sfogliatella', true, 18, 'Dolce', 'Glutine, Latte, Uova', 3.50, 120, 'Pasticcino napoletano croccante con ripieno di ricotta e canditi.'),
    (3, null, 'Pastiera Napoletana', false, 25, 'Dolce', 'Glutine, Latte, Uova', 6.00, 0, 'Dolce napoletano con grano cotto, ricotta e arancia candita.'),
    (4, null, 'Arancini', true, 12, 'Antipasto', 'Glutine, Latte, Uova', 2.50, 150, 'Riso ripieno di ragù, piselli e mozzarella, impanato e fritto.'),
    (4, null, 'Frittatina di Maccheroni', true, 15, 'Antipasto', 'Glutine, Latte, Uova', 3.00, 120, 'Pasta con besciamella, piselli e prosciutto cotto, impanata e fritta.'),
    (4, null, 'Panino con la Milza', true, 8, 'Panino', 'Glutine, Latte', 5.00, 100, 'Panino farcito con milza di vitello soffritta, con o senza ricotta.'),
    (5, null, 'Pizza Margherita', true, 15, 'Pizza', 'Glutine, Latte', 6.00, 100, 'Classica pizza con pomodoro, mozzarella e basilico.'),
    (5, null, 'Pizza Diavola', true, 18, 'Pizza', 'Glutine, Latte', 7.00, 80, 'Pizza piccante con salame piccante e mozzarella.'),
    (5, null, 'Pizza Capricciosa', false, 20, 'Pizza', 'Glutine, Latte', 8.00, 0, 'Pizza con pomodoro, mozzarella, funghi, prosciutto, carciofi e olive.'),
    (5, null, 'Pizza Napoletana', true, 22, 'Pizza', 'Glutine, Latte, Pesce', 7.50, 60, 'Pizza con pomodoro, mozzarella, acciughe e capperi.'),
    (6, null, 'Nigiri di Salmone', true, 25, 'Nigiri', 'Pesce, Soia', 12.00, 50, 'Nigiri con riso e fettine di salmone fresco.'),
    (6, null, 'Nigiri di Tonno', true, 20, 'Nigiri', 'Pesce, Soia', 10.00, 60, 'Nigiri con riso e fettine di tonno fresco.'),
    (6, null, 'Ramen', true, 18, 'Zuppa', 'Glutine, Soia, Uova', 15.00, 70, 'Zuppa tradizionale giapponese con uova, alghe e carne.'),
    (6, null, 'Uramaki California Roll', false, 22, 'Uramaki', 'Glutine, Pesce, Soia', 14.00, 0, 'Uramaki con polpa di granchio, avocado e cetriolo.'),
    (6, null, 'Zuppa di Miso', true, 12, 'Zuppa', 'Soia', 5.00, 90, 'Zuppa tradizionale giapponese con tofu, alga wakame e miso.'),
    (7, null, 'Pizza Margherita', true, 15, 'Pizza', 'Glutine, Latte', 4.50, 104, 'Classica pizza con pomodoro, mozzarella e basilico.'),
    (7, null, 'Pizza Diavola', true, 18, 'Pizza', 'Glutine, Latte', 6.50, 80, 'Pizza piccante con salame piccante e mozzarella.'),
    (7, null, 'Pizza Capricciosa', true, 20, 'Pizza', 'Glutine, Latte', 7.00, 26, 'Pizza con pomodoro, mozzarella, funghi, prosciutto, carciofi e olive.'),
    (7, null, 'Pizza Napoletana', true, 22, 'Pizza', 'Glutine, Latte, Pesce', 7.50, 60, 'Pizza con pomodoro, mozzarella, acciughe e capperi.');

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
    (1, 6.50, '2024-06-23 20:35:52.483929', 10, 'payed', 10.50, 'google', true, null, null);



INSERT INTO basket (user_id)
VALUES
    (1),
    (2),
    (3),
    (4),
    (5),
    (6),
    (7),
    (8),
    (9),
    (10),
    (11),
    (12),
    (13),
    (14),
    (15);

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
