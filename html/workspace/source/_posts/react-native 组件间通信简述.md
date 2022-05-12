---
title: react-native 组件间通信简述
date: 2022-03-04 10:20:34
tags:
---
## 父组件向子组件通讯
通讯是单向的，数据必须是由一方传到另一方。在 React 中，父组件可以向子组件通过传 props 的方式，向子组件进行通讯。
```
import React,{Component,PureComponent} from 'react'
import {Text,View} from 'react-native'
class Parent extends Component {
  constructor(props) {
    super(props)
    this.state = {
      msg:'I am your father!'
    }
  }
  render() {
    return <Son_1 msg={this.state.msg} />;
  }
}
class Son_1 extends PureComponent {
  render() {
    return (
        <View>
          <Text>{this.props.msg}</Text>
      </View>
    )
  }
}
```
如果父组件与子组件之间不止一个层级，如 Parent 与 Child_1_1 这样的关系，可通过 ... 运算符（Object 剩余和展开属性），将父组件的信息，以更简洁的方式传递给更深层级的子组件。通过这种方式，不用考虑性能的问题，通过 babel 转义后的 ... 运算符 性能和原生的一致，且上级组件 props 与 state 的改变，会导致组件本身及其子组件的生命周期改变。
```
class Son_1 extends PureComponent {
  render() {
    return (
        <View>
          <Text>{this.props.msg}</Text>
        <Son_1_1 {...this.props}/>
      </View>
    )
  }
}
class Son_1_1 extends PureComponent{
  render() {
    return (
        <Text>{this.props.msg}</Text>
    )
  }
}
```
## 子组件向父组件通讯
在上一个例子中，父组件可以通过传递 props 的方式，自顶而下向子组件进行通讯。而子组件向父组件通讯，同样也需要父组件向子组件传递 props 进行通讯，只是父组件传递的，是作用域为父组件自身的函数，子组件调用该函数，将子组件想要传递的信息，作为参数，传递到父组件的作用域中。
```
import React,{Component,PureComponent} from 'react'
import {Text,TouchableOpacity} from 'react-native'
class Parent extends Component {
  constructor(props) {
    super(props)
    this.state = {}
  }
  // 父组件定义的方法
  onClickSon = (msgFromSon) => {
    console.log(msgFromSon)
  }
  render() {
    return (
        <Son onClickSon={this.onClickSon}/>
    )
  }
}
class Son_1 extends PureComponent {
  render() {
    return (
      <TouchableOpacity onPress={()=> this.props.onClickSon('I am your son')}>
        <Text>爸爸去哪</Text>  
      </TouchableOpacity>
    )
  }
}
```
## 观察者模式 - DeviceEventEmitter
解决了兄弟组件通信的问题

在传统的前端解耦方面，观察者模式作为比较常见一种设计模式，大量使用在各种框架类库的设计当中。即使我们在写 React，在写 JSX，我们核心的部分还是 JavaScript。

观察者模式也叫 发布者-订阅者模式，发布者发布事件，订阅者监听事件并做出反应，对于兄弟组件的通信，我们一般使用 DeviceEventEmitter 解决。
```
import React,{Component,PureComponent} from 'react'
import {View,DeviceEventEmitter} from 'react-native'
class Parent extends Component{
  render() {
    return (
      <View>
        <Son_1/>
        <Son_2/>
      </View>
    );
  }
}
class Son_1 extends PureComponent{
  componentDidMount() {
    setTimeout(() => {
      // 发布 msg 事件
      DeviceEventEmitter.emit('sendMsg', {text:'Hello Brother'});
    }, 1000);
  }
}
class Son_2 extends PureComponent{
  componentDidMount() {
    this.listener =DeviceEventEmitter.addListener('sendMsg',function(param){
     //  use param do something
    });
  }
  //最后别忘了移除通知
  componentWillUnmount(){
    this.listener.remove();
  }
}
```
参考地址：https://www.javascriptcn.com/read-36620.html