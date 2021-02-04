def inc(E, S, M):
    E = (E + 1) % 16
    if E == 0 : E += 1
    S = (S + 1) % 29
    if S == 0 : S += 1
    M = (M + 1) % 20
    if M == 0 : M += 1
    return E, S, M

E, S, M = map(int, input().split())
a, b, c = 1, 1, 1
for i in range(1, 7981):
    if a == E and b == S and c == M : break
    a, b, c = inc(a, b, c)
print(i)
