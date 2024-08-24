INSERT INTO categories (category_name) VALUES ('Dresses');
INSERT INTO categories (category_name) VALUES ('Shoes');
INSERT INTO categories (category_name) VALUES ('Accessories');

INSERT INTO products (barcode, cost_price, name, product_code, selling_price, category_id)
VALUES
    ('1234567890123', 50.00, 'Red Dress', '00001', 100.00, 1),
    ('2345678901234', 70.00, 'Black Shoes', '00002', 140.00, 2),
    ('3456789012345', 30.00, 'Gold Necklace', '00003', 60.00, 3);

INSERT INTO warehouses (location, name)
VALUES
    ('New York', 'Main Warehouse'),
    ('Los Angeles', 'Secondary Warehouse');

INSERT INTO sales (quantity, sale_date, selling_price, warehouse_id)
VALUES
    (5, '2024-08-12 09:00:00', 500.00, 1),
    (10, '2024-08-12 10:00:00', 1000.00, 2);

INSERT INTO sale_products (sale_id, product_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3);

INSERT INTO stock_entries (entry_date, quantity, warehouse_id)
VALUES
    ('2024-08-11 08:00:00', 50, 1),
    ('2024-08-11 09:00:00', 100, 2);

INSERT INTO entry_products (entry_id, product_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3);