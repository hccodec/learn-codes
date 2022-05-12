2. 本机模块设置

   ```bash
   sudo npm install -g react-native-create-library
   react-native-create-library MyLibrary
   npm install
   ```

# react native环境搭建

## 1. 使用 HomeBrew 安装 Node 和 Watchman

```bash
brew install node
brew install watchman
```

### brew 换源

```bash
更换homebrew

cd "$(brew --repo)"
git remote set-url origin https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/brew.git

cd "$(brew --repo)/Library/Taps/homebrew/homebrew-core"
git remote set-url origin https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/homebrew-core.git

brew update
```

```bash
更换homebrew-bottles

长期更换
echo 'export HOMEBREW_BOTTLE_DOMAIN=https://mirrors.tuna.tsinghua.edu.cn/homebrew-bottles' >> ~/.bash_profile
source ~/.bash_profile
临时更换
export HOMEBREW_BOTTLE_DOMAIN=https://mirrors.tuna.tsinghua.edu.cn/homebrew-bottles
```

### npm换源

```bash
npm config set registry https://registry.npm.taobao.org
npm config list
```

### 安装 yarn 及 yarn 换源

```bash
npm install -g yarn react-native-cli

yarn config set registry https://registry.npm.taobao.org --global
yarn config set disturl https://npm.taobao.org/dist --global
```

## 2. react-native 项目初始化

```bash
react-native init {项目名}
```

#### 卡在 Installing required CocoaPods dependencies

参考https://blogs.csdn.net/qq_37336604/article/details/80340968 与 https://www.jianshu.com/p/79863e9da974 卸载 Cocoapods 重新安装

1. 卸载 cocoapods

   ```bash
   which pod
   sudo rm -rf /use/local/bin/pod
   
   gem list
   sudo gem uninstall cocoapods-*** (-v 1.7.5{自己的版本号})
   gem list
   ```

2. 修改镜像源

   ```bash
   gem sources
   gem sources --remove {显示的网址}
   gem sources -l
   gem sources -a https://gems.ruby-china.com/
   gem sources -l
   ```

3. 安装cocoapods

   ```bash
   git clone https://git.coding.net/CocoaPods/Specs.git /.cocoapods/repos/master2 #该命令等效于 pod setup
   pod repo update
   ```

4. **其他**

5. 

```
npm i react-native-md5
adb shell input keyevent
```

