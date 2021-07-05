DROP TABLE IF EXISTS input_entity

CREATE TABLE input_entity (
    request_id BIGINT AUTO_INCRMENT PRIMARY KEY ,
    room_coords_x INT,
    room_coords_y INT,
    start_x INT,
    start_y INT,
    patches_coords VARCHAR,
    patches_cleaned INT
    )
