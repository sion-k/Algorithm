def judge(S) :
    cnt = S.count("0")
    return chr(ord("A") + cnt - 1) if cnt else "E"

print(judge(input().split()))
print(judge(input().split()))
print(judge(input().split()))
