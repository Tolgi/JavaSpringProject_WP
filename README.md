# JavaSpringProject_WP
Java Spring Project 
Викторија Стојанова индекс:163023 
семинарска работа по предметот Веб Програмирање на ФИНКИ

Инструкции за стартување на проектот:
1. Pull code from GIT
2. Create empty database (barebone installation OR Docker installation of Postgresql)
commands:
	- if the installation is on Docker simply execute 'docker-compose up -d' with the docker-compose template provided in the repository
  	- execute the following commands:
      - sudo docker exec -ti "name_of_running_postgre_container" /bin/bash
      - pg_restore -h localhost -U wp -d hospital -v /path/to/project/hospital_backup.bak

		
	- if the installation is on barebone machine(host), execute the following commands:
		- createdb hospital;
		- create user wp;
		- grand all privileges on "hospital" to wp;
		- psql hospital < /path/to/project/hospital_backup.bak

3. Build project 
4. Start project
5. Frontend:  path_to_project/JavaSpringProject_WP/frontend/hospital-react-project
              - npm install
	      - npm start
