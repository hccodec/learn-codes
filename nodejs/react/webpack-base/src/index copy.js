console.log('宝佳的 react 项目：这么年轻一定要与时俱进啊 ~ ');

import React from 'react';
import ReactDOM from 'react-dom';


// const myh1 = React.createElement('h1', null, "我是个大h1")
// const mydiv = React.createElement('div', null, "我是个大div", myh1)

let a = 10
let str = "你好中国"
let boo = !true
let title = '9999'
const arrStr = ['毛利兰', '柯南', '小五郎', '灰原哀']

const nameArr = []
arrStr.forEach(item => {
    const temp = <h5 key={item}>{item}</h5>
    nameArr.push(temp)
})
const result = arrStr.map(item => item + '~~')
console.log(result)

ReactDOM.render(
    <div>
        {a + 2}
        <hr />
        {str}
        <hr />
        {boo ? '条件为真' : '条件为假'}
        <hr />
        {arrStr.map(item => <div key={item}><h3>{item}</h3></div>)}
        <hr />

        <p className="myele">!!!!!!!!</p>
        <label htmlFor="ooo">lalalalaa</label>

    </div>, document.getElementById('hbj'))