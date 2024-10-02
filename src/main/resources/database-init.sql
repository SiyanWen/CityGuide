DROP TABLE IF EXISTS carts CASCADE;
DROP TABLE IF EXISTS spot_galleries CASCADE;
DROP TABLE IF EXISTS route_galleries CASCADE;
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

CREATE TABLE route_galleries
(
    id          SERIAL PRIMARY KEY NOT NULL,
    name        TEXT,
    user_id     INTEGER UNIQUE     NOT NULL,
    CONSTRAINT fk_route_gallery FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE spot_galleries
(
    id          SERIAL PRIMARY KEY NOT NULL,
    name        TEXT,
    user_id     INTEGER UNIQUE     NOT NULL,
    CONSTRAINT fk_spot_gallery FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE routes
(
    route_id            SERIAL PRIMARY KEY NOT NULL,
    gallery_id          INTEGER            NOT NULL,
    name                TEXT               NOT NULL,
    description         TEXT               NOT NULL,
    distance            NUMERIC            NOT NULL,
    traffic_mode        VARCHAR(50)        NOT NULL,
    budget              NUMERIC            NOT NULL,
    duration_time       NUMERIC            NOT NULL,
    CONSTRAINT fk_route FOREIGN KEY (gallery_id) REFERENCES route_galleries (id) ON DELETE CASCADE
);

CREATE TABLE user_spots
(
    id              SERIAL PRIMARY KEY  NOT NULL,
    original_gid    VARCHAR(100) UNIQUE NOT NULL,
    latitude        NUMERIC             NOT NULL,
    longitude       NUMERIC             NOT NULL,
    route_id        INTEGER,
    cart_id         INTEGER,
    gallery_id      INTEGER,
    name            TEXT                NOT NULL,
    address         TEXT                NOT NULL,
    description     TEXT,
    rating          NUMERIC,
    rating_count    NUMERIC,
    cost            NUMERIC,
    duration_time   NUMERIC,
    open_time       TEXT,
    close_time      TEXT,
    image_url       TEXT,
    reviews         TEXT,
    CONSTRAINT fk_user_spot3 FOREIGN KEY (route_id) REFERENCES routes (route_id) ON DELETE CASCADE
);

CREATE TABLE unit_routes
(
    id                  SERIAL PRIMARY KEY NOT NULL,
    route_id            INTEGER            NOT NULL,
    google_polyline_id  TEXT UNIQUE        NOT NULL,
    startspot_id       INTEGER            NOT NULL,
    endspot_id         INTEGER            NOT NULL,
    traffic_mode        VARCHAR(50)        NOT NULL,
    budget              NUMERIC            NOT NULL,
    distance            NUMERIC            NOT NULL,
    duration_time       NUMERIC            NOT NULL,
    CONSTRAINT fk_unit_route FOREIGN KEY (route_id) REFERENCES routes (route_id) ON DELETE SET NULL,
    CONSTRAINT fk_unit_route2 FOREIGN KEY (startspot_id) REFERENCES user_spots (id) ON DELETE CASCADE,
    CONSTRAINT fk_unit_route3 FOREIGN KEY (endspot_id) REFERENCES user_spots (id) ON DELETE CASCADE
);