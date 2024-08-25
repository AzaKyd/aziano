-- Вставка данных в таблицу categories
INSERT INTO categories (category_name) VALUES ('Женская одежда');
INSERT INTO categories (category_name) VALUES ('Мужская одежда');
INSERT INTO categories (category_name) VALUES ('Аксессуары');

-- Вставка данных в таблицу products
INSERT INTO products (barcode, cost_price, description, image_url, name, product_code, selling_price, category_id)
VALUES ('1234567890123', 100.00, 'Летнее платье', 'http://example.com/dress1.jpg', 'Платье Летнее', 'PRD001', 150.00, 1);
INSERT INTO products (barcode, cost_price, description, image_url, name, product_code, selling_price, category_id)
VALUES ('1234567890124', 200.00, 'Деловая рубашка', 'http://example.com/shirt1.jpg', 'Рубашка Деловая', 'PRD002', 300.00, 2);
INSERT INTO products (barcode, cost_price, description, image_url, name, product_code, selling_price, category_id)
VALUES ('1234567890125', 50.00, 'Кожаный ремень', 'http://example.com/belt1.jpg', 'Ремень Кожаный', 'PRD003', 100.00, 3);

-- Вставка данных в таблицу warehouses
INSERT INTO warehouses (location, name) VALUES ('Алматы', 'Основной склад');
INSERT INTO warehouses (location, name) VALUES ('Нур-Султан', 'Склад №2');
INSERT INTO warehouses (location, name) VALUES ('Шымкент', 'Склад №3');

-- Вставка данных в таблицу sales
INSERT INTO sales (discount, quantity, sale_date, selling_price, warehouse_id)
VALUES (10.00, 2, '2024-08-25 10:00:00', 140.00, 1);
INSERT INTO sales (discount, quantity, sale_date, selling_price, warehouse_id)
VALUES (20.00, 1, '2024-08-26 11:00:00', 280.00, 2);
INSERT INTO sales (discount, quantity, sale_date, selling_price, warehouse_id)
VALUES (5.00, 5, '2024-08-27 12:00:00', 95.00, 3);

-- Вставка данных в таблицу sale_product
INSERT INTO sale_product (sale_id, product_id) VALUES (1, 1);
INSERT INTO sale_product (sale_id, product_id) VALUES (2, 2);
INSERT INTO sale_product (sale_id, product_id) VALUES (3, 3);

-- Вставка данных в таблицу stock_entry
INSERT INTO stock_entry (entry_date, quantity, warehouse_id)
VALUES ('2024-08-20 09:00:00', 50, 1);
INSERT INTO stock_entry (entry_date, quantity, warehouse_id)
VALUES ('2024-08-21 09:00:00', 30, 2);
INSERT INTO stock_entry (entry_date, quantity, warehouse_id)
VALUES ('2024-08-22 09:00:00', 40, 3);

-- Вставка данных в таблицу entry_product
INSERT INTO entry_product (stock_entry_id, product_id) VALUES (1, 1);
INSERT INTO entry_product (stock_entry_id, product_id) VALUES (2, 2);
INSERT INTO entry_product (stock_entry_id, product_id) VALUES (3, 3);
