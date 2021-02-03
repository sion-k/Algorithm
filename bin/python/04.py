# 매개변수 지정하여 호출하기
S = [1, 2, 3, 4, 5]

# S[start, end)의 합을 반환
def sum(start, end) :
    ret = 0
    for i in range(start, end) : ret += S[i]
    return ret

print(sum(0, 5))
print(sum(5, 0))
# 호출할 때 매개변수 순서가 맘에 안드면 매개변수 이름 보고 이런식으로 해버릴 수 있겠다
print(sum(end = 5, start = 0))
print()

# 여러 개의 입력값을 받는 함수 만들기
def min2(*args) :
    ret = args[0]
    for i in args :
        if ret > i : ret = i
    return ret

print(min2(1, 2, 3))
print(min2(-1))
print(min2(2147483647, 2147483648))
# print(min2())
# 입력을 아예 없게 해도 길이가 0인 튜플로 인식해서 컴파일 오류는 안나는 것 같다
# 이와 같은 함수 매개변수를 정의할 때는 길이가 0인 인자도 유의해야겠다.

# 키워드 파라미터 kwargs
def min3(**kwargs) :
    for i in kwargs.values() :
        print(i)
# 매개 변수를 딕셔너리로 받을 수 있다는데... 어떻게 써먹냐
min3(a = 1, b = 3)

# 04-2 사용자 입력과 출력
# 문자열 간의 + 연산 지원 안해주는줄 알았는데 매개 변수를 컴마로 띄우면 빈칸을 넣어주네
print("결과는", int(1), "입니다")

# 한 줄에 결과값 출력하기
for i in range(1, 5 + 1) :
    for j in range(i) :
        # 원래는 함수 정의에 end = "\n" 같이 되어있는 것 같다
        print("*", end = " ")
    print()

# 04-3 파일 읽고 쓰기
# 자바의 File과 유사하게 파일 객체를 생성해서 반환
newfile = open("testCase.txt", "w")
newfile.write("10 9 1")
for m in range(0, 9) :
    newfile.write(str(m + 1) + " " + str(m + 2) + "\n")
newfile.close()

import sys
# 명령 프롬프트 창에서 입력한 매개 변수들이 주어진다
sys.argv