DROP TABLE IF EXISTS `bookmark`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `qna`;
DROP TABLE IF EXISTS `profile`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `department`;
DROP TABLE IF EXISTS `college`;

CREATE TABLE `college` (
                           `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           `name` VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `department` (
                              `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              `name` VARCHAR(50) NOT NULL UNIQUE,
                              `college_id` BIGINT NOT NULL,

                              CONSTRAINT `fk_department_college_id`
                                  FOREIGN KEY (`college_id`)
                                      REFERENCES `college`(`id`)
                                      ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `user` (
                        `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        `username` VARCHAR(255) NOT NULL UNIQUE,
                        `password` VARCHAR(255) NOT NULL,
                        `student_number` VARCHAR(50) NOT NULL UNIQUE,
                        `is_mentor` BOOLEAN NOT NULL,
                        `department_id` BIGINT NOT NULL,
                        `comment_count` BIGINT DEFAULT 0,

                        CONSTRAINT `fk_user_department_id`
                            FOREIGN KEY (`department_id`)
                                REFERENCES `department`(`id`)
                                ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `profile` (
                           `user_id` BIGINT NOT NULL PRIMARY KEY,
                           `hashtag` TEXT,

                           CONSTRAINT `fk_profile_user_id`
                               FOREIGN KEY (`user_id`)
                                   REFERENCES `user`(`id`)
                                   ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `qna` (
                       `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       `user_id` BIGINT NOT NULL,
                       `title` VARCHAR(100) NOT NULL,
                       `college_id` BIGINT,
                       `department_id` BIGINT,
                       `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
                       `bookmark_count` INT NOT NULL DEFAULT 0,
                       `recommend_count` INT NOT NULL DEFAULT 0,
                       `reply_count` INT NOT NULL DEFAULT 0,

                       CONSTRAINT `fk_qna_user_id`
                           FOREIGN KEY (`user_id`)
                               REFERENCES `user`(`id`)
                               ON DELETE CASCADE ON UPDATE CASCADE,

                       CONSTRAINT `fk_qna_college_id`
                           FOREIGN KEY (`college_id`)
                               REFERENCES `college`(`id`)
                               ON DELETE SET NULL ON UPDATE CASCADE,

                       CONSTRAINT `fk_qna_department_id`
                           FOREIGN KEY (`department_id`)
                               REFERENCES `department`(`id`)
                               ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `comment` (
                           `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           `user_id` BIGINT NOT NULL,
                           `content` TEXT NOT NULL,
                           `commentable_id` BIGINT NOT NULL,
                           `commentable_type` ENUM('Community', 'Qna') NOT NULL,
                           `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,

                           CONSTRAINT `fk_comment_user_id`
                               FOREIGN KEY (`user_id`)
                                   REFERENCES `user`(`id`)
                                   ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `bookmark` (
                            `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            `user_id` BIGINT NOT NULL,
                            `bookmarkable_id` BIGINT NOT NULL,
                            `bookmarkable_type` ENUM('Community', 'Qna') NOT NULL,
                            `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,

                            CONSTRAINT `fk_bookmark_user_id`
                                FOREIGN KEY (`user_id`)
                                    REFERENCES `user`(`id`)
                                    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
