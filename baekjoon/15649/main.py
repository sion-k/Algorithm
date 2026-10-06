n, m = map(int, input().split())


def dfs(pick):
    # m개의 수를 모두 고른 경우
    if len(pick) == m:
        print(*pick)
    else:
        for cand in range(1, n + 1):
            if not cand in pick:
                pick.append(cand)  # 1. cand를 선택
                dfs(pick)  # 2. 더 깊이 진행
                pick.pop()  # 3. 탐색이 끝나면 선택을 해제


dfs([])
