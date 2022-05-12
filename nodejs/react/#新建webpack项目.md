# 创建基本的 webpack 4.x 项目

1. 新建一个文件夹运行 `npm init -y` 快速初始化项目

2. 创建目录：文件夹`src`源代码目录，文件夹`dist`产品目录

3. 在 `src` 目录下创建 `index.html` 

4. 运行 `cnpm i webpack webpack-cli -D` 和 `npm i cnpm -g` (全局) 安装 webpack

5. 注意 webpack 4.x 

   + 约定大于配置(尽量减少配置文件体积)
   + 默认打包入口： `./src/index.js` 
   + 输出文件 `./dist/main.js` (执行命令 `webpack` 后 )
   + 4.x 新增 `mode` ，可选值： `development` `production`

6. `cnpm i webpack-dev-server -D`

   ![1565105299732](C:\Users\84697\AppData\Roaming\Typora\typora-user-images\1565105299732.png)

7. `cnpm i html-webpack-plugin -D`

   ![1565105321341](C:\Users\84697\AppData\Roaming\Typora\typora-user-images\1565105321341.png)

   

# react（1 2 3的npm命令存在版本兼容问题，强烈建议全部替换为 4 中的命令）

1. `cnpm i react react-dom -S` 

   *前者创建组件 虚拟DOM 生命周期在包中；后者负责显示*

2. 导入包 -> 创建虚拟DOM元素 -> render 函数渲染

3. 用 babel 转换 JS 中的 html 标签：JSX 语法

   + `cnpm i babel-core babel-loader babel-plugin-transform-runtime -D`
   + `cnpm i babel-preset-env babel-preset-stage-0 -D`
   + `cnpm i babel-preset-react -D`

4. `npm i webpack webpack-cli webpack-dev-server -g`

   `npm i react react-dom -S`

   `npm i babel-loader @babel/core -D`

   ` npm i @babel/plugin-transform-runtime -D` 

   `npm i @babel/preset-env @babel/preset-react -D`

   ![1565107355770](C:\Users\84697\AppData\Roaming\Typora\typora-user-images\1565107355770.png)

   ![1565107375884](C:\Users\84697\AppData\Roaming\Typora\typora-user-images\1565107375884.png)

   # JSX

   # React 创建组件
   
   + 创建组件
   
     ```JSX
     function Hello() {
     	//return null
     	return <div>Hello 组件</div>
     }
     ```
   
   + 为组件传递数据
   
     ```jsx
     //使用组件并传递数据
     
     
     function Hello(props) {
         console.log(props) //只读
         return <div>这是 Hello 组件{props.name}{props.age}{props.gender}</div>
     }
     
     ReactDOM.render(
     	<div>
             {/*<Hello name={dog.name} age={dog.age} gender={dog.gender}></Hello>*/}
             
             // ES6 展开运算符
         	<Hello {...dog}></Hello>
         </div>
     )
     ```
     
   + 配置 extension 省略扩展名
   
     ```js
         resolve: {
             extensions: ['.js', '.jsx', '.json']
         }
     ```
   
     

