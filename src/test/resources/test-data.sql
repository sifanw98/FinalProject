-- 清空测试数据
DELETE FROM payment_methods;
DELETE FROM addresses;
DELETE FROM user_roles;
DELETE FROM users;

-- 插入测试用户
INSERT INTO users (id, email, username, password)
VALUES (1, 'admin@example.com', 'admin', '$2a$10$o45G/pQCuHvrz2QoA0VXnuA4HiKwcj.nIHRW4iwbRkUGF2Cux1Zmm');

-- 插入用户角色
INSERT INTO user_roles (user_id, role)
VALUES (1, 'ROLE_ADMIN');