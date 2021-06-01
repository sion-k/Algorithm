from itertools import permutations
def f(S):
    sum = 0
    for i in range(len(S) - 1):
        sum += abs(S[i] - S[i + 1])
    return sum
N = int(input())
S = map(int, input().split())
ret = 0
for p in permutations(S, N):
    ret = max(ret, f(p))
print(ret)