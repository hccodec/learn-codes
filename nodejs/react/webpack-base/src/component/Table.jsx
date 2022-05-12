import React, { Component } from 'react'


const Action = props => {
    console.log(props)
    return (
        <div>
            <button type='button' onClick={props.editCb}>{props.tr.edit
                ? '保存'
                : '编辑'}</button>
            <button type='button' onClick={props.tr.edit ? props.saveCb : Number}>取消</button>
        </div>
    );
};
export default class Table extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            headers: {
                name: "名称",
                path: "路径映射",
                action: ""
            },
            trs: [
                {
                    name: "定时任务",
                    path: "/xxxx",
                    action: "",
                    edit: false
                }, {
                    name: "定时任务2",
                    path: "/yyyy",
                    action: "",
                    edit: false
                }
            ]
        };
    }
    onSave(data) {
        //点击取消按钮时还原该行为显示狀态
        data.edit = false
        if (data._initName !== undefined) {
            data.name = data._initName
            data.path = data._initPath
            //  data._initName = data._initPath = undefined
            this.forceUpdate()
        }
    }
    onEdit(data) {
        //点击取消按钮时还原该行为编辑狀态，再点击还原
        var editable = data.edit = !data.edit
        if (editable) {
            data._name = data.name
            data._initName = data.name
            data.name = <input
                defaultValue={data._name}
                onInput={(e) => {
                    data._name = e.target.value;
                }} />
            data._path = data.path
            data._initPath = data.path
            data.path = <input
                defaultValue={data._path}
                onInput={(e) => {
                    data._path = e.target.value;
                }} />
            this.forceUpdate()
        } else {
            data.name = data._name
            data.path = data._path
            this.forceUpdate()
        }

    }
    render() {
        var headers = this.state.headers || {};
        var keys = Object.keys(headers);
        console.log(headers);
        return (
            <table>
                <thead>
                    <tr>
                        {keys.map(key => <th>
                            {headers[key]}
                        </th>)}
                    </tr>
                </thead>
                <tbody>
                    {this
                        .state
                        .trs
                        .map(function (tr) {
                            return (
                                <tr>
                                    {keys
                                        .map(function (key, index) {
                                            return (
                                                <td key={key}>
                                                    {key === "action"
                                                        ? <Action
                                                            tr={tr}
                                                            saveCb={this
                                                                .onSave
                                                                .bind(this, tr)}
                                                            editCb={this
                                                                .onEdit
                                                                .bind(this, tr)} />
                                                        : tr[key]
                                                    }
                                                </td>
                                            )
                                        }, this)}
                                </tr>
                            )
                        }, this)}
                </tbody>
            </table>
        )
    }
}