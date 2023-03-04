import socket
import sys

def main():
	s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #create socket 
	s.connect(('127.0.0.1', 12345)) #make a connection request
	
	print('Type something or press Enter twice to end input')
	while True: #Repeating get user input and send
		line = sys.stdin.readline().strip() #Get user input
		if line:
			s.send(line.encode('utf-8')) #Sender send data to socket
		else:
			break
				
	s.close() # or close the connection
	print('Connection closed') #or close the connection

if __name__ == "__main__":
	main()