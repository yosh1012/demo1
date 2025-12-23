# backend

This project has the "data-driven" naming rule.

- create application.conf to connect to DB and Redis
- create Main.scala and main/BUILD.bazel

Use MVC pattern, not DDD because this project doesn't have too many files.


# Lib 

## JSON Library: Play JSON

### Reason for adoption

- Spray JSON doesn't support more than 22 columns