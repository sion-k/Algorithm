a = 10e-10
b = 10e10
print(a * b)
# 내부적으로는 변수들에 자료형이 존재하는 것 같다

a = 0b10101 # 리터럴도 존재하고
b = 0b01010
print(a | b) # : 31; 비트 연산자도 자바랑 똑같네
print(2 ** 2 ** 2 ** 2 ** 2) # pow 연산자도 오른쪽부터 결합한다

# 02-2 문자열 자료형
"abc"
'abc'
"""abc"""
'''abc'''
food = "apple's" # 이러한 갖가지 방법들로 이스케이프 문자를 대체가능
print(food)
food = "apple\'s" # 난 이게 더 편할거 같은데
print(food)
food = """You
Only
Live
Once
"""
# 문자열 상수 내에 줄내림을 넣고 싶으면 이렇게도 가능하네
# 생각해보니까 줄내림 없는 print 함수는 없나
print("\a") # 이런건 왜만든거야 소리도 안나는데
print("\000") # Null 문자 아무것도 안보인다

food = "123"
food = food * 2 # Concatenate 연산 시간 복잡도 O(N^2)인가? -> O(N)
print(food)
food = "012345"
print(food[5])
print(food[-1]) # 음수 인덱스 나와도 런타임 에러가 안뜨겠네;

# 문자열 포매팅
a = "start"
b = "goal"
print("here : %s next : %s" % (a , b))
a = 3.14159265
print("%0.4f" % (a)) # 5번째 자리에서 반올림 해서 표시했다 "%.4f"도 가능

# 문자열 관련 함수들
# 문제 개수 세기 count()
print("stewardess".count("e"))
# 위치 알려주기 1
print("abcd".find("a"))
# 위치 알려주기 2
# 아마도 list에있는 함수인데 문자열에도 쓸 수 있는거보니 같은 인터페이스 구현했나?
print("abcd".index("c"))
# 문자열 삽입
print(":".join(("1", "2", "3", "4")))

# Java trim -> strip
print("   113rd   ".strip())
print("갓자바".replace("자바", "파이썬")) # 시간 복잡도 O(N)이겠지

# 문자열 나누기
print("1 2 3  4 5   6 7 8     9".split())

# 02-3 리스트 자료형
a = [1, 2, 3]
print(a)
a = list()
print(a)
a.append(1)
print(a) # 자바의 List와 유사해 보인다
# 자바는 String과 다른 자료형의 연산을 모두 String으로 캐스팅해줬는데
# 파이썬은 명시적으로 해야한다
# print(a[0] + "1")
print(str(a[0]) + "1")

# del 객체 함수 (가비지 컬렉터같은 역할인가?)
a = [1, 2, 3, 4, 5]
del a[0:2] # 인덱스 슬라이싱이 부분 배열 객체를 새로 만드는게 아닌가보다
# 이 연산의 시간 복잡도는 최대 O(N)으로 내부적으로 연결 리스트인듯?
print(a)
a.append(6) # 원소 추가, 리스트의 원소는 하나로 정해지지 않아서 뭐든지 가능
a.append([2, 1])
print(a)
a.pop()
a.reverse()
a.sort() # Tim sort (최악의 경우 NlgN) 리스트 내의 원소가 모두 같은 종류여야함
print(a)
# remove(x) 첫번째로 등장하는 x를 제거, 없으면 오류 뜨네
a.remove(5)
print(a)

# 02-4 튜플 자료형
# immutable한 리스트인듯? 상수 리스트, ( )으로 둘러싼다
1, 2, 3 # 괄호 없이도 가능
(1, ) # 한개 짜리여도 콤마 생략 불가

# 02-5 딕셔너리 자료형
# 자바의 Map 인터페이스인듯 해싱 자료구조
ballon = {}
ballon[(1, 2)] = 1 # Key는 Immutable해야하니까 튜플로하면 가능하네
print(ballon)
a = 1
b = 2
ballon[(a, b, 3)] = 2 # key 형식이 전부 같지 않아도 되네?
print(ballon)
del ballon[(1, 2, 3)]
print(ballon)
print(ballon.keys()) # dict_keys라는 객체를 돌려준다
print(ballon.values()) # value도 마찬가지
print(ballon.get((1, 2))) # ballon[(1, 2)]와 마찬가지
# 단 해당 Key가 존재하지 않으면 이 방법은 None을 반환하는데
# 배열 인덱싱 방식으로 하면 Key오류 발생
# 그럴 땐 Key값이 없을 경우 디폴트를 가져오는 이걸 사용하자
print(ballon.get((1, 2, 3), -1))

