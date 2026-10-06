import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)

n, m, r = map(int, input().split())

adj = [[] for i in range(n + 1)]

for i in range(m):
    u, v = map(int, input().split())

    adj[u].append(v)
    adj[v].append(u)

for i in range(1, n + 1):
    adj[i].sort()
    adj[i].reverse()

stack = [r]
visit = [False] * (n + 1)
traversal = [0] * (n + 1)
timer = 1

while stack:
    here = stack.pop()
    if visit[here]:
        continue

    visit[here] = True
    traversal[here] = timer
    timer += 1

    for there in adj[here]:
        if not visit[there]:
            stack.append(there)

print(*traversal[1:], sep="\n")
