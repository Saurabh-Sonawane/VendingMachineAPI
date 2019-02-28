# VendingMachineAPI

This is mocroservice build to provide following API for vending machine
1. Init Machine
2. Deposit Coin
3. Get changes for specific value in pence
4. Get coin stock
5. Get all deposits

# Requirement
1. Java 8
2. Gradle 3.4 or Above (If you don't have Gradle set up you can use Gradlew available in repository)

# How to build
    1. Using Gradle 3.4 or Above

        gradle clean build

    2. Using Gradlew

        ./gradlew clean build

    Above task will create jar file in build/lib folder.

# How to run
    java -jar build/libs/Machine-1.0.jar
    
    This will launch tomcat on port 8080, then you can use mentioned API
    
# API Details

You can use Postman Json available in repository

1. Init Machine

        Method  : POST
        Url     : http://localhost:8080/vending-api/admin/init-machine
        Body    : [{"coinName"		:	"ONE_PENNY", "initialCount"	    :	100},
                   {"coinName"		:	"TWO_PENCE", "initialCount"	    :	200},
                   {"coinName"		:	"FIVE_PENCE", "initialCount"    :	300},
	               {"coinName"		:	"TEN_PENCE", "initialCount"	    :	400},
                   {"coinName"		:	"TWENTY_PENCE", "initialCount"	:	500},
                   {"coinName"		:	"FIFTY_PENCE", "initialCount"	:	600},
                   {"coinName"		:	"ONE_POUND", "initialCount"	    :	700},
                   {"coinName"		:	"TWO_POUND", "initialCount"	    :	800}]

2. Deposit Coin
    
        Method  : POST
        Url     : http://localhost:8080/vending-api/deposit/{coinName}
        coinName : any one from list ["ONE_PENNY", "TWO_PENCE", "FIVE_PENCE", "TEN_PENCE", "TWENTY_PENCE", "FIFTY_PENCE", "ONE_POUND", "TWO_POUND"]
        
3. Get changes for specific value in pence

        Method  : GET
        Url     : http://localhost:8080/vending-api/return/change/{sumValueInPence}
        
4. Get coin stock

        Method  : GET
        Url     : http://localhost:8080/vending-api/admin/current-stock
        
5. Get all deposits

        Method  : GET
        Url     : http://localhost:8080/vending-api/admin/all-deposits
