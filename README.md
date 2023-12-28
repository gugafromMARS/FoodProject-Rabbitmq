# User-Order-Restaurant-Shipping

![Logo](https://github.com/gugafromMARS/sb-rabbitmq-mcs/assets/116969206/0e81fe59-45cf-43b3-bb5d-14f75813b434)


Welcome, this is a simple project with Rabbitmq with exchange in microservices, the theme of the project is for order food from any restaurant registered on the app.
So, first of all, if you are an user, you can register yourself, and choose any food of the menu from any restaurant registered on the app.
If you are a restaurant, you can register the restaurant with your menu and prices, you can update your menu, removing or adding new foods.
Summary of the application behind the scenes: 
- Im an user registered in user service, im making the order in a order service, the order service is going to make an open feign communication with user service to get the user address from a get user api,
  after this, the order service send for order queue the order dto with all the info including the restaurant email, the restaurant have a consumer for this queue and is going to make a order preparation, when its done, restaurant sends to
restaurant queue for any shipping consumer receives and make the distribution, sending for the user the a tracking id. 

## Architecture

![ArchitectureImg](https://github.com/gugafromMARS/FoodProject-Rabbitmq/assets/116969206/827a9a8d-69a0-4ce9-a8a9-9580af205437)



## Technology

Here are some technologys used on this project.

* Java version 17
* RabbitMQ

## Services

Services used.

* Github
  
## Getting started

1- Run this command on terminal for start a container with RabbitMQ
```shell script
docker run -d -p 15672:15672 -p 5672:5672 --hostname my-rabbitmq --name my-rabbitmq-container rabbitmq:3.13.0-rc.2-management
```
## App functionalitys

In this project you have only on option :

* **Send Order**
  
You have a controller in order microservice thats your producer who going to send order to exchange.

## Authors

**gugafromMars**

[Github-gugafromMars](https://github.com/gugafromMARS)

Thanks to visiting and happy coding!
