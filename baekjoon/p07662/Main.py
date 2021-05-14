import sys, heapq
input = sys.stdin.readline
TC = int(input())
for tc in range(TC):
    K = int(input())
    S = [False] * K # K번째 연산에서 삽입한 원소가 존재하는지 여부
    minh = []
    maxh = []
    for i in range(K):
        o, n = input().split()
        n = int(n)
        if o == 'I': # 원소 삽입
            # (원소의 값, 인덱스[0, k)) 튜플을 삽입
            heapq.heappush(minh, (n, i))
            heapq.heappush(maxh, (-n, i))
            S[i] = True
        else: # 원소 제거
            if n == 1: # 최대
                # maxh에서 실제로 집합에 존재하는 원소를 찾는다
                while maxh and not S[maxh[0][1]] : heapq.heappop(maxh)
                # maxh가 비어있거나 maxh.top이 집합에 존재함
                if maxh:
                    S[maxh[0][1]] = False
                    heapq.heappop(maxh)
            else: # 최소
                # minh에서 실제로 집합에 존재하는 원소를 찾는다
                while minh and not S[minh[0][1]] : heapq.heappop(minh)
                # minh가 비어있거나 minh.top이 집합에 존재함
                if minh:
                    S[minh[0][1]] = False
                    heapq.heappop(minh)
    while maxh and not S[maxh[0][1]]: heapq.heappop(maxh)
    while minh and not S[minh[0][1]]: heapq.heappop(minh)
    if maxh and minh : print(-maxh[0][0], minh[0][0])
    else : print("EMPTY")
