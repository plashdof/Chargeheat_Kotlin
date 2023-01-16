# 손난로 대여 앱

## 2022 이노씽크 MakerTon / Posco 부문 프로젝트

## (Android 앱 개발 / NodeJS 서버 개발 담당)

 <img src ="https://user-images.githubusercontent.com/86242930/212768731-274624cf-8ca9-418a-a69b-8080642ef511.jpg" width="800" height="600"/>

### 개요

- 탄소절감을 위한 아이디어 기반
- Posco 사원 / The Sharp 아파트 주민이 이용할수있는 하드웨이 및 소프트웨어 개발

### 아이디어

- 일회용 손난로의 낭비를 막기위해 재사용가능한 전기손난로 착안
- 전기손난로를 대여하기 위한 에코포인트 제도 착안
    - 에코포인트 제도를 쌓기위해, 친환경적 활동 독려
    - 쌓은 포인트로 다시 친환경적 활동을 하는 선순환적 구조 제안
- 에코포인트 적립을 관장하는 admin page 관리자대신, 추후에 AI가 도입되어 빅데이터 적립 가능성도 제시

### 설계

- HW : 아두이노를 이용한 사물인터넷 스테이션 대여 서비스.
- SW :  손난로 대여 / 에코포인트 적립 Android 앱 개발
    - 에코포인트 인증샷 전송 기능
    - QR 대여 기능
    - 스테이션 위치찾기 기능
    - 로그인/회원가입/프로필 조회 기능

### 개발기간

- 2023.01.03 ~ 2023.01.13 (약 10일간)

### 구동영상

https://user-images.githubusercontent.com/86242930/212768761-8ebf0c55-ee2c-4bd3-8207-bc7e1f82bbb6.mp4

### 화면구현

**→ Splash / Login Page**

<img src ="https://user-images.githubusercontent.com/86242930/212768773-e65bc4ac-8410-49c4-b288-3c81ab18005a.jpg" width="200" height="400"/>

<img src ="https://user-images.githubusercontent.com/86242930/212768783-c7213c07-54ed-4c83-9dbe-8443f492ab5a.jpg" width="200" height="400"/>

**→ Signup Page**

<img src ="https://user-images.githubusercontent.com/86242930/212768799-2b69f442-c086-4274-86da-522d9f82233a.jpg" width="200" height="400"/>

**→ HomePage** 

<img src ="https://user-images.githubusercontent.com/86242930/212768809-80b85147-479e-4d31-bde6-dccd7e341762.jpg" width="200" height="400"/>

**→ ProfilePage**

<img src ="https://user-images.githubusercontent.com/86242930/212768819-f62fe4ab-8fe2-4504-85ec-3a00f7d6d1eb.jpg" width="200" height="400"/>

**→ EcoPointPage** 

- 카메라로 촬영한 Uri 사진데이터를 Base64String 으로 변환후 서버에 전송
- 서버에서 admin page 에 전송하여, 포인트 적립 승인/거절 입력
- 포인트 적립 승인여부에 따라 포인트 변경

<img src ="https://user-images.githubusercontent.com/86242930/212768823-af09b3b1-89f3-45ed-9871-479ad5f4027f.jpg" width="200" height="400"/>

<img src ="https://user-images.githubusercontent.com/86242930/212768834-47a11518-d9f1-4e7d-9b95-134aa1ffa728.jpg" width="200" height="400"/>

**→ QR Page**  

- Zxing 라이브러리 사용
- 촬영된 QR 혹은 일련번호로 입력시 대여되었다는 데이터 서버로 전송
- 서버에서 아두이노로 대여사실 전송.
- 아두이노가 손난로 거치대 작동시켜서, 사용자에게 손난로를 밀어줌

<img src ="https://user-images.githubusercontent.com/86242930/212769054-05fa3624-4267-4b83-bc51-a4548aec1821.jpg" width="200" height="400"/>

<img src ="https://user-images.githubusercontent.com/86242930/212768848-3e764c5b-db2b-4137-8553-55d31ff59ce0.jpg" width="200" height="400"/>

<img src ="https://user-images.githubusercontent.com/86242930/212768863-5b2bc2e8-c97b-47db-a899-fc7296c102e1.jpg" width="200" height="400"/>
