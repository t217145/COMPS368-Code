import socket

def main():
    # create server socket
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as serversocket:
        # bind server socket to port 12345
        serversocket.bind(("", 12345))
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
                        line = 'Echo: ' + line.decode('utf-8').strip() # add some words in server side to proof that the message is really send from server to client
                        print (line)
                        sock.send(line.encode('utf-8')) #send back the message to client
            finally:
                sock.close()
                print ('Connection closed')

if __name__ == "__main__":
    main()
