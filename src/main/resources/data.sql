DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL
);

INSERT INTO USERS (first_name, last_name) VALUES
  ('Aliko', 'Dangote'),
  ('Bill', 'Gates'),
  ('Folrunsho', 'Alakija');

DROP TABLE IF EXISTS POSTS;
  
CREATE TABLE POSTS (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  content VARCHAR(2500) NOT NULL,
  create_ts timestamp default CURRENT_TIMESTAMP()
);

INSERT INTO POSTS (id, user_id, content) VALUES
  (1, 1, 'Aliko Post 1'),
  (2, 2, 'Bill Post 1'),
  (3, 3, 'Folrunsho Post 1'),
  (4, 1, 'Aliko Post 2'),
  (5, 2, 'Bill Post 2'),
  (6, 3, 'Folrunsho Post 2'),
  (7, 2, 'Bill Post 3'),
  (8, 2, 'Bill Post 4');

  

DROP TABLE IF EXISTS FOLLOWERS;
  
CREATE TABLE FOLLOWERS (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  following_id INT NOT NULL
);

INSERT INTO FOLLOWERS (id, user_id, following_id) VALUES
  (1, 1, 2),
  (2, 2, 3),
  (3, 3, 1);
