# Auth system
## Run locally
1. Clone the repository.
```shell
git clone https://github.com/WhyNeet/simple-auth-system.git
```
2. Run the local Postgres database.
```shell
docker-compose -f docker-compose.local.yaml up
```
3. Setup the environment.<br />
Set `POSTGRES_URL`, `POSTGRES_USERNAME` and `POSTGRES_PASSWORD` variables. If that is not possible, hardcode those in `resources/application.properties`.
<br />
Hardcoded properties (for local development):
```
server.servlet.context-path = /api

spring.datasource.url = jdbc:postgresql://localhost:5432/primary
spring.datasource.username = admin
spring.datasource.password = admin

spring.jpa.hibernate.ddl-auto = validate

spring.flyway.enabled = true
spring.flyway.url = jdbc:postgresql://localhost:5432/primary
spring.flyway.password = admin
spring.flyway.user = admin
spring.flyway.schemas = public
spring.flyway.locations = classpath:db/migration

auth.jwt.secret = TVXrlxZ5Hy2/5MIW5mgWjkDOLETqJMk/HqIH7gmjkzeomDznahEv22ub7MRLzwdgcE/TFFaJTpb+CkzCAR25RQ==
auth.jwt.max-age = 600
```
4. Run the app:
```shell
./gradlew bootRun
```

## Endpoints
- `/api/auth`
    - `/register`<br />Register a new user. Requires JSON body with the following format:
        ```json
        {
          "username": "your_username",
          "fullName": "your full name",
          "password": "your_password"
        }
        ```
    - `/login`<br />Log in as existing user. Requires JSON body with the following format:
      ```json
      {
        "username": "your_username",
        "password": "your_password"
      }
      ```
- `/api/artwork/generate`
Requires the user to be authenticated (access_token cookie is assigned automatically).
  - `/{shape}`<br />Used to generate artwork with given shape.<br />`{shape}` = `Rectangle` / `Triangle`
  - `/{shape}/{empty}/{filled}`<br />Used to generate artwork with given shape and custom empty and filled cells.<br />`{shape}` = `Rectangle` / `Triangle`<br />`{empty}` - character(s) to fill into empty cells in an artwork<br />`{filled}` - character(s) to fill into filled cells in an artwork