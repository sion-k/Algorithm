# 1부터 n까지의 합반환
def sum(n) : return n * (n + 1) // 2

# 자연수 k가 n개의 삼각수의 합으로 표현 가능한지 여부
def check(k, n):
    if n == 0 : return k == 0
    i = 1
    while sum(i) <= k :
        if check(k - sum(i), n - 1) : return True
        i += 1
    return False

tc = int(input())
for i in range(tc) : print("1" if check(int(input()), 3) else "0")
