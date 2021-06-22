# Deployment as docker image on linux

All the sh files need executable permissions in order to be executed as shell scripts. This service will be deployed using the [Dockerfile](../../Dockerfile)  in project root directory

> Use `$ chmod 777 <filename>` to make a file executable  

_User would also need to have access to git repository to be able to download code._

_User would also need `sudo` permission in order to execute the scripts and docker commands thereafter._  

## Step 1 : Setup initial configuration 

Use [config.sh](config.sh) to setup initial configuration including git repository. THis file includes -

- Branch Name  
    > `branch`=main
    
- Directory Name/Path where the code would be downloaded. This would be different in case of a multi module project. 
    > `dir_name`=camunda-masala-noodles

- Name of the project as mentioned in `name` tag in pom.xml                                                                                                                        
    >  `prj_name`=camunda-masala-noodles

- Port where the API needs to be started. This is usually configured in [application.yaml](../../src/main/resources/application.yaml) 
    >  `prj_port`=10101

- GIT project url for accessing the code via SSH Key. You can also use HTTPS which would ask you to enter credentials. 
    >  `repo_url`=https://github.com/sourabhparsekar/camunda-masala-noodles.git
   
- Root path of the project where build command has to be executed. This would be different in case of a multi module project      
    >  `build_path`=$dir_name

- Maven build command which would be used to create a JAR file
    >  `build_cmd`="mvnw package -DskipTests"

- The name of the JAR file created by executing build command which is created in `.\target\` folder 
    >  `jar_name`=camunda-masala-noodles-1.0.0-SNAPSHOT.jar
 
## Step 2 : Execute shell scripts to build and deploy JAR as container

- Execute [1-build.sh](1-build.sh) to build the jar file which is available in `./target`  
    > `sh 1-build.sh`

- Execute [2-deploy.sh](2-deploy.sh) to deploy the jar file as docker container which is available in `./target`  
    > `sh 2-deploy.sh`
