# FOOD-ORDER-DELIVERY

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

1- Go to Docker Hub choose RabbitMQ version and pull the image in your terminal
```shell script
docker pull {YOUR RABBITMQ VERSION}
```

2- Run this command on terminal for start a container with RabbitMQ
```shell script
docker run -d -p 15672:15672 -p 5672:5672 --hostname my-rabbitmq --name my-rabbitmq-container rabbitmq:3.13.0-rc.2-management
```

3- Run this commands for build mysql databases for all microservices.
3.1- Docker container for users service. 
```shell script
docker run -d -p 3306:3306 --name users_restaurant_db -v $(pwd)/usersrestaurantdata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=restaurantusersdb  mysql:latest
```

3.2- Docker container for restaurant service. 
```shell script
docker run -d -p 3307:3306 --name restaurants_db -v $(pwd)/restaurantsdata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=restaurantsdb  mysql:latest
```

3.3- Docker container for orders service. 
```shell script
docker run -d -p 3308:3306 --name restaurant_orders_db -v $(pwd)/restaurantordersdata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=restaurantordersdb  mysql:latest
```

## App functionalitys

Summary of api possibilities in all microservices.

### Users Service
* **Create User**
* **Get User by email**
* **Delete User**
* **Update User**
Update user email or user address.

### Orders Service
* **Create Order**
* **Get Order By UUID**
* **Get All Orders from user email**

### Restaurants Service
* **Create Restaurant**
  Restaurant name, email, address and menu (food and the respetive price).
* **Get all Restaurants**
* **Get Restaurant by name**
* **Delete Restaurant**
* **Update Restaurant email**
* **Update Restaurant menu (remove item)**
* **Update Restaurant menu (add item)**
* **Update Restaurant orders list (Ready to go)**
  This endpoint when the order received from order service, restaurant as soon as order is ready to go, this endpoint is for remove the order from list and send a message for restaurant queue for shipping consumer receive the info.
* **Get all orders to do from a Restaurant (in that momment)**

### Shipping Service
Have a consumer in restaurant queue, when the order is ready and when is going to make the delivery it sends a message for the shipping queue and user consumer receive it.

## Authors

**gugafromMars**

[Github-gugafromMars](https://github.com/gugafromMARS)

Thanks to visiting and happy coding!
