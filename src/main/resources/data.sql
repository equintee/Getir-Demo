INSERT INTO customer_tiers (id, name, discount_rate, minimum_order_count, update_time) VALUES
(1, 'Regular', 0, 0, CURRENT_TIMESTAMP),
(2, 'Gold', 0.1, 10, CURRENT_TIMESTAMP),
(3, 'Platinum', 0.2, 20, CURRENT_TIMESTAMP);


INSERT INTO customers (id, username, tier_id, create_time) VALUES
(1, 'Baris Alper Yilmaz', 1, CURRENT_TIMESTAMP),
(2, 'Yunus Akgun', 2, CURRENT_TIMESTAMP),
(3, 'Dries Mertens', 3, CURRENT_TIMESTAMP);


-- Insert 10 orders for customer_id=2 (Discount rate: 10%)
INSERT INTO orders (id, customer_id, item_price, discount_rate, amount_paid, create_time) VALUES
(1, 2, 10.0, 10.0, 10.0 * (1 - 10.0 / 100), CURRENT_TIMESTAMP),
(2, 2, 20.0, 10.0, 20.0 * (1 - 10.0 / 100), CURRENT_TIMESTAMP),
(3, 2, 30.0, 10.0, 30.0 * (1 - 10.0 / 100), CURRENT_TIMESTAMP),
(4, 2, 40.0, 10.0, 40.0 * (1 - 10.0 / 100), CURRENT_TIMESTAMP),
(5, 2, 50.0, 10.0, 50.0 * (1 - 10.0 / 100), CURRENT_TIMESTAMP),
(6, 2, 60.0, 10.0, 60.0 * (1 - 10.0 / 100), CURRENT_TIMESTAMP),
(7, 2, 70.0, 10.0, 70.0 * (1 - 10.0 / 100), CURRENT_TIMESTAMP),
(8, 2, 80.0, 10.0, 80.0 * (1 - 10.0 / 100), CURRENT_TIMESTAMP),
(9, 2, 90.0, 10.0, 90.0 * (1 - 10.0 / 100), CURRENT_TIMESTAMP),
(10, 2, 100.0, 10.0, 100.0 * (1 - 10.0 / 100), CURRENT_TIMESTAMP);

-- Insert 20 orders for customer_id=3 (Discount rate: 20%)
INSERT INTO orders (id, customer_id, item_price, discount_rate, amount_paid, create_time) VALUES
(11, 3, 10.0, 20.0, 10.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(12, 3, 20.0, 20.0, 20.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(13, 3, 30.0, 20.0, 30.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(14, 3, 40.0, 20.0, 40.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(15, 3, 50.0, 20.0, 50.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(16, 3, 60.0, 20.0, 60.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(17, 3, 70.0, 20.0, 70.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(18, 3, 80.0, 20.0, 80.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(19, 3, 90.0, 20.0, 90.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(20, 3, 100.0, 20.0, 100.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(21, 3, 10.0, 20.0, 10.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(22, 3, 20.0, 20.0, 20.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(23, 3, 30.0, 20.0, 30.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(24, 3, 40.0, 20.0, 40.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(25, 3, 50.0, 20.0, 50.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(26, 3, 60.0, 20.0, 60.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(27, 3, 70.0, 20.0, 70.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(28, 3, 80.0, 20.0, 80.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(29, 3, 90.0, 20.0, 90.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP),
(30, 3, 100.0, 20.0, 100.0 * (1 - 20.0 / 100), CURRENT_TIMESTAMP);



