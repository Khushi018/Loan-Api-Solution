## Overview

Spring Boot application that integrates with an external loan API, stores data in MySQL, and exposes a custom REST endpoint.

## Features
* Clean layered architecture(config, entity, exception, dtos etc)
* External API integration using RestTemplate
* Data persistence using JPA & MySQL
* Transformed response as per requirement
* Exception handling for scenarios like invalid account

## API
```
GET /loan/{loanAccountNumber}
```

## Sample Response

```json
{
  "loanAccountNumber": "1",
  "dueDate": "2024-04-01",
  "emiAmount": 10000
}
```

## Tech Stack

Spring Boot, JPA, MySQL, RestTemplate
