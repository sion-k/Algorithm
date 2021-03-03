import sys
input = sys.stdin.readline

# 정답이 n이라 가정할 때, m을 비교했을 때 (s, b)가 맞는지 여부
def judge(n, m, s, b):
    cnt = 0 # 스트라이크 개수 비교
    for i in range(3):
        if n[i] == m[i] : cnt += 1
    if cnt != s : return False
    cnt = 0 # 볼 개수 비교
    for i in range(3):
        for j in range(3):
            if i == j : continue
            if n[i] == m[j] : cnt += 1
    if cnt != b : return False
    return True

N = int(input())
query = []
for i in range(N) : query.append(list(map(int, input().split())))

ret = 0
for n1 in range(1, 10):
    for n2 in range(1, 10):
        if n1 == n2 : continue
        for n3 in range(1, 10):
            if n1 == n3 or n2 == n3 : continue
            ok = True
            for q in query:
                n = str(n1) + str(n2) + str(n3)
                if not judge(n, str(q[0]), q[1], q[2]):
                    ok = False
                    break
            if ok : ret += 1

print(ret)
