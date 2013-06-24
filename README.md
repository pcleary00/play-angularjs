play-angularjs
==============

Angular JS Tutorial written in the Play Framework with a Slick Database.

## Getting Started
First, you need to install play.  You can get play from http://www.playframework.com/
Alternatively, you can try out the new Typesafe Activator at https://typesafe.com/platform/getstarted

Once you have all of the tools installed, simple clone this project
```bash
$ git clone https://github.com/pcleary00/play-angularjs.git
$ cd play-angularjs
$ play run
```

## What is where?
### Angular JS files
Located in public/app

### Web API
The Web API consists of the routes file and the controller.

* You can find the routes file in conf/routes
* You can find the controller in app/controllers/Application.scala

### Database - Slick Code
The Slick code can be found in app/models/Phones.scala

### What about the database configuration?
The database is configured to use the Slick built in H2 Driver by default.

The database is configured in conf/application.conf

### How do you load the test data?
That would be in app/Global.scala

It is definitely hacky, when the application starts up, I create the database by running
Phones.ddl.create.  I catch an exception, which is thrown if the tables already exist and simply print them out.

Ugly, yes.  Quick and dirty, yes.

### How do you hook up Angular JS?
Look in app/views/angular.scala.html

## Best way to navigate the project
1. Open up the routes file, there you will see the URLs and links to the Application code
2. Open up the Application scala file.  There, you will see how we handle requests.
3. Track the calls to the data access layer, which is just Phones.scala


