import stomp
HOST, PORT = 'localhost', 7672
USER, PASS = 'admin', 'admin'
DEST = '/topic/comps368u9Topic'

class Listener:
	def on_message(self, headers, body):
		print('[Received: ' + body + ']\n> ', end='')
		
conn = stomp.connect.StompConnection10([(HOST, PORT)])
#conn.start()
conn.connect(USER, PASS, wait=True)
conn.set_listener('', Listener())
conn.subscribe(DEST)
print("Enter a message to send, or 'quit'.")

while True:
	print('> ', end='')
	message = input()
	if message == 'quit':
		break;
	conn.send(DEST, message)
	
conn.unsubscribe(DEST)