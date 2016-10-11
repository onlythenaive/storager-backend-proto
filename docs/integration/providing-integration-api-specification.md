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
{
  "comment": string,
  "points": {
    "indicatorCode": string,
    "periodCode": string,
    "territoryCode": string,
    "date": ISO date string,
    "real": double,
    "plan": double (optional),
  }[],
  "providerSecurityToken": string
}
```

Response:
```
{
  "id": integer,
  "comment": string,
  "providerId": integer,
  "createdAt": ISO timestamp string,
  "status": string,
  "indicatorInfos": {
    "indicatorCode": string,
    "totalPoints": integer
  }[]
}
```


### "createPatchInSandbox"
Create a new patch in a sandbox mode (patch is not persisted in the data storage).

Request:
```
{
  "comment": string,
  "points": {
    "indicatorCode": string,
    "periodCode": string,
    "territoryCode": string,
    "date": ISO date string,
    "real": double,
    "plan": double (optional),
  }[],
  "providerSecurityToken": string
}
```

Response:
```
{
  "id": integer,
  "comment": string,
  "providerId": integer,
  "createdAt": ISO timestamp string,
  "status": string,
  "indicatorInfos": {
    "indicatorCode": string,
    "totalPoints": integer
  }[]
}
```


### "getIndicator"
Gets a specific implicitly granted indicator by its code.

Request:
```
{
  "code": string,
  "providerSecurityToken": string
}
```

Response:
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


### "getIndicatorPage"
Gets a page of implicitly granted indicators with filtering by code, implicit ascendant code and title.

Request:
```
{
  "codePattern": string (a pattern of indicator code, optional),
  "ascendantCode": string (exact code of the ascendant, optional),
  "titlePattern": string (a pattern of indicator title, optional),
  "page": integer (optional),
  "size": integer (optional),
  "providerSecurityToken": string
}
```

Response:
```
{
  "items": {
    "code": string,
    "ascendantCode": string (optional),
    "title": string,
    "description": string (optional),
    "path": string[] (optional),
    "terminal": boolean (optional)
  }[],
  "page": integer,
  "total": integer
}
```


### "getPatch"
Gets a specific patch by its id.

Request:
```
{
  "id": integer,
  "providerSecurityToken": string
}
```

Response:
```
{
  "id": integer,
  "comment": string,
  "providerId": integer,
  "createdAt": ISO timestamp string,
  "status": string,
  "indicatorInfos": {
    "indicatorCode": string,
    "totalPoints": integer
  }[]
}
```


### "getPatchPage"
Gets a page of previously created patches by the current provider with filtering by status and creation timestamp;

Request:
```
{
  "createdSince": ISO timestamp string (optional),
  "createdUntil": ISO timestamp string (optional),
  "status": string (optional),
  "page": integer (optional),
  "size": integer (optional),
  "providerSecurityToken": string
}
```

Response:
```
{
  "items": {
    "id": integer,
    "comment": string,
    "providerId": integer,
    "createdAt": ISO timestamp string,
    "status": string,
    "indicatorInfos": {
      "indicatorCode": string,
      "totalPoints": integer
    }[]
  }[],
  "page": integer,
  "total": integer
}
```


### "getPeriods"
Gets all periods.

Request:
```
{
  "providerSecurityToken": string
}
```

Response:
```
{
  "items": {
    "code": string,
    "title": string
  }[]
}
```


### "getProvider"
Gets the current authenticated provider.

Request:
```
{
  "providerSecurityToken": string
}
```

Response:
```
{
  "id": integer,
  "title": string,
  "description": string (optional),
  "registeredAt": ISO timestamp string,
  "grants": string[]
}
```


### "getTerritory"
Gets a specific territory by its code.

Request:
```
{
  "code": string,
  "providerSecurityToken": string
}
```

Response:
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


### "getTerritoryPage"
Gets a page of territories with filtering by code, implicit ascendant code and title;

Request:
```
{
  "codePattern": string (a pattern of territory code, optional),
  "ascendantCode": string (exact code of the ascendant, optional),
  "titlePattern": string (a pattern of territory title, optional),
  "page": integer (optional),
  "size": integer (optional),
  "providerSecurityToken": string
}
```

Response:
```
{
  "items": {
    "code": string,
    "ascendantCode": string (optional),
    "title": string,
    "description": string (optional),
    "path": string[] (optional),
    "terminal": boolean (optional)
  }[],
  "page": integer,
  "total": integer
}
```
