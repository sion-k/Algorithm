class Calculator :
    def __init__(self, a, b):
        # 필드를 함수 내에서 정의할 수가 있다니
        self.u = a
        self.v = b
    # 메소드의 첫번째 매개변수로 인스턴스를 명시해줘야 한다
    def add(self) : return self.u + self.v

a = Calculator(1, 1)
print(a.add())

class UnionFind :
    def __init__(self, n):
        self.parent = [i for i in range(n)]
        self.rank = [0] * n

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
        return self.parnet[u]

set = UnionFind(10)
print(set.parent)
print(set.rank)