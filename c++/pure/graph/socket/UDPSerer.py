'''
Description: 
Version: 1.0
Author: hccodec
Date: 2021-12-11 14:00:11
LastEditors: hccodec
LastEditTime: 2021-12-11 14:28:48
'''
from socket import *
# serverName = 'localhost'
serverPort = 12000

# clientSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(('', serverPort))
print("The server is ready to receive")

# msg = input('Input lowercase sentence: ')
# clientSocket.sendto(msg.encode(), (serverName, serverPort))


# modifiedMessage, serverAddr = clientSocket.recvfrom(2048)
# print(modifiedMessage.decode())
# clientSocket.close()

while True:
    msg, clientAddr = serverSocket.recvfrom(2048)
    modifiedMsg = msg.decode().upper()
    serverSocket.sendto(modifiedMsg.encode(), clientAddr)