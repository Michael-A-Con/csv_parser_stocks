class Entry:
    def __init__(self):
        self.date = ""
        self.type = ""
        self.stock = ""
        self.amount = ""
        self.price = ""
        self.fees = "0"
        self.split = "1"
        self.dividend = ""

    def print_string(self):
        print(f"date: {self.date}, type: {self.type}, stock: {self.stock}, amount: {self.amount}, price: {self.price}, div: {self.dividend}")

    def reset(self):
        self.date = ""
        self.type = ""
        self.stock = ""
        self.amount = ""
        self.price = ""
        self.dividend = ""
        self.split = "1"
