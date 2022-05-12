# 生成ssl证书

以下步骤仅用于配置内部使用或测试需要的SSL证书。

## 第1步：生成私钥

使用openssl工具生成一个RSA私钥

`$ openssl genrsa -des3 -out server.key 2048`

## 第2步：生成CSR

生成私钥之后，便可以创建csr（证书签名请求）文件了。

此时可以有两种选择。理想情况下，可以将证书发送给证书颁发机构（CA），CA验证过请求者的身份之后，会出具签名证书（很贵）。另外，如果只是内部或者测试需求，也可以使用OpenSSL实现自签名，具体操作如下：

``` shell
$ openssl req -new -key server.key -out server.csr
```

说明：需要依次输入国家，地区，城市，组织，组织单位，Common Name和Email。其中Common Name，可以写自己的名字或者域名，如果要支持https，Common Name应该与域名保持一致，否则会引起浏览器警告。

```
Country Name (2 letter code) [AU]:CN
State or Province Name (full name) [Some-State]:Beijing
Locality Name (eg, city) []:Beijing
Organization Name (eg, company) [Internet Widgits Pty Ltd]:joyios
Organizational Unit Name (eg, section) []:info technology
Common Name (e.g. server FQDN or YOUR name) []:demo.joyios.com
Email Address []:liufan@joyios.com
```

## 第3步：删除私钥中的密码

```shell
$ cp server.key server.key.org
$ openssl rsa -in server.key.org -out server.key
```

## 第4步：生成自签名证书

```shell
$ openssl x509 -req -days 365 -in server.csr -signkey server.key -out server.crt
```

## 第5步：安装私钥和证书

将私钥和证书文件复制到Apache的配置目录下即可，在Mac 10.10系统中，复制到/etc/apache2/目录中即可。