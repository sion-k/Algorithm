n = int(input())

solved = [False] * (n + 1)
value = [0] * (n + 1)


def dp(n):
    """
    n킬로그램을 배달하는데 필요한 봉지의 최소 개수
    만약, 정확하게 n킬로그램을 만들 수 없다면 -1
    """
    if n == 0: return 0
    if solved[n]: return value[n]

    ret = 5000

    # 3킬로그램 봉지를 사용할 수 있다면
    if n >= 3 and dp(n - 3) != -1: ret = min(ret, 1 + dp(n - 3))

    # 5킬로그램 봉지를 사용할 수 있다면
    if n >= 5 and dp(n - 5) != -1: ret = min(ret, 1 + dp(n - 5))

    if ret == 5000: ret = -1

    value[n] = ret
    solved[n] = True

    return ret


print(dp(n))
