show databases;
CREATE DATABASE itemsdb;
use itemsdb;

show tables;

CREATE TABLE inventory (
    id SERIAL PRIMARY KEY,          -- Unique ID for the inventory record
    item_id VARCHAR(255) NOT NULL,  -- Unique ID of the item (references Item collection in MongoDB)
    available_units INT NOT NULL,   -- Number of available units
    reserved_units INT NOT NULL,    -- Number of reserved units
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp when the record was created
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Timestamp when the record was last updated
);

DESC inventory;