n, s = map(int, input().split())
a = list(map(int, input().split()))

cnt = 0


def dfs(here, pick):
    """
    지금까지 고른 수열이 pick일 때, a[here]부터 고를지 말지를 선택하기 시작해서
    모든 가능한 경우의 수를 탐색하는 함수
    """
    if here == n:
        if pick and sum(pick) == s:
            global cnt
            cnt += 1
    else:
        # a[here]을 선택하는 경우
        pick.append(a[here])
        dfs(here + 1, pick)

        # a[here]을 선택하지 않는 경우
        pick.pop()
        dfs(here + 1, pick)


dfs(0, [])
print(cnt)
