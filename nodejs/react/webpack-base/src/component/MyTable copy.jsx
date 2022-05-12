import React, { Component } from 'react'
import cssobj from "@/css/MyTable.css";
import cssobj2 from "@/css/MyTableCell.css";

class MyTableCell extends Component {

    keyPress(e) {
        e.target.value = e.target.value.replace(/[^0-9]/g, '');
    }

    getTableDataInput(num) {
        return <input placeholder="0"
            onChange={(e) => { this.props.onChange(num, e, this.props.obj.id, e) }}
            type="text"
            onPaste={() => false}
            onInput={(e) => this.keyPress(e)} />
    }

    ///////////////         改动处 一      /////////////////////////

    // 数据单元格 UI
    render = () => <td>
        {this.getTableDataInput(0)}度{this.getTableDataInput(1)}分
        </td>

    ///////////////////////////////////////////////////////////////

}

export class MyTable extends Component {
    id = 2018110066             //调试用id
    courseId = 24               //调试用courseId

    ///////////////         改动处 二      /////////////////////////

    // 表格标题
    tableTitle = '光栅测量实验数据记录表格'

    // 数据单元格行数
    elementCount = 4;

    // 是否允许表格增删数据列
    allowGrowAndShrink = true

    // 初始的数据单元格的表头
    dataHeader = [-2, -1, 1, 2]

    // (仅允许增删数据列时生效) 数据单元格列数增长方式： 
    // 0为两侧增长，1为右侧增长，-1为左侧增长（此值暂无作用，效果与设为 1 相同）
    changeTwoSides = 0

    // (仅允许增删数据列时生效) 增长后的新数据单元格表头的新元素
    getNewRowEdge = (dataHeader) =>
        this.changeTwoSides == 0 ? dataHeader.length / 2 + 1 : dataHeader.length + 1

    // (仅允许增删数据列时生效) 增加按钮和删除按钮的文字
    buttonTitles = ['增加明纹组', '删除明纹组']

    // (仅允许增删数据列时生效) 最大数据单元格列数
    max = 10

    // (仅允许增删数据列时生效) 最小数据单元格列数 —— 不能小于 dataHeader 的元素个数
    min = 4

    //表格的排版
    tableBody = () => [
        // 其他输入框
        ,
        // 表格部分
        <tbody>
            <tr>
                <th colSpan="2">次数</th>
                {this.state.dataHeader.map(item => <th key={item}>{item}</th>)}
            </tr>
            <tr>
                <th rowSpan="2">参考光波长： (nm)<br />
                    {this.getOtherDataInput(0, '参考光波长 (nm)')}
                </th>
                <th>左游标读数</th>
                {this.state.tableData[0].map(item => this.getTableCell(item))}
            </tr>
            <tr>
                <th>右游标读数</th>
                {this.state.tableData[1].map(item => this.getTableCell(item))}
            </tr>
            <tr>
                <th rowSpan="2">待测光</th>
                <th>左游标读数</th>
                {this.state.tableData[2].map(item => this.getTableCell(item))}
            </tr>
            <tr>
                <th>右游标读数</th>
                {this.state.tableData[3].map(item => this.getTableCell(item))}
            </tr>
        </tbody>
    ]

    /**
     * 计算每个单元格代表的值
     * @param {object} item 单元格中输入框的值的数组
     */
    calculateCell = (item) =>
        item.value[0] + item.value[1] / 60

    /**
     * 单元格中的输入框输入的值是否合法的判定规则
     * @param {number} num 单元格中输入框的序号
     * @param {object} value 单元格中相应序号的输入框的值
     */
    judgeRule = (num, value) =>
        (num == 0) && (value >= 0 && value < 360) || (num == 1) && (value >= 0 && value < 60)

    //指定要发送的实验数据
    experimentData = (list, otherData) => {
        return {
            l1: list[0].map(this.calculateCell),
            r1: list[1].map(this.calculateCell),
            l2: list[2].map(this.calculateCell),
            r2: list[3].map(this.calculateCell),
            lambda1: otherData[0]
        }
    }

    /////////////////////////////////////////////////////////////////

