ALTER TABLE games
    MODIFY COLUMN icon text not null,
    MODIFY COLUMN main_image text not null;
ALTER TABLE levels
    MODIFY COLUMN main_image text not null;