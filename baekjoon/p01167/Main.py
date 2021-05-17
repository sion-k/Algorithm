import sys
input = sys.stdin.readline
V = int(input())
adj = [[] for i in range(V + 1)]
for i in range(V):
    line = list(map(int, input().rstrip().split()))
    u = line[0]
    j = 1
    while line[j] != -1:
        adj[u].append((line[j], line[j + 1]))
        j += 2

# here에서 (가장 먼 정점, 그 정점까지의 거리) 반환
def postorder(here, parent):
    ret = [here, 0]
    for (there, weight) in adj[here]:
        if there == parent : continue
        cand = postorder(there, here)
        cand[1] += weight
        if cand[1] > ret[1] : ret = cand
    return ret

print(postorder(postorder(1, 0)[0], 0)[1])
