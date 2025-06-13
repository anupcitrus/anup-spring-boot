-- Sample data for shops table
-- This file will be executed automatically by Spring Boot if spring.jpa.hibernate.ddl-auto is set to create or create-drop
-- For update mode, we'll insert data manually

INSERT IGNORE INTO shops (name, address, phone, email, created_date, updated_date) VALUES
('Tech World Electronics', '123 Main Street, Downtown, City', '+1-555-0101', 'info@techworld.com', NOW(), NOW()),
('Green Garden Nursery', '456 Oak Avenue, Suburb, City', '+1-555-0102', 'contact@greengarden.com', NOW(), NOW()),
('Fashion Forward Boutique', '789 Fashion Lane, Mall District, City', '+1-555-0103', 'hello@fashionforward.com', NOW(), NOW()),
('Books & Beyond', '321 Library Road, Academic Quarter, City', '+1-555-0104', 'books@booksbeyond.com', NOW(), NOW()),
('Fresh Market Grocery', '654 Market Square, Central, City', '+1-555-0105', 'orders@freshmarket.com', NOW(), NOW()),
('Auto Parts Plus', '987 Industrial Blvd, Industrial Zone, City', '+1-555-0106', 'parts@autopartsplus.com', NOW(), NOW()),
('Cozy Coffee Corner', '147 Cafe Street, Arts District, City', '+1-555-0107', 'hello@cozycoffee.com', NOW(), NOW()),
('Sports Zone', '258 Athletic Way, Sports Complex, City', '+1-555-0108', 'info@sportszone.com', NOW(), NOW()),
('Home & Garden Center', '369 Home Depot Lane, Residential, City', '+1-555-0109', 'service@homegarden.com', NOW(), NOW()),
('Digital Solutions Store', '741 Tech Park Drive, Business District, City', '+1-555-0110', 'support@digitalsolutions.com', NOW(), NOW());
