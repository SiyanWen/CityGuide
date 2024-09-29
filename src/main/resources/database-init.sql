DROP TABLE IF EXISTS carts CASCADE;
DROP TABLE IF EXISTS spot_gallaries CASCADE;
DROP TABLE IF EXISTS route_gallaries CASCADE;
DROP TABLE IF EXISTS unit_routes CASCADE;
DROP TABLE IF EXISTS user_spots CASCADE;
DROP TABLE IF EXISTS routes CASCADE;
DROP TABLE IF EXISTS authorities CASCADE;
DROP TABLE IF EXISTS users CASCADE;


CREATE TABLE users
(
    id          SERIAL PRIMARY KEY   NOT NULL,
    email       TEXT UNIQUE          NOT NULL,
    enabled     BOOLEAN DEFAULT TRUE NOT NULL,
    password    TEXT                 NOT NULL,
    user_name   TEXT,
    city_id     TEXT,
    profile_pic_url TEXT
);

CREATE TABLE authorities
(
    id        SERIAL PRIMARY KEY NOT NULL,
    email     TEXT               NOT NULL,
    authority TEXT               NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (email) REFERENCES users (email) ON DELETE CASCADE
);

CREATE TABLE carts
(
    id          SERIAL PRIMARY KEY NOT NULL,
    user_id     INTEGER UNIQUE     NOT NULL,
    CONSTRAINT fk_cart FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE route_gallaries
(
    id          SERIAL PRIMARY KEY NOT NULL,
    name        TEXT,
    user_id     INTEGER            NOT NULL,
    CONSTRAINT fk_route_gallary FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE spot_gallaries
(
    id          SERIAL PRIMARY KEY NOT NULL,
    name        TEXT,
    user_id     INTEGER            NOT NULL,
    CONSTRAINT fk_spot_gallary FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE routes
(
    id                  SERIAL PRIMARY KEY NOT NULL,
    gallery_id          INTEGER            NOT NULL,
    name                TEXT UNIQUE        NOT NULL,
    description         TEXT               NOT NULL,
    distance            FLOAT              NOT NULL,
    traffic_mode        VARCHAR(50)        NOT NULL,
    budget              FLOAT              NOT NULL,
    duration_time       INTEGER            NOT NULL,
    CONSTRAINT fk_route FOREIGN KEY (gallery_id) REFERENCES route_gallaries (id) ON DELETE CASCADE
);

create table user_spots
(
    id              SERIAL PRIMARY KEY NOT NULL,
    original_gid    VARCHAR(100)       NOT NULL,
    route_id        INTEGER,
    cart_id         INTEGER,
    gallery_id      INTEGER,
    name            TEXT               NOT NULL,
    address         TEXT               NOT NULL,
    description     TEXT,
    ranking         FLOAT,
    cost            FLOAT,
    duration_time   INTEGER,
    open_time       TEXT,
    close_time      TEXT,
    image_url       TEXT,
    review          TEXT,
    CONSTRAINT fk_user_spot FOREIGN KEY (gallery_id) REFERENCES spot_gallaries (id) ON DELETE SET NULL,
    CONSTRAINT fk_user_spot2 FOREIGN KEY (cart_id) REFERENCES carts (id) ON DELETE SET NULL,
    CONSTRAINT fk_user_spot3 FOREIGN KEY (route_id) REFERENCES routes (id) ON DELETE SET NULL
);

CREATE TABLE unit_routes
(
    id                  SERIAL PRIMARY KEY NOT NULL,
    route_id            INTEGER            NOT NULL,
    google_polyline_id  TEXT UNIQUE        NOT NULL,
    startpoint_id       INTEGER            NOT NULL,
    endpoint_id         INTEGER            NOT NULL,
    traffic_mode        VARCHAR(50)        NOT NULL,
    duration_time       INTEGER            NOT NULL,
    CONSTRAINT fk_unit_route FOREIGN KEY (route_id) REFERENCES routes (id) ON DELETE SET NULL,
    CONSTRAINT fk_unit_route2 FOREIGN KEY (startpoint_id) REFERENCES user_spots (id) ON DELETE CASCADE,
    CONSTRAINT fk_unit_route3 FOREIGN KEY (endpoint_id) REFERENCES user_spots (id) ON DELETE CASCADE
);