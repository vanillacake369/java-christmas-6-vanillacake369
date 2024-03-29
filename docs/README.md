# 기능 체크리스트
## 메뉴
- [x] 메뉴판을 구성한다.
  - [x] 메뉴 카테고리에 대한 MenuType을 구성한다.
  - [x] 카테고리와 메뉴명, 가격을 가지는 Menu를 구성한다.
## 입력
- [x] 사용자로부터 예상 방문 날짜를 입력 받는다.
- [x] 입력한 방문 날짜에 대한 검증을 한다.
  - [x] 잘못된 입력인 경우, 다시 입력하게끔 처리한다.
  - [x] 사용자 입력 방문 날짜 검증
    - [x] 방문할 날짜는 1 이상 31 이하의 숫자로만 입력받아 주세요.
    - [x] 1 이상 31 이하의 숫자가 아닌 경우, "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
    - [x] 모든 에러 메시지는 "[ERROR]"로 시작하도록 작성해 주세요.
- [x] 사용자로부터 "주문 형식에 따라" 메뉴명과 메뉴개수를 입력 받는다.
- [x] 입력한 메뉴명과 메뉴 개수에 대한 검증을 한다. 
  - [x] 잘못된 입력인 경우, 다시 입력하게끔 처리한다.
  - [x] 사용자 입력 메뉴 검증
    - [x] 고객이 메뉴판에 없는 메뉴를 입력하는 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 준다.
    - [x] 메뉴의 개수는 1 이상의 숫자만 입력되도록 해주세요. 이외의 입력값은 "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 준다.
    - [x] 메뉴 형식이 예시와 다른 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 준다.
    - [x] 중복 메뉴를 입력한 경우(e.g. 시저샐러드-1,시저샐러드-1), "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여준다.
    - [x] 모든 에러 메시지는 "[ERROR]"로 시작
## 날짜 불변객체 생성
- [x] 날짜 입력에 대해 검증한다.
## 주문메뉴 불변객체 생성
- [x] 주문메뉴 입력에 대해 검증한다.
## 사용자 불변객체 생성
- [x] 날짜와 주문메뉴 입력에 대해 검증한다.
- [x] 사용자 입력 날짜를 통해 입력 요일을 계산한다.
- [x] 날짜,요일을 날짜 불변 객체에 저장한다.
- [x] 사용자 입력으로부터 메뉴와 개수를 파싱한다.
- [x] 메뉴와 개수를 주문 불변 객체에 저장한다.
- [x] 날짜 불변객체와 주문 불변 객체를 통해 사용자 불변객체를 생성한다.
## 할인 적용 판단
- [x] 사용자 입력값에 대해 이벤트 주의 사항에 따라 이벤트를 적용할 지 판별한다.
  - [x] 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
  - [x] 음료만 주문 시, 주문할 수 없습니다.
  - [x] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.  
    (e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)
## 정책 간 분리
- [x] 할인 정책과 이벤트 정책에 대해 인터페이스화를 분리한다.
- [x] 이벤트는 Observer 인터페이스를, 할인은 이 이벤트와 Observer를 extends한다. 이를 통해 할인 정책 또한 하나의 이벤트 정책으로 사용되게끔한다.
## 할인 적용
- [x] 각 이벤트 계획 조건에 따라 적용할 이벤트를 등록/관리한다.
  - [x] 이벤트관리자(Subject)와 이벤트정책의 각 구현체(Observer)에 대한 옵저버 패턴을 구조화한다. 
  - [x] 각 이벤트 정책 구현체 와 각 사용자에 대한 이벤트 조건을 분리한다.
    - [x] 크리스마스 -> 이벤트 기간: 2023.12.1 ~ 2023.12.25
    - [x] 평일 할인 -> (일요일~목요일)
    - [x] 주말 할인 -> (금요일, 토요일)
    - [x] 특별 할인 -> 이벤트 달력에 별이 있으면
    - [x] 증정 이벤트 -> 할인 전 총주문 금액이 12만 원 이상일 때,
  - [x] 각 정책 별 사용자에 대한 이벤트 조건 검증에 따라, 이벤트 구현체(Observer)가 본인을 이벤트관리자의 이벤트리스트에 추가/삭제한다. 
    - [x] 2023.12.1 ~ 2023.12.25 기간 내라면 크리스마스 디데이 할인 적용
    - [x] 일요일~목요일이라면 평일 할인 적용
    - [x] 금요일,토요일이라면 주말 할인 적용
    - [x] 이벤트 달력에 별이 있다면 특별할인 적용
    - [x] 할인 전 총주문 금액이 12만원 이상이라면 증정 이벤트 적용
