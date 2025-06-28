# KUSLS-Back 프로젝트

이 문서는 KUSLS-Back 프로젝트의 개발 환경, 사용 도구, Git 커밋 컨벤션, 그리고 코드 컨벤션에 대한 가이드라인을 제공합니다. 효율적인 협업을 위해 모든 팀원은 아래 명시된 규칙과 도구 사용법을 숙지하고 따라야 합니다.

## 1. 개발 환경 및 도구 (Development Environment & Tools)

본 프로젝트는 다음과 같은 개발 환경 및 도구를 사용합니다.

- **IDE (통합 개발 환경)**:

    - **IntelliJ IDEA**: 주 개발 IDE로 사용됩니다. 최적의 개발 경험을 위해 IntelliJ의 Gradle 통합 및 Database Tool 플러그인 활용을 권장합니다.

- **컨테이너 가상화**:

    - **Docker**: 애플리케이션의 개발, 테스트 및 배포 환경을 컨테이너 기반으로 통일합니다. `Dockerfile`과 `docker-compose.yml`을 사용하여 애플리케이션과 데이터베이스 컨테이너를 관리합니다.

        - **Docker 설치**: 로컬에서 애플리케이션을 Docker 컨테이너로 실행하려면 Docker Desktop이 설치되어 있어야 합니다.

        - **애플리케이션 실행/재시작 (IntelliJ 권장)**:

            1. **코드/설정 변경 시**: Java 코드, `application.yml`, `.env` 등 프로젝트 내 수정사항이 발생하면, 먼저 Gradle을 통해 **새로운 JAR 파일(`app.jar`)을 빌드**해야 합니다. (터미널: `./gradlew build`)

            2. **Docker Compose 구동**: IntelliJ IDEA의 `Services` 탭에서 `docker-compose` 스택을 찾아 `up` (녹색 화살표) 버튼을 클릭합니다.

                - `docker-compose.yml`에 `build: .` 설정이 되어 있다면, 컨테이너 시작 시 변경된 JAR 파일을 포함하여 **애플리케이션 이미지를 자동으로 다시 빌드**합니다.

                - **컨테이너 중지**: `Services` 탭에서 `stop` (빨간색 사각형) 버튼을 클릭하거나, 터미널에서 `docker-compose down` 명령어를 사용합니다.

        - **환경 변수 설정**: `docker-compose.yml`에 `env_file: ./.env`가 설정되어 있으므로, `.env` 파일에 정의된 변수들이 컨테이너로 자동으로 주입됩니다. IntelliJ `Run Configuration`에서 별도로 환경 변수를 추가할 필요는 없습니다.

- **데이터베이스 관리**:

    - **DataGrip**: 데이터베이스 관리 및 SQL 쿼리 실행을 위한 전문 도구로 사용됩니다. **Docker 컨테이너에서 실행되는 MySQL 데이터베이스에 DataGrip으로 직접 접속하여 관리할 수 있습니다.**

        - **데이터베이스 생성**: `docker-compose`로 MySQL 컨테이너를 띄우면 `docker-compose.yml` 및 `.env` 설정에 따라 `kusls_db`와 같은 데이터베이스가 자동으로 생성됩니다.

        - **스키마 초기화**: `src/main/resources/schema.sql` 파일에 정의된 테이블 생성 및 구조 스크립트는 Spring Boot 애플리케이션 시작 시 (`application.yml`의 `spring.sql.init.mode=always` 설정에 따라) 자동으로 실행됩니다. DataGrip에서 수동으로 스크립트를 실행할 경우, SQL 파일 상단에 `USE [데이터베이스_이름];`을 추가하거나, DataGrip UI의 드롭다운에서 해당 데이터베이스를 선택 후 실행해야 합니다.

        - **DataGrip으로 Docker MySQL 접속 방법**:

            1. DataGrip을 열고 `Database` 탭에서 `+` 버튼 클릭 후 `Data Source` > `MySQL`을 선택합니다.

            2. **General 탭 설정**:

                - **Name**: `KUSLS_DB_Docker` (원하는 이름)

                - **Host**: `localhost` (Docker Compose에서 3306 포트를 호스트에 연결했으므로)

                - **Port**: `3306`

                - **User**: `.env` 파일의 `MYSQL_USER` 값 (예: `kusls_user`)

                - **Password**: `.env` 파일의 `MYSQL_PASSWORD` 값 (예: `kusls_pass`)

                - **Database**: `.env` 파일의 `MYSQL_DATABASE` 값 (예: `kusls_db`)

            3. 하단에 `Download` 버튼이 보이면 클릭하여 JDBC 드라이버를 다운로드합니다.

            4. `Test Connection`을 클릭하여 `Succeeded` 메시지를 확인한 후 `Apply` 및 `OK`를 클릭하여 연결을 완료합니다.

            5. `Database` 탭에서 새로 추가된 연결을 확장하여 테이블 및 데이터를 확인할 수 있습니다.

- **API 문서화**:

    - **Swagger (Springdoc OpenAPI)**: API 명세를 자동 생성하고 웹 인터페이스를 통해 확인할 수 있습니다. 개발 중 API 테스트 및 문서 공유에 활용됩니다.

        - **접근 경로**: 애플리케이션 실행 후 `http://localhost:8080/swagger-ui.html` 로 접속하여 확인할 수 있습니다.

