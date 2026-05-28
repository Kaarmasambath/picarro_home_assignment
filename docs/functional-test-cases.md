# Functional Test Cases

## API Tests - REST Countries v3.1

### TC-API-001: Get Country by Name - Valid Name
| Field | Value |
|-------|-------|
| **ID** | TC-API-001 |
| **Title** | Verify GET /name/{name} returns country data for valid partial name |
| **Requirement** | FR-API-1, AC-API-1 |
| **Preconditions** | API is accessible at https://restcountries.com/v3.1 |
| **Steps** | 1. Send GET request to /v3.1/name/germany |
| **Expected Result** | Status 200, JSON array with country object containing name, capital, region fields |
| **Automated** | Yes - `RestCountriesApiTest.testGetCountryByName_ValidName_Returns200` |

### TC-API-002: Get Country by Full Name - Exact Match
| Field | Value |
|-------|-------|
| **ID** | TC-API-002 |
| **Title** | Verify GET /name/{name}?fullText=true returns exact match |
| **Requirement** | FR-API-2, AC-API-2 |
| **Preconditions** | API is accessible |
| **Steps** | 1. Send GET request to /v3.1/name/Germany?fullText=true |
| **Expected Result** | Status 200, single country object for "Germany" |
| **Automated** | Yes - `RestCountriesApiTest.testGetCountryByFullName_ExactMatch_Returns200` |

### TC-API-003: Get Country by Alpha-2 Code
| Field | Value |
|-------|-------|
| **ID** | TC-API-003 |
| **Title** | Verify GET /alpha/{code} returns country for alpha-2 code |
| **Requirement** | FR-API-3, AC-API-3 |
| **Preconditions** | API is accessible |
| **Steps** | 1. Send GET request to /v3.1/alpha/DE |
| **Expected Result** | Status 200, country object for Germany with cca2="DE" |
| **Automated** | Yes - `RestCountriesApiTest.testGetCountryByCode_Alpha2_Returns200` |

### TC-API-004: Get Country by Alpha-3 Code
| Field | Value |
|-------|-------|
| **ID** | TC-API-004 |
| **Title** | Verify GET /alpha/{code} returns country for alpha-3 code |
| **Requirement** | FR-API-3, AC-API-3 |
| **Preconditions** | API is accessible |
| **Steps** | 1. Send GET request to /v3.1/alpha/DEU |
| **Expected Result** | Status 200, country object for Germany with cca3="DEU" |
| **Automated** | Yes - `RestCountriesApiTest.testGetCountryByCode_Alpha3_Returns200` |

### TC-API-005: Get All Countries with Fields
| Field | Value |
|-------|-------|
| **ID** | TC-API-005 |
| **Title** | Verify GET /all?fields=... returns filtered response |
| **Requirement** | FR-API-4, AC-API-4 |
| **Preconditions** | API is accessible |
| **Steps** | 1. Send GET request to /v3.1/all?fields=name,capital,currencies |
| **Expected Result** | Status 200, array of objects containing requested fields |
| **Automated** | Yes - `RestCountriesApiTest.testGetAllCountries_WithFields_Returns200` |

### TC-API-006: Invalid Country Name Returns 404
| Field | Value |
|-------|-------|
| **ID** | TC-API-006 |
| **Title** | Verify GET /name/{name} returns 404 for non-existent country |
| **Requirement** | FR-API-5, AC-API-5 |
| **Preconditions** | API is accessible |
| **Steps** | 1. Send GET request to /v3.1/name/NonExistentCountryXYZ |
| **Expected Result** | Status 404 |
| **Automated** | Yes - `RestCountriesApiTest.testGetCountryByName_InvalidName_Returns404` |

### TC-API-007: Get All Without Fields Returns 400
| Field | Value |
|-------|-------|
| **ID** | TC-API-007 |
| **Title** | Verify GET /all without fields parameter returns 400 |
| **Requirement** | FR-API-6, AC-API-6 |
| **Preconditions** | API is accessible |
| **Steps** | 1. Send GET request to /v3.1/all (no fields parameter) |
| **Expected Result** | Status 400 |
| **Automated** | Yes - `RestCountriesApiTest.testGetAllCountries_NoFields_Returns400` |

### TC-API-008: Get Countries by Region
| Field | Value |
|-------|-------|
| **ID** | TC-API-008 |
| **Title** | Verify GET /region/{region} returns countries in that region |
| **Requirement** | FR-API-7, AC-API-7 |
| **Preconditions** | API is accessible |
| **Steps** | 1. Send GET request to /v3.1/region/europe |
| **Expected Result** | Status 200, array of European countries |
| **Automated** | Yes - `RestCountriesFilterTest.testGetCountriesByRegion_Europe_ReturnsArray` |

### TC-API-009: Get Countries by Currency
| Field | Value |
|-------|-------|
| **ID** | TC-API-009 |
| **Title** | Verify GET /currency/{currency} returns countries using that currency |
| **Requirement** | FR-API-8, AC-API-8 |
| **Preconditions** | API is accessible |
| **Steps** | 1. Send GET request to /v3.1/currency/usd |
| **Expected Result** | Status 200, array including United States |
| **Automated** | Yes - `RestCountriesFilterTest.testGetCountriesByCurrency_USD_ReturnsArray` |

### TC-API-010: Get Countries by Language
| Field | Value |
|-------|-------|
| **ID** | TC-API-010 |
| **Title** | Verify GET /lang/{language} returns countries speaking that language |
| **Requirement** | FR-API-9, AC-API-9 |
| **Preconditions** | API is accessible |
| **Steps** | 1. Send GET request to /v3.1/lang/spanish |
| **Expected Result** | Status 200, array including Spain, Mexico, Argentina |
| **Automated** | Yes - `RestCountriesFilterTest.testGetCountriesByLanguage_Spanish_ReturnsArray` |

