---
title: conda及pip配置文件 - 已整理
date: 2022-03-04 10:20:34
tags:
---
## conda 清华源

路径：
-（Windows）`%USERPROFILE%/.condarc`
-（Linux）`~/.condarc`

检查命令：`conda config --show-sources`（事实上，`show--sources`只需输入到`show-`即可达到效果）

```
channels:
  - defaults
show_channel_urls: true
default_channels:
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/conda-forge/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/msys2/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/bioconda/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/menpo/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/pytorch/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/simpleitk/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/r
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/pro
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/msys2
custom_channels:
  conda-forge: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  msys2: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  bioconda: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  menpo: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  pytorch: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  simpleitk: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
ssl_verify: true
```

## conda 北外镜像

```
channels:
  - defaults
show_channel_urls: true
default_channels:
  - https://mirrors.bfsu.edu.cn/anaconda/pkgs/free/
  - https://mirrors.bfsu.edu.cn/anaconda/pkgs/main/
#Conda Forge
  - https://mirrors.bfsu.edu.cn/anaconda/cloud/conda-forge/
#msys2（可略）
  - https://mirrors.bfsu.edu.cn/anaconda/cloud/msys2/
#bioconda（可略）
  - https://mirrors.bfsu.edu.cn/anaconda/cloud/bioconda/
#menpo（可略）
  - https://mirrors.bfsu.edu.cn/anaconda/cloud/menpo/
#pytorch
  - https://mirrors.bfsu.edu.cn/anaconda/cloud/pytorch/
# for legacy win-64（可略）
  - https://mirrors.bfsu.edu.cn/anaconda/cloud/peterjc123/
ssl_verify: true
```

## pip 阿里源

路径：
-（Windows）`%USERPROFILE%/pip/pip.ini`
-（Linux）`~/.pip/pip.conf`

检查命令：`pip config list`

```
[global]
timeout = 6000
index-url = http://mirrors.aliyun.com/pypi/simple/
trusted-host = mirrors.aliyun.com
```

------------------------
参考地址