####Simple application with Spring boot using a configurable embedded/external DB datasource and RESTful calls that demonstrate how Guava Eventbus can be used to intercept invocations.


Here
1. A POST call to
localhost:8080/customer/

with json data like 
{"id":"501","owningCustomer":{"id":"1001","name":"John Lennon"}}

will result in a adding a bank account to a customer.


2. A GET call to
localhost:8080/customer/1001

will result in getting customer of id=101 and will result in producing
{
    "id": "1001",
    "name": "George Harrison1-JPA",
    "bankAccounts": "[501, ]"
}

A log message is printed out when a new account is added using the POST request and that event is logged using the EventBus api.
