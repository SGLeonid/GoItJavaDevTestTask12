
CREATE SCHEMA IF NOT EXISTS space_travel;
USE space_travel;

CREATE TABLE IF NOT EXISTS client (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(200) CHECK(LENGTH(name) >= 3)
);

CREATE TABLE IF NOT EXISTS planet (
    -- Check planet.id for having only uppercase latin letters and numbers
    id VARCHAR(32) PRIMARY KEY NOT NULL CHECK (id REGEXP '^[A-Z0-9]*$'),
    name VARCHAR(500) CHECK(LENGTH(name) >= 1)
);

CREATE TABLE IF NOT EXISTS ticket (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    created_at DATE,
    client_id BIGINT NOT NULL,
    from_planet_id VARCHAR(32) NOT NULL,
    to_planet_id VARCHAR(32) NOT NULL,

    FOREIGN KEY(client_id) REFERENCES client(id),
    FOREIGN KEY(from_planet_id) REFERENCES planet(id),
    FOREIGN KEY(to_planet_id) REFERENCES planet(id)
);