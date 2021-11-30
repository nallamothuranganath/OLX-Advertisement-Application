This application demonstrates OLX-Advertises

Setup:
Edit application.yml & change the database configuration as per your environment.

REST call details:

A) Posts new advertise:

API Gateway:
------------
http://localhost:9191/olx/advertise/ 						[POST]

Service Specific URI:
-----------
http://localhost:9000/olx/advertise/						[POST]

Header: auth-token

Request Body:

JSON:
-----
{
"title": "sofa sale", 
"price": 54000, 
"categoryId": 1, 
"description": "3 Seater Sofa"
}

<advertises>
<title>Chair Sale</title>
<price>1000</price>
<categoryId>1</categoryId>
<description>Study Chair for sale</description>
</advertises>

XML:
----
<advertises>
<title>Lenovo Laptop</title>
<price>20000</price>
<categoryId>5</categoryId>
<description>Lenovo Laptop for sale</description>
</advertises>

Possible exceptions:
--------------------
Invalid token.
Invalid categoryid. 


B) Updates existing advertise:

API Gateway:
------------
http://localhost:9191/olx/advertise/1						[PUT]

Service Specific URI:
-----------
http://localhost:9000/olx/advertise/1						[PUT]

Header: auth-token

Request Body:
JSON:
-----
{
"title": "Sofa available for Sale", 
"price": 30000, 
"categoryId": 3, 
"statusId": 2, 
"description": "Sofa 5 years old available for Sale in Pune"
}

XML:
----
<advertises>
<title>Lenovo Laptop Old</title>
<price>20000</price>
<categoryId>5</categoryId>
<statusId>2</statusId>
<description>OldLenovo Laptop for sale</description>
</advertises>

Possible exceptions:
--------------------
Invalid advertise id or advertise not found.
Invalid categoryid.
Invalid statusid.
Invalid token.


C) Reads all advertisements posted by logged in user

API Gateway:
------------
http://localhost:9191/olx/advertise/user/advertise				[GET]

Service Specific URI:
-----------
http://localhost:9000/olx/advertise/user/advertise				[GET]

Header: auth-token

Possible exceptions:
--------------------
Invalid token.
Invalid advertise id or no advertises found.

D) Reads specific advertisement posed by logged in user


API Gateway:
------------
http://localhost:9191/olx/advertise/user/advertise/1			[GET]

Service Specific URI:
-----------
http://localhost:9000/olx/advertise/user/advertise/1	 		[GET]

Header: auth-token

Possible exceptions:
--------------------
Invlaid token.
Invalid advertise id or Advertise not found.
Advertise is not assosiated with the logged in User.


E) Deletes specific advertisement posted by logged in user

API Gateway:
------------
http://localhost:9191/olx/advertise/user/advertise/1			[DELETE]

Service Specific URI:
-----------
http://localhost:9000/olx/advertise/user/advertise/1   			[DELETE]	

Header: auth-token

Possible exceptions:
--------------------
Invalid token.
Advertise not found.
Advertise is not assosiated with the logged in User.

F) Search advertisements based upon given filter criteria

API Gateway:
------------
http://localhost:9191/olx/advertise/search/filtercriteria		[GET]

Service Specific URI:
-----------
http://localhost:9000/olx/advertise/search/filtercriteria    	[GET]
 
Request paramaters:
	1)
	Name: category
	Type: Integer
	
	2)
	Name: postedBy
	Type: String
	
	3)
	Name: dateCondition
	Type: String
	
	possible URI combinations:
	dateCondition=equals&onDate=2021-10-17
	dateCondition=greaterthan&fromDate=2021-11-08
	dateCondition=lessthan&fromDate=2021-11-08
	dateCondition=between&fromDate=2021-10-17&toDate=2021-11-08
	
	Note: when there is datecondition in the URI it should also send onDate or fromdate or todate
	
	4)
	Name: onDate
	Type: LocalDate
	
	5)
	Name: fromDate
	Type: LocalDate
	
	6)
	Name: toDate
	Type: LocalDate
	
	7)
	Name: sortBy
	Type: String
	
	8)
	Name: startIndex
	Type: Integer
	
	9)
	Name: records
	Type: Integer
	
Sample URI's:
-------------
http://localhost:9191/olx/advertise/search/filtercriteria?category=3
http://localhost:9191/olx/advertise/search/filtercriteria?category=5&postedBy=ANAND
http://localhost:9191/olx/advertise/search/filtercriteria?category=5&postedBy=RANGANATH
http://localhost:9191/olx/advertise/search/filtercriteria?postedBy=RANGANATH
http://localhost:9191/olx/advertise/search/filtercriteria?dateCondition=equals&onDate=2021-10-17
http://localhost:9191/olx/advertise/search/filtercriteria?dateCondition=greaterthan&fromDate=2021-11-11
http://localhost:9191/olx/advertise/search/filtercriteria?dateCondition=lessthan&fromDate=2021-10-31
http://localhost:9191/olx/advertise/search/filtercriteria?dateCondition=between&fromDate=2021-10-17&toDate=2021-11-08
http://localhost:9191/olx/advertise/search/filtercriteria?fromDate=2021-10-17&toDate=2021-11-08	
http://localhost:9191/olx/advertise/search/filtercriteria?onDate=2021-10-17
http://localhost:9191/olx/advertise/search/filtercriteria?category=3&onDate=2021-11-08
	
sory by and paging
------------
http://localhost:9191/olx/advertise/search/filtercriteria?toDate=2021-11-08&sortBy=username
http://localhost:9191/olx/advertise/search/filtercriteria?toDate=2021-11-08&sortBy=price
http://localhost:9191/olx/advertise/search/filtercriteria?toDate=2021-11-08&startIndex=1&records=2	

Possible exceptions:
--------------------
Filter Criteria URI should send values for any of the fields in (category, postedBy, dateCondition, onDate, fromDate, toDate, sortBy, startIndex).
Invalid date  range.
No advertises found.
dateCondition should send onDate when condition is equals
dateCondition should send fromDate when condition is greaterthan
dateCondition should send fromDate when condition is lessthan
dateCondition should send fromDate and toDate when condition is between.

  	
	
G) Matches advertisements using the provided 'searchText' within all fields of an advertise

API Gateway:
------------
http://localhost:9191/olx/advertise/search/						[GET]

Service Specific URI:
-----------
http://localhost:9000/olx/advertise/search/						[GET]	

Sample URI:
-----------
http://localhost:9191/olx/advertise/search/sofa

Possible exceptions:
--------------------
Invalid value or value not found in the data so show relevant message to the client.

H) Return advertise details

API Gateway:
------------
http://localhost:9191/olx/advertise/1							[GET]

Service Specific URI:
-----------
http://localhost:9000/olx/advertise/1           				[GET]

Header: auth-token

Possible exceptions:
--------------------
Invalid token
Advertise not found.