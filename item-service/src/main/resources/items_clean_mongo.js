use itemsdb

// Remove all data but keep the collection
// db.items.deleteMany({})

// Remove the entire collection  (data + collection + indexes)
db.items.drop()

/*
db.getCollectionNames().forEach(function(collection) {
    if (collection.indexOf("system.") !== 0) {
        db[collection].remove({})
    }
})
 */