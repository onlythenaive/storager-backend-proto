# Integration API: Providing

## Security

### Authentication
All the integration requests made by providing systems must be valid SOAP messages and contain a security token among other input parameters required by a specific operation.

### Authorization
*TODO: add this section*


## Operations

### Indicators

#### getIndicatorPage
Gets a page of implicitly granted indicators with filtering by code, implicit ascendant code and title.

Request:
```
{
  "codePattern": String (a pattern of indicator code, optional),
  "implicitAscendantCode": String (exact code of an implicit ascendant, optional),
  "titlePattern": String (a pattern of indicator title, optional),
  "providerSecurityToken": String
}
```

Response:
```
{
  "items": [
    {
      "code": String,
      "ascendantCode": String (optional),
      "title": String,
      "description": String (optional),
      "terminal": Boolean (optional)
    }
  ],
  "page": Integer,
  "total": Integer
}
```

#### getIndicator
Get a specific implicitly granted indicator by its code.

Request:
```
{
  "code": String,
  "providerSecurityToken": String
}
```

Response:
```
{
  "code": String,
  "ascendantCode": String (optional),
  "title": String,
  "description": String (optional),
  "terminal": Boolean (optional)
}
```


### Patches

* Get a page of previously created patches by the current provider with filtering by status and creation timestamp;
* Create a new patch;
* Create a new patch in a sandbox mode.

### Periods

* Get all periods.

### Providers

* Get the current provider.

### Territories

* Get a page of territories with filtering by code, implicit ascendant code and title;
* Get a specific territory by its code.