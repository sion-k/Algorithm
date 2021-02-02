M = int(input())
N = int(input())
sqr = [False] * 10001

for i in range(1, 101) : sqr[i * i] = True
S = []
for i in range(M, N + 1) :
    if sqr[i] : S.append(i)

if S :
    print(sum(S))
    print(min(S))
else :
    print(-1)