    //填充模拟数据时用到的生成随机值的函数
    propduceRandomData = () => {
        let tableData = this.state.tableData
        let otherData = this.state.otherData
        tableData.map(item => item.map(item => {
            item.value[0] = Math.floor(Math.random() * 360)
            item.value[1] = Math.floor(Math.random() * 60)
        }))
        otherData.map(() => Math.floor(Math.random() * 60))
        this.setState({ tableData: tableData, otherData: otherData })
    }

    /////////////////////////////////////////////////////////////////

    constructor() {
        super();
        let temp = []
        switch (this.changeTwoSides) {
            case 0:
                switch (this.dataHeader.length) {
                    case 2: temp = [1, 2]; break
                    case 4: temp = [3, 1, 2, 4]; break
                    case 6: temp = [5, 3, 1, 2, 4, 6]; break
                    default: temp = []; alert('允许数据列像两侧增长时表头数组必须定义为偶数个元素'); break
                }
                break
            default:
                for (let i = 0; i < this.dataHeader.length; i++)
                    temp.push(i + 1)
                break
        }
        this.idModel = temp
        let list = []
        for (let i = 0; i < this.elementCount; i++)
            list.push(this.idModel.map(item => this.cell(i * this.max + item)))
        this.state = { dataHeader: this.dataHeader, tableData: list, otherData: [] }
    }

    getOtherDataInput(num, hint) {
        return <input
            placeholder={hint}
            onChange={event => this.setOtherData(num, event)}
            style={{ width: 100, marginLeft: 20, marginRight: 20, padding: 5 }} />
    }

    cell = (id) => { return { id: id, value: [] } }

    getTableCell = (item) => <MyTableCell key={item.id} obj={item} onChange={this.changeData.bind(this)} />

    changeData(a, e, id) {
        var list = this.state.tableData
        var row = Math.floor(id / this.max)
        var col = id - row * this.max
        col = this.idModel.indexOf(col) + 1
        if (col == 0) { row--; col = this.max }
        var obj = list[row][col - 1]
        if (!this.judge(a, e)) return
        obj.value[a] = e.target.value
        this.setState({ tableData: list })
    }

    changeTableData(add) {
        var dataHeader = this.state.dataHeader
        var length = dataHeader.length
        var list = this.state.tableData
        list.map((item, index) => {
            if (add) {
                switch (this.changeTwoSides) {
                    case 0:
                        item.unshift(this.cell(index * this.max + this.idModel[0]))
                        item.push(this.cell(index * this.max + this.idModel[length - 1]))
                        console.log(this.item)
                        break
                    case -1:
                        item.unshift(this.cell(index * this.max + this.idModel[0]))
                        console.log(this.item)
                        break
                    case 1:
                        item.push(this.cell(index * this.max + this.idModel[length - 1]))
                        console.log(this.item)
                        break
                }
            } else {
                switch (this.changeTwoSides) {
                    case 0:
                        item.shift()
                        item.pop()
                        console.log(this.item)
                        break
                    case -1:
                        item.shift()
                        console.log(this.item)
                        break
                    case 1:
                        item.pop()
                        console.log(this.item)
                        break
                }
            }
        })
        this.setState({ tableData: list }, () => console.log(this.state.tableData))
    }

    changeGroups(add) {
        var dataHeader = this.state.dataHeader
        var edge = this.getNewRowEdge(dataHeader)
        var idModel = this.idModel
        if (add) {
            if (dataHeader.length < this.max) {
                switch (this.changeTwoSides) {
                    case 0:
                        dataHeader.unshift(-edge)
                        idModel.unshift(idModel[0] + 2)
                        dataHeader.push(edge)
                        idModel.push(idModel[idModel.length - 1] + 2)
                        console.log(this.idModel)
                        break
                    case -1:
                        dataHeader.unshift(-edge)
                        idModel.unshift(idModel[0] + 2)
                        console.log(this.idModel)
                        break
                    case 1:
                        dataHeader.push(edge)
                        idModel.push(idModel[idModel.length - 1] + 1)
                        console.log(this.idModel)
                        break
                }
            } else {
                alert("已达极限值")
                return
            }
        }
        else
            if (dataHeader.length > this.min) {
                switch (this.changeTwoSides) {
                    case 0:
                        dataHeader.pop()
                        idModel.pop()
                        dataHeader.shift()
                        idModel.shift()
                        console.log(this.idModel)
                        break
                    case -1:
                        dataHeader.shift()
                        idModel.shift()
                        console.log(this.idModel)
                        break
                    case 1:
                        dataHeader.pop()
                        idModel.pop()
                        console.log(this.idModel)
                        break
                }
            } else {
                alert("已达极限值")
                return
            }
        this.setState({ dataHeader: dataHeader }, () => this.changeTableData(add))
    }

