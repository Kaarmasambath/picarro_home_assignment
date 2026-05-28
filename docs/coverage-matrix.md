# Coverage / Traceability Matrix

## Functional Requirements Coverage

### REST Countries API

| Requirement | Description | Priority | Test Case(s) | Automated Test | Status |
|-------------|-------------|----------|--------------|----------------|--------|
| FR-API-1 | GET /name/{name} returns 200 and valid JSON | Must | TC-API-001 | `RestCountriesApiTest.testGetCountryByName_ValidName_Returns200` | ✅ Automated |
| FR-API-2 | GET /name/{name}?fullText=true returns exact match | Must | TC-API-002 | `RestCountriesApiTest.testGetCountryByFullName_ExactMatch_Returns200` | ✅ Automated |
| FR-API-3 | GET /alpha/{code} returns 200 for valid code | Must | TC-API-003, TC-API-004 | `RestCountriesApiTest.testGetCountryByCode_Alpha2_Returns200`, `testGetCountryByCode_Alpha3_Returns200` | ✅ Automated |
| FR-API-4 | GET /all?fields=... returns 200 with requested fields | Must | TC-API-005 | `RestCountriesApiTest.testGetAllCountries_WithFields_Returns200` | ✅ Automated |
| FR-API-5 | Invalid name/code returns 404 | Must | TC-API-006 | `RestCountriesApiTest.testGetCountryByName_InvalidName_Returns404`, `testGetCountryByCode_InvalidCode_Returns404` | ✅ Automated |
| FR-API-6 | GET /all without fields returns 400 | Must | TC-API-007 | `RestCountriesApiTest.testGetAllCountries_NoFields_Returns400` | ✅ Automated |
| FR-API-7 | GET /region/{region} returns 200 and array | Should | TC-API-008 | `RestCountriesFilterTest.testGetCountriesByRegion_Europe_ReturnsArray` | ✅ Automated |
| FR-API-8 | GET /currency/{currency} returns 200 and array | Should | TC-API-009 | `RestCountriesFilterTest.testGetCountriesByCurrency_USD_ReturnsArray` | ✅ Automated |
| FR-API-9 | GET /lang/{language} returns 200 and array | Should | TC-API-010 | `RestCountriesFilterTest.testGetCountriesByLanguage_Spanish_ReturnsArray` | ✅ Automated |

### OrangeHRM UI

| Requirement | Description | Priority | Test Case(s) | Automated Test | Status |
|-------------|-------------|----------|--------------|----------------|--------|
| FR-UI-1 | Valid login leads to dashboard | Must | TC-UI-001 | `LoginTest.testValidLogin_NavigatesToDashboard` | ✅ Automated |
| FR-UI-2 | Invalid login shows error | Must | TC-UI-002 | `LoginTest.testInvalidLogin_ShowsError` | ✅ Automated |
| FR-UI-3 | Main navigation usable when logged in | Must | TC-UI-003, TC-UI-004 | `NavigationTest.testNavigateToPim_FromDashboard`, `testNavigateToLeave_FromDashboard` | ✅ Automated |
| FR-UI-4 | Add Employee form has required fields | Must | TC-UI-005 | `PimAddEmployeeTest.testAddEmployeeForm_HasRequiredFields` | ✅ Automated |
| FR-UI-5 | Valid submission completes with success | Must | TC-UI-006 | `PimAddEmployeeTest.testAddEmployee_ValidData_ShowsSuccess` | ✅ Automated |
| FR-UI-6 | Missing required fields show validation | Must | TC-UI-007 | `PimAddEmployeeTest.testAddEmployee_MissingFirstName_ShowsValidation` | ✅ Automated |

---

## Acceptance Criteria Coverage

### REST Countries API

| AC ID | Description | Test Case | Automated Test | Status |
|-------|-------------|-----------|----------------|--------|
| AC-API-1 | Valid name returns 200 with name, capital, region | TC-API-001 | `testGetCountryByName_ValidName_Returns200` | ✅ Covered |
| AC-API-2 | Full name with fullText=true returns exact match | TC-API-002 | `testGetCountryByFullName_ExactMatch_Returns200` | ✅ Covered |
| AC-API-3 | Valid alpha2/alpha3 code returns 200 | TC-API-003, TC-API-004 | `testGetCountryByCode_Alpha2_Returns200`, `testGetCountryByCode_Alpha3_Returns200` | ✅ Covered |
| AC-API-4 | GET /all?fields returns only requested fields | TC-API-005 | `testGetAllCountries_WithFields_ReturnsRequestedFields` | ✅ Covered |
| AC-API-5 | Invalid name/code returns 404 | TC-API-006 | `testGetCountryByName_InvalidName_Returns404` | ✅ Covered |
| AC-API-6 | GET /all without fields returns 400 | TC-API-007 | `testGetAllCountries_NoFields_Returns400` | ✅ Covered |
| AC-API-7 | Valid region returns array of countries | TC-API-008 | `testGetCountriesByRegion_Europe_ReturnsArray` | ✅ Covered |
| AC-API-8 | Valid currency returns array of countries | TC-API-009 | `testGetCountriesByCurrency_USD_ReturnsArray` | ✅ Covered |
| AC-API-9 | Valid language returns array of countries | TC-API-010 | `testGetCountriesByLanguage_Spanish_ReturnsArray` | ✅ Covered |

### OrangeHRM UI

