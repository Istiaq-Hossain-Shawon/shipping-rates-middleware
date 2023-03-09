
# Shipping Rates Middleware REST API Documentation

This API provides functionality to retrieve shipping rates of two logistics company  **City Link Express** and **JT Express** based on user input. The API is secured with JWT token authentication and can be accessed through HTTP requests.
## Getting started
To start using the API, you will need to have a valid JWT token. To obtain a token, you can send a POST request to `/api/requesttoken` endpoint with a valid username and password in the request body.

Once you have obtained a valid token, you can make requests to the `/api/shipping-rates` endpoint with a valid JSON request body containing the required information.
## Endpoints
The API has the following endpoints:

`/api/requesttoken`: Allows users to obtain a JWT token for authentication

`/api/shipping-rates`: Allows users to retrieve shipping rates based on input data

## Run Project Using Docker

Follow these steps to get started:

`Step 1:` Clone this repository

```bash
git clone https://github.com/Istiaq-Hossain-Shawon/shipping-rates-middleware

```
`Step 2:` Go inside to project root directory shipping-rates-middleware and open cmd


` Step 3: `Build Spring Boot Project with Maven
```bash
mvn clean install 
```
` Step 4:` Do docker compose build
```bash
docker-compose build

```
` Step 5:` Do docker compose up
```bash
docker-compose up

```
### Open Browers:

It will run this spring boot api project with the production(prod) environment by default

Use below URL for swagger documentation:

http://localhost:8096/api/swagger-ui/index.html


### Test Flow using Swagger UI:

`Step 1: ` Get The JWT Token using  `api/requesttoken` endpoint with providing Username & password in request body.Expand JWT Token area in swagger. 

![alt text](https://github.com/Istiaq-Hossain-Shawon/shipping-rates-middleware/blob/main/wiki-images/GetTokenImage.png?raw=true) 


Request Body :
```bash
{
   "username":"user1",
   "password":"123456"
}

```

` Step 2:` Go `api/shipping-Rates` endpoint controller and  click the lock button to provide jwt token.It will open a pop up window  

![alt text](https://github.com/Istiaq-Hossain-Shawon/shipping-rates-middleware/blob/main/wiki-images/AuthorizeButtonGetRates.PNG?raw=true) 


` Step 3:` Give jwt token in input field with Bearer  in the pop up window.

![alt text](https://github.com/Istiaq-Hossain-Shawon/shipping-rates-middleware/blob/main/wiki-images/JWT%20AUthorize.png?raw=true) 



`Step 4: ` Authorize  `api/shipping-Rates` Endpoints with JWT token

![alt text](https://github.com/Istiaq-Hossain-Shawon/shipping-rates-middleware/blob/main/wiki-images/Authorized.png?raw=true) 


` Step 5:`  Execute `api/shipping-Rates` 

![alt text](https://github.com/Istiaq-Hossain-Shawon/shipping-rates-middleware/blob/main/wiki-images/CallGetRatesAPIWIthToken.PNG?raw=true) 

Request Body :
```bash
{   
    "destinationCountry": "AW",
    "destinationPostcode": "50000",
    "destinationState": "Aruba",
    "documentWeight": 11,
    "height": 12,
    "length": 12,
    "originCountry": "MY",
    "originPostcode": "40000",
    "originState": "Selangor",
    "weight": 21,
    "goodsSelectedType": 1,
    "width": 20,
    "shippingRatesType": "domestic",
    "shippingType": "EZ"
}
```
 
`Step 6:` Output

![alt text](https://github.com/Istiaq-Hossain-Shawon/shipping-rates-middleware/blob/main/wiki-images/OutPutResponse.PNG?raw=true) 



## Have a look at the workflow

![alt text](https://github.com/Istiaq-Hossain-Shawon/shipping-rates-middleware/blob/main/wiki-images/ApiMiddlewareWorkflow.jpg?raw=true) 



### Run Using Eclipse:

Follow these steps to get started:

#### Step 1: Configuring Eclipse IDE for Java

You need to download the last version of Eclipse IDE for Java EE Developers, for example [Lunar](https://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/lunasr2) version. Make sure that Eclipse has installed the Maven plugin.


#### Step 2: Clone this repository

```bash
$ git clone https://github.com/Istiaq-Hossain-Shawon/shipping-rates-middleware

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


# Error Handling
The API returns error responses with appropriate HTTP status codes and error messages in the response body.

# Technologies Used
This API is built using `Java`, `the Spring Framework`, `Mysql` Database for RDMS and `Docker`. It uses JWT token authentication for security.

# Conclusion
This API provides a convenient way for users to retrieve shipping rates based on input data. The API is secured with JWT token authentication and returns appropriate error messages for invalid requests.
