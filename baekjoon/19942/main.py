from itertools import combinations
n = int(input())
mp, mf, ms, mv = map(int, input().split())
s = [list(map(int, input().split())) for i in range(n)]

INF = 987654321

def price(cand):
    t = [0] * 5
    for i in cand:
        for j in range(5):
            t[j] += s[i][j]
    if t[0] >= mp and t[1] >= mf and t[2] >= ms and t[3] >= mv:
        return t[4]
    else:
        return INF

ret = INF
best = []
subset = []
for size in range(1, n + 1):
    for comb in combinations(range(n), size):
        subset.append(comb)
subset = sorted(subset)
for sub in subset:
    cand = price(sub)
    if ret > cand:
        ret = cand
        best = sub
if ret == INF:
    print(-1)
else:
    print(ret)
    for x in best:
        print(x + 1, end=" ")
