# QA Automation Framework

Automated testing framework for **REST Countries API** and **OrangeHRM UI**.

## Quick Start

```bash
# Run all tests
mvn clean test

# Run API tests only
mvn test -DsuiteXmlFile=src/test/resources/testng-api.xml

# Run UI tests only
mvn test -DsuiteXmlFile=src/test/resources/testng-ui.xml
```

## Project Structure

```
src/
├── main/java/com/qa/
│   ├── api/
│   │   └── RestCountriesClient.java       # API client wrapper
│   ├── config/
│   │   └── ReadConfig.java                # Configuration reader
│   ├── constant/
│   │   └── FrameworkConstants.java        # Framework constants
│   ├── listeners/
│   │   └── ListenersClass.java            # TestNG listeners
│   ├── report/
│   │   ├── ExtentReport.java              # Extent report setup
│   │   ├── ExtentManager.java             # Thread-safe extent management
│   │   └── ExtentLogger.java              # Logging utility
│   ├── ui/
│   │   ├── pages/                         # Page Object Model
│   │   │   ├── LoginPage.java
│   │   │   ├── DashboardPage.java
│   │   │   └── PimPage.java
│   │   └── utils/
│   │       └── DriverFactory.java         # WebDriver factory
│   └── utilities/
│       └── ActionUtilities.java           # Common UI actions
│
├── test/java/com/qa/
│   ├── api/
│   │   ├── RestCountriesApiTest.java      # FR-API-1 to FR-API-6
│   │   ├── RestCountriesFilterTest.java   # FR-API-7 to FR-API-9
│   │   ├── RestCountriesSchemaTest.java   # JSON Schema validation
│   │   └── TestDataProvider.java          # External test data
│   ├── base/
│   │   └── BaseTest.java                  # Base test class
│   └── ui/
│       ├── LoginTest.java                 # FR-UI-1, FR-UI-2
│       ├── NavigationTest.java            # FR-UI-3
│       ├── PimAddEmployeeTest.java        # FR-UI-4 to FR-UI-6
│       └── AccessibilityTest.java         # Accessibility testing
│
└── test/resources/
    ├── config.properties                  # Configuration
    ├── testng.xml                         # Full test suite
    ├── testng-api.xml                     # API tests only
    ├── testng-ui.xml                      # UI tests only
    ├── schemas/
    │   └── country-response-schema.json   # JSON schema
    └── testdata/
        └── api-testdata.json              # External test data

docs/
├── functional-test-cases.md               # Detailed test cases
└── coverage-matrix.md                     # FR/AC traceability

.github/workflows/
├── ci.yml                                 # CI pipeline
└── scheduled-tests.yml                    # Nightly test runs
```

## Technology Stack

| Area | Technology | Rationale |
|------|------------|-----------|
| Language | Java 17 | Enterprise standard, strong typing |
| Build | Maven | Dependency management, profiles |
| API Testing | RestAssured | Fluent API for REST testing |
| UI Testing | Selenium WebDriver | Industry standard for browser automation |
| Test Framework | TestNG | Parallel execution, grouping, reporting |
| Assertions | TestNG Assert | Built-in assertions |
| Reporting | ExtentReports | Rich HTML reports |
| Test Data | JavaFaker | Realistic test data generation |
| Schema Validation | json-schema-validator | API response validation |

## Systems Under Test

### REST Countries API
- **Base URL:** https://restcountries.com/v3.1
- **Auth:** None
- **Endpoints Tested:** /name, /alpha, /all, /region, /currency, /lang, /capital

### OrangeHRM UI
- **URL:** https://opensource-demo.orangehrmlive.com
- **Credentials:** Admin / admin123
- **Flows Tested:** Login, Navigation, PIM Add Employee

## Test Coverage

