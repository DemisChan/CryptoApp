# Project Refactoring Plan

## Current Issues and Planned Improvements
### Task from Android development course - Andrey Sumin

### 1. Architecture Implementation
- **Current:** No clear architecture structure
- **Plan:** Implement Clean Architecture with clear separation of concerns
    - Data Layer
    - Domain Layer
    - Presentation Layer

### 2. POJO Class Structure
- **Current:** Mixed responsibility in `coinPriceInfo` class
- **Plan:** Split into three distinct classes:
    - Entity class (for business logic)
    - DTO class (for network operations)
    - DB Model class (for database operations)

### 3. Asynchronous Operations
- **Current:** Using RxJava in API service
- **Plan:** Migrate to Kotlin Coroutines for:
    - Simplified async code
    - Better integration with Kotlin
    - Reduced boilerplate

### 4. UI Components
- **Current:**
    - Using Kotlin synthetics
    - Basic adapter implementation with `notifyDataChange`
- **Plan:**
    - Implement View Binding or Data Binding
    - Optimize adapter updates
    - Remove deprecated Kotlin synthetics

### 5. Data Transformation
- **Current:** Direct `formattedTime` implementation
- **Plan:** Create dedicated mapper class for data transformations

### 6. Screen Rotation Handling
- **Current:** Using separate activity for details
- **Plan:**
    - Convert second activity to fragment
    - Implement master-detail flow
    - Add support for dual-pane layout in landscape mode

## Implementation Priority

1. Architecture restructuring
2. POJO class separation
3. Coroutines migration
4. UI components modernization
5. Mapper implementation
6. Screen rotation optimization

## Benefits

- Improved code maintainability
- Better separation of concerns
- Modern Android development practices
- Enhanced user experience
- Better screen rotation handling
- Improved testability

