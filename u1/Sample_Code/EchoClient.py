import socket
import sys

def main():
    # connect to server
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect(('127.0.0.1', 8001))
        line = 1
        while line:
            # Ctrl-C to terminate the loop
            line = sys.stdin.readline()
            if line:
                s.send(line.encode('utf-8'))
                reply = s.recv(1024).strip()
                print ('echo: ' + reply.decode('utf-8'))

if __name__ == "__main__":
    main()
