DROP TABLE IF EXISTS unit_routes CASCADE;
DROP TABLE IF EXISTS cart_spots CASCADE;
DROP TABLE IF EXISTS spot_likes CASCADE;
DROP TABLE IF EXISTS user_spots CASCADE;
DROP TABLE IF EXISTS route_likes CASCADE;
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

CREATE TABLE routes
(
    id                  SERIAL PRIMARY KEY NOT NULL,
    creator_id          INTEGER            NOT NULL,
    name                TEXT               NOT NULL,
    description         TEXT               NOT NULL,
    distance            NUMERIC            NOT NULL,
    traffic_mode        VARCHAR(50)        NOT NULL,
    budget              NUMERIC            NOT NULL,
    duration_time       NUMERIC            NOT NULL,
    CONSTRAINT fk_route FOREIGN KEY (creator_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE route_likes
(
    id          SERIAL PRIMARY KEY NOT NULL,
    user_id     INTEGER            NOT NULL,
    route_id    INTEGER            NOT NULL,
    CONSTRAINT fk_route_like1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_route_like2 FOREIGN KEY (route_id) REFERENCES routes (id) ON DELETE CASCADE
);

CREATE TABLE user_spots
(
    id              SERIAL PRIMARY KEY  NOT NULL,
    original_gid    VARCHAR(100) UNIQUE NOT NULL,
    latitude        NUMERIC             NOT NULL,
    longitude       NUMERIC             NOT NULL,
    route_id        INTEGER,
    cart_id         INTEGER,
    name            TEXT                NOT NULL,
    address         TEXT                NOT NULL,
    description     TEXT,
    rating          NUMERIC,
    rating_count    NUMERIC,
    cost            NUMERIC,
    duration_time   NUMERIC,
    opening_hours   JSON,
    type            JSON,
    cover_img_url   TEXT,
    reviews         TEXT
);

CREATE TABLE spot_likes
(
    id          SERIAL PRIMARY KEY NOT NULL,
    user_id     INTEGER            NOT NULL,
    spot_id     INTEGER            NOT NULL,
    CONSTRAINT fk_spot_gallery1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_spot_gallery2 FOREIGN KEY (spot_id) REFERENCES user_spots (id) ON DELETE CASCADE
);

CREATE TABLE cart_spots
(
    id              SERIAL PRIMARY KEY NOT NULL,
    original_gid    TEXT               NOT NULL,
    user_id         INTEGER            NOT NULL,
    name            TEXT               NOT NULL,
    address         TEXT               NOT NULL,
    rating          NUMERIC,
    rating_count    NUMERIC,
    cost            NUMERIC,
    duration_time   NUMERIC,
    cover_img_url   TEXT,
    opening_hours   JSONB,
    latitude        NUMERIC            NOT NULL,
    longitude       NUMERIC            NOT NULL,
    CONSTRAINT fk_cart_spot FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_cart_spot2 FOREIGN KEY (original_gid) REFERENCES user_spots (original_gid) ON DELETE CASCADE
);

CREATE TABLE unit_routes
(
    id                  SERIAL PRIMARY KEY NOT NULL,
    route_id            INTEGER            NOT NULL,
    google_polyline_id  TEXT UNIQUE        NOT NULL,
    startspot_id        INTEGER            NOT NULL,
    endspot_id          INTEGER            NOT NULL,
    traffic_mode        VARCHAR(50)        NOT NULL,
    budget              NUMERIC            NOT NULL,
    distance            NUMERIC            NOT NULL,
    duration_time       NUMERIC            NOT NULL,
    CONSTRAINT fk_unit_route FOREIGN KEY (route_id) REFERENCES routes (id) ON DELETE SET NULL,
    CONSTRAINT fk_unit_route2 FOREIGN KEY (startspot_id) REFERENCES user_spots (id) ON DELETE CASCADE,
    CONSTRAINT fk_unit_route3 FOREIGN KEY (endspot_id) REFERENCES user_spots (id) ON DELETE CASCADE
);

INSERT INTO users (email, enabled, password, user_name, city_id, profile_pic_url)
VALUES
    ('john.doe@example.com', TRUE, 'hashed_password_123', 'John Doe', 'C001', 'https://example.com/images/john.jpg'),
    ('jane.smith@example.com', TRUE, 'hashed_password_456', 'Jane Smith', 'C002', 'https://example.com/images/jane.jpg'),
    ('robert.james@example.com', FALSE, 'hashed_password_789', 'Robert James', 'C003', 'https://example.com/images/robert.jpg');


INSERT INTO authorities (email, authority)
VALUES
    ('john.doe@example.com', 'ROLE_USER'),
    ('jane.smith@example.com', 'ROLE_ADMIN'),
    ('robert.james@example.com', 'ROLE_USER');


INSERT INTO routes (creator_id, name, description, distance, traffic_mode, budget, duration_time)
VALUES
    (1, 'Mountain Adventure', 'A scenic mountain trail', 12.5, 'Walking', 50.00, 3.5),
    (2, 'City Tour', 'A tour of downtown attractions', 5.2, 'Driving', 30.00, 1.5),
    (1, 'Beach Path', 'A relaxing route along the beach', 8.7, 'Cycling', 20.00, 2.0);


INSERT INTO route_likes (user_id, route_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 1),
    (1, 3);


INSERT INTO user_spots (original_gid, latitude, longitude, route_id, name, address, description, rating, rating_count, cost, duration_time, opening_hours, cover_img_url, reviews)
VALUES
    ('GID001', 40.7128, -74.0060, 1, 'Central Park', 'NYC, NY', 'Famous urban park', 4.8, 1200, 0, 2.5, '{"Mon-Fri": "06:00-22:00"}', 'https://example.com/images/centralpark.jpg', 'Great place to relax'),
    ('GID002', 34.0522, -118.2437, 2, 'Hollywood Sign', 'LA, CA', 'Iconic landmark', 4.6, 800, 0, 1.5, '{"Mon-Sun": "Open 24 hours"}', 'https://example.com/images/hollywood.jpg', 'Must-visit place'),
    ('GID003', 37.7749, -122.4194, 3, 'Golden Gate Bridge', 'SF, CA', 'Famous suspension bridge', 4.9, 1500, 0, 2.0, '{"Mon-Sun": "Open 24 hours"}', 'https://example.com/images/goldengate.jpg', 'Stunning views');


INSERT INTO spot_likes (user_id, spot_id)
VALUES
    (1, 1),
    (2, 2),
    (1, 3),
    (3, 1);


INSERT INTO cart_spots (original_gid, user_id, name, address, rating, rating_count, cost, duration_time, cover_img_url, opening_hours, latitude, longitude)
VALUES
    ('GID001', 1, 'Central Park', 'NYC, NY', 4.8, 1200, 0, 2.5, 'https://example.com/images/centralpark.jpg', '{"Mon-Fri": "06:00-22:00"}', 40.7128, -74.0060),
    ('GID002', 2, 'Hollywood Sign', 'LA, CA', 4.6, 800, 0, 1.5, 'https://example.com/images/hollywood.jpg', '{"Mon-Sun": "Open 24 hours"}', 34.0522, -118.2437);


INSERT INTO unit_routes (route_id, google_polyline_id, startspot_id, endspot_id, traffic_mode, budget, distance, duration_time)
VALUES
    (1, 'polyline_123', 1, 2, 'Walking', 50.00, 12.5, 3.5),
    (2, 'polyline_456', 2, 3, 'Driving', 30.00, 5.2, 1.5);
