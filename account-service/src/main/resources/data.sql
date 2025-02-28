-- 插入用户数据
-- 注意：密码是"password"的BCrypt哈希值，可以在生产环境中修改为更安全的密码
INSERT INTO users (email, username, password) VALUES
('admin@example.com', 'admin', '$2a$10$o45G/pQCuHvrz2QoA0VXnuA4HiKwcj.nIHRW4iwbRkUGF2Cux1Zmm'),
('user1@example.com', 'user1', '$2a$10$o45G/pQCuHvrz2QoA0VXnuA4HiKwcj.nIHRW4iwbRkUGF2Cux1Zmm'),
('user2@example.com', 'user2', '$2a$10$o45G/pQCuHvrz2QoA0VXnuA4HiKwcj.nIHRW4iwbRkUGF2Cux1Zmm');

-- 插入用户角色
INSERT INTO user_roles (user_id, role) VALUES
(1, 'ROLE_ADMIN'),
(1, 'ROLE_USER'),
(2, 'ROLE_USER'),
(3, 'ROLE_USER');

-- 插入地址数据
INSERT INTO addresses (address_line1, address_line2, city, state, postal_code, country, address_type, user_id) VALUES
-- 用户1的地址
('123 Admin St', 'Suite 100', 'New York', 'NY', '10001', 'USA', 'SHIPPING', 1),
('123 Admin St', 'Suite 100', 'New York', 'NY', '10001', 'USA', 'BILLING', 1),
-- 用户2的地址
('456 User St', 'Apt 201', 'Los Angeles', 'CA', '90001', 'USA', 'SHIPPING', 2),
('789 Billing Ave', NULL, 'Los Angeles', 'CA', '90002', 'USA', 'BILLING', 2),
-- 用户3的地址
('101 Customer Rd', NULL, 'Chicago', 'IL', '60601', 'USA', 'SHIPPING', 3),
('101 Customer Rd', NULL, 'Chicago', 'IL', '60601', 'USA', 'BILLING', 3);

-- 插入支付方式数据
INSERT INTO payment_methods (type, card_holder_name, card_number, expiration_month, expiration_year, user_id) VALUES
-- 用户1的支付方式
('CREDIT_CARD', 'Admin User', '4111111111111111', '12', '2025', 1),
-- 用户2的支付方式
('CREDIT_CARD', 'Test User', '5555555555554444', '06', '2024', 2),
('PAYPAL', NULL, NULL, NULL, NULL, 2),
-- 用户3的支付方式
('DEBIT_CARD', 'Sample User', '6011111111111117', '03', '2026', 3);