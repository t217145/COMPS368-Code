import sys
import threading

class Adder(threading.Thread):
    """Adder"""
    
    def __init__(self, buf, cnt):
        threading.Thread.__init__(self)
        self.buf = buf
        self.cnt = cnt
    
    def run(self):
        for count in range(0,self.cnt):
            self.buf.increment()
            
class Buf:
    """Buf"""
    
    def __init__(self):
        self.sum = 0
        
    def getSum(self):
        return self.sum 
        
    def increment(self):
        # non atomic operation for the modification of common value
        tmpSum = self.sum
        # report thread index and global value before modification
        print('Thread-%d\tread %d' % (threading.get_ident(),tmpSum))
        self.sum = tmpSum + 1

if __name__ == "__main__":
    # Get arguments from command line
    if len(sys.argv) != 3:
        print('Correct usage: python SharedDataTest.py numThreads reps')
        sys.exit(1)

    numThreads = int(sys.argv[1])
    reps = int(sys.argv[2])

    # create shared Buf instance
    buf = Buf()
    # create list of thread instance
    tlist = [Adder(buf,reps) for i in range (0,numThreads)]

    # start each thread
    for t in tlist:
        t.start()

    # join each thread
    for t in tlist:
        t.join()

    # generate report
    correctSum = numThreads * reps
    print ('Computed sum = %d' % (buf.getSum()))
    print ('Correct sum = %d' % (correctSum))
    print ('Overlapped increments = %f%%' % (100.0 * (correctSum - buf.getSum()) / correctSum))