CREATE TABLE game_genre (
                            games_id bigint  NOT NULL,
                            genres_id int  NOT NULL,
                            CONSTRAINT game_genre_pk PRIMARY KEY (games_id,genres_id)
);

-- Table: games
CREATE TABLE games (
                       id bigint  NOT NULL AUTO_INCREMENT,
                       name varchar(255)  NOT NULL,
                       description text  NOT NULL,
                       price float  NOT NULL,
                       age_rating int  NOT NULL,
                       release_date datetime  NOT NULL,
                       icon json  NOT NULL,
                       main_image json  NOT NULL,
                       images json  NOT NULL,
                       CONSTRAINT games_pk PRIMARY KEY (id)
);

-- Table: genres
CREATE TABLE genres (
                        id int  NOT NULL AUTO_INCREMENT,
                        name varchar(255)  NOT NULL,
                        UNIQUE INDEX genres_ak_1 (name),
                        CONSTRAINT genres_pk PRIMARY KEY (id)
);

-- Table: levels
CREATE TABLE levels (
                        id bigint  NOT NULL AUTO_INCREMENT,
                        name varchar(255)  NOT NULL,
                        description text  NOT NULL,
                        price float  NOT NULL,
                        main_image json  NOT NULL,
                        images json  NOT NULL,
                        games_id bigint  NOT NULL,
                        hidden bool  NOT NULL,
                        CONSTRAINT levels_pk PRIMARY KEY (id)
);

CREATE TABLE purchased_game (
                                id bigint  NOT NULL AUTO_INCREMENT,
                                buy_date datetime  NOT NULL,
                                user_id bigint  NOT NULL,
                                games_id bigint  NOT NULL,
                                CONSTRAINT purchased_game_pk PRIMARY KEY (id)
);

-- Table: purchased_level
CREATE TABLE purchased_level (
                                 id bigint  NOT NULL AUTO_INCREMENT,
                                 buy_date datetime  NOT NULL,
                                 levels_id bigint  NOT NULL,
                                 purchased_game_id bigint  NOT NULL,
                                 CONSTRAINT purchased_level_pk PRIMARY KEY (id)
);

-- Reference: game_genre_games (table: game_genre)
ALTER TABLE game_genre ADD CONSTRAINT game_genre_games FOREIGN KEY game_genre_games (games_id)
    REFERENCES games (id);

-- Reference: game_genre_genres (table: game_genre)
ALTER TABLE game_genre ADD CONSTRAINT game_genre_genres FOREIGN KEY game_genre_genres (genres_id)
    REFERENCES genres (id);

-- Reference: levels_games (table: levels)
ALTER TABLE levels ADD CONSTRAINT levels_games FOREIGN KEY levels_games (games_id)
    REFERENCES games (id);

-- Reference: purchased_game_games (table: purchased_game)
ALTER TABLE purchased_game ADD CONSTRAINT purchased_game_games FOREIGN KEY purchased_game_games (games_id)
    REFERENCES games (id);

-- Reference: purchased_level_levels (table: purchased_level)
ALTER TABLE purchased_level ADD CONSTRAINT purchased_level_levels FOREIGN KEY purchased_level_levels (levels_id)
    REFERENCES levels (id);

-- Reference: purchased_level_purchased_game (table: purchased_level)
ALTER TABLE purchased_level ADD CONSTRAINT purchased_level_purchased_game FOREIGN KEY purchased_level_purchased_game (purchased_game_id)
    REFERENCES purchased_game (id);