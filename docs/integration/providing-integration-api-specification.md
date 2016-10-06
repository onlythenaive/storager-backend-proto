# Integration API: Providing

## Request Format
All the integration requests made by providing systems must be valid SOAP messages. 

## Security
All the integration requests made by providing systems must contain a security token among other input parameters required by a specific operation.

## Operations

### "createPatch"
Creates a new patch.

Request:
```
```

Response:
```
```


### "createPatchInSandbox"
Create a new patch in a sandbox mode.

Request:
```
```

Response:
```
```


### "getIndicator"
Gets a specific implicitly granted indicator by its code.

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


### "getIndicatorPage"
Gets a page of implicitly granted indicators with filtering by code, implicit ascendant code and title.

Request:
```
{
  "codePattern": String (a pattern of indicator code, optional),
  "implicitAscendantCode": String (exact code of an implicit ascendant, optional),
  "titlePattern": String (a pattern of indicator title, optional),
  "providerSecurityToken": String,
  "page": Integer (optional),
  "size": Integer (optional)
}
```

Response:
```
{
  "items": {
    "code": String,
    "ascendantCode": String (optional),
    "title": String,
    "description": String (optional),
    "terminal": Boolean (optional)
  }[],
  "page": Integer,
  "total": Integer
}
```


### "getPatch"
Gets a specific patch by its id.

Request:
```
{
  "id": Integer,
  "providerSecurityToken": String
}
```

Response:
```
{
  "id": Integer,
  "comment": String,
  "providerId": Integer,
  "createdAt": Timestamp,
  "status": String,
  "indicatorInfos": {
    "indicatorCode": String,
    "totalPoints": Integer
  }[]
}
```


### "getPatchPage"
Gets a page of previously created patches by the current provider with filtering by status and creation timestamp;

Request:
```
{
  "createdSince": Timestamp (optional),
  "createdUntil": Timestamp (optional),
  "status": String (optional),
  "providerSecurityToken": String,
  "page": Integer (optional),
  "size": Integer (optional)
}
```

Response:
```
{
  "items": {
    "id": Integer,
    "comment": String,
    "providerId": Integer,
    "createdAt": Timestamp,
    "status": String,
    "indicatorInfos": {
      "indicatorCode": String,
      "totalPoints": Integer
    }[]
  }[],
  "page": Integer,
  "total": Integer
}
```


### "getPeriods"
Gets all periods.

Request:
```
{
  "providerSecurityToken": String
}
```

Response:
```
{
  "code": String,
  "title": String
}[]
```


### "getProvider"
Gets the current authenticated provider.

Request:
```
{
  "providerSecurityToken": String
}
```

Response:
```
{
  "id": Integer,
  "title": String,
  "description": String (optional),
  "registeredAt": Timestamp,
  "grants": String[]
}
```


### "getTerritory"
Gets a specific territory by its code.

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


### "getTerritoryPage"
Gets a page of territories with filtering by code, implicit ascendant code and title;

Request:
```
{
  "codePattern": String (a pattern of territory code, optional),
  "implicitAscendantCode": String (exact code of an implicit ascendant, optional),
  "titlePattern": String (a pattern of territory title, optional),
  "providerSecurityToken": String,
  "page": Integer (optional),
  "size": Integer (optional)
}
```

Response:
```
{
  "items": {
    "code": String,
    "ascendantCode": String (optional),
    "title": String,
    "description": String (optional),
    "terminal": Boolean (optional)
  }[],
  "page": Integer,
  "total": Integer
}
```
