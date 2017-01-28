# Berlin Clock Service

The Set Theory Clock, also known as the Berlin Clock, makes use of the principle of set theory to depict the time. The time of day is displayed in a 24-hour format and can be determined by simply adding and multiplying the glowing lights.

The first, uppermost row consists of 4 red lights, whereby each of these lights stands for 5 full hours. The 4 red lights in the second row display one full hour apiece. For example, if the first 2 lights in the uppermost row and all 4 lights in the second row are lit up, that represents 1400 hours, or 2 p.m. (2 × 5 + 4 hours). The third row is composed of 11 lights: 3 red and 8 yellow. Each light in this row stands for 5 elapsed minutes. The 3 red lights have been assigned to mark the quarters of an hour and are intended to make reading the clock easier. Last of all, the yellow row at the very bottom displays units of single minutes. Extract from http://www.3quarks.com/en/BerlinClock/.


## Version
**1.0**


## Premises

In order to implement the BerlinClockService, it was assumed:

  - The BerlinClockService always recieve three parameter (int hour, int minute and int second);
  
  - New Clients should be created just calling BerlinClockService class and passing the parameter.
  
  - New Clients should call service via REST

  
## Service

The service is available throughout the http://<server>:<port>/berlinclock relative URL.

	http://localhost:8080/berlinclock?hour=<hour>&minute=<minute>&second=<second>

##### ACCESS VIA HTTP GET

Send a GET request will result in a response with a new multiline String that contains informations about Berlin Clock.

**For a valid response (HTTP 200 Status)**
	
	curl "http://localhost:8080/berlinclock?hour=9&minute=42&second=12"
	
	O
	ROOO
	RRRR
	YYRYYRYYOOO
	YYOO

***For a invalid response (HTTP 400 Status)***

	curl "http://localhost:8080/berlinclock?hour=99&minute=0&second=0"

	{"timestamp":1474856216927,"status":400,"error":"Bad Request","exception":"com.gleisoncs.berlinclock.exception.InvalidTimeException","message":"Param hour is incorrect: 99. Correct is between (0,23)","path":"/berlinclock"}


##### ACCESS DIRECT

***Acessing direct to the class***

	BerlinClockService berlinClockService = new BerlinClockService();
	berlinClockService.getTime(0,0,0);
	
***Acessing direct to the class via Dependency Injection***

	@Autowired
	BerlinClockService berlinClockService;
	
	public static void main(String args[]){
		berlinClockService.getTime(0,0,0);
	}


## Tech

It was used the following technologies:

* Java 1.8
* Spring Boot 1.4.1
* Apache Maven 3.3.9
* JUnit


### Building and packaging

You will need Maven to build this project. You can build the package using the following command on the project root directory:

    mvn clean package


### Executing

There are two ways of running this application.

The first one is for running it through Maven:

    mvn spring-boot:run

The second one is for running the jar package (located in the target directory after a packging) directly:

    java -jar berlinclock-1.0.jar -Dserver.port=8090 (optional param)


### Next Steps / TODOs

In order to finish this implementation, it's necessary to follow this steps:

- Implement Security via REST


### License
MIT