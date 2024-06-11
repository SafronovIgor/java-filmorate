-- Film
CREATE TABLE IF NOT EXISTS "mpa_rating"
(
    id     IDENTITY   NOT NULL PRIMARY KEY,
    rating VARCHAR(5) NOT NULL
);

CREATE TABLE IF NOT EXISTS "genre"
(
    id         IDENTITY     NOT NULL PRIMARY KEY,
    genre_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "film"
(
    id            IDENTITY     NOT NULL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    description   VARCHAR(200),
    release_date  DATE         NOT NULL,
    duration      BIGINT       NOT NULL,
    mpa_rating_id BIGINT,
    FOREIGN KEY (mpa_rating_id) REFERENCES "mpa_rating" (id)
);

CREATE TABLE IF NOT EXISTS "film_genre"
(
    film_id  BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    PRIMARY KEY (film_id, genre_id),
    FOREIGN KEY (film_id) REFERENCES "film" (id),
    FOREIGN KEY (genre_id) REFERENCES "genre" (id)
);

CREATE TABLE IF NOT EXISTS "likes"
(
    user_id BIGINT NOT NULL,
    film_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, film_id),
    FOREIGN KEY (film_id) REFERENCES "film" (id)
);

-- User
CREATE TABLE IF NOT EXISTS "user"
(
    id       IDENTITY     NOT NULL PRIMARY KEY,
    name     VARCHAR(255),
    email    VARCHAR(255) NOT NULL,
    login    VARCHAR(255) NOT NULL,
    birthday DATE
);

CREATE TABLE IF NOT EXISTS "friend_status"
(
    status_id   IDENTITY     NOT NULL PRIMARY KEY,
    status_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "friends"
(
    user_id   BIGINT NOT NULL,
    friend_id BIGINT NOT NULL,
    status_id BIGINT NOT NULL DEFAULT 1,
    PRIMARY KEY (user_id, friend_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id),
    FOREIGN KEY (friend_id) REFERENCES "user" (id),
    FOREIGN KEY (status_id) REFERENCES "friend_status" (status_id)
);