---

## UI Tests - OrangeHRM

### TC-UI-001: Valid Login Navigates to Dashboard
| Field | Value |
|-------|-------|
| **ID** | TC-UI-001 |
| **Title** | Verify valid credentials navigate to dashboard |
| **Requirement** | FR-UI-1, AC-UI-1 |
| **Preconditions** | OrangeHRM demo site accessible, valid credentials available |
| **Steps** | 1. Navigate to login page<br>2. Enter username "Admin"<br>3. Enter password "admin123"<br>4. Click Login |
| **Expected Result** | User redirected to dashboard, user dropdown visible |
| **Automated** | Yes - `LoginTest.testValidLogin_NavigatesToDashboard` |

### TC-UI-002: Invalid Login Shows Error
| Field | Value |
|-------|-------|
| **ID** | TC-UI-002 |
| **Title** | Verify invalid credentials show error message |
| **Requirement** | FR-UI-2, AC-UI-2 |
| **Preconditions** | OrangeHRM demo site accessible |
| **Steps** | 1. Navigate to login page<br>2. Enter invalid username<br>3. Enter invalid password<br>4. Click Login |
| **Expected Result** | Error message displayed, remains on login page |
| **Automated** | Yes - `LoginTest.testInvalidLogin_ShowsError` |

### TC-UI-003: Navigate to PIM Module
| Field | Value |
|-------|-------|
| **ID** | TC-UI-003 |
| **Title** | Verify navigation to PIM module from dashboard |
| **Requirement** | FR-UI-3, AC-UI-3 |
| **Preconditions** | User logged in |
| **Steps** | 1. Click PIM in main navigation |
| **Expected Result** | PIM module loads, URL contains "pim" |
| **Automated** | Yes - `NavigationTest.testNavigateToPim_FromDashboard` |

### TC-UI-004: Navigate to Leave Module
| Field | Value |
|-------|-------|
| **ID** | TC-UI-004 |
| **Title** | Verify navigation to Leave module from dashboard |
| **Requirement** | FR-UI-3, AC-UI-3 |
| **Preconditions** | User logged in |
| **Steps** | 1. Click Leave in main navigation |
| **Expected Result** | Leave module loads, URL contains "leave" |
| **Automated** | Yes - `NavigationTest.testNavigateToLeave_FromDashboard` |

### TC-UI-005: Add Employee Form Has Required Fields
| Field | Value |
|-------|-------|
| **ID** | TC-UI-005 |
| **Title** | Verify Add Employee form displays required fields |
| **Requirement** | FR-UI-4, AC-UI-4 |
| **Preconditions** | User logged in, on PIM page |
| **Steps** | 1. Click Add Employee button |
| **Expected Result** | Form displays with First Name, Last Name, and Save button |
| **Automated** | Yes - `PimAddEmployeeTest.testAddEmployeeForm_HasRequiredFields` |

### TC-UI-006: Add Employee with Valid Data
| Field | Value |
|-------|-------|
| **ID** | TC-UI-006 |
| **Title** | Verify adding employee with valid data succeeds |
| **Requirement** | FR-UI-5, AC-UI-5 |
| **Preconditions** | User logged in, on Add Employee form |
| **Steps** | 1. Enter First Name<br>2. Enter Last Name<br>3. Click Save |
| **Expected Result** | Employee created, redirected to details or list |
| **Automated** | Yes - `PimAddEmployeeTest.testAddEmployee_ValidData_ShowsSuccess` |

### TC-UI-007: Add Employee Missing Required Shows Validation
| Field | Value |
|-------|-------|
| **ID** | TC-UI-007 |
| **Title** | Verify missing required fields show validation errors |
| **Requirement** | FR-UI-6, AC-UI-6 |
| **Preconditions** | User logged in, on Add Employee form |
| **Steps** | 1. Leave First Name empty<br>2. Enter Last Name<br>3. Click Save |
| **Expected Result** | Validation error displayed, form not submitted |
| **Automated** | Yes - `PimAddEmployeeTest.testAddEmployee_MissingFirstName_ShowsValidation` |

---

## Data Consistency Validation

### TC-DC-001: API Response Data Consistency
| Field | Value |
|-------|-------|
| **ID** | TC-DC-001 |
| **Title** | Verify API response matches expected data structure |
| **Requirement** | Data Consistency - API |
| **Preconditions** | API accessible |
| **Steps** | 1. GET /v3.1/name/germany<br>2. Verify response fields match expected values |
| **Expected Result** | name.common="Germany", capital="Berlin", region="Europe" |
| **Automated** | Yes - `RestCountriesApiTest.testDataConsistency_GermanyResponse_MatchesExpectedData` |

### TC-DC-002: UI Data Consistency After Employee Creation
| Field | Value |
|-------|-------|
| **ID** | TC-DC-002 |
| **Title** | Verify employee appears in system after creation |
| **Requirement** | Data Consistency - UI |
| **Preconditions** | User logged in |
| **Steps** | 1. Add employee via PIM form<br>2. Navigate to employee list<br>3. Verify navigation succeeds |
| **Expected Result** | Employee list accessible after creation |
| **Automated** | Yes - `PimAddEmployeeTest.testAddEmployee_DataConsistency_EmployeeAppearsInSystem` |
| **Notes** | Demo site resets data periodically; full search verification limited |
