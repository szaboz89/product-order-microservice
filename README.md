# Microservices Examples with Spring Boot

### Microservices in Product System:

- **Product Microservice**
    - Responsibilities:
        - Manage which products are available in the system
        - Get available amount for each product from **Store-Microservice via REST API**
        - Check if available amount of products below minimum and request more from **Store-Microservice via JMS**
        - UI for CRUD operations on products
    - Source: https://github.com/szaboz89/product-microservice
    - Testing: http://localhost:8080
- **Product Store Microservice**
    - Responsibilities:
        - Store the amount of available products
        - Handle **JMS request about required products from Product-Microservice**
        - Update the amount of available products if demand processed
        - Handle **JMS request about new order from Order-Microservice** in order to decrease the amount of ordered product
        - Send **JMS reply message to Order-Microservice** whether the order can be fulfilled
        - Handle **JMS message about deleted order from Order-Microservice** in order to increase the available amount  
        - UI for managing stock and processing demand
    - Source: https://github.com/szaboz89/product-store-microservice
    - Testing: http://localhost:8081
- **Product Order Microservice**
    - Responsibilities:
        - Manage orders for products
        - Get the available products from **Product-Microservice REST API**
        - Send request to **Store-Microservice via JMS** for decreasing the available amount of the product
        - Handle **JMS reply message received from Store-Microservice** and save order if required amount available
        - In case of deleting order send **JMS message to Store-Microservice** for increasing the available amount of the product
        - UI for viewing available products and managing orders
    - Source: _this repository_
    - Testing: http://localhost:8082