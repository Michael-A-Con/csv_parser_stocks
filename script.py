import sys
import gspread
from Entry import Entry

def parse_csv(file):
    line = ''
    limiter = ','
    max_val = 9
    now = 0
    entry = Entry()
    tradeNum = 2
    with open(file, 'r') as f:
        f.readline()
        for line in f:
            data = line.strip().split(limiter)
            for value in data:
                if "CUSIP" in value or "order batcher" in value:
                    continue

                value = value.replace('"', '')
                value = value.replace('$', '')
                value = value.replace('(', '')
                value = value.replace(')', '')

                if now == 0:  # date
                    entry.date = value
                elif now == 3:  # stock
                    entry.stock = value
                elif now == 5:  # type action
                    entry.type = value
                elif now == 7:  # price
                    entry.price = value
                elif now == 8:  # amount
                    if "Buy" in entry.type:  # if it's a buy
                        entry.amount = value
                        entry.dividend = "0"
                    elif "CDIV" in entry.type:  # if it's a dividend
                        entry.amount = "0"
                        entry.dividend = value
                    elif "ACH" in entry.type:  # if it's a dividend
                        entry.amount = value
                        entry.dividend = "0"

                now += 1
                # restart now to 0 since new entry
                if now >= max_val:
                    entry.print_string()
                    export(entry ,tradeNum)
                    tradeNum = tradeNum+1
                    entry.reset()
                    now = 0

                # print()  # Move to the next line



def export(entry, num):
    sa = gspread.service_account()

    sh = sa.open("developing script tests")

    wks = sh.worksheet("Sheet1")
    lineA = "A"+str(num)
    lineH = ":H"+str(num)
    row = lineA+lineH
    wks.update(row, [[entry.date, entry.type, entry.stock, entry.amount]])
    
    
    
        
if len(sys.argv) < 2:
    print(f"Expected at least 1 value, the name of the csv file")
    sys.exit()

file_string = sys.argv[1]
print(file_string)
parse_csv(file_string)