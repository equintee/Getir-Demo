<h1>Swagger URL</h1>
http://localhost:8080/swagger-ui/index.html

<h1>Database URL</h1>
I used H2 database so there is no need to setup enviroment before running.
http://localhost:8080/h2-console/login.jsp
username: sa
password:
You can just click login default crediantials are used.

<h1>Implementation</h1>
I left comments in the code for questionable implementations.

<h1>JaCoCo report</h1>
Wrote unit tests for services. Jacoco thinks that one lambda function is not covered. However im pretty sure i cover it :D

![image](https://github.com/user-attachments/assets/80f99463-4b47-45c8-a143-dbbc4c6dcb0c)

<h1>SonarQube result</h1>
Security issue caused by new Random() instance. This is demo so i just ignored it.
Reliablity issue comes from exception handling. I would use spesific exceptions to handle it if this was production.

![image](https://github.com/user-attachments/assets/067fd2dd-ff92-4bf3-bbd7-44e2d8ba6a4a)

