---
title: WSL linux相关设置
date: 2022-03-04 10:20:34
tags:
---
```
sudo dpkg-reconfigure locales

sudo vim /etc/apt/sources.list

# 对应 20-04 LTS 发行版
=======================================

# 默认注释了源码镜像以提高 apt update 速度，如有需要可自行取消注释
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal main restricted universe multiverse
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-updates main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-updates main restricted universe multiverse
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-backports main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-backports main restricted universe multiverse
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-security main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-security main restricted universe multiverse

# 预发布软件源，不建议启用
# deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-proposed main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-proposed main restricted universe multiverse

=======================================

sudo apt-get update

sudo apt-get upgrade

=======================================
sudo apt-get install xrdp

///
sudo apt-get install xfce4

sudo apt-get install gnome

export DISPLAY=localhost:0
xfce4-session
///
sudo apt-get install xrdp
sudo sed -i 's/port=3389/port=3390/g' /etc/xrdp/xrdp.ini
sudo echo xfce4-session >~/.xsession
sudo service xrdp restart



chown -R bob:bob /home/bob

=======================================
sudo apt-get install ubuntu-desktop unity compizconfig-settings-manager

sudo apt-get install ubuntu-desktop unity compizconfig-settings-manager

compiz出现错误：
=======================================
cd ~    

切换到自己的home目录之后，

rm -rf .cache/compiz
rm -rf .cache/compiz-1
rm -rf .cache/compizconfig-1
rm -rf .config/compiz
rm -rf .config/compiz-1
rm -rf .config/compizconfig-1
rm -rf .gconf/apps/compiz
rm -rf .gconf/apps/compiz-1
rm -rf .gconf/apps/compizconfig-1

好像删完这个.config/compiz-1 好像就自动恢复了。
=======================================
export DISPLAY=localhost:0
ccsm
compiz
=======================================
安装了mysql 后，启动时，程序提示 “No directory, logging in with HOME=/”
sudo service mysql stop

sudo usermod -d /var/lib/mysql/ mysql      //使用usermod命令来重新定位mysql所在的目录

sudo service mysql start
=======================================
```
