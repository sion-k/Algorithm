N = int(input())
S = [ ["@"] * (5 * N) for i in range(5 * N)]
for i in range(2 * N):
    for j in range(N, 4 * N):
        S[i][j] = " "
for i in range(3 * N, 4 * N):
    for j in range(N, 4 * N):
        S[i][j] = " "
for i in range(5 * N):
    for j in range(5 * N):
        print(S[i][j], end = "")
    print()
