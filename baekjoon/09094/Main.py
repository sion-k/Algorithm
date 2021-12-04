import sys
input = sys.stdin.readline
TC = int(input())
def f(a, b, m):
    sum = a ** 2 + b ** 2 + m
    return sum % (a * b) == 0
while TC:
    n, m = map(int, input().split())
    cnt = 0
    for a in range(1, n):
        for b in range(a + 1, n):
            if f(a, b, m):
                cnt += 1
    print(cnt)
    TC -= 1