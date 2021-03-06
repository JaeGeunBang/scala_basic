/*
함수.
: 재사용 가능한 로직의 핵심 구성 요소. 즉, 이름을 가진 재활용 가능한 표현식.
*/

object ch4 {
  def main(args: Array[String]) {
    // 1. 함수
    // def <식별자> = <표현식>
    // 단순히 고정값을 반환하는 함수가 필요하다면 사용함.
    def hi = "Hello"
    // hi로 호출 가능, hi()로 호출 불가능

    // def <식별자>: <output 타입> = <표현식>
    // 반환하는 output 타입을 명시적으로 개자한다면 사용함. 맨 처음 함수랑 차이 없음.
    def hi: String = "Hello"

    // def <식별자>(<식별자>:<input 타입> ...): <output 타입> = <표현식>
    // input, output 타입을 명시적으로 기재한다면 사용함.
    def multiplier(x: Int, y: Int): Int = {x * y}

    // 블록 구성
    def safeTrim(s: String): String = {
      if (s == null) return null
      s.trim() // 마지막 표현식은 함수의 반환 값.
    }

    // 2. 프로시저: 반환 값을 가지지 않는 함수. (ex. println())
    // 반환 타입이 없는 단순 함수이므로, Unit 타입으로 추출될 것. (기본 반환 타입임.)
    def log(d: Double) = println("Hello")
    def log(d: Double) {
      println("Hello")
    }
    
    // 3. 빈 괄호를 가지는 함수
    // 매개변수가 없는 함수를 정의할 때 쓰이는데, 위에서 소개한 함수보다 함수와 값을 분명하게 구분해주기 때문에 더 좋은 방식이다.
    // def <식별자>()[: <타입>] = <표현식>
    def hi(): String = "hello"
    // hi, hi()를 통해 호출 가능

    // 표현식 블록을 통한 함수 호출
    def formatEuro(amt: Double) = "$amt%.2f"
    formatEuro {
      val rate = 1.32
      0.235 + 0.7123 + rate * 5.32
    }

    // 4. 재귀함수
    // 무한 루프가 빠지지 않도록 외부 조건과 함께 호출함.
    // 하지만 아래 재귀함수는 스팩 오버플로우 에러에 빠질 수 있음.
    def power(x: Int, n: Int): Long = {
      if (n >= 1) x * power(x, n-1)
      else 1
    }
    // 재귀함수 최적화 (에노테이션 제공)
    // @annotaion.tailrec
    // 재귀적 호출이 함수의 마지막이어야 에러 발생을 하지 않음
    // 아래 함수는 에러 발생
    @annotaion.tailrec
    def power(x: Int, n: Int): Long = {
      if (n >= 1) x * power(x, n-1)
      else 1
    }
    // 아래와 같이 바꾼다.
    @annotaion.tailrec
    def power(x: Int, n: Int, t: Int = 1): Int = {
      if (n < 1) t
      else power(x, n-1, x*t)
    }
    
    // 5. 중첩함수
    def max(a: Int, b: Int, c: Int) = {
      def max(x: Int, y: Int) {
        if (x > y) x else y
      }
      max(a, max(b, c)) //매개변수가 다르기 때문에 충돌이 발생하지 않음. 매개변수가 똑같다면 지역함수가 외부 함수에 우선적으로 동작함.
    }
    
    // 6. 이름으로 매개변수 지정하기
    // 보통 선언된 매개변수 순서대로 파라미터를 넘기는데, 명시적으로 매개변수를 지정할 수 있다.
    def greet(prefix: String, name: String) = "$prefix $name"
    val greeting2 = greet(name = "brown", prefix = "mr")

    // 7. 기본값을 갖는 매개변수
    // 함수 오버로딩의 좀 더 업데이트된 버전임. 미리 기본값을 가지게 한다.
    def greet(prefix: String = "", name: String) = "$prefix $name"
    val greeting1 = greet(name = "Paul")

    // 8. 가변 매개변수 (가변인수:vargrg)
    // 정해지지 않은 개수의 입력 인수들로 함수를 정의할 수 있다.
    def sum(items: Int*): Int = {
      var total = 0
      for (i <- items)
        total += i
      total
    }

    // 9. 매개변수 그룹
    // 매개변수들을 그룹을 묶어서 이와같이 함수를 선언할 수 있음.
    def max(x: Int) (y: Int) = if( x > y) x else y
    val larger = max(20)(39)

    // 10. 타입 매개변수
    // 자바의 제네릭같은 개념.
    def itentity[A] (a: A): A = a
    val s: String = identity[String]("Hello")
    val d: Double = identity[Double](2.717)
  }
}

