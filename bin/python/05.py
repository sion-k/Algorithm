class Calculator :
    def __init__(self, a, b):
        # 필드를 함수 내에서 정의할 수가 있다니
        self.u = a
        self.v = b
    # 메소드의 첫번째 매개변수로 인스턴스를 명시해줘야 한다
    def add(self) : return self.u + self.v

a = Calculator(1, 1)
print(a.add())

# UnionFind 구현해보면서 연습
class UnionFind :
    def __init__(self, n):
        self.parent = [i for i in range(n)]
        self.rank = [0] * n

    def __str__(self):
        return str(self.parent)

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

print(UnionFind(10))
# self 쓰는거 너무 불편한데??

# 클래스 변수
# 자바의 static field와 동일하다 자바의 필드처럼 쓰면 무조건 이게됨

# 05-2 모듈
# 함수나 변수 또는 클래스를 모아놓은 파일. 라이브러리인듯
# import는 같은 경로에 있는 모듈 혹은
# 파이썬 라이브러리 디렉터리의 모듈만 불러오기 가능
# import ReversedPrime # 04.py는 불러올 수가 없다. 이름 잘지어야겠네
# print(ReversedPrime.isPrime(3)) 소스 파일 자체를 불러오는건가?
from sys import stdin

# if __name__ == "__main__":의 의미
# import 하자 마자 그냥 그 소스파일이 실행되는 것을 방지하기 위해
# import해서 사용하는 경우는 실행되지 않게 만든다
# __name__은 내부적으로 사용하는 변수 이름
