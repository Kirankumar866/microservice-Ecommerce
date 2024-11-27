-- Insert sample data into the category table
INSERT INTO category (id, name, description)
VALUES
    (nextval('category_seq'), 'Electronics', 'Devices and gadgets'),
    (nextval('category_seq'), 'Furniture', 'Home and office furniture'),
    (nextval('category_seq'), 'Clothing', 'Apparel and accessories'),
    (nextval('category_seq'), 'Groceries', 'Daily essentials and food items'),
    (nextval('category_seq'), 'Books', 'Books and study materials');

-- Insert sample data into the product table
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'Smartphone', 'Latest model smartphone with advanced features', 100, 699.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Laptop', 'High-performance laptop for professionals', 50, 1299.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Sofa', 'Comfortable 3-seater sofa', 20, 499.99, (SELECT id FROM category WHERE name = 'Furniture')),
    (nextval('product_seq'), 'Dining Table', 'Wooden dining table for 6 people', 10, 599.99, (SELECT id FROM category WHERE name = 'Furniture')),
    (nextval('product_seq'), 'T-shirt', 'Cotton T-shirt available in multiple colors', 200, 19.99, (SELECT id FROM category WHERE name = 'Clothing')),
    (nextval('product_seq'), 'Jeans', 'Denim jeans for casual wear', 150, 39.99, (SELECT id FROM category WHERE name = 'Clothing')),
    (nextval('product_seq'), 'Rice', 'High-quality basmati rice (5kg)', 500, 9.99, (SELECT id FROM category WHERE name = 'Groceries')),
    (nextval('product_seq'), 'Cooking Oil', '1-liter bottle of refined cooking oil', 300, 4.99, (SELECT id FROM category WHERE name = 'Groceries')),
    (nextval('product_seq'), 'Novel', 'Bestselling fiction novel', 100, 14.99, (SELECT id FROM category WHERE name = 'Books')),
    (nextval('product_seq'), 'Notebook', 'Pack of 5 ruled notebooks', 200, 7.99, (SELECT id FROM category WHERE name = 'Books'));
