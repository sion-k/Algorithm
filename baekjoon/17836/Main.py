import heapq

dyx = [ [ -1, 0 ], [ 1, 0 ], [ 0, -1 ], [ 0, 1 ] ]

N, M, T = map(int, input().split())
S = [input().split() for i in range(N)]

# (y, x)에서 (N - 1, M - 1)까지의 맨해튼 거리
def h(y, x) : return abs(N - y - 1) + abs(M - x - 1)

def inRange(y, x) : return 0 <= y < N and 0 <= x < M

# a-star
# f = g + h, (f, g, y, x)
pq = []
heapq.heappush(pq, (0, 0, 0, 0))
dist = [ [987654321] * M for i in range(N) ]
dist[0][0] = 0
ret = 987654321
while pq:
    f, g, y, x = heapq.heappop(pq)
    if S[y][x] == "2":
        ret = min(ret, g + h(y, x))
        continue
    if y == N - 1 and x == M - 1:
        ret = min(ret, g)
        break
    for dy, dx in dyx:
        ny, nx = y + dy, x + dx
        if not inRange(ny, nx) or S[ny][nx] == "1": continue
        ng = g + 1
        nf = ng + h(ny, nx)
        if dist[ny][nx] > dist[y][x]:
            dist[ny][nx] = dist[y][x]
            heapq.heappush(pq, (nf, ng, ny, nx))
print("Fail" if ret > T else ret)