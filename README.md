# Voluntari.ze
## About
Voluntari.ze is an academic project, supposed to be a social network for non-profit organizations to interact with common people who are volunteers and want to donate and help their causes somehow.

## Parts of this project
* The `Voluntari.zeAPI` directory contains the Web API created for the application, which.
* The `Volutnari.ze_Web` directory contains the html code for the front-end of  the aplication.
* The `Documentação` directory contains the slides and word document (containing class diagrams and others) used on a seminar to presentate our project to the res of the university, which was assigned to [mariacandido](https://github.com/maricandido).

## Technologies
### Front-end:
* `Angular` Framework
* `Primeng` Lib for styling
* `NgRx` Lib for state managing
* Resposibles: [moreiraisa](https://github.com/moreirasisa), [ViniciusChanes](https://github.com/ViniciusChanes)

### Back-end
* `Spring Boot` Framework
* `H2 Database` for in-emory database
* Responsibles: [A0KAM1](https://github.com/A0KAM1), [pedrolucasgp](https://github.com/pedrolucasgp)

## How-To-Run
### Requirements
* Java 17 SDK. See [Oracle.com](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
* Angular CLI. See [Angular](https://angular.dev/installation) `npm install -g @angular/cli`
* IntelliJ IDE to run the project. See [JetBrains](https://www.jetbrains.com/help/idea/installation-guide.html).
* Node.js. See [Node.Js](https://nodejs.org/en/download/package-manager).
* Git. See [Git](https://git-scm.com/downloads).
### Steps
* Install the required tools.
* Clone the project with `git clone https://github.com/A0KAM1/Voluntari.ze.git`.
* Configure the IDE with Java 17 SDK and Lombok.
* Open the project API folder on IntelliJ.
* Go Voluntari.zeAPI>social.network>src>main>>java and open Application file. run the project bu running Current File.
* The API project is up on your [http://localhost:8080](http://localhost:8080)
* Run `npm i` and `ng s` inside the Web folder to install the necessary packages and start the web project.
* The web project is up on your [http://localhost:4200](http://localhost:4200)

_Obs_
You can access to read and modify the h2 database on [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