| AC ID | Description | Test Case | Automated Test | Status |
|-------|-------------|-----------|----------------|--------|
| AC-UI-1 | Valid login shows dashboard with logged-in state | TC-UI-001 | `testValidLogin_NavigatesToDashboard` | ✅ Covered |
| AC-UI-2 | Invalid login shows error, stays on login | TC-UI-002 | `testInvalidLogin_ShowsError`, `testInvalidLogin_RemainsOnLoginPage` | ✅ Covered |
| AC-UI-3 | Navigation reaches PIM and Leave modules | TC-UI-003, TC-UI-004 | `testNavigateToPim_FromDashboard`, `testNavigateToLeave_FromDashboard` | ✅ Covered |
| AC-UI-4 | Add Employee form shows required fields | TC-UI-005 | `testAddEmployeeForm_HasRequiredFields` | ✅ Covered |
| AC-UI-5 | Valid submission shows success | TC-UI-006 | `testAddEmployee_ValidData_ShowsSuccess` | ✅ Covered |
| AC-UI-6 | Missing required shows validation errors | TC-UI-007 | `testAddEmployee_MissingFirstName_ShowsValidation` | ✅ Covered |

---

## Bonus Coverage

### API Schema Validation

| Test | Description | Status |
|------|-------------|--------|
| `RestCountriesSchemaTest.testGetCountryByName_ResponseMatchesSchema` | Validates /name response against JSON schema | ✅ Automated |
| `RestCountriesSchemaTest.testGetCountryByCode_ResponseMatchesSchema` | Validates /alpha response against JSON schema | ✅ Automated |
| `RestCountriesSchemaTest.testGetCountriesByRegion_ResponseMatchesSchema` | Validates /region response against JSON schema | ✅ Automated |
| `RestCountriesSchemaTest.testCountryResponse_ContainsRequiredFields` | Validates required fields present | ✅ Automated |
| `RestCountriesSchemaTest.testAlpha2Code_FormatIsValid` | Validates alpha-2 code format (2 uppercase letters) | ✅ Automated |
| `RestCountriesSchemaTest.testAlpha3Code_FormatIsValid` | Validates alpha-3 code format (3 uppercase letters) | ✅ Automated |
| `RestCountriesSchemaTest.testRegion_ValueIsValid` | Validates region is from allowed enum | ✅ Automated |
| `RestCountriesSchemaTest.testPopulation_IsNonNegative` | Validates population >= 0 | ✅ Automated |

### Accessibility Testing (axe-core)

| Test | Description | Status |
|------|-------------|--------|
| `AccessibilityTest.testLoginPage_NoCriticalAccessibilityViolations` | Checks for critical/serious violations | ✅ Automated |
| `AccessibilityTest.testDashboardPage_NoCriticalAccessibilityViolations` | Dashboard accessibility check | ✅ Automated |
| `AccessibilityTest.testLoginPage_MeetsWCAG21AA` | WCAG 2.1 AA compliance check | ✅ Automated |
| `AccessibilityTest.testLoginPage_HasProperHeadingStructure` | Heading structure validation | ✅ Automated |
| `AccessibilityTest.testLoginPage_FormElementsHaveLabels` | Form label validation | ✅ Automated |
| `AccessibilityTest.testLoginPage_ColorContrastMeetsStandards` | Color contrast check | ✅ Automated |
| `AccessibilityTest.testLoginPage_GenerateAccessibilityReport` | Full accessibility report | ✅ Automated |

---

## Coverage Summary

| Category | Total | Automated | Coverage |
|----------|-------|-----------|----------|
| API Must-have (FR-API-1 to FR-API-6) | 6 | 6 | 100% |
| API Should-have (FR-API-7 to FR-API-9) | 3 | 3 | 100% |
| UI Must-have (FR-UI-1 to FR-UI-6) | 6 | 6 | 100% |
| API Schema Validation (Bonus) | 8 | 8 | 100% |
| Accessibility Testing (Bonus) | 7 | 7 | 100% |
| **Total Requirements** | **15** | **15** | **100%** |
| **Total Tests (incl. Bonus)** | **30+** | **30+** | **100%** |

---

## Data Consistency Validation

| Scenario | Description | Test | Status |
|----------|-------------|------|--------|
| API Data Consistency | Verify Germany response matches expected fields/values | `testDataConsistency_GermanyResponse_MatchesExpectedData` | ✅ Automated |
| UI Data Consistency | Verify employee creation persists in system | `testAddEmployee_DataConsistency_EmployeeAppearsInSystem` | ✅ Automated |

### Approach & Limitations

**API (REST Countries):**
- API response treated as source of truth
- Schema validation ensures response structure matches expected contract
- Known data (Germany: capital=Berlin, region=Europe) validated against response

**UI (OrangeHRM):**
- No direct database access available on demo site
- Validation limited to UI state verification
- Employee creation verified by successful navigation to employee list
- Demo site periodically resets data, affecting long-term persistence tests

---

## Gaps and Notes

| Gap | Reason | Mitigation |
|-----|--------|------------|
| Direct DB validation for OrangeHRM | Demo site doesn't expose database | UI-based verification after actions |
| Full employee search verification | Demo data resets | Verify navigation succeeds post-creation |
| Mobile/responsive testing | Out of scope per requirements | Desktop only tested |
| Performance testing | Out of scope per requirements | Not implemented |

---

## CI/CD Integration

| Pipeline | Trigger | Tests |
|----------|---------|-------|
| GitHub Actions (ci.yml) | Push/PR to main | API + UI tests |
| GitHub Actions (scheduled-tests.yml) | Daily at 6 AM UTC | Full regression |
| TeamCity | Configurable | API, UI, or Full suite |

---

**All Must-have requirements (FR-API-1 to FR-API-6, FR-UI-1 to FR-UI-6) are 100% covered by automated tests.**
