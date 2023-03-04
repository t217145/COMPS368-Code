import sys
import threading
import time
import random

class Boss(threading.Thread):
    """Boss"""

    def __init__(self, acc):
        threading.Thread.__init__(self)
        self.myAcc = acc

    def run(self):
        while True:
            # randomly deposits 1 to 10 dollars
            amount = random.randint(1,10)
            self.myAcc.deposit(amount)
            # sleep for half a second
            time.sleep(0.5)

class Servant(threading.Thread):
    """Servant"""

    def __init__(self, acc):
        threading.Thread.__init__(self)
        self.myAcc = acc

    def run(self):
        while True:
            # randomly withdraw 3 to 12 dollars
            amount = random.randint(3,12)
            self.myAcc.withdraw(amount)
            
class Account:
    """Account"""
    
    def __init__(self):
        self.balance = 0
        self.mylock = threading.RLock()
        self.cvDeposit = threading.Condition(self.mylock)
                
    def getBalance(self):
        return self.balance 
        
    def showlog(self, action):
        print('\t\t\t\t\t\t%c: %d' % (action, self.getBalance()))
        
    def withdraw(self, amount):
        self.mylock.acquire(True)
        print('Withdraw attempt: %d' % amount)
        while self.balance < amount:
            self.cvDeposit.wait()
        self.balance = self.balance - amount
        self.showlog('W')
        self.mylock.release()
        
    def deposit(self, amount):
        self.mylock.acquire(True)
        print('Deposit attempt: %d' % amount)
        self.balance = self.balance + amount
        self.cvDeposit.notifyAll()
        self.showlog('D')
        self.mylock.release()
        
if __name__ == "__main__":
    acc = Account() 
    # create Boss and Servant threads
    boss = Boss(acc)
    servant = Servant(acc)

    # start each thread
    boss.start()
    servant.start()