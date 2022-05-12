'''
Description: 
Version: 1.0
Author: hccodec
Date: 2021-12-11 14:00:11
LastEditors: hccodec
LastEditTime: 2021-12-11 14:31:05
'''
from socket import *
serverName = '192.168.0.148'
serverPort = 12000

clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName, serverPort))
msg = input('Input lowercase sentence: ')
clientSocket.send(msg.encode())
modifiedMessage = clientSocket.recv(1024)
print('From Server: ', modifiedMessage.decode())
clientSocket.close()