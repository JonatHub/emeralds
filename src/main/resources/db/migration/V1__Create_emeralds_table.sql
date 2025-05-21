CREATE TABLE emeralds (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL CHECK (price > 0),
    carat_weight DOUBLE PRECISION NOT NULL CHECK (carat_weight > 0),
    origin VARCHAR(100) NOT NULL,
    certification VARCHAR(255),
    clarity VARCHAR(50) NOT NULL,
    color VARCHAR(50) NOT NULL,
    image_url VARCHAR(255),
    stock_quantity INTEGER NOT NULL CHECK (stock_quantity >= 0),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
); 