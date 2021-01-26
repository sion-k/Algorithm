L, K = tuple(map(int, input().split()))
S = (0,) + tuple(map(int, input().split()))
# S[here] 까지 1개 이상 골랐는지 여부 picked와 그 합이 sum일 때
# 합이 K인 경우의 수 반환
def btk(here, picked, sum) :
    global L
    if here > N :
        # 최소 1개 이상 골라야 함
        if not picked : return 0
        global K
        return 1 if sum == K else 0
    return btk(here + 1, picked, sum) + btk(here + 1, True, sum + S[here])

print(btk(1, False, 0))