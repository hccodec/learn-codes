'''
Description: 
Version: 1.0
Author: hccodec
Date: 2021-12-11 14:00:11
LastEditors: hccodec
LastEditTime: 2021-12-11 14:16:37
'''
from socket import *
serverName = '192.168.0.148'
serverPort = 12000

clientSocket = socket(AF_INET, SOCK_DGRAM)
msg = input('Input lowercase sentence: ')
clientSocket.sendto(msg.encode(), (serverName, serverPort))
modifiedMessage, serverAddr = clientSocket.recvfrom(2048)
print(modifiedMessage.decode())
clientSocket.close()