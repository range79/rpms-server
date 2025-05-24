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






### 📦 Version 2.0.0 – Beta

> ✅ This is the official beta release introducing major architectural changes and dual database support. The system has been modularized, and several new features have been implemented.
> ⚠️ Some modules (like package-level security) are still in progress.

---

#### 🚀 Major Changes

* **Dual Database Support (PostgreSQL + MongoDB)**
  PostgreSQL has been integrated alongside MongoDB.

  * All `users` and security-related data are now stored in PostgreSQL.
  * MongoDB remains in use for flexible, document-oriented data such as packages.

* **JPA Integration**
  Java Persistence API (JPA) is now used to manage PostgreSQL entities.

* **PostgreSQL JDBC Driver Added**
  PostgreSQL driver has been added to the project dependencies.

---

#### 🧩 Architecture & Structural Improvements

* **Modular Project Structure**
  The overall architecture was redesigned and modularized for better maintainability.
  The `api` module has been introduced to separate external interface logic.

* **Controller Refactoring**
  Package controllers were split for cleaner responsibility separation:

  * `AdminPackageController`
  * `UserPackageController`

* **Enum Refactor**
  All enums have been moved to a dedicated package: `com.range.rpms.enums`

---

#### 🆕 New Features

* **AdminPackageService**
  A new service for administrative-level package operations.

* **FileExtensionUtil**
  Utility class to validate and manage file types during package uploads.

* **UserProfile Entity**
  New entity to store user-related metadata such as avatar, bio, birth date, and social links.

* **Friend Request System**
  Social features added with support for:

  * `FriendRequest` entity
  * `FriendRequestStatus` enum
  * Custom error handling with `FriendRequestException`

* **PackageVisibility Enum**
  New visibility options for packages:

  * `PUBLIC`: Visible to everyone
  * `PRIVATE`: Only visible to the author
  * `ONLY_FRIENDS`: Visible only to the author's friends

* **UserMetadata DTO**
  A lightweight and secure data transfer object for exposing public user info to the frontend.

* **User Settings Functionality**
  Users can now update their personal settings (e.g., profile info, preferences).

---

#### 🌐 Frontend Initialization

* Initial layout and component setup completed.
* 💫 Special thanks to **Selin Yakup** for contributing to the frontend!

---

#### 🧼 Cleanup & Code Improvements

* Reduced excessive Swagger annotations for cleaner code.
* Improved package service functionality.
* Performed general code cleanup and reorganization.

---

#### ⚠️ Known Issues

* **Security on Packages Not Yet Implemented**
  Package-level access control (e.g., private/friends-only access) is planned but not yet functional.

* **Controller Compatibility Issues**
  Some controllers are temporarily non-functional due to ongoing architectural changes. Fixes are scheduled.

---