| Category | Requirements | Tests | Status |
|----------|--------------|-------|--------|
| API Must-have (FR-API-1 to FR-API-6) | 6 | 14 | ✅ Automated |
| API Should-have (FR-API-7 to FR-API-9) | 3 | 15 | ✅ Automated |
| API Schema Validation | - | 9 | ✅ Automated |
| UI Must-have (FR-UI-1 to FR-UI-6) | 6 | 16 | ✅ Automated |
| Accessibility | - | 7 | ✅ Automated |

**Total: 60+ automated tests covering 100% of requirements**

See [docs/coverage-matrix.md](docs/coverage-matrix.md) for full traceability.

## Data Consistency Validation

### Scenario 1: API Data Consistency (REST Countries)
- **Test:** `RestCountriesApiTest.testDataConsistency_GermanyResponse_MatchesExpectedData`
- **Approach:** Validates response structure matches expected JSON schema and known values
- **Verifies:** Germany returns capital=Berlin, region=Europe
- **Source of Truth:** API response treated as source of truth

### Scenario 2: UI Data Consistency (OrangeHRM)
- **Test:** `PimAddEmployeeTest.testAddEmployee_DataConsistency_EmployeeAppearsInSystem`
- **Approach:** Creates employee via PIM form, verifies navigation to PIM list succeeds
- **Limitation:** No direct database access on demo site; uses UI state verification
- **Note:** Demo site resets data periodically

## Configuration

All configuration in `src/test/resources/config.properties`:

```properties
# API Configuration
api.rest-countries.baseurl=https://restcountries.com
api.rest-countries.version=v3.1

# UI Configuration
ui.orangehrm.baseurl=https://opensource-demo.orangehrmlive.com
ui.orangehrm.username=Admin
ui.orangehrm.password=admin123

# Browser Configuration
browser.name=chrome
browser.headless=true

# Timeout Configuration (seconds)
timeout.implicit=10
timeout.explicit=15
timeout.page-load=30
```

## CI/CD Integration

### GitHub Actions
- **ci.yml** - Runs on push/PR to main branches
  - API tests (always)
  - UI tests (with Chrome headless)
- **scheduled-tests.yml** - Nightly full regression at 6 AM UTC

### TeamCity
- Configuration in `.teamcity/settings.kts`
- Separate build configurations for API, UI, and full suite

## Design Decisions & Tradeoffs

### Page Factory Pattern
Used `@FindBy` annotations for element locators, improving readability and separating locators from test logic.

### ActionUtilities Base Class
Common UI actions (click, sendKey, wait) in base class to reduce duplication and ensure consistent wait handling.

### Explicit Waits
Used `WebDriverWait` with `ExpectedConditions` instead of implicit waits for better control on dynamic pages.

### External Test Data
Test data externalized to JSON files (`api-testdata.json`) allowing data changes without code modifications.

### JavaFaker for UI Tests
Generates realistic employee names for PIM tests, avoiding hardcoded test data.

### ThreadLocal WebDriver
Ensures thread-safe WebDriver instances for parallel test execution.

### Headless by Default
Chrome runs headless for CI compatibility; easily overridden via config.

## What I'd Add With More Time

1. **Screenshot on Failure** - Automatic screenshots attached to ExtentReport
2. **Video Recording** - Record test execution for debugging
3. **More UI Flows** - Leave module, Recruitment, Time tracking
4. **Retry Mechanism** - TestNG retry analyzer for flaky tests
5. **Multiple Environments** - Dev, staging, production configs
6. **Performance Baseline** - Response time assertions for API
7. **Docker Support** - Containerized test execution
8. **Parallel UI Execution** - Grid setup for parallel browser tests

## Reports

After test execution:
- **ExtentReport:** `ExtentReport/ExtentReportResults.html`
- **TestNG Report:** `target/surefire-reports/index.html`

## Requirements

- Java 17+
- Maven 3.8+
- Chrome browser (for UI tests)

## Documentation

- [Functional Test Cases](docs/functional-test-cases.md)
- [Coverage Matrix](docs/coverage-matrix.md)

## Author

Karthikeyan Sambath
