# JPA 톺아보기

## 영속성 컨텍스트

### 엔티티 생명주기
* 비영속 `new`
* 영속 `persist`
* 준영속 `detach`
* 삭제 `remove`
* 
### 쓰기 지연
영속성 컨텍스트에서 데이터베이스로의 변경 사항을 즉시 실행하지 않고, 트랜잭션 커밋 시점에 한 번에 처리
* `flush` : 영속성 컨텍스트에서 DB로 반영
* `clear` : 영속성 컨텍스트를 비움
#### example
* `/create-member`
* `/create-member-with-flush`
* `/create-member-with-flush-and-clear`

### 동일성보장 / 1차 캐시

## 연관관계 매핑
### ManyToOne, OneToMany
#### 연관관계의 주인 (`mappedBy`)

### FetchType
#### 즉시로딩 (`FetchType.EAGER`)
#### 지연로딩 (`FetchType.LAZY`)

## `N+1` 문제
### IN 즉시로딩 (`FetchType.EAGER`)
### IN 지연로딩 (`FetchType.LAZY`)
### `fetch join`

## JPA 쓰면 좋은 점
