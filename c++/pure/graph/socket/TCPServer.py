# coding=utf-8
from socket import *
serverPort = 12000

serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('', serverPort))
serverSocket.listen(1)
print("The server is ready to receive")

while True:
    connSocket, addr = serverSocket.accept()
    msg = connSocket.recv(1024)
    print("receive: {}, change to {}".format(msg.decode(), msg.decode().upper()))
    modifiedMsg = msg.decode().upper()
    connSocket.send(modifiedMsg.encode())
    connSocket.close()
    if msg == "exit":
        break