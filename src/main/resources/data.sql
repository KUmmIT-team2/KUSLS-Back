INSERT IGNORE INTO college (id, name) VALUES (1, '문과대학');
INSERT IGNORE INTO college (id, name) VALUES (2, '이과대학');
INSERT IGNORE INTO college (id, name) VALUES (3, '건축대학');
INSERT IGNORE INTO college (id, name) VALUES (4, '공과대학');
INSERT IGNORE INTO college (id, name) VALUES (5, '사회과학대학');
INSERT IGNORE INTO college (id, name) VALUES (6, '경영대학');
INSERT IGNORE INTO college (id, name) VALUES (7, '부동산과학원');
INSERT IGNORE INTO college (id, name) VALUES (8, '융합과학기술원');
INSERT IGNORE INTO college (id, name) VALUES (9, '생명과학대학');
INSERT IGNORE INTO college (id, name) VALUES (99, '전체');

-- 문과대학
INSERT IGNORE INTO department (name, college_id) VALUES ('국어국문학과', 1);
INSERT IGNORE INTO department (name, college_id) VALUES ('영어영문학과', 1);
INSERT IGNORE INTO department (name, college_id) VALUES ('중어중문학과', 1);
INSERT IGNORE INTO department (name, college_id) VALUES ('철학과', 1);
INSERT IGNORE INTO department (name, college_id) VALUES ('사학과', 1);
INSERT IGNORE INTO department (name, college_id) VALUES ('지리학과', 1);

-- 이과대학
INSERT IGNORE INTO department (name, college_id) VALUES ('수학과', 2);
INSERT IGNORE INTO department (name, college_id) VALUES ('화학과', 2);
INSERT IGNORE INTO department (name, college_id) VALUES ('물리학과', 2);

-- 건축대학
INSERT IGNORE INTO department (name, college_id) VALUES ('건축학부', 3);

-- 공과대학
INSERT IGNORE INTO department (name, college_id) VALUES ('사회환경공학부', 4);
INSERT IGNORE INTO department (name, college_id) VALUES ('기계로봇자동차공학부', 4);
INSERT IGNORE INTO department (name, college_id) VALUES ('전기전자공학부', 4);
INSERT IGNORE INTO department (name, college_id) VALUES ('화공학부', 4);
INSERT IGNORE INTO department (name, college_id) VALUES ('컴퓨터공학부', 4);
INSERT IGNORE INTO department (name, college_id) VALUES ('재료공학과', 4);
INSERT IGNORE INTO department (name, college_id) VALUES ('항공우주모빌리티공학과', 4);
INSERT IGNORE INTO department (name, college_id) VALUES ('생물공학과', 4);
INSERT IGNORE INTO department (name, college_id) VALUES ('산업공학과', 4);

-- 사회과학대학
INSERT IGNORE INTO department (name, college_id) VALUES ('정치외교학과', 5);
INSERT IGNORE INTO department (name, college_id) VALUES ('경제학과', 5);
INSERT IGNORE INTO department (name, college_id) VALUES ('행정학과', 5);
INSERT IGNORE INTO department (name, college_id) VALUES ('국제무역학과', 5);
INSERT IGNORE INTO department (name, college_id) VALUES ('응용통계학과', 5);

-- 경영대학
INSERT IGNORE INTO department (name, college_id) VALUES ('경영학과', 6);
INSERT IGNORE INTO department (name, college_id) VALUES ('기술경영학과', 6);

-- 부동산과학원
INSERT IGNORE INTO department (name, college_id) VALUES ('부동산학과', 7);

-- 융합과학기술원
INSERT IGNORE INTO department (name, college_id) VALUES ('첨단바이오공학부', 8);
INSERT IGNORE INTO department (name, college_id) VALUES ('시스템생명공학과', 8);
INSERT IGNORE INTO department (name, college_id) VALUES ('융합생명공학과', 8);

-- 생명과학대학
INSERT IGNORE INTO department (name, college_id) VALUES ('동물자원전공', 9);
INSERT IGNORE INTO department (name, college_id) VALUES ('식품과학전공', 9);
INSERT IGNORE INTO department (name, college_id) VALUES ('식품유통전공', 9);
INSERT IGNORE INTO department (name, college_id) VALUES ('환경보건전공', 9);
INSERT IGNORE INTO department (name, college_id) VALUES ('산림조경전공', 9);
INSERT IGNORE INTO department (name, college_id) VALUES ('생명과학특성학과', 9);
INSERT IGNORE INTO department (name, college_id) VALUES ('식량자원과학과', 9);
