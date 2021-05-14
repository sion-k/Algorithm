# 07-2 정규 표현식 시작하기
# 문자 클래스 [ ]
# 문자들의 집합, 그냥 이어쓰면 or연산이지만 -로 이으면 범위를 나타냄
a = "[abcd]"
a = "[a-d]"
a = "[a-cd]" # 모두 같은 표현식
dot = "[.]" # 메타문자도 문자 클래스 안에선 문자 자체가됨 (^제외)
# 문자 클래스를 간단하게 표기하는 방법
# 자바와 동일하게 대문자로 쓰면 반대의 의미
decimal = "\d" # "[0-9]"
whitespace = "\s" # "[ \t\n\r\f\v] 문자 클래스에 공백도 가능
# Dot(.)
# 줄바꿈 문자인 \n을 제외한 모든 문자와 매칭 (자바는 포함)

# 반복 {m, n} [m, n]의 범위의 반복을 표현

import re
pattern = re.compile("\d{3}-\d{4}-\d{4}")
matcher = pattern.match("ㄴㄴ10102-4424-1124")
if matcher: # 정규식에 매칭되면 match 객체 반환
    print(matcher.group())
else:
    print("NO")

# match 객체의 메소드
if matcher:
    print("[%d, %d]" % (matcher.start(), matcher.end()))
    print(matcher.span())

# 패턴 객체 생성하지 않고 사용하기
matcher = re.match(".*엄.*준.*식.*", "엄마가준비한식사")
if matcher :
    print(matcher.group())

# 07-3 강력한 정규 표현식의 세계로
# 메타문자
a = "A|B" # 문자열 전체를 반으로 나눠 둘중으로 하나로 매칭
startStr = "^abc" # 문자열 제일 처음을 의미
print(re.match(startStr, "abc"))
print(re.match(startStr, " abc")) # 문자열의 제일 처음 시작이 아니므로

# 그루핑된 문자열 재참조하기
# 동일한 문자열이 연속해서 등장 하는지 확인하는 정규식
# \b는 단어 구분자 \1은 첫번째로 매칭되는 문자
p = re.compile(r"(\w+)\s\1")
# 같은 단어가 여러번 등장하는지 확인하는 정규식
dup = re.compile(r"(\w+).*?\1")
m = dup.match(input())
if m:
    print(m.group(1))