# java-convenience-store-precourse

<br><br>

## Goal

---

구매자의 할인 혜택과 재고 상황을 고려하여 최종 결제 금액을 계산하고 안내하는 결제 시스템을 구현

<br><br>

## Requirements

---

### 1. User

**입력**

- 구매할 상품과 수량을 입력할 수 있다.
    - [상품명-갯수],[상품명-갯수]
- 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우
    * Y: 증정 받을 수 있는 상품을 추가한다.
    * N: 증정 받을 수 있는 상품을 추가하지 않는다.
- 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우
    - Y: 일부 수량에 대해 정가로 결제한다.

    * N: 정가로 결제해야하는 수량만큼 제외한 후 결제를 진행한다.

* 멤버십 할인 적용 여부를 입력할 수 있다.
    * Y: 멤버십 할인을 적용한다.
    * N: 멤버십 할인을 적용하지 않는다.
* 추가 구매 여부를 입력 할 수 있다.
    * Y: 재고가 업데이트된 상품 목록을 확인 후 추가로 구매를 진행한다.
    * N: 구매를 종료한다.

**출력**

- 환영 인사와 함께 상품명, 가격, 프로모션 이름, 재고를 안내 받는다.
- 만약 재고가 0개라면 재고 없음을 안내 받는다.

<br>

### 2. System

**재고 관리**

- 각 상품의 재고 수량을 고려하여 결제 가능 여부를 확인
- 결제된 수량만큼 해당 상품의 재고에서 차감하여 수량을 관리
- 최신 재고 상태를 유지

**프로모션 할인**

- 프로모션 기간 내에 포함된 경우에만 할인을 적용
- Buy N Get 1 Free의 형태로 진행
- 동일 상품에 여러 프로모션이 적용되지 않는다.
- 프로모션 재고를 우선적으로 차감, 프로모션 재고가 부족할 경우에는 일반 재고를 사용
- 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 필요한 수량을 추가로 가져오면 혜택을 받을 수 있음을 안내한다.
    * 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제하게 됨을 안내한다.

**멤버쉽 할인**

- 멤버십 회원은 프로모션 미적용 금액의 30%를 할인받는다.
    * 프로모션 적용 후 남은 금액에 대해 멤버십 할인을 적용한다.
    * 멤버십 할인의 최대 한도는 8,000원이다.

**영수증 출력**

- 영수증은 고객의 구매 내역과 할인을 요약하여 출력
- 영수증 항목은 아래와 같다.
    * 구매 상품 내역
        * [ ] 구매한 상품명
        * [ ] 수량
        * [ ] 가격
    * 증정 상품 내역
        * [ ] 프로모션에 따라 무료로 제공된 증정 상품의 목록
    * 금액 정보
        * [ ] 총 구매액: 구매한 상품의 총 수량과 총 금액
        * [ ] 행사 할인: 프로모션에 의해 할인된 금액
        * [ ] 멤버십 할인: 멤버십에 의해 추가로 할인된 금액
        * [ ] 내 실 돈: 최종 결제 금액
          -영수증의 구성 요소를 보기 좋게 정렬하여 고객이 쉽게 금액과 수량을 확인할 수 있게 한다.

<br>

### 3. Data

**Promotion List**

| id  | name   | buy | get | start_date | end_date   |
|-----|--------|-----|-----|------------|------------|
| 100 | 탄산2+1  | 2   | 1   | 2024-01-01 | 2024-12-31 |
| 101 | MD추천상품 | 1   | 1   | 2024-01-01 | 2024-12-31 |
| 102 | 반짝할인   | 1   | 1   | 2024-11-01 | 2024-11-30 |

**Products List**

| id | Name   | Price | Quantity | Promotion |
|----|--------|-------|----------|-----------|
| 1  | 콜라     | 1000  | 10       | 100       |
| 2  | 콜라     | 1000  | 10       | -         |
| 3  | 사이다    | 1000  | 8        | 100       |
| 4  | 사이다    | 1200  | 7        | -         |
| 5  | 오렌지주스  | 1800  | 9        | 101       |
| 6  | 탄산수    | 1200  | 5        | 100       |
| 7  | 물      | 500   | 10       | -         |
| 8  | 비타민 워터 | 1500  | 6        | -         |
| 9  | 감자칩    | 1500  | 5        | 102       |
| 10 | 감자칩    | 1500  | 5        | -         |
| 11 | 초코바    | 1200  | 5        | 101       |
| 12 | 초코바    | 1200  | 5        | -         |
| 13 | 에너지바   | 2000  | 5        | -         |
| 14 | 정식도시락  | 6400  | 8        | -         |
| 15 | 컵라면    | 1700  | 1        | 101       |
| 16 | 컵라면    | 1700  | 10       | -         |

<br><br>

## 기능

---

- [x] 재고 저장하기
- [x] 재고 감소하기
- [x] 재고 계산하기
- [x] 사용자 입출력 받기
- [x] 주문 생성하기
- [x] 주문 피드백 받기 및 적용
- [x] 영수증 출력하기
