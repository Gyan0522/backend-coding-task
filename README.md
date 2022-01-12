

Steps to setup project

1. git clone  https://github.com/Gyan0522/backend-coding-task.git

2. mvn install

3. mvn -N io.takari:maven:wrapper

5. ./mvnw clean spring-boot:run


Note :
While setting up the project , ensure that maven is installed
steps to export the maven path from cmd line: 
Maven path setup 
$ export M2_HOME=/path to maven/apache-maven-3.6.2 
$ export M2=$M2_HOME/bin
$ export MAVEN_OPTS=-Xms256m -Xmx512m
$ export PATH=$M2:$PATH 

API Endpoint : 


POST
1. http://localhost:8080/v1/order/create

Body:
    {
      "orderId": "1", 
      "customerId": "1", 
      "amount": "100"
     }

2.http://localhost:8080/v1/payment/create

Body
   {
   "orderId": "1", 
   "amount": "10"
    }

GET
   1. http://localhost:8080/v1/order/balance?orderId=1
   2. http://localhost:8080/v1/customer/balance?customerId=1



Link to Swagger UI

### http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
