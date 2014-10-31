# Vert.x Example Maven Project

Example project for creating a Vert.x module with a Gradle build.

By default this module contains a simple Java verticle which listens on the event bus and responds to `ping!`
messages with `pong!`.

This example also shows you how to write tests in Java, Groovy, Ruby and Python



* Switch to project's root directory
* cd ~
* cd Documents/workspace/

* To generate a project type the following at the command line, in your project's root directory:

* mvn archetype:generate -Dfilter=io.vertx: -DgroupId=com.yizhao -DartifactId=my-module -Dversion=0.1

* Choose archetype:

* 1
* Choose a number:
* 3
* Confirm properties configuration:

* y
* A directory with a name corresponding to artifactId will be created for you, with the example project in it. Let's go into it:

* cd my-module/

* It's a functioning Maven project which creates a working Vert.x module. So you can do all the normal Maven stuff, for example, try:

* mvn install

* You can use the idea and eclipse goals to create IDE projects from your Maven project, for example

* mvn eclipse:eclipse

* Import to Eclipse
* Open Eclipse IDE
* import->Maven->Existing Maven Projects
