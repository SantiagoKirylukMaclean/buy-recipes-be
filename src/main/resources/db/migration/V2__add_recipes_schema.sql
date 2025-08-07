-- Create recipes table
CREATE TABLE recipes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create recipe_products table (junction table with own primary key)
CREATE TABLE recipe_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    recipe_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_recipe_products_recipe FOREIGN KEY (recipe_id) REFERENCES recipes(id) ON DELETE CASCADE,
    CONSTRAINT fk_recipe_products_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE RESTRICT
);

-- Create cart_recipes table (junction table with own primary key)
CREATE TABLE cart_recipes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_id BIGINT NOT NULL,
    recipe_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cart_recipes_cart FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
    CONSTRAINT fk_cart_recipes_recipe FOREIGN KEY (recipe_id) REFERENCES recipes(id) ON DELETE RESTRICT
);

-- Create indexes on foreign keys
CREATE INDEX idx_recipe_products_recipe_id ON recipe_products(recipe_id);
CREATE INDEX idx_recipe_products_product_id ON recipe_products(product_id);
CREATE INDEX idx_cart_recipes_cart_id ON cart_recipes(cart_id);
CREATE INDEX idx_cart_recipes_recipe_id ON cart_recipes(recipe_id);

-- Insert sample recipes
INSERT INTO recipes (name, description) VALUES
    ('Classic Pancakes', 'Fluffy and delicious pancakes for breakfast'),
    ('Vegetable Stir Fry', 'Quick and healthy vegetable stir fry'),
    ('Fruit Salad', 'Refreshing fruit salad with seasonal fruits');

-- Add ingredients to recipes
-- Pancakes ingredients
INSERT INTO recipe_products (recipe_id, product_id, quantity) VALUES
    (1, 4, 1), -- Milk
    (1, 5, 1); -- Bread (as a substitute for flour in this example)

-- Stir Fry ingredients
INSERT INTO recipe_products (recipe_id, product_id, quantity) VALUES
    (2, 3, 2); -- Orange (as a substitute for vegetables in this example)

-- Fruit Salad ingredients
INSERT INTO recipe_products (recipe_id, product_id, quantity) VALUES
    (3, 1, 2), -- Apple
    (3, 2, 2), -- Banana
    (3, 3, 1); -- Orange