- [x] 이벤트 관리자의 등록/삭제된 이벤트 리스트들에 대해 이벤트를 적용시킨다.
  - [x] 각 이벤트 별로, 적용된 이벤트들은 본인과 본인의 이벤트 내용을 이벤트 결과 내에 저장한다.
## 이벤트 결과 DTO
- [x] 이벤트 결과 DTO를 생성한다.
  - [x] <증정 메뉴>
  - [x] <혜택 내역>
  - [x] <할인 전 총 주문 금액>
  - [x] <주문 메뉴>
  - [x] <할인 후 예상 결제 금액>
  - [x] <총 혜택 금액>
  - [x] <총 할인 금액>
  - [x] <12월 이벤트 배지> 
- [x] 이벤트 적용이 완료된 후, 이벤트 결과 DTO를 업데이트한다.
## 혜택과 할인에 대한 계산
- [x] 모든 정책에 대해 혜택 내역을 계산한다.
- [x] 모든 정책에 대해 총혜택 금액을 계산한다.
- [x] 할인 정책에 따라 할인 금액만을 계산한다.
- [x] 총 혜택 금액에 따라 12월 이벤트 배지를 부여한다.
  - [x] 배지 선정 역할은 이벤트 배지에게 위임한다. 
- [x] 할인 후 예상 결제 금액을 계산한다.
  - [x] 할인 전 총 주문 금액 - 할인 금액
## 출력
- [x] 주문 메뉴 목록들을 출력한다.
  - [x] 주문 메뉴의 출력 순서는 자유롭게 출력
- [x] 할인 전 총 주문 금액을 출력한다.
- [x] 증정 메뉴를 출력한다.
  - [x] 증정 이벤트에 해당하지 않는 경우, "없음"을 출력한다.
- [x] 혜택 내역을 출력한다.
  - [x] 고객에게 적용된 이벤트 내역만 보여 주세요.
  - [x] 적용된 이벤트가 하나도 없다면 혜택 내역 "없음"으로 보여 주세요.
  - [x] 혜택 내역에 여러 개의 이벤트가 적용된 경우, 출력 순서는 자유롭게 출력.
- [x] 총혜택 금액을 출력한다.
- [x] 증정 메뉴를 출력한다.
  - [x] 증정 이벤트에 해당하지 않는 경우, 증정 메뉴 "없음"을 출력한다.
- [x] 할인 후 예상 결제 금액을 출력한다.
- [x] 12월 이벤트 배지를 출력한다.
  - [x] 이벤트 배지가 부여되지 않는 경우, "없음"을 출력한다.
## 기타 공통 Utils
- [x] 문자열에 대해 "," 을 delimiter 로 하여 각 문자 별로 파싱한다.



# 이런 확장성을 염려하고 구현하였다.
- 모든 매직넘버는 변경될 수 있다.
  > 가령, 12월 이벤트가 아닌, 1월 이벤트로 변경될 수 있다.
  > 이를 위해 가능한 모든 매직넘버는 enum화하거나 도메인 내부에 변수화처리하였다.
- 이벤트나 할인정책은 추가/수정될 수 있다.
  > 이를 위해 EventPolicy를 최상위 인터페이스로 두었고,
  > 옵저버패턴을 사용하여 각각의 정책들이 입력값에 따라 적용될 정책인지를 정할 수 있게 하였다. 
- 메뉴 카테고리나 메뉴가 추가/수정될 수 있다.
  > 이를 위해 enum으로 관리함에 따라 
