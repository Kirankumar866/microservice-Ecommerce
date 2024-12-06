DROP TABLE IF EXISTS customer_order CASCADE;

CREATE TABLE customer_order (
    id SERIAL PRIMARY KEY,
    reference VARCHAR(255),
    total_amount NUMERIC(19,2),
    payment_method VARCHAR(255),
    customer_id VARCHAR(255),
    created_date TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE
);