DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL
);

INSERT INTO USERS (first_name, last_name) VALUES
  ('Aliko', 'Dangote'),
  ('Bill', 'Gates'),
  ('Folrunsho', 'Alakija'),
  ('Mukesh', 'Ambani'),
  ('Gautam', 'Adani'),
  ('Shiv', 'Nadar'),
  ('Lakshmi', 'Mittal'),
  ('Savitri', 'Jindal');

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
  (7, 4, 'Ambani Post 2'),
  (8, 8, 'Jindal Post 1'),
  (9, 4, 'Ambani Post 3'),
  (10, 1, 'Aliko Post 3'),
  (11, 7, 'Mittal Post 1'),
  (12, 6, 'Nadar Post 1'),
  (13, 3, 'Folrunsho Post 3'),
  (14, 5, 'Adani Post 1'),
  (15, 7, 'Mittal Post 2');

  

DROP TABLE IF EXISTS FOLLOWERS;
  
CREATE TABLE FOLLOWERS (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  following_id INT NOT NULL
);

INSERT INTO FOLLOWERS (user_id, following_id) VALUES
  (1, 2),
  (2, 3),
  (3, 1),
  (2, 1),
  (5, 4),
  (6, 4),
  (4, 1),
  (7, 3),
  (8, 1),
  (4, 2),
  (2, 4);
