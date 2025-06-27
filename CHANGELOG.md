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



# 📦 Range Package Manager 2.0.0 – Stable Release Notes

> This release finalizes the major architectural changes, introduces dual database support, and implements new social features in a fully stable and secure manner. All security configurations have been integrated and tested, ensuring production readiness.

---

## 🚀 Major Changes

* PostgreSQL and MongoDB now work seamlessly side-by-side.
* PostgreSQL handles all user and security data via JPA, while MongoDB manages flexible package data.
* JPA integration ensures efficient and stable database operations.

---

## 🧩 Architecture & Structural Improvements

* Project is modularized with clear separation of concerns.
* Controllers are refactored and responsibilities are split (e.g., AdminPackageController, UserPackageController).
* Enums and common utilities are centralized in dedicated packages.
* New services like AdminPackageService are fully operational.

---

## 🆕 New Features

* User profile management including avatars, bios, and social links.
* Friend request system with proper status handling and custom exceptions.
* Package visibility options: PUBLIC, PRIVATE, and ONLY\_FRIENDS.
* User metadata DTO for secure and optimized frontend communication.
* User settings functionality allowing profile and preference updates.

---

## 🔒 Security

* All API endpoints are secured with robust authentication and authorization mechanisms.
* JWT-based authentication is fully integrated and tested.
* Package-level access control is implemented for different visibility levels.
* Modular `SecurityFilterChain` ensures tailored security policies per module.

---

## 🧹 Code Cleanup & Performance

* Codebase optimized for maintainability and clarity.
* Swagger annotations reduced for cleaner API documentation.
* Unused dependencies removed for faster builds and runtime.

---

## 🌐 Frontend Integration (Planned)

* Initial backend API support is prepared for frontend consumption.
* A modern frontend client will be developed and integrated in future releases to enhance user experience and interface.

---

## ✅ Known Issues

* No critical issues currently reported.
* Minor improvements will be addressed in upcoming patch releases based on user feedback.



## 📜 License Change Notice

Starting from **version 2.0.0**, the project license has been changed from **Apache License** to **GNU General Public License v3.0 (GPL-3.0)**.

* This change aims to promote **strong copyleft** principles, ensuring that all derivative works remain open source under the same license.
* Users and contributors must comply with the terms of GPL-3.0 from this version onwards.
* Previous versions remain under the Apache License.

## Version 2.0.1

* ✅ **Fixed Swagger access issue:** Updated `CommonSecurityConfig` to allow proper access to Swagger UI.

# version 2.0.2 

* readme updated

# Version 2.1.0  dev 

* User setting repo implemented(still don't have controller)
* Auth api java added
* added swagger doc to friend request action api 

### 📄 Version 2.2.0 Beta

* The **UserContext** utility has been upgraded and can now retrieve user data.
* **AuthController** has been updated. JWT tokens are now stored directly in HTTP headers. I’m considering adding a refresh token mechanism in a future release (possibly version 4).
* The `packages/Securits` package has been renamed to `package/security`.
* The method `UserRegisterResponse userToUserRegisterResponse(User user)` has been removed.
* API versioning support has been added.
