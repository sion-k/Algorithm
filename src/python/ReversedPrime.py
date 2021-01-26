# prime[x] (1 <= x <= 100000)
# = x가 소수면 True 아니면 False
prime = [False] * 2 + [True] * 100000
# 에라토스테네스의 체를 이용하여 전처리
for i in range(2, 100001) :
    # i가 소수라면 i * n (2 <= n)은 소수가 아님
    if prime[i] :
        for j in range(i * 2, 100001, i) :
            prime[j] = False

L = int(input())
S = input().split()

# 문자열 x를 뒤집어서 정수로 반환한다. x의 뒤에 붙은 0은 무시한다
def reverse(x) : return int(x[::-1])

# 정수 x가 소수인지 반환
def isPrime(x) : return prime[x]

for x in S :
    x = reverse(x)
    print((str(x) + " ") if isPrime(x) else "", end = "")
