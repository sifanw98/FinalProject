CREATE KEYSPACE IF NOT EXISTS orders_keyspace
    WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}
     AND durable_writes = true;