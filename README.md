## 공통 컴포넌트

[![Build Status](https://travis-ci.com/suloginscene/common.svg?branch=master)](https://travis-ci.com/suloginscene/common)
[![Coverage Status](https://coveralls.io/repos/github/suloginscene/common/badge.svg?branch=master)](https://coveralls.io/github/suloginscene/common?branch=master)

[회원서버](https://github.com/suloginscene/member-server) 및
[회계서버](https://github.com/suloginscene/accounting-server) 가 의존하는 공통 컴포넌트입니다.

컴포넌트 스캔을 활용한 스프링 자동설정으로 빈을 등록합니다.  
로컬 및 CI 환경에서의 설치는 (git clone 후) mvn install로 이루어집니다.  
운영 환경의 경우, 클라이언트 컴포넌트(서버 애플리케이션)의 도커 이미지에 포함됩니다.

---

### 설정

- 프로퍼티  
  보안 설정을 비롯하여 공통으로 필요한 프로퍼티를 정의합니다.
- 프로파일  
  사용할 프로파일들을 정의하고, 현재 프로파일을 검증하여 테스트용 메서드의 실행을 관리합니다.

### 보안

- JWT  
  io.jsonwebtoken를 래핑하여 간결한 JwtFactory, JwtReader를 사용할 수 있습니다.
- 시큐리티  
  JWT 필터 및 핸들러, CORS 설정, 현재 사용자 참조 애너테이션을 제공합니다.

### 기능

- 메일  
  프로파일에 따라 콘솔 또는 JavaMailSender를 사용하는 Mailer를 등록합니다.
- 예외  
  http 상태 코드별 예외 타입과 핸들러를 제공합니다. 400 오류의 경우 정해진 형식에 따라 응답 본문을 반환합니다.

### 테스트

- 테스트  
  기존 리퀘스트 빌더, Json 파서를 래핑합니다. 의존성의 참조 범위 때문에 테스트 범위에 작성 후 메이븐 빌드 시 포함시켰습니다.
- 이벤트  
  스프링의 ApplicationEventPublisher는 인터페이스인 관계로 SpyBean이 작동하지 않아 래핑하였습니다.

### 기타

- 검증   
  Validator를 구현하는 추상 클래스에서 템플릿메서드 패턴으로 명확한 시그니처를 갖는 추상 메서드를 선언하였습니다.
- 시간  
  날짜와 시간의 Range 타입을 구현하고, 사용할 포매터 및 정규표현식을 정의합니다.
- 문자열  
  쿼리스트링, 캐멀케이스, href 등 문자열 조작에 관한 편의기능을 제공합니다.
