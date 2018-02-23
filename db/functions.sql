DELIMITER $$
CREATE FUNCTION `nextval`(`seq_name` VARCHAR(100))
    RETURNS BIGINT NOT DETERMINISTIC
    BEGIN
        DECLARE cur_val BIGINT;

        SELECT cur_value
        INTO cur_val
        FROM
            _sequence
        WHERE
            name = seq_name;

        IF cur_val IS NOT NULL
        THEN
            UPDATE
                _sequence
            SET
                cur_value = IF(
                    (cur_value + increment) > max_value OR (cur_value + increment) < min_value,
                    IF(cycle = TRUE, IF((cur_value + increment) > max_value,
                                        min_value,
                                        max_value
                    ),
                       NULL
                    ),
                    cur_value + increment
                ),
                increment = (RAND() * 50)
            WHERE
                name = seq_name;
        END IF;
        RETURN cur_val;
    END;
$$