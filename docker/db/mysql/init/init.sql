CREATE TABLE user
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL
);

CREATE TABLE board
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT         NOT NULL,
    status  VARCHAR(10) NOT NULL
);

CREATE TABLE move
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    board_id    INT         NOT NULL,
    origin      VARCHAR(10) NOT NULL,
    destination VARCHAR(10) NOT NULL,
    turn        INT         NOT NULL
);
