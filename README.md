

## Getting started


#### Run Project Using Docker :

Follow these steps to get started:

#### Step 1: Clone this repository

```bash
$ git clone https://github.com/Istiaq-Hossain-Shawon/ShippingRateMiddleware

```
#### Step 2: Go to project root directory ShippingRateMiddleware and open cmd

#### Step 3: Build Spring Boot Project with Maven
```bash
mvn clean install 
```
#### Step 4: Do docker compose build:
```bash
docker-compose build

```
#### Step 4: Do docker compose build:
```bash
docker-compose up

```
### Open Browers:

use to this below URL for swagger documentation:

http://localhost:8096/api/swagger-ui/index.html

Docker will run with the production(prod) environment by default

### Run Using Eclipse:

Follow these steps to get started:

#### Step 1: Configuring Eclipse IDE for Java

You need to download the last version of Eclipse IDE for Java EE Developers, for example [Lunar](https://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/lunasr2) version. Make sure that Eclipse has installed the Maven plugin.


#### Step 2: Clone this repository

```bash
$ git clone https://github.com/Istiaq-Hossain-Shawon/ShippingRateMiddleware

```

#### Step 3: Import  Maven Project into Eclipse

To import an existing Maven project into Eclipse, just right-click the Package Explorer and go to:

`
Import... > Existing Maven Projects > Select root directory > Finish
`

It's possible that you need to update Maven project. To do it, just right-click the project and go to:

`
Maven > Update Project...
`

#### Step 4: Provide Mysql Username and password.

To provide mysql username and password , go to application-dev.yml  file from resources  and changes it :

`
url: jdbc:mysql://localhost:3306/shippingrate?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false
username: *****
password: *****
`

#### Step 5: Run Sprint boot  application

