import datetime

date, days = input().split(" ")
year, month, day = map(int, date.split("-"))

date = datetime.datetime(year, month, day)
days = datetime.timedelta(int(days) - 1)

print(str(date + days)[:10])