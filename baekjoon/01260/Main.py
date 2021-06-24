# DFS와 BFS
import sys
from collections import deque
input = sys.stdin.readline

L, M, V = list(map(int, input().split()))
adj = [set() for i in range(L + 1)]

for i in range(M):
    u, v = map(int, input().split())
    adj[u].add(v)
    adj[v].add(u)

# dfs
visit = [False] * (L + 1)
stack = []
stack.append(V)
dfs = []
while stack :
    here = stack.pop()
    if visit[here] : continue
    dfs.append(here)
    for there in adj[here] :
        stack.append(there)
    visit[here] = True

print(' '.join(dfs))

# bfs
visit = [False] * L
queue = deque([V-1])
visit[V - 1] = True
bfs = []
while len(queue)!=0:
    here = queue.popleft()
    bfs.append(str(here + 1))
    link = deque(adj[here].copy())
    while len(link)!=0:
        index = link.popleft()
        if visit[index]:
            continue
        queue.append(index)
        visit[index] = True
    visit[here] = True
print(' '.join(bfs))
