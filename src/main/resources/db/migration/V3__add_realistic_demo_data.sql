-- Add more realistic products with diverse categories and prices
INSERT INTO products (name, price_in_cents) VALUES
    ('Spaghetti', 350),
    ('Ground Beef', 850),
    ('Tomato Sauce', 250),
    ('Parmesan Cheese', 550),
    ('Garlic', 100),
    ('Olive Oil', 650),
    ('Eggs', 450),
    ('Bacon', 750),
    ('All-Purpose Flour', 300),
    ('Sugar', 250),
    ('Chocolate Chips', 450),
    ('Butter', 400),
    ('Vanilla Extract', 550),
    ('Baking Soda', 150),
    ('Salt', 100),
    ('Chicken Breast', 950),
    ('Rice', 400),
    ('Broccoli', 350),
    ('Carrots', 200),
    ('Onions', 150),
    ('Bell Peppers', 300),
    ('Soy Sauce', 350),
    ('Honey', 500),
    ('Lemon', 150),
    ('Potatoes', 300);

-- Add more realistic recipes with attractive names and descriptions
INSERT INTO recipes (name, description) VALUES
    ('Spaghetti Carbonara', 'A classic Italian pasta dish with creamy egg sauce, pancetta, and cheese. Simple yet luxurious!'),
    ('Chocolate Chip Cookies', 'Soft and chewy cookies with melty chocolate chips. Perfect for dessert or a sweet snack!'),
    ('Teriyaki Chicken Stir Fry', 'Tender chicken and crisp vegetables in a sweet and savory teriyaki sauce. Served over steamed rice.'),
    ('Homemade Pizza', 'Crispy crust topped with tomato sauce, melted cheese, and your favorite toppings. Better than delivery!'),
    ('Garlic Mashed Potatoes', 'Creamy, buttery mashed potatoes with roasted garlic. The perfect side dish for any meal!');

-- Add recipe ingredients with realistic quantities
-- Spaghetti Carbonara ingredients
INSERT INTO recipe_products (recipe_id, product_id, quantity) VALUES
    (4, 6, 1),  -- Spaghetti
    (4, 7, 3),  -- Eggs
    (4, 8, 1),  -- Bacon
    (4, 4, 1),  -- Parmesan Cheese
    (4, 5, 2),  -- Garlic
    (4, 6, 1);  -- Olive Oil

-- Chocolate Chip Cookies ingredients
INSERT INTO recipe_products (recipe_id, product_id, quantity) VALUES
    (5, 9, 1),   -- All-Purpose Flour
    (5, 10, 1),  -- Sugar
    (5, 11, 1),  -- Chocolate Chips
    (5, 12, 1),  -- Butter
    (5, 7, 2),   -- Eggs
    (5, 13, 1),  -- Vanilla Extract
    (5, 14, 1),  -- Baking Soda
    (5, 15, 1);  -- Salt

-- Teriyaki Chicken Stir Fry ingredients
INSERT INTO recipe_products (recipe_id, product_id, quantity) VALUES
    (6, 16, 2),  -- Chicken Breast
    (6, 17, 1),  -- Rice
    (6, 18, 1),  -- Broccoli
    (6, 19, 2),  -- Carrots
    (6, 20, 1),  -- Onions
    (6, 21, 1),  -- Bell Peppers
    (6, 22, 1),  -- Soy Sauce
    (6, 23, 1),  -- Honey
    (6, 5, 2);   -- Garlic

-- Homemade Pizza ingredients
INSERT INTO recipe_products (recipe_id, product_id, quantity) VALUES
    (7, 9, 1),   -- All-Purpose Flour
    (7, 3, 1),   -- Tomato Sauce
    (7, 4, 1),   -- Parmesan Cheese
    (7, 5, 1),   -- Garlic
    (7, 6, 1),   -- Olive Oil
    (7, 15, 1);  -- Salt

-- Garlic Mashed Potatoes ingredients
INSERT INTO recipe_products (recipe_id, product_id, quantity) VALUES
    (8, 25, 4),  -- Potatoes
    (8, 12, 1),  -- Butter
    (8, 4, 1),   -- Milk
    (8, 5, 3),   -- Garlic
    (8, 15, 1);  -- Salt

-- Create sample carts with different scenarios

-- Cart 1: Family dinner with multiple recipes
INSERT INTO carts (total_in_cents) VALUES (0);
-- Add Spaghetti Carbonara recipe to cart 1
INSERT INTO cart_recipes (cart_id, recipe_id) VALUES (2, 4);
-- Add Garlic Mashed Potatoes recipe to cart 1
INSERT INTO cart_recipes (cart_id, recipe_id) VALUES (2, 8);
-- Add extra items to cart 1
INSERT INTO cart_items (cart_id, product_id) VALUES (2, 24); -- Lemon
INSERT INTO cart_items (cart_id, product_id) VALUES (2, 6);  -- Olive Oil

-- Cart 2: Baking day with cookies and extra ingredients
INSERT INTO carts (total_in_cents) VALUES (0);
-- Add Chocolate Chip Cookies recipe to cart 2
INSERT INTO cart_recipes (cart_id, recipe_id) VALUES (3, 5);
-- Add extra items for more baking
INSERT INTO cart_items (cart_id, product_id) VALUES (3, 9);  -- More flour
INSERT INTO cart_items (cart_id, product_id) VALUES (3, 10); -- More sugar
INSERT INTO cart_items (cart_id, product_id) VALUES (3, 11); -- More chocolate chips
INSERT INTO cart_items (cart_id, product_id) VALUES (3, 7);  -- More eggs

-- Cart 3: Asian cuisine night
INSERT INTO carts (total_in_cents) VALUES (0);
-- Add Teriyaki Chicken Stir Fry recipe to cart 3
INSERT INTO cart_recipes (cart_id, recipe_id) VALUES (4, 6);
-- Add extra Asian ingredients
INSERT INTO cart_items (cart_id, product_id) VALUES (4, 22); -- More soy sauce
INSERT INTO cart_items (cart_id, product_id) VALUES (4, 17); -- More rice