Reactive Kafka with Spring Webflux
=============

- Versions
  - Java : 11
  - Spring boot : 2.6.6
  - Reactive Kafka : 1.3.11 
  - Spring Webflux : 2.6.6

How send a message?
-------------


The default Path is :
```
curl --location --request POST 'localhost:18080/produce' \
--header 'Content-Type: application/json' 
```

### There are two types of messages you can send (using request body) :
1. URI Message type
```
{
    "type" : "uri",
    "topic" : "my-topic",
    "uri" : "http://com.github.deogicorgi/users/1",
    "requestedAt" : "2022-04-01T13:00:00"
}
```

2. Body Message type
```
{
    "type": "message",
    "topic": "my-topic",
    "message": "{\"message\":\"My Message\"}",
    "requestedAt": "2022-04-01T13:00:00"
}
```

### Depending on the message you send, you will receive the following response :


1. Success
```
{
    "status": true,
    "message": "{\"message\":\"myMessage\"}",
    "errorMessage": null,
    "requestedAt": "2022-04-01T13:00:00",
    "producedAt": "2022-04-01T22:49:25.695124"
}
```

2. Failure
```
{
    "status": false,
    "message": "{\"message\":\"myMessage\"}",
    "errorMessage": "Invalid value null for configuration bootstrap.servers: entry must be non null",
    "requestedAt": "2022-04-01T13:00:00",
    "producedAt": "2022-04-01T22:49:25.695124"
}
```
