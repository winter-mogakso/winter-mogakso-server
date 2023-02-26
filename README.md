# FAC User server with Spring boot 3
Spring boot 3을 사용해 FAC 대자보의 사용자 서버를 개발하고 있습니다. 현재 프로젝트 구성입니다.

+ openJDK 19
+ Spring boot 3.0.2
+ MongoDB(Docker 테스트 용도, 추후 변경)
+ Docker(Local 환경에서는 신경 쓸 필요 없습니다.)

## Commit message form
+ \<FEAT>: 새로운 기능 추가
+ \<REFACTOR>: 기존 기능 변경
+ \<FIX>: 버그 픽스
+ \<CHORE>: 문서 변경 (ex: README.md, .gitignore, application.yml 등) 

```sh
git commit -m "<FEAT> UserRepository"
git commit -m "<REFACTOR> JWT Authentication logic in the UserService"
git commit -m "<FIX> Can't connect the MySQL"
git commit -m "<CHORE> MySQL account and password in the application.yml"
```

## 환경 구성
application.yml에서 확인하실 수 있습니다.

+ local: 기본 프로필입니다. prefix가 없습니다.
+ dev: 개발환경 프로필입니다. prefix는 /api/user입니다. branch는 development 입니다.
+ prod: 실서비스 프로필입니다. prefix는 /api/user입니다. branch는 release 입니다.

```
// 기본 프로필의 Request 경로
<POST> http://localhost:8080/{원하는 api 경로}

// dev 프로필의 Request 경로
<POST> https://dev.ajou-only-five.shop/api/user/{원하는 api 경로}

// prod 프로필의 Request 경로
<POST> https://www.ajou-only-five.shop/api/user/{원하는 api 경로}
```
