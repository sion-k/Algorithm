import sys
input = sys.stdin.readline
print = sys.stdout.write

sumCache = [0] * 46

# 1부터 n까지의 합반환
def sum(n) :
    if sumCache[n] != 0 : return sumCache[n]
    sumCache[n] = n * (n + 1) // 2
    return sumCache[n]

cache = [[0] * 4 for i in range(1001)]

# 자연수 k가 n개의 삼각수의 합으로 표현 가능한지 여부
def dp(k, n = 3):
    if n == 0 : return k == 0
    if cache[k][n] != 0 : return cache[k][n]
    i = 1
    while sum(i) <= k :
        if dp(k - sum(i), n - 1):
            cache[k][n] = True
            return True
        i += 1
    cache[k][n] = False
    return False

tc = int(input())
for i in range(tc) : print("1\n" if dp(int(input())) else "0\n")
