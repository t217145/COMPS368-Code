import socket
import sys

def main():
	sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) #create datagram socket 
	
	print('Type something or press Enter twice to end input')
	while True: #Repeating get user input and send
		line = sys.stdin.readline().strip() #Get user input
		if line:
			sock.sendto(line.encode('utf-8'), ('127.0.0.1',12345)) #Sender send data to socket
		else:
			break

	print('Closed') #or close the application

if __name__ == "__main__":
	main()