This application demonstrates OLX-users

Setup:
Edit application.yml & change the database configuration as per your environment.

REST call details:

A) Logins a user:

API Gateway:
------------
http://localhost:9191/olx/user/authenticate			[POST]

Service Specific URI:
-----------
http://localhost:9001/olx/user/authenticate			[POST]


Request Body:
-------------
{"userName": "anand", "password": "anand123"} 
{"userName": "ranganath", "password": "ranganath123"}
{"userName": "divesh", "password": "divesh123"}


Response:
---------
token

Possible exceptions:
--------------------
BadCredentialsException

B) Logs out a user:

API Gateway:
------------
http://localhost:9191/olx/user/logout				[DELETE]

Service Specific URI:
-----------
http://localhost:9001/olx/user/logout 				[DELETE]

Header: auth-token

Response:
--------------------
true/false


C) Registers an user

API Gateway:
------------
http://localhost:9191/olx/user/ 					[POST]

Service Specific URI:
-----------
http://localhost:9001/olx/user/ 					[POST]

Request Body: 
----
{
"firstName": "Anand",
"lastName": "Kulkarni",
"userName": "anand",
"password": "anand123",
"email": "anand@gmail.com",
"phone": 12345
}

Response:
----
{"id": 1,
"firstName": "Anand",
"lastName": "Kulkarni",
"userName": "anand",
"password": "anand123",
"email": "anand@gmail.com",
"phone": 12345
}


D) Returns user information

API Gateway:
------------
http://localhost:9191/olx/user/ 					[GET]

Service Specific URI:
-----------
http://localhost:9001/olx/user/	 					[GET]

Header: auth-token

Response:
----
{"id": 1,
"firstName": "Anand",
"lastName": "Kulkarni",
"userName": "anand",
"password": "anand123",
"email": "anand@gmail.com",
"phone": 12345
    }

Possible exceptions:
--------------------
Invalid User

E) Validate User token

API Gateway:
------------
http://localhost:9191/olx/user/validate/token 		[GET]

Service Specific URI:
-----------
http://localhost:9001/olx/user/validate/token	 	[GET]

Header: Authorization

Response:
--------------------
true/false