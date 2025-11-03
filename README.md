# 우아한테크코스 웹 백엔드 8기 3주차 프리코스

> 간단한 로또 발매기를 구현한다.

## 1. 기능 요구사항

- 로또 번호의 숫자 범위는 1~45까지이다. 
- 1개의 로또를 발행할 때 중복되지 않는 6개의 숫자를 뽑는다. 
- 당첨 번호 추첨 시 중복되지 않는 숫자 6개와 보너스 번호 1개를 뽑는다. 
- 당첨은 1등부터 5등까지 있다. 당첨 기준과 금액은 아래와 같다. 
  - 1등: 6개 번호 일치 / 2,000,000,000원
  - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원 
  - 3등: 5개 번호 일치 / 1,500,000원 
  - 4등: 4개 번호 일치 / 50,000원 
  - 5등: 3개 번호 일치 / 5,000원
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 만큼 로또를 발행해야 한다. 
- 로또 1장의 가격은 1,000원이다. 
- 당첨 번호와 보너스 번호를 입력받는다.
- 사용자가 구매한 로또 번호와 당첨 번호를 비교하여 당첨 내역 및 수익률을 출력하고 로또 게임을 종료한다. 
- 사용자가 잘못된 값을 입력할 경우 IllegalArgumentException을 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다. 
  - Exception이 아닌 IllegalArgumentException, IllegalStateException 등과 같은 명확한 유형을 처리한다.

## 2. 주요 기능

### -1. 입출력

- 입력고 출력을 책임지는 InputView, OutputView 구현

### -2. 검증 객체

- Validator를 통해 각 입력값에 검증을 진행하고, 도메인에 요구하는 객체의 속성으로 변환

### -3. 컬렉션 내재화 (Cars)

- 컬렉션(List)를 클래스로 감싸 캡슐화를 통해 의미 있는 객체로 사용하도록 구현

### -4. 도메인 서비스 

- 여러 도메인에 걸쳐 적용되는 로직에 대해 행위를 정의할 수 있도록 구현

### -5. BigDecimal 적용

- 당첨금액 / 구입 금액에 대해 BigDecimal 적용

### -5. Singleton Pattern 적용

- AppConfig 클래스를 통해 Controller와 Service 등 공유가 필요한 객체들은 외부 주입을 통해 단 하나의 인스턴스 보장

### -6. 사용자가 잘못된 입력할 경우, 해당 부분에서 재시도

- 각 입력값에 대해 검증을 시도하고, 올바르지 않은 값에 대해 예외 메시지 출력 후 재입력 로직

## 3. 예외 처리

### -1. 중복된 로또 번호가 존재할 경우

### -2. 로또 번호가 6개보다 많거나 적을 경우

### -3. 올바르지 않은 숫자를 입력할 경우 (빈 문자열 혹은 Null)

### -4. 로또 티켓에 로또 번호가 없을 경우

### -5. 보너스 번호가 당첨 번호와 중복되어 있을 경우

### -6. 로또 번호가 1과 45 사이에 존재하지 않을 경우

### -7. 로또 구입 금액이 1000원 단위가 아닐 경우

## 4. 프로젝트 구조

```aiexclude

lotto
├── config
│   └── AppConfig
│
├── controller
│   ├── validation
│   │   ├── AmountValidator
│   │   ├── BonusNumberValidator
│   │   └── WinningNumberValidator
│   └── LottoController
│
├── domain
│   ├── generator
│   │   ├── LottoNumberGenerator
│   │   └── LottoNumberGeneratorImpl
│   │
│   ├── service
│   │   ├── IncomeRateCalculator
│   │   ├── LottoTicketCalculator
│   │   └── WinningLottoCalculator
│   │
│   ├── Lotto
│   ├── LottoRank
│   ├── LottoResult
│   ├── LottoTicket
│   └── WinningLotto
│
├── service
│   └── LottoService
│
├── util
│   ├── ErrorMessages
│   └── LottoConstants
│
├── view
│   ├── InputView
│   └── OutputView
│
└── Application

```
