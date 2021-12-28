N, K = map(int, input().split())
P = []
for i in range(K):
    u, v = map(int, input().split())
    if u > v:
        temp = u
        u = v
        v = temp
    P.append((u, v))
check = [False] * K
P = sorted(P, key = lambda x : x[1])
ret = 1
for i in range(len(P)):
    if not check[i]:
        ret += 1
        for j in range(i + 1, len(P)):
            if P[j][0] < P[i][1]:
                check[j] = True
print(ret)