- 가능한 패키지 간의 결합도와 응집도를 신경쓰였다.
  > 공통의 관심사에 대해서는 하나의 패키지로 묶었다.
- 입력에 대해 불변객체를 사용함으로서 동치성을 보장하였다.
  > 추후 다양한 서비스나 복잡한 비즈니스 로직이 들어오더라도, 불변객체를 사용함으로서 오염방지를 할 수 있게 하였다.
  > 또한 스스로 검증을 함으로서, 입력 이외에도 다른 용도로 사용되는 경우를 고려해주었다.
- 검증에 대해 함수형 인터페이스를 활용하여 유연하게 설계하였다.
  > 최대한 화이트리스팅의 검증을 처리하였고, 
  > 처리해야하는 화이트리스트가 늘어나는 경우를 대비하여 함수형 인터페이스를 활용해주었다.   



# 테스트 체크리스트
- [x] 모든 도메인 별로 로직 테스트를 하였는가?
- [x] 각 레이어 별로 테스트를 하였는가?
- [x] 단위 테스트가 정말 "단위" 테스트인가? 다른 도메인이나 레이어와의 관계는 없는가?
- [x] 통합 테스트가 이루어졌는가?
- [x] 테스트 내부 로직이 합리적인가?
- [x] happy case, unhappy case 모두 검증하였는가?



# 리팩토링 체크리스트
- [x] VO에 대해 세 가지 특성을 지켰는가?
  - [x] 동치성
  - [x] setter 미제공
  - [x] 입력에 대한 검증
- [x] final 클래스들에 대한 객체 생성을 잘 막았는가?
  ```
    public final class utils {
        private utils(){}
    }
  ```
- [x] 디미터 법칙을 의거하고 있는가?
  ```
  void doThis(obj.getList().getAtomicValue()){} // X
  void doThis(obj.getData()){} // O
    
  ```
- [ ] 억지스러운 의존관계는 없는가?
- [ ] final 필드들은 적합한 목적성을 띄고 있는가? 혹은 주입되고 값이 변동없어야하는 필드임에도 final이 아닌 필드가 있는가?
- [ ] EventResultDTO에서 update함수에 의해 값들이 변경될 수 있다. 어색하지 않은가??
  > 필드를 private로 막고, 해당 함수로만 접근할 수 있도록 한 것. 아직 다른 적합한 방법이 떠오르지 않음



# 프로그래밍 요구사항 체크리스트
- [x] JDK 17 버전에서 실행 가능해야 한다. JDK 17에서 정상적으로 동작하지 않을 경우 0점 처리한다.
- [x] 프로그램 실행의 시작점은 Application의 main()이다.
- [x] build.gradle 파일을 변경할 수 없고, 외부 라이브러리를 사용하지 않는다.
- [x] Java 코드 컨벤션 가이드를 준수하며 프로그래밍한다.
- [x] 프로그램 종료 시 System.exit()를 호출하지 않는다.
- [x] 프로그램 구현이 완료되면 ApplicationTest의 모든 테스트가 성공해야 한다. 테스트가 실패할 경우 0점 처리한다.
- [x] 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 이름을 수정하거나 이동하지 않는다.
- [x] indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
- [x] 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
- [x] 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- [x] 3항 연산자를 쓰지 않는다.
- [x] 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- [x] 함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- [x] JUnit 5와 AssertJ를 이용하여 본인이 정리한 기능 목록이 정상 동작함을 테스트 코드로 확인한다.
- [x] else 예약어를 쓰지 않는다.
- [x] 힌트: if 조건절에서 값을 return 하는 방식으로 구현하면 else를 사용하지 않아도 된다.
- [x] else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
- [x] 도메인 로직에 단위 테스트를 구현해야 한다. 단, UI(System.out, System.in, Scanner) 로직은 제외한다.
- [x] 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
- [x] 사용자가 잘못된 값을 입력할 경우 IllegalArgumentException를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
- [x] Exception이 아닌 IllegalArgumentException, IllegalStateException 등과 같은 명확한 유형을 처리한다.