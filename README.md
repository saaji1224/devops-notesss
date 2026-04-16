# Installation steps
* Install tomcat
* Install java
```bash
# To start/stop tomcat any where
ln -s /opt/tomcat9/bin/startup.sh /usr/bin/startTomcat
ln -s /opt/tomcat9/bin/shutdown.sh /usr/bin/stopTomcat
```
* On the home

/opt/tomcat9/webapps/manager/META-INF # in this folder we need to comment the
<!--
  <Valve className="org.apache.catalina.valves.RemoteCIDRValve"
          allow="127.0.0.0/8,::1/128" />
  -->
```
