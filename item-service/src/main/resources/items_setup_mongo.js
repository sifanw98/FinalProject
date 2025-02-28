
use itemdb

db.createCollection("items")

// Use the "itemsdb" database (MongoDB will create it if it doesn't exist)
use itemsdb

// Create the 'items' collection (optional in MongoDB, it will auto-create when you insert data)
db.createCollection("items")

// Create an index on the item_id field. Ensure itemId is unique
db.items.createIndex({ "itemId": 1 }, { unique: true }) // 1 for ascending order

// Create a compound index on category and brand.
db.items.createIndex({ category: 1, brand: 1 }) // 1 for ascending order for both fields

// Create a text index for full-text search on name, description, and brand
db.items.createIndex(
    {
        name: "text",
        description: "text",
        brand: "text"
    },
    {
        weights: {
            name: 5, // This is priority
            description: 1,
            brand: 2
        },
        name: "TextSearchIndex"
    }
)