L, K = map(int, input().split())
# (무게, 가치)
S = [()] * (L + 1)
for i in range(1, L + 1) :
    S[i] = tuple(map(int, input().split()))
cache = []
for i in range(L + 1) :
    cache.append([-1] * (K + 1))

def dp(i, capacity) :
    if i > L : return 0
    if cache[i][capacity] != -1 : return cache[i][capacity]
    cand = dp(i + 1, capacity)
    if capacity >= S[i][0] :
        cand = max(cand, S[i][1] + dp(i + 1, capacity - S[i][0]))
    cache[i][capacity] = cand
    return cache[i][capacity]

print(dp(1, K))