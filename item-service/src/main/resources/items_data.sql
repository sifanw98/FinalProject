
-- Insert inventory data, items_id matching id in mongodb
INSERT INTO inventory (item_id, available_units, reserved_units, created_at, updated_at)
VALUES ('12345', 100, 0, '2023-10-01 12:00:00', '2023-10-01 12:00:00');

-- Insert multiple inventory data:
INSERT INTO inventory (item_id, available_units, reserved_units, created_at, updated_at)
VALUES 
    ('67890', 50, 10, '2023-10-01 12:00:00', '2023-10-01 12:00:00'),
    ('54321', 200, 20, '2023-10-01 12:00:00', '2023-10-01 12:00:00'),
    ('98765', 75, 5, '2023-10-01 12:00:00', '2023-10-01 12:00:00'),
    ('11223', 150, 0, '2023-10-01 12:00:00', '2023-10-01 12:00:00');

SELECT * FROM INVENTORY;