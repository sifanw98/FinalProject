
// Insert sample data into the 'items' collection matching the item_id from MySQL inventory
db.items.insertMany([
    {
        item_id: "12345",
        upc: "111-222-333",
        name: "Laptop",
        description: "High-end laptop with 16GB RAM and 512GB SSD",
        price: 999.99,
        pictureUrls: [
            "http://example.com/laptop1.jpg",
            "http://example.com/laptop2.jpg"
        ],
        category: "Electronics",
        brand: "BrandX",
        created_at: new Date("2023-10-01T12:00:00Z"),
        updated_at: new Date("2023-10-01T12:00:00Z")
    },
    {
        item_id: "67890",
        upc: "111-222-334",
        name: "Smartphone",
        description: "Latest smartphone with 128GB storage and high-resolution camera",
        price: 599.99,
        pictureUrls: [
            "http://example.com/smartphone1.jpg",
            "http://example.com/smartphone2.jpg"
        ],
        category: "Electronics",
        brand: "BrandY",
        created_at: new Date("2023-10-01T12:00:00Z"),
        updated_at: new Date("2023-10-01T12:00:00Z")
    },
    {
        item_id: "54321",
        upc: "111-222-335",
        name: "Headphones",
        description: "Noise-cancelling wireless headphones with 30-hour battery life",
        price: 199.99,
        pictureUrls: [
            "http://example.com/headphones1.jpg",
            "http://example.com/headphones2.jpg"
        ],
        category: "Accessories",
        brand: "BrandZ",
        created_at: new Date("2023-10-01T12:00:00Z"),
        updated_at: new Date("2023-10-01T12:00:00Z")
    },
    {
        item_id: "98765",
        upc: "111-222-336",
        name: "Wireless Mouse",
        description: "Ergonomic wireless mouse with long battery life",
        price: 49.99,
        pictureUrls: [
            "http://example.com/mouse1.jpg",
            "http://example.com/mouse2.jpg"
        ],
        category: "Accessories",
        brand: "BrandW",
        created_at: new Date("2023-10-01T12:00:00Z"),
        updated_at: new Date("2023-10-01T12:00:00Z")
    },
    {
        item_id: "11223",
        upc: "111-222-337",
        name: "Keyboard",
        description: "Mechanical keyboard with customizable RGB lighting",
        price: 129.99,
        pictureUrls: [
            "http://example.com/keyboard1.jpg",
            "http://example.com/keyboard2.jpg"
        ],
        category: "Accessories",
        brand: "BrandK",
        created_at: new Date("2023-10-01T12:00:00Z"),
        updated_at: new Date("2023-10-01T12:00:00Z")
    }
]);

db.items.find()