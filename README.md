# Gestor-de-Datos-TechInt-Version-Web

Local Deployment Check-List:
Apache 0.0.0.0:80 in httpd.config.
Change my.ini bind-address to 0.0.0.0 and create MySql User.
Firewall if necessary, ports 80 and 3306.
Change spring.datasource.url=jdbc:mysql://[Machine_1_IP]:3306/holdingDataBase.
Access through http://[Machine_1_IP]:80 for direct Apache-hosted site.
Or, access through http://[Machine_2_IP]:8080 for Springboot-Hosted site.

Check Public IP for AWS Inbound Connection Permission at http://checkip.amazonaws.com/