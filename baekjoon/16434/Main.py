import sys

input = sys.stdin.readline
N, ATK = map(int, input().split())
S = [tuple(map(int, input().split())) for i in range(N)]


def f(x):
    atk = ATK;
    hp = x
    for i in range(N):
        t, a, h = S[i]
        if t == 1:
            if (hp - 1) // a >= (h - 1) // atk:
                hp -= a * ((h - 1) // atk)
            else:
                return False
        else:
            atk += a; hp = min(hp + h, x)
    return True


lo = 0;
hi = 123455876544123457
while lo + 1 < hi:
    mid = (lo + hi) // 2
    if mid != 0 and f(mid):
        hi = mid
    else:
        lo = mid
print(hi)
