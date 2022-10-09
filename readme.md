## customer-api

Simple rest server using spring boot

Table `customers`

Columns:

- cust_id: AI, INT (PK)
- cust_name: VARCHAR(100)
- cust_address: VARCHAR(200)

## How to run

Just run `docker compose up -d --build`

It will spawns web-server containers on port `8080` and postgresql database on port `5432`

## Endpoints

### Save Customers

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

```
curl --location --request GET 'http://localhost:8080/v1/customers/{id}'
```

### Delete Customer ById

```
curl --location --request DELETE 'http://localhost:8080/v1/customers/{id}'
```

### Partial Update Customers

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

### List customers

```
curl --location --request GET 'http://localhost:8080/v1/customers?{query_param}'
```

```
{query_param}

- page: default 1, int
- pageSize: default 10, int
- name: Optional, string
- address: Optional, string
- sortDirection: Optional, default ASC, enum (ASC|DESC
- sortBy: Optional, default depends on domain, for customer it will be sorted based on custId
```
