# ActiveMQ Messaging Application

## **Project Description**
This project demonstrates the simple use of **ActiveMQ** for message exchange between microservices. It includes one producer and three consumers, each implemented as a separate microservice. The application is built using **Java 22** and **Spring Boot** and leverages **Docker** and **Docker Compose** for containerization and service orchestration.

The project showcases how to implement queues and topics for asynchronous message communication in distributed systems.

---

## **Technologies Used**

### **1. Apache ActiveMQ**
Apache ActiveMQ is a powerful open-source message broker supporting various protocols such as JMS (Java Message Service). We use ActiveMQ for message exchange between microservices.

**Key Features of ActiveMQ:**
- Support for queues and topics.
- Reliable message delivery.
- Durable subscriptions for persistent message consumption.

### **2. Microservices**
The project follows a microservices architecture:
- **Producer**: Generates and sends messages to ActiveMQ.
- **Consumer A, B, C**: Receive messages from queues or topics, each with its own processing logic.

### **3. Spring Boot**
Spring Boot simplifies microservice development. In this project, we utilize:
- **Spring JMS** for integration with ActiveMQ.
- **Spring Configuration** for easy setup of services.

### **4. Java 22**
The application is built on the latest version of Java, offering:
- Improved performance.
- New features in the standard library.
- Enhanced security and compatibility with modern frameworks.

### **5. Docker**
Docker is used to containerize all components:
- Each service (ActiveMQ, Producer, Consumers) runs in its own container.
- Provides an isolated and consistent environment for each component.

### **6. Docker Compose**
Docker Compose orchestrates the services, ensuring they start in the correct order:
1. ActiveMQ
2. Producer
3. Consumers

---

## **Project Structure**

```plaintext
project-root/
├── activemq/                     # ActiveMQ broker ports 61616, 8161
│   └── Dockerfile
├── producer/                     # Producer microservice port 8080
│   ├── Dockerfile
│   └── target/
│       └── producer.jar
├── consumer-a/                   # Consumer A microservice port 8081
│   ├── Dockerfile
│   └── target/
│       └── consumer-a.jar
├── consumer-b/                   # Consumer B microservice port 8082
│   ├── Dockerfile
│   └── target/
│       └── consumer-b.jar
├── consumer-c/                   # Consumer C microservice port 8083
│   ├── Dockerfile
│   └── target/
│       └── consumer-c.jar
├── docker/                       # Docker Compose configuration
│   ├── docker-compose.yml
└── README.md                     # Documentation
```


 **How to Run**

 **1. Prerequisites**
- **Docker** and **Docker Compose** must be installed.
- **Java 22** is required for development and `.jar` file creation.

 **2. Build the Project**
Navigate to each subproject (`producer`, `consumer-a`, `consumer-b`, `consumer-c`) and create a `.jar` file:

```bash
mvn clean package
```

This will generate a `.jar` file in the `target` folder of each module.

 **3. Start the Containers**
1. Build Docker images:

   ```bash
   docker-compose build
   ```

2. Start all services:

   ```bash
   docker-compose up
   ```

 **4. Verify the Setup**
- Access the **ActiveMQ Web Console** at: [http://localhost:8161](http://localhost:8161).
- The **Producer** runs on port `8080`.
- The **Consumers** run on ports `8081`, `8082`, and `8083`.

 **5. Stop the Containers**
 
   ```bash
   docker-compose down
   ```

 **Good luck to everyone!**