This application demonstrates OLX-Masterdata

Setup:
Edit application.yml & change the database configuration as per your environment.

REST call details:

A) Returns all advertisement categories:

API Gateway:
------------
http://localhost:9191/olx/masterdata/advertise/category 			[GET]

Service Specific URI:
-----------
http://localhost:9002/olx/masterdata/advertise/category				[GET]


B) Returns all possible advertise status:

API Gateway:
------------
http://localhost:9191/olx/masterdata/advertise/status 				[GET]

Service Specific URI:
-----------
http://localhost:9002/olx/masterdata/advertise/status				[GET]


