## customer-api

Simple rest server using spring boot

Table `customers`

Columns:

- cust_id: AI, INT (PK)
- cust_name: VARCHAR(100)
- cust_address: VARCHAR(200)

## Application structure

This service is built on top of DDD paradigm

Split in to three bounded countext

### application

It is the place acts as the entrypoints

It has:

- request
- response
- rest


### domain

Where our domain lies

It has:

- entity
- repository
- service

### infrastructure

Outcontext for our domain

It interacts with external party e.g. database

## Database migration

I use liquibase as the database migration tools

changelog is saved in `src/resources/db/changelog`

Migration will run every time our application is started.

## Deployment

It is a local application. To make life easier, I containerize using docker and docker-compose to package 
my application and the database itself (postgre)

## How to run

Just run `docker compose up -d --build`

It will spawns web-server containers on port `8080` and postgresql database on port `5432`

## Endpoints

[postman-collection](misc/fpt-test.postman_collection.json)

### Save Customers

Create customers. 

Customer should be unique

- name & address

Request body

List of customer payload

```json
[
    {
        "name": <string, max 100>,
        "address": <string, max 200>
    },
]
```

Response

List of created customers with their Id

```json
{
    "customers": [
        {
            "id": <integer>,
            "name": <string>,
            "address": <string>
        },
    ]
}
```

Response code

- 500 internal, if error happens. e.g uniquness violation constraints

- 400, if validation failed

- 200 If ok

Example curl

```
curl --location --request POST 'http://localhost:8080/v1/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "customers": [
        {
            "name": "Kenny test",
            "address": "test2"
        },
        {
            "name": "jenny",
            "address": "test3"
        }
    ]
}'
```

### Get Customer ById


Get a customer

Path param:

- id (mandatory)

Example Curl

```
curl --location --request GET 'http://localhost:8080/v1/customers/{id}'
```

Response

```json
{
    "customer": {
        "id": 2,
        "name": "jenny",
        "address": "test3"
    }
}
```

Status code

- 200 OK
- 404 if no item found
- 500 otherwise

### Delete Customer ById

Delete a customer by Id

Currently we only support single `id`

**It is a hard delete.**

```
curl --location --request DELETE 'http://localhost:8080/v1/customers/{id}'
```

Status codes

- 200 OK
- 404 if deleted item not found
- 500 otherwise

### Partial Update Customers

Patch customers

Case when you need to update part of the attributes.

**If you pass only the Id, it will also successfully update
but doesn't mutate anything**

Request body

```json
[
        {
            "id": <Integer, mandatory>,
            "name": <String, optional>
        }
]
```

Example curl

```
curl --location --request PATCH 'http://localhost:8080/v1/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "customers": [
        {
            "id": 2,
            "name": "John doe updated2"
        },
        {
            "id": 3,
            "address": "alamat john doe 3 di update"
        }
    ]
}'
```

Response Body

```json
{
    "customers": [
        {
            "id": 1,
            "name": "kenny updated",
            "address": "test2"
        }
    ]
}
```

Status codes

- 200 OK
- 400 Validation failed
- 500 other errors


### List customers

```
{query_param}

- page: default 1, int
- pageSize: default 10, int
- name: Optional, string
- address: Optional, string
- sortDirection: Optional, default ASC, enum (ASC|DESC
- sortBy: Optional, default depends on domain, for customer it will be sorted based on custId
```

Example curl

```
curl --location --request GET 'http://localhost:8080/v1/customers?{query_param}'
```

Response body

```json
{
    "customers": [ // data
        {
            "id": 2,
            "name": "jenny",
            "address": "test3"
        }
    ],
    "numberOfItems": 1, // pagination info
    "numberOfPages": 1
}
```

Status codes

- 200 OK
- 500 other errors

## To do

Software engineering is a process. This application has lots of todos

- [ ] security
- [ ] logging
- [ ] docs generator