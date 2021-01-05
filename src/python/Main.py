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