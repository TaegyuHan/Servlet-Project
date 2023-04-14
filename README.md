# Servlet Project

## 프로젝트를 진행한 이유
- 데이터베이스 구조의 이해와 서버 개발에 대한 이해를 쌓기 위해서였습니다. 데이터베이스를 효과적으로 활용하고 데이터를 안전하게 관리하기 위한 방법을 배우고자 했습니다.


- 소프트웨어 아키텍처를 이해하고 적용하는 것을 연습하기 위해서였습니다. 소프트웨어 개발 시 구조와 설계에 대한 이해가 필수적이라고 생각했습니다. 따라서, MVC 패턴을 적용하며 소프트웨어 아키텍처를 학습하고자 했습니다.

## 이번 프로젝트에서 얻고자 하는 목표

- 톰캣, Servlet 연동: 서블릿과 톰캣의 동작 원리를 이해하고, 서블릿을 활용해 API 요청을 처리하는 방법을 익히고자 했습니다.


- Servlet API 구현: 서블릿 API를 직접 구현하며, 웹 어플리케이션 개발에 필요한 기본 지식을 습득하고자 했습니다.


- JDBC 사용방법 숙달: 자바에서 데이터베이스를 연동하기 위한 JDBC API를 학습하고, 데이터베이스에 대한 기본적인 CRUD(Create, Read, Update, Delete) 기능을 구현해보며 JDBC 사용 방법을 익히고자 했습니다.


- DTO, DAO 구현하기: DTO(Data Transfer Object)와 DAO(Data Access Object) 패턴을 이해하고, 실제로 적용해보며 객체 지향 프로그래밍의 장점과 개발 효율성을 높이는 방법을 익히고자 했습니다.


- 테스트 코드 작성하기 (Mock, Junit): 자동화된 테스트 코드를 작성해보며, 버그를 미리 발견하고 수정하는 방법을 학습하고자 했습니다.


- MVC 패턴 적용시켜 보기 (Controller, Service 구현): MVC(Model-View-Controller) 패턴을 이해하고, 서비스 로직과 컨트롤러를 분리하여 개발 효율성을 높이는 방법을 익히고자 했습니다.

## 프로젝트 구조 및 설명
프로젝트 구조는 아파치 톰캣으로 API 요청을 받아 Java Servlet을 호출하고 Servlet Filter를 이용해서 호출된 API의 데이터를 확인하는 작업을 진행했습니다. 이후 받은 데이터를 Service로직을 태운 뒤 DAO에서 JDBC를 이용해 MariaDB에 저장하는 구조를 만들어 진행했습니다.


## 기술 및 라이브러리
- OS: Windows 11 Pro > version (22H2)
- IDE: IntelliJ > version 2021.2.1
- Server: Apache Tomcat > version 9.0.73
- DB: MariaDB > version 10.8.3-MariaDB-1:10.8.3+maria~jammy

### Language: Java > version 17.0.1
Library:
- javax.servlet-api: 4.0.1: 
  - Java Servlet API를 구현하기 위한 라이브러리입니다.
- org.mariadb.jdbc:mariadb-java-client: 3.1.3: 
  - MariaDB 데이터베이스와 Java를 연동하기 위한 JDBC 드라이버 라이브러리입니다.
- com.zaxxer:HikariCP: 5.0.1: 
  - 데이터베이스 커넥션 풀링 라이브러리입니다. DB 커넥션을 관리하는 데 사용됩니다.
- org.junit:junit5-engine: 
  - 5.0.0-ALPHA: JUnit5 프레임워크의 엔진 라이브러리입니다. 자동화된 테스트를 작성하는 데 사용됩니다.
- org.junit.jupiter:junit-jupiter: 5.9.2 (test scope): 
  - JUnit5 프레임워크의 확장 API 라이브러리입니다. 테스트 코드 작성 시 활용됩니다.
- org.mockito:mockito-core: 5.2.0 (test scope): 
  - 자바용 mocking 프레임워크로 테스트 코드 작성 시에 가짜 객체(mock object)를 만들어 사용할 수 있습니다.
- org.json:json: 20230227: 
  - JSON(JavaScript Object Notation) 파싱을 위한 라이브러리입니다.
- org.slf4j:slf4j-api: 1.7.32: 
  - 간단한 로깅 API로, 로깅 프레임워크를 사용하기 전에 먼저 익히는 것을 추천합니다.
- ch.qos.logback:logback-classic: 1.4.6: 
  - 로깅을 위한 프레임워크 중 하나로, SLF4J API를 사용하는 라이브러리입니다.