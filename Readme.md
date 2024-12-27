## Simple JAVA service
- This service is simple demonstration of custom server configuration using Jetty, SQL database connection using JDBC, api creation using jax-rs,
### Features
- Service is created without any framework to demonstrate simple execution flow form configuring db and connecting it using JDBC, created Server using Jetty, registered APIs using Jax rs
- POM configures entrypoint for running artifact

### Steps :
1. Install dependencies
2. Crate Package ; `mvn package`
3. option 1: run jar using cmd: `java --jar target/file.jar`
4. option 2: run main app file : java -D app

## Questions 
1. How can create new Server?
2. How can register new APIs on server?
3. How can configure DB?