import socket
import sys

def main():
    # connect to server
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s: #create a TCP socket
        s.connect(('127.0.0.1', 12345))
        line = 1
        print('Input something to send to server or empty line to end')
				
        while line:
            # Ctrl-C to terminate the loop
            line = sys.stdin.readline().strip()
            if line: #send out the message only when the input is not empty
                s.send((line + "\n").encode('utf-8')) # send out the message in utf-8 format. I have added a '\n' to handle the reading text in server side for Java version
                reply = s.recv(1024).strip() # get the server reply
                print (reply.decode('utf-8'))

if __name__ == "__main__":
    main()
