# 정점 u에서 v까지의 거리 반환
def diff(u, v) : return abs(u[0] - v[0]) + abs(u[1] - v[1])
def dfs(here):
    if here == n + 1 : return True
    visit[here] = True
    for there in range(n + 2):
        if adj[here][there] and not visit[there]:
            if dfs(there) : return True
    return False
t = int(input())
while t:
    global n, adj, visit
    n = int(input())
    p = [tuple(map(int, input().split())) for i in range(n + 2)]
    adj = [[False] * (n + 2) for i in range(n + 2)]
    for i in range(n + 2):
        for j in range(n + 2):
            adj[i][j] = diff(p[i], p[j]) <= 1000
    visit = [False] * (n + 2)
    print("happy" if dfs(0) else "sad")
    t -= 1
