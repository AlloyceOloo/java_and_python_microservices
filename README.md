# 🚀 Event-Driven Microservices Demo

A lightweight, containerized microservices system demonstrating
**event-driven architecture**, **service decoupling**, and
**asynchronous processing** using:

-   **Spring Boot** (REST API + Kafka producer)
-   **Python Consumer** (Kafka event processor)
-   **Redpanda** (Kafka-compatible event streaming platform)
-   **PostgreSQL** (persistent data storage)
-   **Docker Compose** (orchestration)

This project is intended as a clean reference implementation of modern
microservice patterns such as:\
✓ Service boundaries\
✓ Async communication\
✓ Event sourcing (lightweight)\
✓ Message-driven workflows\
✓ Containerized deployments

------------------------------------------------------------------------

# 🏗️ Architecture Overview

                       +------------------------+
                       |   Spring Boot Service  |
                       |  (API + Event Producer)|
                       +-----------+------------+
                                   |
                                   |  Publishes events
                                   v
    +-----------+        +---------------------+         +----------------------+
    | PostgreSQL|<------>|      Redpanda       |<--------| Python Event Consumer|
    |   (DB)    |        |   (Kafka broker)    |  Events |  (Event Processor)   |
    +-----------+        +---------------------+         +----------------------+

------------------------------------------------------------------------

# ✨ Key Features

### ✅ Event-driven communication

### ✅ Real microservice boundaries

### ✅ Technology polyglot

### ✅ Scalable architecture

### ✅ Local development via Docker Compose

------------------------------------------------------------------------

# 📦 Getting Started

## 1. Clone

``` bash
git clone git@github.com:<your-org>/<repo>.git
cd <repo>
```

## 2. Run

``` bash
docker compose up --build
```

## 3. Test API

``` bash
curl -X POST http://localhost:8080/api/v1/wallets/123/credit      -H "Content-Type: application/json"      -H "Idempotency-Key: test-123"      -d '{"amount":100, "currency":"KES", "source":"test"}'
```

------------------------------------------------------------------------

# 🧱 Directory Structure

    .
    ├── docker-compose.yml
    ├── event-stream/
    ├── kafka-consumer/
    ├── spring-boot/
    └── README.md

------------------------------------------------------------------------

# 🛠️ Tech Stack

  Layer           Technology
  --------------- ------------------
  API             Spring Boot
  Messaging       Redpanda / Kafka
  Consumer        Python
  Database        PostgreSQL
  Orchestration   Docker Compose

------------------------------------------------------------------------

# 🔌 Event Flow

1.  API receives request\
2.  Writes to DB\
3.  Publishes event\
4.  Redpanda stores event\
5.  Python consumer processes event

------------------------------------------------------------------------

# 📚 Extend This

-   CQRS\
-   Event Sourcing\
-   Multi-service orchestration\
-   Real-time analytics

------------------------------------------------------------------------

# 🐳 Deployment

``` bash
docker compose up -d
docker compose down
```

------------------------------------------------------------------------

# 📄 License

MIT License
