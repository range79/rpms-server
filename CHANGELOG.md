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

