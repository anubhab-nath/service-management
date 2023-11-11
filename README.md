# Service Management System

## Overview

- A web-based application that allows users to create, retrieve, update and delete 'Services' on which feedback/reviews
can be provided.
- Based on the access levels, users are allowed to perform above operations.

## User Access Levels
- Admin
- General User

## Features
- **Create Service Providers**: Admins can create new Service Provider (SP), where they have to provide below details:
    - Email (unique)
    - Name of SP
    - Address
    - Phone number
    - Category (limited for now to: DINE_IN, IN_STORE_SHOPPING)
- **Retrieve Service Provider**: Any User can retrieve any SP if they can provide its unique spId.
- **Retrieve All Service Providers**: Any User can retrieve all the SPs which are present in the database.
- **Update Service Provider**: Admins can update an already present Service Provider if they have its unique spId.
- **Delete Service Provider**: Admins can delete a Service Provider using its spId.