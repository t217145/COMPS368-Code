import socket

def main():
	sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) #create datagram socket
	sock.bind(('127.0.0.1', 12345)) #bind to port 12345
	print ('Waiting for connection in')
	
	while True:
		(line, address) = sock.recvfrom(1024) #Receiver receive data and address from socket
		print ('{a} Message from sender [{h}] {a}'.format(a='*'*10, h=address[0]))
		print (line.decode('utf-8').strip()) #process it

if __name__ == "__main__":
	main()