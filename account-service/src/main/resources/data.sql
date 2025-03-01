INSERT INTO users (email, username, password) VALUES
('admin@example.com', 'admin', '$2y$10$8gvVygE9nysrm/1S0VSPROFacIs0qjyOim6QqkHo1anoBWXr6gOcS'),
('user1@example.com', 'user1', '$2y$10$8gvVygE9nysrm/1S0VSPROFacIs0qjyOim6QqkHo1anoBWXr6gOcS'),
('user2@example.com', 'user2', '$2y$10$8gvVygE9nysrm/1S0VSPROFacIs0qjyOim6QqkHo1anoBWXr6gOcS');

INSERT INTO user_roles (user_id, role) VALUES
(1, 'ROLE_ADMIN'),
(1, 'ROLE_USER'),
(2, 'ROLE_USER'),
(3, 'ROLE_USER');

INSERT INTO addresses (address_line1, address_line2, city, state, postal_code, country, address_type, user_id) VALUES
('123 Admin St', 'Suite 100', 'New York', 'NY', '10001', 'USA', 'SHIPPING', 1),
('123 Admin St', 'Suite 100', 'New York', 'NY', '10001', 'USA', 'BILLING', 1),
('456 User St', 'Apt 201', 'Los Angeles', 'CA', '90001', 'USA', 'SHIPPING', 2),
('789 Billing Ave', NULL, 'Los Angeles', 'CA', '90002', 'USA', 'BILLING', 2),
('101 Customer Rd', NULL, 'Chicago', 'IL', '60601', 'USA', 'SHIPPING', 3),
('101 Customer Rd', NULL, 'Chicago', 'IL', '60601', 'USA', 'BILLING', 3);

INSERT INTO payment_methods (type, card_holder_name, card_number, expiration_month, expiration_year, user_id) VALUES
('CREDIT_CARD', 'Admin User', '4111111111111111', '12', '2025', 1),
('CREDIT_CARD', 'Test User', '5555555555554444', '06', '2024', 2),
('PAYPAL', NULL, NULL, NULL, NULL, 2),
('DEBIT_CARD', 'Sample User', '6011111111111117', '03', '2026', 3);