- **Docker 리소스 정리**:

    - 개발 과정에서 Docker 이미지들이 계속 쌓여 디스크 공간을 차지할 수 있습니다. 주기적으로 사용하지 않는 리소스들을 정리하는 것이 좋습니다.

    - **모든 중지된 컨테이너 삭제**: `docker container prune`

    - **모든 사용하지 않는 이미지 삭제 (태그 유무 상관없음)**: `docker image prune -a`

    - **모든 사용하지 않는 Docker 리소스(컨테이너, 이미지, 네트워크, 볼륨)**: `docker system prune --volumes` (`--volumes` 옵션은 DB 볼륨 데이터도 삭제하므로 주의!)

    - 일반적으로 `docker-compose down -v`와 `docker image prune -a`만으로 충분하며, `docker system prune`은 더 강력한 정리 시 사용합니다.


## 2. `.env` 파일 예시 (Environment Variables Example)

프로젝트의 루트 디렉토리에 `.env` 파일을 생성하고 다음 변수들을 정의합니다. 이 파일은 Git에 커밋되지 않도록 `.gitignore`에 반드시 추가해야 합니다.

```
# Spring Boot 애플리케이션이 데이터베이스에 접속할 때 사용하는 정보
DB_URL=jdbc:mysql://db:3306/kusls_db
DB_USERNAME=kusls_user
DB_PASSWORD=kusls_pass

# JWT (JSON Web Token) 비밀 키
JWT_SECRET=Z/OINAaLvB4OAbtlNvzZCnlElJdYtRnHnAE0mjXKCK8= # 실제 운영 환경에서는 강력하고 무작위적인 키로 변경

# MySQL Docker 컨테이너 초기 설정용 (컨테이너 시작 시 MySQL 서버가 이 값을 사용하여 DB, 사용자 등을 초기화)
MYSQL_ROOT_PASSWORD=your_secure_root_password # MySQL 'root' 계정의 비밀번호
MYSQL_DATABASE=kusls_db                      # 컨테이너 시작 시 자동으로 생성될 DB 이름
MYSQL_USER=kusls_user                        # 컨테이너 시작 시 자동으로 생성될 사용자 이름
MYSQL_PASSWORD=kusls_pass                    # 위 사용자의 비밀번호
```

## 3. Git Commit 컨벤션 (Git Commit Convention)

모든 Git 커밋 메시지는 다음 규칙을 따릅니다. 이는 프로젝트 이력 관리를 용이하게 하고, 팀원 간의 소통을 명확하게 합니다.

```
<타입>: <설명>
```

- **타입 (Type)**:

    - `feat`: 새로운 기능 추가 (Feature)

    - `fix`: 버그 수정 (Bug Fix)

    - `docs`: 문서 수정 (README.md, 주석 등)

    - `style`: 코드 포맷팅, 세미콜론 누락, 공백 등 기능 변화 없는 수정

    - `refactor`: 코드 리팩토링 (기능 변화 없음, 코드 구조 개선)

    - `test`: 테스트 코드 추가 또는 수정

    - `chore`: 빌드, 패키지 매니저 설정, 환경설정 등 기타 변경

- **설명**: 커밋 내용을 간결하고 명확하게 요약합니다.


**예시**:

```
feat: 사용자 회원가입 기능 추가
fix: 로그인 시 비밀번호 불일치 오류 수정
docs: README.md에 커밋 컨벤션 추가
```

## 4. 코드 컨벤션 (Code Convention)

일관된 코드 스타일은 가독성을 높이고 유지보수를 용이하게 합니다.

- **Java (Spring Boot) 코드**:

    - **클래스 및 인터페이스 이름**: 파스칼 케이스(PascalCase)를 사용합니다. 각 단어의 첫 글자를 대문자로 합니다.

        - **예시**: `SignupRequest`, `UserService`, `UserRepository`

    - **메서드 및 변수 이름**: 카멜 케이스(camelCase)를 사용합니다. 첫 단어는 소문자로 시작하고, 이후 각 단어의 첫 글자를 대문자로 합니다.

        - **예시**: `createUser()`, `findByUsername()`, `isMentor`

    - **상수**: 모두 대문자로 표기하며, 단어는 언더스코어(`_`)로 구분합니다.

        - **예시**: `MAX_USERNAME_LENGTH`

- **데이터베이스 (Database) 관련**:

    - **테이블 이름**: 모두 소문자로 표기하며, 여러 단어는 언더스코어(`_`)로 구분하는 스네이크 케이스(snake_case)를 사용합니다. (일반적으로 복수형 사용)

        - **예시**: `users`, `user_profiles`, `community_posts`

    - **컬럼 이름**: 모두 소문자로 표기하며, 여러 단어는 언더스코어(`_`)로 구분하는 스네이크 케이스(snake_case)를 사용합니다.

        - **예시**: `user_id`, `created_at`, `is_mentor`

    - **JPA 매핑**: 엔티티 필드명은 Java 컨벤션을 따르지만, `@Table(name = "테이블명")` 및 `@Column(name = "컬럼명")` 어노테이션을 사용하여 실제 데이터베이스의 스네이크 케이스 테이블/컬럼명과 매핑합니다. (예: `isMentor` 필드를 `is_mentor` 컬럼에 매핑)