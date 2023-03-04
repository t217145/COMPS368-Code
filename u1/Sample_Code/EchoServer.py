import socket

def main():
    # create server socket
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as serversocket:
        # bind server socket to port 8001
        serversocket.bind(("", 8001))
        #become a server socket
        serversocket.listen(1)
        print ('Receiver started')
        while True:
            try:
                #accept connections from outside
                (sock, address) = serversocket.accept()
                print ('Connection accepted')
                line = 1
                while line:
                    line = sock.recv(1024)
                    if line:
                        print ('echo: ' + line.decode('utf-8').strip())
                        sock.send(line)
            finally:
                sock.close()
                print ('Connection closed')

if __name__ == "__main__":
    main()
