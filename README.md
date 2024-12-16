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

-> Hibernate 가 기본적으로 select 쿼리를 별도로 날리고 있어 -> 무슨 이유에서 일까?  
-> `default_batch_fetch_size` 를 사용해서 IN절로 바꾸기는 함

#### 지연로딩 (`FetchType.LAZY`)

## `N+1` 문제

### IN 즉시로딩 (`FetchType.EAGER`)

### IN 지연로딩 (`FetchType.LAZY`)

### `fetch join`

## JPA 쓰면 좋은 점

### DB 방언이 다 커버됨

### 페이징, 정렬이 매우 쉬움

## 논의해보고 싶은 주제

### 1. DDD에서 Domain vs Entity, 같이 할 순 없을까?

### 2. 영속성컨텍스트의 트랜젝션 commit 처리는 어디에서 되어야 할까? service vs repository  
