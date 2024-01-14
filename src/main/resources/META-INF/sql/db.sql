DROP DATABASE bookstore_w_24;

CREATE DATABASE bookstore_w_24
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

INSERT INTO publishers(id, name) VALUES
    (default, 'PWN'),
    (default, 'Ossolineum'),
    (default, 'WNT');

INSERT INTO authors(id, name) VALUES
     (default, 'Mickiewicz'),
     (default, 'Sienkiewicz'),
     (default, 'Prus');
