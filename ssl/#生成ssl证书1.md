# 生成ssl证书

```bash
keytool -genkey -alias tomcat  -storetype PKCS12 -keyalg RSA -keysize 2048  -keystore keystore.p12 -validity 3650
```

****

以下步骤仅用于配置内部使用或测试需要的SSL证书。

### 1. 创建根证书密钥文件(自己做CA)root.key

```powershell
openssl genrsa -des3 -out root.key
```

### 2. 创建根证书的申请文件root.csr：

```powershell
openssl req -new -key root.key -out root.csr
```

### 3. 创建一个自当前日期起为期十年的根证书root.crt

```powershell
openssl x509 -req -days 3650 -sha1 -extensions v3_ca -signkey root.key -in root.csr -out root.crt
```

### 4. 创建服务器证书密钥server.key

```powershell
openssl genrsa –des3 -out server.key 2048
```

运行时会提示输入密码,此密码用于加密key文件(参数des3便是指加密算法,当然也可以选用其他你认为安全的算法.),以后每当需读取此文件(通过openssl提供的命令或API)都需输入口令.如果觉得不方便,也可以去除这个口令,但一定要采取其他的保护措施! 
去除key文件口令的命令: 

```powershell
openssl rsa -in server.key -out server.key
```

### 5. 创建服务器证书的申请文件server.csr：

```powershell
openssl req -new -key server.key -out server.csr
```

```txt
Country Name (2 letter code) [AU]:CN ← 国家名称，中国输入CN 
State or Province Name (full name) [Some-State]:SiChuan ← 省名，拼音 
Locality Name (eg, city) []:SiChuan ← 市名，拼音 
Organization Name (eg, company) [Internet Widgits Pty Ltd]:MyCompany Corp. ← 公司英文名 
Organizational Unit Name (eg, section) []: ← 可以不输入 
Common Name (eg, YOUR name) []:www.mycompany.com ← 服务器主机名，若填写不正确，浏览器会报告证书无效，但并不影响使用 
Email Address []:admin@mycompany.com ← 电子邮箱，可随便填

Please enter the following ‘extra’ attributes 
to be sent with your certificate request 
A challenge password []: ← 可以不输入 
An optional company name []: ← 可以不输入
```

### 6. 创建自当前日期起有效期为期两年的服务器证书server.crt：

```powershell
openssl x509 -req -days 730 -sha1 -extensions v3_req -CA root.crt -CAkey root.key -CAserial root.srl -CAcreateserial -in server.csr -out server.crt
```

### 7. 创建客户端证书密钥文件client.key：

```powershell
openssl genrsa -des3 -out client.key 2048
```

### 8. 创建客户端证书的申请文件client.csr：

```powershell
openssl req -new -key client.key -out client.csr
```

### 9. 创建一个自当前日期起有效期为两年的客户端证书client.crt：

```powershell
openssl x509 -req -days 730 -sha1 -extensions v3_req -CA root.crt -CAkey root.key -CAserial root.srl -CAcreateserial -in client.csr -out client.crt
```



### 10. 将客户端证书文件client.crt和客户端证书密钥文件client.key合并成客户端证书安装包client.pfx：

```powershell
openssl pkcs12 -export -in client.crt -inkey client.key -out client.pfx
```

### 11. 保存生成的文件备用

其中`server.crt`和`server.key`是配置单向`SSL`时需要使用的证书文件，client.crt是配置双向SSL时需要使用的证书文件，`client.pfx`是配置双向`SSL`时需要客户端安装的证书文件`.crt`文件和`.key`可以合到一个文件里面，把2个文件合成了一个`.pem`文件（直接拷贝过去就行了）