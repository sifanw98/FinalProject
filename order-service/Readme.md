# Order Service

A microservice for managing orders in an online shopping system. It supports order creation, update, cancellation, and retrieval. The service uses Cassandra for persistence, Kafka for event publishing, and communicates with an external Item Service via Feign.

## Tech Stack

- **Language/Framework:** Java 17, Spring Boot 3.1.4, Spring Data Cassandra
- **Messaging:** Apache Kafka, Zookeeper
- **Database:** Apache Cassandra
- **Containerization:** Docker, Docker Compose

## Setup

### 1. Build the Project

In the project root, run:
```bash
mvn clean package
```

### 2. Docker Setup

a. Create init.cql

In the project root, create a file named init.cql with:

```cql
CREATE KEYSPACE IF NOT EXISTS orders_keyspace 
WITH replication = {'class':'SimpleStrategy', 'replication_factor':1} 
AND durable_writes = true;
```
b. Use the Provided docker-compose.yml
Create or update your docker-compose.yml with the following content:

```yamlversion: '3.8'

services:
  cassandra:
    image: cassandra:latest
    container_name: cassandra
    ports:
      - "9042:9042"
    volumes:
      - cassandra_data:/var/lib/cassandra
    environment:
      - CASSANDRA_CLUSTER_NAME=ShoppingCluster
      - CASSANDRA_DC=datacenter1
      - CASSANDRA_BROADCAST_RPC_ADDRESS=cassandra
    healthcheck:
      test: [ "CMD", "cqlsh", "-e", "DESCRIBE KEYSPACES" ]
      interval: 30s
      timeout: 10s
      retries: 5

  cassandra-init:
    image: cassandra:latest
    container_name: cassandra-init
    depends_on:
      - cassandra
    volumes:
      - ./init.cql:/init.cql
    entrypoint: ["sh", "-c", "sleep 30 && cqlsh cassandra -f /init.cql"]

  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    container_name: zookeeper
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181

  kafka:
    image: confluentinc/cp-kafka:latest
    container_name: kafka
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1

  orderservice:
    build: .
    container_name: orderservice
    depends_on:
      cassandra:
        condition: service_healthy
      cassandra-init:
        condition: service_started
      kafka:
        condition: service_started
    environment:
      SPRING_DATA_CASSANDRA_CONTACT_POINTS: cassandra
      SPRING_DATA_CASSANDRA_PORT: 9042
      SPRING_DATA_CASSANDRA_KEYSPACE_NAME: orders_keyspace
      SPRING_DATA_CASSANDRA_LOCAL_DATACENTER: datacenter1
      SPRING_DATA_CASSANDRA_SCHEMA_ACTION: CREATE_IF_NOT_EXISTS
      SPRING_KAFKA_BOOTSTRAP_SERVERS: kafka:9092
    ports:
      - "8080:8080"

volumes:
  cassandra_data:
```

c. Launch the Stack
Run:
```bash
docker-compose up --build
```

## API Endpoints

**Base URL:** `http://localhost:8080`

### Endpoints

- **Create Order**  
  **Method:** `POST`  
  **Endpoint:** `/orders?userId={uuid}`  
  **Body:** JSON array of OrderItem objects  
  **Response:** Returns the created Order object

- **Update Order**  
  **Method:** `PUT`  
  **Endpoint:** `/orders/{orderId}/status?status={OrderStatus}`  
  **Body (Optional):** JSON array of updated OrderItem objects  
  **Response:** Returns the updated Order object

- **Cancel Order**  
  **Method:** `DELETE`  
  **Endpoint:** `/orders/{orderId}`  
  **Response:** No content

- **Get Order Details**  
  **Method:** `GET`  
  **Endpoint:** `/orders/{orderId}`  
  **Response:** Returns the Order object

- **Get Order Items**  
  **Method:** `GET`  
  **Endpoint:** `/orders/{orderId}/items`  
  **Response:** Returns a JSON array of OrderItem objects

## Environment Variables (via docker-compose)

**Cassandra:**

- `SPRING_DATA_CASSANDRA_CONTACT_POINTS`: `cassandra`
- `SPRING_DATA_CASSANDRA_PORT`: `9042`
- `SPRING_DATA_CASSANDRA_KEYSPACE_NAME`: `orders_keyspace`
- `SPRING_DATA_CASSANDRA_LOCAL_DATACENTER`: `datacenter1`
- `SPRING_DATA_CASSANDRA_SCHEMA_ACTION`: `CREATE_IF_NOT_EXISTS`

**Kafka:**

- `SPRING_KAFKA_BOOTSTRAP_SERVERS`: `kafka:9092`

