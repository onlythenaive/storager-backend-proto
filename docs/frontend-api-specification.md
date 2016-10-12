# Frontend API Specification

## Routes

### Index Page
All the requests which do not start with any: `/data/`, `/integration/`, `/security/` or `/static/` return the index HTML page in response. These requests are allowed to be unauthenticated.

### Data 
Requests starting with `/data/` are designed to return frontend-required data in JSON format. These requests are **not** allowed to be unauthenticated.

### Integration 
Requests starting with `/integration/` are designed to be used by external systems, therefore they must be ignored by the frontend application.

### Security 
Requests starting with `/security/` are designed to invoke special client actions:
* Logon: `/security/logon` (unauthenticated requests are allowed);
* Logout: `/security/logout`.

### Static Assets
Requests starting with `/static/` are designed to return static assets, f.e:
* JavaScript code;
* HTML templates;
* CSS resources;
* Binary images.

These requests are allowed to be unauthenticated.

### Other Routes
* Application icon: `/favicon.ico`.

## Security
Before being performed, client requests on business features routes are validated against existing roles to have sufficient authorization.

There are 2 normal roles:
* USER (authorized to access application in read-only mode);
* ADMIN (authorized to add/update/remove data related to providers, indicators and territories).

There are also a special role:
* GUEST (anonymous access).

Client requests need a special header `X-Auth-Token` to be set and contain a valid security token. These tokens can be obtained through the standard logon feature.

When not containg any valid security token, requests are treated as anonymous by default.

## Features

### Periods

#### Retrieve all existing time periods:
```
GET /data/periods
```

Takes no parameters.

Accepts nothing.

Produces:
```
{
  "code": string,
  "title": string
}[]
```

Required roles: **USER** | **ADMIN**.


### Territories

#### Retrieve all root-level territories:
```
GET /data/territories
```

Takes no parameters.

Accepts nothing.

Produces:
```
{
  "code": string,
  "ascendantCode": string (optional),
  "title": string,
  "description": string (optional),
  "path": string[] (optional),
  "terminal": boolean (optional)
}[]
```

Required roles: **USER** | **ADMIN**.

#### Retrieve a specific territory by its code:
```
GET /data/territories/:code
```

Parameters:
* `code`: territory code.

Accepts nothing.

Produces:
```
{
  "code": string,
  "ascendantCode": string (optional),
  "title": string,
  "description": string (optional),
  "path": string[] (optional),
  "terminal": boolean (optional)
}
```

Required roles: **USER** | **ADMIN**.

#### Retrieve all descendants of a specific territory by its code:
```
GET /data/territories/:code/descendants
```

Parameters:
* `code`: territory code.

Accepts nothing.

Produces:
```
{
  "code": string,
  "ascendantCode": string (optional),
  "title": string,
  "description": string (optional),
  "path": string[] (optional),
  "terminal": boolean (optional)
}[]
```

Required roles: **USER** | **ADMIN**.

#### Add a new territory:
```
POST /data/territories
```

Takes no parameters.

Accepts:
```
{
  "code": string,
  "ascendantCode": string (optional),
  "title": string,
  "description": string (optional)
}
```

Produces:
```
{
  "code": string,
  "ascendantCode": string (optional),
  "title": string,
  "description": string (optional),
  "path": string[] (optional),
  "terminal": boolean (optional)
}
```

Required roles: **ADMIN**.

#### Update an existing territory by its code:
```
PUT /data/territories/:code
```

Parameters:
* `code`: territory code.

Accepts:
```
{
  "code": string,
  "ascendantCode": string (optional),
  "title": string,
  "description": string (optional)
}
```

Produces:
```
{
  "code": string,
  "ascendantCode": string (optional),
  "title": string,
  "description": string (optional),
  "path": string[] (optional),
  "terminal": boolean (optional)
}
```

Required roles: **ADMIN**.

#### Remove an existing territory by its code:
```
DELETE /data/territories/:code
```

Parameters:
* `code`: territory code.

Accepts nothing.

Produces nothing.

Required roles: **ADMIN**.


## Special Notes
* User management is not designed (and is not planned).
