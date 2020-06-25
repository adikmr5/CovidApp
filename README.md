# Covid-App
CovidApp-by eYRC#1804
-An attempt to connect the frontline warrior to people at root level

PROBLEM ADDRESSED:
Several studies show that India’s testing strategy lacks in many ways which may increase the number of COVID-19 patients enormously. The main problem that we have addressed in our idea is that India is not doing enough testing that could help it trace and treat infected patients and contain the spread of the disease. It also involves finding a solution of the human tendency not to reveal their symptoms which is a critical issue. In this solution, we have tried to fight against the disease at a community level involving the local police, the private doctors and the people.


DESCRIPTION:
Our solution to the above problem is a native mobile application which mainly uses Firebase for backend services.
The application consists of two broad sections that is people and the police of a particular area.
Our aim is to make full use of the people in the medical field to ensure more and more testing. Thus, in our application we identify the available private hospitals for checking common symptoms of covid-19 who in turn will suggest them for immediate testing or a period of self-quarantine. This has been done by using Google Map API services.
People who experience symptoms can simply login to the application, connect to a private hospital providing their basic information and we connect them to the nearest doctors.
The app uses Firebase Realtime Database to store and sync this information which can later be analyzed.
The app also includes a separate section where people of a community connect to the police of the area to inform them about a suspected case or any kind of problem (e.g. Crowding) the community faces. To eliminate false information, we use Firebase Authentication feature for the informants.
To encourage the participations of more people in medical field to participate in this fight, we also have a local chat messaging feature through which people who login can talk to specified consultants for information and procced for further decisions of a checkup.


HARDWARE AND SOFTWARE REQUIREMENT:
Since it is an application for android, there is no such hardware requirement. For user, they need an android based mobile device (version 4.0 and above). And for government, they need an infrastructure to felicitate the flow to data.

PROCESS FLOW:
Currently, on our main screen there is an option for login as police or login as citizen. This is only for demonstration purpose. This could be two app, one only for citizens where they can register their complains and chat a medical representative and the second app will be for police where they could login through their login ID and see the complains of their area.

"""""""""Police Login can only be logged in by using some authenticated police id and name
for now we have used 10 dummy police id to just show the process of authentication...
To Login and see the complains in this prototype you can use--->
          (police name->arun kumar)
          (police id-> 800123)
          (police area under surveillance->ashok nagar)"""""""""
         
"""""""""for login into citizen you can register a new user or u can use our dummy user who is
(email-id->user@gmail.com)  
(password->user123)""""""""
          
"Process Flow diagram is present int the readme.pdf section please have a look there"
