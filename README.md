# 🎈 ***Spring_Security Login SignUp Page Project***


## 🔊 프로젝트 소개
웹개발을 하면서 가장 기본이 되는건 로그인/회원가입을 구현하는 것이라고 생각했습니다. 기능을 직접 구현하려 했으나 좋은 보안 프레임워크인 ***SpringSecurity***가 있다는 것을 알았기에 이를 활용하여 구현해보았습니다.


## 🎨 Dependencies
- *Spring Web*
- *Spring Security*
- *Spring Data JPA*
- *Mustache*
- *MySQL Driver*
- *Lombok*


## 💻 개발환경
- **Version** : Java 17
- **IDE** : IntelliJ
- **Framework** : SpringBoot 3.4.3
- **Database** : MySQL 8.0

## 📖 파일 구조
    loginProject/
    ├── config/
    │   └── SpringSecurity : 스프링시큐리티 Config
    ├── controller/
    │   └── HomeController : 로그인/회원가입 컨트롤러
    ├── dto/
    │   └── UserForm : 회원가입 DTO
    ├── entity/
    │   └── User : 회원가입 Entity
    ├── handler/
    │   ├── CustomAuthenticationFailureHandler : 로그인 인증실패 커스텀 핸들러
    │   └── CustomLogoutSuccessHandler : 로그아웃 성공 커스텀 핸들러
    ├── repository/
    │   └── UserRepository : 회원가입 Repository
    ├── service/
    │   ├── CustomUserDetailService : 커스텀 UserDetailService 
    │   └── UserService : 회원가입 서비스 계층
    └── LoginProjectApplication


## 🔒 ***Spring Security*** 구조
![Spring Security구조](https://github.com/user-attachments/assets/4e3f0d77-69b9-46a8-9adf-4c23fccce04a)

    1. 사용자 인증 요청
    
    2. 사용자 자격 증명 (아이디, 패스워드) 기반으로 Authentication Token 생성

    3. 인증을 위해 AuthenticationManager에 Authentication Token을 전달받아, authenticate 메소드를 호출

    4. AuthenticationManager를 구현한 AuthenticationProvider에서 실제 인증 처리를 진행.

    5. 비밀번호 암호화 인터페이스, 패스워드 비교

    6 ~ 8. 사용자 정보 인터페이스, DB에서 사용자 정보를 반환

    9 ~ 10. 인증 및 사용자 정보가 포함된 Authentication을 반환

    11. SecurityContext에 Authentication 저장 후 인증 완료 처리

    
## 📺 주요 기능
![image](https://github.com/user-attachments/assets/47c51a35-52b6-41d9-ba92-4b651bd679d8)

- 회원가입
  - 닉네임, 이메일, 비밀번호를 입력 후, 회원가입 버튼 클릭
  - 컨트롤러 ***PostMapping*** 을 통해 ***userService***의 ***save method***에서 이메일 검사
  - 검사 후, 알맞은 ***Alert Mesaage*** 와 함께 로그인 페이지로 ***redirect***

- 로그인
  - 이메일, 비밀번호를 입력 후, 로그인 버튼 클릭
  - ***Spring Security*** 에서 인증과정을 거치고 거침
  - 성공 시, 메인 페이지로 ***redirect*** 실패 시, ***CustomAuthenticationFailureHandler*** 로 인해 다시 로그인 페이지로 ***redirect***

 - 로그아웃
   - 로그인 성공 후, 로그아웃 버튼을 클릭
   - ***Spring Security*** 에서 자동으로 세션과 인증정보를 삭제, 무효화

## ✏ 후기
내가 무엇을 하고싶은지 몰랐지만 어느순간 백엔드에 내가 관심이 있다는 것을 알게 되었습니다. 

백엔드를 구현하는데 많은 프레임워크들이 있지만 그중에서 *SpringBoot*가 생각났기에 책을 구매해 하나씩 따라해보다가 
작은 프로젝트를 한번 해보자는 마음에 시작한 별거아닌 프로젝트이지만, 새로운 것을 알게되고 기억 속에서 잊어버린 *Java*에 지식이 살아나고 코딩이 잘 되지 않을때의 분노와 해결했을 때의 쾌감을 오래간만에 느낄 수 있어서 재밌있었습니다.

~~CSRF 토큰 때문에 3시간동안 끙끙된건 비밀~~

## 🏷 출처
- [HTML/CSS/JS](https://inpa.tistory.com/entry/CSS-%F0%9F%92%8D-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85-%ED%8E%98%EC%9D%B4%EC%A7%80-%EC%8A%A4%ED%83%80%EC%9D%BC-%F0%9F%96%8C%EF%B8%8F-%EB%AA%A8%EC%9D%8C)
- [***Spring Security*** 구조](https://bravenamme.github.io/2019/08/01/spring-security-start/)
