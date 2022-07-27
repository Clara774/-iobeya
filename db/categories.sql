
--
-- Procédures
DROP PROCEDURE IF EXISTS create_categories;

DELIMITER //

CREATE PROCEDURE create_categories(IN nbCatategoryWithoutParent INT, IN nbCategoryMax INT)
BEGIN
	DECLARE nbCategories INT DEFAULT 0;
	DECLARE idParent INT;
	DECLARE nameCategory VARCHAR(250);
	DECLARE countCategory INT;

START TRANSACTION;

-- clear table
TRUNCATE TABLE categories;

WHILE nbCategories < nbCatategoryWithoutParent DO
		INSERT INTO categories (name) VALUES (CONCAT("category.", nbCategories));
		SET nbCategories = nbCategories + 1;
END WHILE;

SELECT COUNT(*) INTO countCategory FROM categories;

WHILE countCategory < nbCategoryMax DO
SELECT id, name INTO idParent, nameCategory FROM categories ORDER BY RAND() LIMIT 1;
CALL create_child(idParent, 0, nbCategoryMax, nameCategory);

SELECT COUNT(*) INTO countCategory FROM categories;
END WHILE;

COMMIT;
END //

DELIMITER ;

-- -------------------

DROP PROCEDURE IF EXISTS create_child;
DELIMITER //

CREATE PROCEDURE create_child(IN parentId INT, IN childLevel INT, IN nbCategoryMax INT, IN nameParent VARCHAR(250))
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE randomChild INT;
    DECLARE lastId INT;
	DECLARE countCategory INT;
	DECLARE nameCategory VARCHAR(241);

	SET max_sp_recursion_depth = 255;

	IF childLevel < 9 THEN
SELECT COUNT(*) INTO countCategory FROM categories;
SELECT ROUND(RAND() * 10) INTO randomChild;

WHILE i < randomChild AND countCategory < nbCategoryMax DO
			SET nameCategory = CONCAT(nameParent, '.', i);

INSERT INTO categories (name, parent_id) VALUES (nameCategory, parentId);
SELECT LAST_INSERT_ID() INTO lastId;
CALL create_child(lastId, childLevel + 1, nbCategoryMax, nameCategory);

SET i = i + 1;
SELECT COUNT(*) INTO countCategory FROM categories;
END WHILE;
END IF;
END //

DELIMITER ;

-- -------------------

DROP PROCEDURE IF EXISTS create_child;
DELIMITER //

CREATE PROCEDURE create_child(IN parentId INT, IN nameParent VARCHAR(250), IN maxNodeLevel INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE randomChild INT;
	DECLARE nodeLevel INT DEFAULT 0;
    DECLARE lastParent INT;
	DECLARE nameCategory VARCHAR(250);

	SET max_sp_recursion_depth = 255;

    -- get not level
    SELECT MAX(parent_id) INTO lastParent FROM categories WHERE id = parentId;
    WHILE lastParent IS NOT NULL DO
        SET nodeLevel = nodeLevel + 1;
        SELECT MAX(parent_id) INTO lastParent FROM categories WHERE id = lastParent;
    END WHILE;

	IF nodeLevel < maxNodeLevel - 1 THEN
        SELECT ROUND(RAND() * 10) INTO randomChild;

        WHILE i < randomChild DO
            SET nameCategory = CONCAT(nameParent, '.', i);

            INSERT INTO categories (name, parent_id) VALUES (nameCategory, parentId);
            SET i = i + 1;
        END WHILE;
    END IF;
END //

DELIMITER ;

--- Trigger
DELIMITER //

CREATE TRIGGER before_insert_categories
    BEFORE INSERT
    ON categories FOR EACH ROW
BEGIN
    DECLARE nb INT;

    SELECT COUNT(*)
    INTO nb
    FROM categories
    WHERE parent_id IS NULL;

    IF nb >= 1000 THEN
        SIGNAL SQLSTATE '45001' SET MESSAGE_TEXT = 'Max categories';
    /*ELSE
        INSERT INTO categories(name, parent_id)
        VALUES(new.name, new.parent_id);*/
    END IF;
END //

DELIMITER ;
