# Jenkins
![alt text](image.png)
* It is a open source and java based tool.
* `CI` is the process of automating the build and testing the code every time when developer pushes the latest code to version control system.
* `CI` is a software development practice where developers regularly merge the code changes into a central repo,after which the automation builds and test are been running.
* Build types
    
    * `Daily Build` : When ever developers wants have there own sort of builds they will try to deploy them at that commit id and currently we are having that process manullay only
    * `Nighty Build` : Nightly builds are one that having build cutover taken place during a particular time frame and all the commmit ids that are been incorperated into that particular time frame will be developed into build. a  place Certain time build deployed in `QA`
    * `Release Channel build` : It is nothing but the functionality that are going into the production once they are been fixed they are been cutted over to a branch is called `release branch` aand this release branch will be deployed in environment called as `stage` environment and the testing team will be deploying.
* `/var/lib/jenkins` it jenkins home directory
* create a one server
* In jnekins runing as `user` not `root`
* If you want perform any sort of operations all these oprations will be running a user called `jenkins`
* Our user called as Jnekins user should be having a respective permissions. 

* Jobs created(configured) in `Master node`
* Jobs execution doing in `Slave (worker node)`
```bash
     gcloud compute instances create jenkinsmaster  --zone=us-west4-b --machine-type=e2-medium  --create-disk=auto-delete=yes,boot=yes,device-name=sonarqube,image=projects/centos-cloud/global/images/centos-7-v20230615,mode=rw,size=20
$ sudo -i
$ yum install wget 
$ sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
$ sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io-2023.key
$ yum search jdk
$ yum install java-11-openjdk.i686
$ yum install jenkins
$ systemctl start jenkins
$ systemctl status jenkins
ip_address:8080
$ cat /var/lib/jenkins/secrets/intialAdminPassword
```
* If we want to perform any task that task is been considerd as a `Job`.It is a sequence of actions.
  * `Freestyle jobs` : most common type jobs.Legacy applications
     * set > file_name.txt in job `build step`
     * `/var/lib/jenkins/workspace`
     * Data will be stored in jenkins workspace.
  * `Pipeline jobs` : work flow --> DSL --> Jenkins file
     * Jenkins Pipeline (or simply "Pipeline" with a capital "P") is a suite of plugins which supports implementing and integrating continuous delivery pipelines into Jenkins.
  * Githug org jobs
  * Folder jobs 
  * `Multi-branch pipeline jobs` : In your project each and every branch can able to build on its own 
  * When we create a job and that job is been build to run the applictaion 
# Manage Jnekins
  * `System` : Any configuration setting (global)
  * `Tools` : In jenkins machine we configure tools like `Git,java,maven`
  * `Plugins` : By deafult jenkins is configured plugins.
    * Jenkins plugins are a piece of software,onecwe install them they will enhance the jenkins functionality.
    * By def
    * `Updates`
    * `Available Plugins`
    * `Installed Plugins`
    * `Advanced Plugins`
  * `Nodes`
  * `Security`: Permission of users
  * `Credentials` : Storing the passwords 
* Webserver job:
  * freestyle: Build steps: Execute shell
  ```bash
    * sudo yum install httpd -y
    * sudo systemctl start httpd
    * suod systemctl enable httpd
  ```
    * Select SCM and configure `git repo`(in this git repo we can create one index.html file <h1>comming from gitgub</h1>)
    * Build steps : Execute shell
    ```bash
    sudo cp index.html /var/www/html
    ```
  * `Build Periodically`: It builds for every minute, even though there is no change in git 
  * `Poll SCM` : Check every minute and only build when there is change 
  * `WebHook` : Two options to configure
    * `github repo`: Go repo > setings > webhooks > Add webhook > playload url: jenkins_url/github-webhook > content type: applictaion/json >add webhook
    * `jenkins level`: go to job > configure >Build triggers: Github hook trigger 
---
# java spring application
  * https://github.com/spring-projects/spring-petclinic.git
  * create a new free style job in jenkins
    * Configure > scm:[git clone https://github.com/spring-projects/spring-petclinic.git] > buid steps: Execute shell[mvn package]
  ```bash
  export JAVA_HOME=/opt/jdk-17
  export PATH=$PATH:$JAVA_HOME/bin
  export PATH=$PATH:/opt/apache-maven-3.8.8/bin
  export M2_HOME=/opt/apache-maven-3.8.8
  mvn package -DskipTests
  ```
# Scenario
* Lets create a freestyle job to build spring petclininc application and this is based on java
  *  When developer commits the code,automatically a build should trigger.
  * By default when the developers build the package,all the unit test cases will be executed and will be stored in `target/surefire-reports` as `.xml` file.
    * NOw the devs need to verify the report.
  * I need provide them an option tp verify the reports without login to jenkinsmaster/slave
```bash
# Go to post-build actions : Publish Junit test results report
  * test report XMLS : target/surefire-reports/*.xml
```
* Before Post-build action
![alt text](image-1.png) 
* After Poste-build actions
![alt text](image-2.png)
  * Downloading the artifacts in local
```bash
# post-build actions: Archive the Artifcats
  * Files to archive: target/*.jar
```
![alt text](image-4.png)

* If `slave is not ready` issues occured then we need to restart the `jenkins`
  * Two ways of restart
    * `restart` : url/restart what are jobs are pending state that jobs are discard. It means forcefull restart.
    * `safe restart` : It means all jobs (pending,running) are excuted after that restart.
      * url/safeRestart --> every time is not recommended
      * manage jenkins > plugins 
      