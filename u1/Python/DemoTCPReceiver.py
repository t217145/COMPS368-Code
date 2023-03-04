import socket

def main():
	serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #create socket
	serversocket.bind(("", 12345)) #bind to port 12345
	serversocket.listen(1) #listen request from sender and wait
	print ('Waiting for connection in')
	
	while True:
		(sock, address) = serversocket.accept() #Receiver accept, create a socket
		print ('Connection with {addr} created'.format(addr=address[0]))
		
		print ('{a} Message from sender {a}'.format(a='*'*10))
		while True: #Repeating read
			line = ''
			line = sock.recv(1024).strip() #Receiver receive data from socket
			if line:
				print (line.decode('utf-8')) #process it
			else:
				break
		sock.close() # or close the connection
		print ('Connection closed')

if __name__ == "__main__":
	main()