# Cook Veg Masala Noodles using Camunda Workflow

This standalone process application is an example using [Camunda Workflow](https://camunda.com/) & [Springboot](https://spring.io/projects/spring-boot). As for the use case, we will try to _cook veg masala instant noodles_ else order from our favourite restaurant if we fail. 

_**Note:** You may see that some cooking steps may not be intuitive, but they are added to make use of features of camunda._ 

Out of the [BMPN 2.0 Implementations](https://docs.camunda.org/manual/7.15/reference/bpmn20/), we have used below in our use-case to reach a solution:
![Camunda Palette](./documentation/images/camunda-palette.png)

- `Service Tasks`: A Service Task is used to invoke services. We have used `JavaDelegate` interface to delegate process.   
- `Gateway`: Gateways control token flow in a process. They allow modeling decisions based on data and events.  
- `Event Based Gateway`: The event-based Gateway allows you to make a decision based on events. Each outgoing sequence flow of the gateway needs to be connected to an intermediate catching event.
- `Message Intermediate Catch Event`: Message events are events which reference a named message. A message has a name and a payload.
- `Timer Intermediate Catch Event`: Timer events are events which are triggered by a defined timer. They can be used as start event, intermediate event or boundary event.


### Veg Masala Noodles Cooking Flow: 
![Noodles Cooking Workflow Process](./src/main/resources/cook_noodles_process.png)

- We start by checking that we have minimum ingredients, where instant noodles, water, pan and spatula are mandatory. Vegetables, Cheese etc are optional to make instant noodles.
- If the minimum ingredients are available then we put everything in pan, give it a stir, close it with a lid and keep it over heat. (Use your preference there).
- If you smell it nice then open the lid and check if it's cooked.  
- If you get busy with something else and you forget to check it, then after 15 minutes cooking timer will expire, and you have to order online as noodles have burnt.
- Luckily, if you see that it's cooked well, then you can eat it. Else.. Sorry, but your efforts are wasted, and you need to order online. 
- With that we reach end of our non-intuitive flow to cook instant noodles. By the way, that's how I cook instant noodles, and they turn out good most of the time. 

## Code Configuration

### Camunda Springboot Initializer

Camunda Springboot project can be created by [Camunda Platform Initializer](https://start.camunda.com/) which contains Spring Boot framework. This makes Spring ready to work inside your camunda workflow application. 

These starters pre-configure the Camunda process engine, REST API and Web applications, so they can easily be used in the standalone process application.

For detailed notes and guides on setup, refer [Camunda Docs-Springboot](https://docs.camunda.org/get-started/spring-boot/)

### Default Ports

On systems running the `API`, it is recommended to use the below port for starting an instance of the API.

> `10101` – default api listening port

### API Build & deploy from GIT repo
Below section would cover high level tasks required to configure and deploy api jar using Apache Maven

1. Clone repository on local system. By default, jars would be taken from Maven Central Repository.
2. Update properties in logback-spring.xml, application.yaml if applicable
3. Build with maven task `mvnw clean install`
4. Copy .jar file from /target/ to your `deployment-directory`
5. Environment specific `application.yaml` & `logback-spring.xml` are to be modified and placed in deployment-directory along with `.jar` if applicable
6. Start execution with `java -jar camunda-masala-noodles-<version>.jar`
7. Logs are generated in deployment-directory/logs folder with file name 'camunda-masala-noodles-logger.log' or as mentioned in logback-spring.xml

## Appendix - Deployment as Docker container

To deploy API as Docker Container refer [Docker-Image-Deployment](./documentation/deployment/Readme.md)
