USE usersystem;


CREATE TABLE IF NOT EXISTS user_status(
status_id  INT(1) PRIMARY KEY NOT NULL,
status_name  VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS user(
user_id   VARCHAR(40) PRIMARY KEY,
name      VARCHAR(100) NOT NULL,
user_name  VARCHAR(100) NOT NULL,
password_hashed  VARCHAR(100) NOT NULL,
ts_insert  DATETIME NOT NULL,
ts_update  DATETIME NOT NULL,
status  INT(1) NOT NULL,
FOREIGN KEY(status)  REFERENCES user_status(status_id)
);

CREATE TABLE if NOT EXISTS role(
role_id  VARCHAR(40) PRIMARY KEY,
role_name VARCHAR(100) NOT NULL,
ts_insert  DATETIME NOT NULL,
ts_update  DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role(
user_role_id   VARCHAR(40) PRIMARY KEY,
user_id   VARCHAR(20) NOT NULL,
role_id   VARCHAR(20) NOT NULL,
ts_insert  DATETIME NOT NULL,
ts_update  DATETIME NOT NULL,
FOREIGN KEY (user_id) REFERENCES user(user_id),
FOREIGN KEY (role_id) REFERENCES role(role_id)
);

CREATE TABLE IF NOT EXISTS session(
 id_session  INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 access_token  VARCHAR(100) NOT NULL,
 user_id VARCHAR(50) NOT NULL,
 session_create  DATE NOT NULL,
 session_expiration  DATE NOT NULL,
 FOREIGN KEY (user_id) REFERENCES user(user_id)
);
