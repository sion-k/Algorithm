N = int(input())
# 남성 목록
S1 = input().split()
# 여성 목록
S2 = input().split()
# 남성들의 여성에 대한 선호도순으로 저장(거꾸로)
P1 = {}
# 여성들의 남성에 대한 선호도순으로 저장(거꾸로)
P2 = {}
for i in range(N):
    line = input().split()
    P1[line[0]] = line[-1:0:-1]
for i in range(N):
    line = input().split()
    P2[line[0]] = line[-1:0:-1]

# 어떤 여성의 현재 후보
cand = {}
# 대응된 남성의 집합
matched = set()
# 남성이 자신이 가장 선호하는 여성에게 대응
for a in S1:
    # 남성 a가 가장 선호하는 여성
    b = P1[a][-1]
    # 여성 b가 가장 선호하던 남성
    pa = cand[b] if b in cand else None 
    # 여성 b가 아직 대응되지 않았거나, a가 더 나은 경우 새로 대응
    if not pa or P2[b].index(a) > P2[b].index(pa):
        cand[b] = a
        # 자리를 뺏긴 남성은 다음 우선순위를 확인
        if pa:
            P1[pa].pop()
            matched.remove(pa)
        matched.add(a)
# 모든 남성이 대응될 때까지 반복
while len(matched) < N:
    # 대응되지 않은 남성을 찾는다
    for a in S1:
        if a not in matched:
            # 남성 a가 그 다음으로 가장 선호하는 여성
            b = P1[a][-1]
            # 여성 b가 가장 선호하던 남성
            pa = cand[b] if b in cand else None 
            # 여성 b가 아직 대응되지 않았거나, a가 더 나은 경우 새로 대응
            if not pa or P2[b].index(a) > P2[b].index(pa):
                cand[b] = a
                # 자리를 뺏긴 남성은 다음 우선순위를 확인
                if pa:
                    P1[pa].pop()
                    matched.remove(pa)
                matched.add(a)
                break
            # a가 대응되지 못했다면 우선순위를 또 이동
            else:
                P1[a].pop()
for b in S2 : print("%s %s" % (cand[b], b))