N = int(input())
S = [input().split() for i in range(N)]
for i in range(N):
    S[i][0] = int(S[i][0])
    S[i][2] = int(S[i][2])
S = sorted(S)
cnt = 0
R = {}
R["Bessie"] = 7
R["Elsie"] = 7
R["Mildred"] = 7
rep = []
for i, a, b in S:
    R[a] += b
    cmp = 0
    for key in R : cmp = max(cmp, R[key])
    cand = []
    for key in R:
        if R[key] == cmp:
            cand.append(key)
    cand = sorted(cand)
    if rep != cand:
        rep = cand
        cnt += 1
print(cnt)