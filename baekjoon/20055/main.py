n, k = map(int, input().split())
a = list(map(int, input().split()))

# 회전하는 벨트 중에서 상단에 보이는 칸들의 번호
belt = [i for i in range(2 * n)]
# 각각의 로봇들이 상단에 보이는 칸에서의 위치
robot = []

step = 1
cnt = 0

while True:
    belt.insert(0, belt[-1])
    belt.pop()

    robot = [r + 1 for r in robot]

    if robot and robot[0] == n - 1:
        robot.pop(0)

    for i in range(len(robot)):
        if (i == 0 or robot[i] + 1 != robot[i - 1]) and a[belt[robot[i] + 1]]:
            robot[i] += 1
            a[belt[robot[i]]] -= 1

            if a[belt[robot[i]]] == 0:
                cnt += 1

    if robot and robot[0] == n - 1:
        robot.pop(0)

    if a[belt[0]]:
        robot.append(0)
        a[belt[0]] -= 1

        if a[belt[0]] == 0:
            cnt += 1

    if cnt >= k:
        break
    else:
        step += 1

print(step)
