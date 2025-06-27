DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS qna_post;
DROP TABLE IF EXISTS community_post;
DROP TABLE IF EXISTS user_badge;
DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS college;
DROP TABLE IF EXISTS badge;

CREATE TABLE college (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE department (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    college_id BIGINT,
    FOREIGN KEY (college_id)
        REFERENCES college(id)
        ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(30) NOT NULL UNIQUE,
    is_mentor BOOLEAN DEFAULT FALSE,
    department_id BIGINT,
    FOREIGN KEY (department_id)
       REFERENCES department(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE profile (
    user_id BIGINT PRIMARY KEY,
    bio TEXT,
    url VARCHAR(255),
    FOREIGN KEY (user_id)
     REFERENCES users(id)
     ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE badge (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    description TEXT,
    icon_url VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user_badge (
    user_id BIGINT NOT NULL,
    badge_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, badge_id),
    FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,
    FOREIGN KEY (badge_id)
        REFERENCES badge(id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE community_post (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    college_id BIGINT,
    department_id BIGINT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,
    FOREIGN KEY (college_id)
        REFERENCES college(id)
        ON DELETE SET NULL,
    FOREIGN KEY (department_id)
        REFERENCES department(id)
        ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE qna_post (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    college_id BIGINT,
    department_id BIGINT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_answered BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id)
      REFERENCES users(id)
      ON DELETE CASCADE,
    FOREIGN KEY (college_id)
      REFERENCES college(id)
      ON DELETE SET NULL,
    FOREIGN KEY (department_id)
      REFERENCES department(id)
      ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE comment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    commentable_id BIGINT NOT NULL,
    commentable_type ENUM('CommunityPost', 'QnaPost') NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id)
     REFERENCES users(id)
     ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
