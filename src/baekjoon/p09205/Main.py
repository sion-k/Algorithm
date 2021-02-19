# 정점 u에서 v까지의 거리 반환
def diff(u, v) : return abs(u[0] - v[0]) + abs(u[1] - v[1])
# 플로이드 와셜을 통해 adj[i][j]가 도달 가능한지 여부 계산
def floyd(adj):
    n = len(adj)
    for k in range(n):
        for i in range(n):
            for j in range(n):
                adj[i][j] = adj[i][j] or (adj[i][k] and adj[k][j])
t = int(input())
while t:
    n = int(input())
    # (x, y) 정점
    p = [tuple(map(int, input().split())) for i in range(n + 2)]
    # i번째 정점에서 j번째 정점간의 간선 존재 여부
    adj = [[False] * (n + 2) for i in range(n + 2)]
    for i in range(n + 2):
        for j in range(n + 2):
            adj[i][j] = diff(p[i], p[j]) <= 1000
    floyd(adj)
    print("happy" if adj[0][n + 1] else "sad")
    t -= 1