    dataSource() {
        var json1 = JSON.stringify({ stuId: this.id, courseId: this.courseId })
        var json2 = JSON.stringify(this.experimentData(this.state.tableData, this.state.otherData))
        var dataSource = JSON.parse((json1 + json2).replace(/}{/, ','))
        console.log(dataSource)
        return JSON.stringify(dataSource)
    }

    judge(a, e) {
        var v = e.target.value
        if (this.judgeRule(a, v)) {
            e.target.style.border = '1px dashed #ccc'
            e.target.style.boxShadow = '0 0 0px'
            return true
        }
        else {
            e.target.style.border = '1px solid red'
            e.target.style.boxShadow = '0 0 5px red'
            return false
        }
    }

    setOtherData(num, event) {
        let otherData = this.state.otherData
        otherData[num] = event.target.value
        this.setState({ otherData: otherData })
    }

    upload() {
        let myHeaders = new Headers()
        myHeaders.append('Content-Type', 'application/json')
        myHeaders.append('token', '99ef0434-fafb-4477-889b-b4e7a7ecc17b')
        let data = this.dataSource()
        this.setState({ isLoading: true })
        fetch('http://222.18.57.34:10001/experiment-data', {
            method: 'POST',
            headers: myHeaders,
            body: data
        })
            .then((response) => {
                this.setState({ isLoading: false })
                if (response.status == 200) return response.text()
                else {
                    this.setState({ res: response.status })
                    return response.status
                }
            })
            .then((res) => {
                this.setState({
                    isLoading: false,
                    res: res == 'OK' ? '上传成功' :
                        '上传失败: ' + (res == 405 ? '之前已上传过，本次上传无效' : res)
                })
            })
            .catch(e => {
                alert(e.message)
                this.setState({ isLoading: false, res: e.message })
            })
    }

    renderTable = () => {
        return <table frame='box' cellSpacing={0} >
            <caption>
                {this.tableTitle}
                {this.allowGrowAndShrink ?
                    <div style={{
                        fontSize: 15,
                        marginTop: 10,
                    }}>
                        <button
                            style={{ background: 'green', color: 'white', opacity: this.state.dataHeader.length < this.max ? 1 : 0 }}
                            onClick={() => this.changeGroups(true)}>{this.buttonTitles[0]}</button>
                        <button
                            style={{ background: 'red', color: 'white', opacity: this.state.dataHeader.length > this.min ? 1 : 0 }}
                            onClick={() => this.changeGroups(false)}>{this.buttonTitles[1]}</button>
                    </div> : null}
                {this.tableBody()[0]}
            </caption>
            {this.tableBody()[1]}
        </table>
    }

    render() {
        return <div>
            <h1 className={cssobj.title}>SRTP 实验数据表格模板</h1>
            <div className={cssobj2.container}>
                {this.renderTable()}
                {this.state.isLoading ? <p>上传中，请稍候…</p> : <p>{this.state.res}</p>}
                <div>
                    <button onClick={() => this.upload()}>上传</button>
                    <button onClick={() => this.propduceRandomData()}>填充模拟数据</button>
                </div>
            </div>

            <div className={cssobj2.container} style={{ opacity: 0.5 }}>
                <div>
                    <h1 style={{ margin: 0 }}> 数据：</h1>
                </div>
                <div>
                    {this.state.tableData.map((i, index) =>
                        <div key={index}>
                            {this.state.tableData[index].map(item => <div className={cssobj.horizontalView} key={item.id}> {item.id} {item.value[0]} {item.value[1]}</div>)}
                        </div>
                    )}
                    {this.state.otherData.map(i => i + ", ")}
                </div>
            </div>
        </div >
    }
}
