## React 几个核心概念

#### 虚拟DOM （Virtual Document Object Mpdel）

+ **DOM本质**： JS对象表示页面元素，**框架**提供操作DOM对象的API
+ **React中的虚拟DOM**： JS对象模拟页面的DOM、DOM嵌套
+ **目的**：**实现页面中DOM元素高效更新**

##### 案例：点击列头实现表格数据排序

1. **数据来源**：数据库

2. **存放形式**：对象数组

3. **如何渲染**: **应按需更新页面**

   1. for循环&拼接字符串
   2. 模板引擎  art-template

   

   #### 故将手动模拟DOM树  -> React 中 虚拟 DOM 概念

#### 虚拟DOM （Virtual Document Object Mpdel）

+ **tree diff: ** 两棵树的对比

+ 

+ webpackxiangmu 

+ **component diff: ** 组件级别对比（类型相同 **暂** 无需更新，进行 **元素级别** 对比）

+ **element diff**

  