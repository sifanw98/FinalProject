use itemsdb

db.getCollectionNames().forEach(function(collection) {
    if (collection.indexOf("system.") !== 0) {
        db[collection].remove({})
    }
})