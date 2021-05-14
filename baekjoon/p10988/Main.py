# s[start, end]가 팰린드롬인지 반환
def pal(s, start, end):
    # 길이가 1이거나 빈 문자열은 팰린드롬이다
    if start >= end : return True
    return pal(s, start + 1, end - 1) if s[start] == s[end] else False
s = input()
print(1 if pal(s, 0, len(s) - 1) else 0)
