import sys
input = sys.stdin.readline
print = sys.stdout.write

class UnionFind:
    # [0, n]개의 각각의 정점으로 초기화
    def __init__(self, n):
        self.parent = [i for i in range(n + 1)]
        self.rank = [0] * (n + 1)

    def union(self, u, v):
        u = self.find(u)
        v = self.find(v)
        if u == v : return
        if self.rank[u] > self.rank[v] : u, v = v, u
        self.parent[u] = v
        if self.rank[u] == self.rank[v] : self.rank[v] += 1

    def find(self, u):
        if self.parent[u] == u : return u
        self.parent[u] = self.find(self.parent[u])
        return self.parent[u]

n, m = map(int, input().split())
uf = UnionFind(n)
for i in range(m):
    c, a, b = map(int, input().split())
    if c == 0 : uf.union(a, b)
    else:
        a = uf.find(a)
        b = uf.find(b)
        print("YES" if a == b else "NO")
        print("\n")
