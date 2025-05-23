# Changelog

---

### Version 0.0.1-SNAPSHOT
🚀 Project foundation laid out.

---

### Version 0.0.2-SNAPSHOT
- **Frontend** updated
- **Add Package** service improved
- **Search Package** service refactored
- Removed `"detail"` field from **Error Response**
- Updated **Upload Package** controller

---

### Version 0.0.3
- Added missing dependencies

---

### Version 1.0.0-beta
This version includes a major overhaul and several key updates:

- Introduced **PackageMetaDataController**
- Removed the `Dependencies` entity
- Added a default API response wrapper
- Renamed `PackageDto` to `PackageMetaDataDto`
- Added `UploadFileRequest` DTO
- Added `CannotDowngradeException`, `UnsupportedFileException`, and `PackageDeletionException`
- Removed unnecessary `ErrorResponseDto`
- Fully documented the API using **Swagger**
- Added `ErrorResponseUtil`
- Introduced `AbstractExceptionHandler` for centralized exception handling
- All exceptions now inherit from this abstract handler to comply with the **DRY** principle
- Implemented `AuthController`, `JwtFilter`, and `JwtUtil` for authentication

---

### ⚠ Known Issue
There is still a bug in the application. This version was pushed before being fully finalized in order to help resolve the issue.  
Once the issue is fixed, the login functionality and admin panel HTML will be completed.



### Version 1.0.0-final

* Fixed JWT filter issue
* Added logger to `AuthServiceImpl`
* Added validation to controllers
* Removed admin panel and login HTML — will either collaborate with a frontend developer or use a ready-made template
* JWT filter is now working
* Renamed `ApiResponse` to `GenericResponse` due to a naming conflict with Swagger




### 📦 Version 2.0.0 – Early Beta

> ⚠️ This is an early beta release. Major architectural improvements have been introduced. The system now supports dual database configuration. Some modules are still under development.

---

#### 🚀 Major Changes

* **PostgreSQL Added (Dual DB Setup)**
  PostgreSQL has been integrated alongside MongoDB.
  The `users` table and all security-related data are now stored in PostgreSQL for enhanced safety and relational structure.

* **JPA Integration**
  Java Persistence API (JPA) is now used for handling PostgreSQL entities.

* **PostgreSQL Driver Added**
  JDBC driver for PostgreSQL has been added to the project dependencies.

* **Admin PackageService Implemented**
  A new service and its implementation were added for administrative package operations.

---

#### 🛠 New Features

* **FileExtensionUtil**
  Utility for validating and managing supported file types during package upload.

* **UserProfile Entity**
  A new entity to store user-specific metadata like avatar, bio, birth date, and social links.

* **Friend Request System Introduced**

    * `FriendRequest` entity
    * `FriendRequestStatus` enum
    * `FriendRequestException` for custom error handling

* **PackageVisibility Enum**
  Enum introduced to define visibility status of packages:

    * `PUBLIC`: Visible to everyone
    * `PRIVATE`: Only visible to the author
    * `ONLY_FRIENDS`: Visible only to the author’s friends

* **UserMetadata DTO**
  A DTO for exposing minimal user data to the frontend in a clean and secure way.

---

#### 🧼 Refactoring & Improvements

* **Controller Refactor**
  The package controller was split into:

    * `AdminPackageController`
    * `UserPackageController`

* **Enum Refactor**
  All enums have been moved to a dedicated package (`com.range.rpms.enums`) for better maintainability.

---

#### 🌐 Frontend Initialization

* Initial frontend layout created
* Thanks to **Selin Yakup** for contributing to the frontend setup 💫



### 📦 Version 2.0.0 – Early Beta 2

#### 🆕 New Features

* Added the `api` module to the project.
* Implemented user settings functionality.
* Added user profile support.

#### 🛠️ Structural Changes

* Refactored the overall project architecture.
* Modularized package structure.
* Made improvements to the package service.

#### 🧹 Cleanup

* Reduced Swagger annotations due to excessive clutter in the codebase.

#### ⚠️ Known Issues

* Several controllers are currently non-functional due to architectural changes. Fixes are planned.