# 02-6 집합 자료형
print({1, 2, 3})
# 문자열을 집합의 인자로 할 수 있네?
print(set("Algorithm")) # 내부적인 순서는 없는듯?
# { } 리터럴은 딕셔너리형에서 사용해가지고 빈 집합 만들라면
# 아닌데 ? { }이것도 set 리터럴이 맞고 dictionary가 그거를 응용하는듯 하다
s = set()
print(s) # 빈 집합 출력하면 set()이라고나오네?
print(set([1, 2, 3]) - set([1, 2]))
s = set([1, 2, 3])
s.remove(3) # 없는걸 제거하려면 마찬가지로 오류

# 02-7 불 자료형
# 특이하게 예약어가 True와 False이다

# 자료형의 불린값 반환
# 빈 문자열이나 빈 리스트는 False
a = [1, 2, 3, 4]
while a: # 이런식으로 응용 가능하다
    print(a.pop())

print(bool("")) # bool() 함수로 True False를 알 수 있음

# 02-8 자료형의 값을 저장하는 공간, 변수
print(id("abcd"))
s = "abc" + "d"
print(id(s))
# 메모리 주소를 반환하는 함수, 특이하게 10진수다
# 예상했던대로 같은 문자열 풀에 들어가있다

# 동일한 주소인지 확인해주는 명령어 is
print("abcd" is s)

# 파이썬의 깊은 복사
a = [1, 2, 3]
b = a[:] # 인덱싱 방법을 사용하는 법
print(a)
print(b)
print(a is b)
print(str(id(a)) + " " + str(id(b)))
# copy 모듈 이용
from copy import copy # import를 중간에 해도 돼? ㅁㅊ
b = copy(a) # clone()하고 똑같은거 같은데 깊은 복사인가?
a = [1, [1, 2]]
b = copy(a)
print(b)
print(str(id(a)) + " " + str(id(b))) # 재귀적으로 깊은 복사 해놓은듯?

# 변수를 만드는 여러가지 방법
a, b = 1, 2
a += 1
print(a) # 아니 튜플은 못바꾼다면서요...?
# 찾아보니까 불변한 "순서"를 가지는 객체의 집합이라고 함
b += 2
a, b = b, a # 이런 기상천외한 짓도 가능
# 튜플을 일시적으로 생성해서 값을 대입하는 식인듯
print(a)
print(b)

# Q6
s = [1, 3, 5, 4, 2]
s.sort()
s.reverse()
print(s)

# Q7
print(" ".join(["Life", "is", "too", "short"]))

# Q8
print((1, 2, 3) + (4,))

# Q9
a = {} # 빈 딕셔너리
print(type(a))
a[1,] = "python" # 이렇게 하면 딕셔너리인줄 알아먹는건가?
print(a[1,])
print(a)

# Q10
a = {"A" : 90, "B" : 80, "C" : 70}
a.pop("B")

# 03-1 if문
money = {}
if money:
    print("!")

# x in s, x not in s
print(1 in [1, 2, 3])
# 리스트나 튜플 문자열에 대해서 사용가능하다 특이하네
print(1 not in [1, 2, 3])

# 조건부 표현식 (자바의 삼항 연산자같은 느낌)
# 조건문이 참인 경우 if 조건문 else 조건문이 거짓인 경우
a = None
print(1 if a else 2) # 신기하다

# 03-3 for문
# range함수 : range(10) [0, 10) 구간을 만들어주는 객체 생성
sum = 0
for i in range(1, 11): # 가끔씩은 닫힌 구간도 사용하고 싶은데...
    sum += i
print(sum)

# 리스트 내포 사용하기
# 표현식 for 항목 in iterable (if 조건문)
a = [1, 2, 3]
result = [num * 3 for num in a if num % 2 == 0]
print(result)

# Q1
a = "Life is too short, you need python"

if "wife" in a: print("wife")
elif "python" in a and "you" not in a: print("python")
elif "shirt" not in a: print("shirt")
elif "need" in a: print("need")
else: print("none")

# Q2
sum = 0
i = 1
while i <= 1000:
    if i % 3 == 0:
        sum += i
    i += 1
print(sum)

# Q3
i = 1
while i <= 5:
    print("*" * i)
    i += 1

# Q4
for i in range(1, 101):
    print(i)

# Q5
s = [70, 60, 55, 75, 95, 90, 80, 80, 85, 100]
sum = 0
for i in s:
    sum += i
print("%0.2f" % (sum / 10))

# Q6
numbers = [1, 2, 3, 4, 5]
result = [num * 2 for num in numbers if num % 2 == 1]
print(result)

# 04-1 함수
# 매개변수에 초기값 미리 설정하기
# 단 초기값을 설정할 매개 변수는 맨 뒤에 있어야 함
def add(a, b, p = True):
    if p : return a + b
print(add(1, 2, True))
print(add(1, 2, False))
print(add(1, 2)) # 재정의 대신에 이렇게 매개변수 생략 가능하게 하네

# lambda식
add = lambda a, b : a + b
# 함수형 프로그래밍이구나
print(add(3, 4))