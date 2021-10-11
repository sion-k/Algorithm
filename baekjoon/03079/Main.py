import sys
input = sys.stdin.readline
N, M = map(int, input().split())
T = [int(input()) for i in range(N)]
def f(x):
    ret = 0
    for t in T : ret += x // t
    return ret
lo, hi = 0, 1000000000000000000
while lo + 1 < hi:
    mid = (lo + hi) // 2
    if f(mid) >= M : hi = mid
    else : lo = mid
print(hi)