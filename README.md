# FeeService

Fee Service for managing fee payment, get details and view the receipt.

Prerequisite: common-services is a dependency, and should be installed first before building fee-rak service

1. Navigate to fee-rak directory and run **mvn clean install**
2. Run the application in the terminal by **mvn spring-boot:run**
3. To view the fee-receipt, open the browser at **http://localhost:8081/fee-service/receipt/**, enter the studentId to view the details

Collection published at https://documenter.getpostman.com/view/3286793/2sAYXBGzKi
1. executePayment: localhost:8081/fee-service/fee-payment/executePayment
2. fetch recent payment details: localhost:8081/fee-service/fee-payment/details
3. view receipt, open http://localhost:8081/fee-service/receipt/ in the browser and enter details to get receipt
4. To view database table, visit http://localhost:8081/fee-service/h2-console
