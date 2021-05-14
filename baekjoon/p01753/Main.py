import sys, heapq
input = sys.stdin.readline
print = sys.stdout.write
V, E = map(int, input().split())
adj = [[] for i in range(V + 1)]
K = int(input())
for i in range(E):
    u, v, w = map(int, input().split())
    adj[u].append((v, w))
pq = [(0, K)]
dist = [987654321] * (V + 1)
dist[K] = 0
while pq:
    pair = heapq.heappop(pq)
    here, cost = pair[1], pair[0]
    # 더 나은 답을 알고 있다면 무시
    if dist[here] < cost : continue
    for e in adj[here]:
        there = e[0]
        nextCost = cost + e[1]
        if dist[there] > nextCost:
            dist[there] = nextCost
            heapq.heappush(pq, (nextCost, there))
for i in range(1, V + 1):
    print("INF" if dist[i] == 987654321 else str(dist[i]))
    print("\n")
