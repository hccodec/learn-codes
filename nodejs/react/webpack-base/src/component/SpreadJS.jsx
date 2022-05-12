
import React, { Component } from 'react';
import { SpreadSheets, Worksheet, Column } from '@grapecity/spread-sheets-react';

class APP extends Component {
    constructor(props) {
        super(props);
        this.spreadBackColor = 'aliceblue';
        this.sheetName = 'Goods List';
        this.hostStyle = { height: '600px' };
        this.data = [
            { 1: 'Apple', 2: 'Fruit', 3: 1, 'Shopping Place': 'Wal-Mart' },
            { 1: 'Potato', 2: 'Fruit', 3: 2.01, 'Shopping Place': 'Other' },
            { 1: 'Tomato', 2: 'Vegetable', 3: 3.21, 'Shopping Place': 'Other' },
            { 1: 'Sandwich', 2: 'Food', 3: 2, 'Shopping Place': 'Wal-Mart' },
            { 1: 'Hamburger', 2: 'Food', 3: 2, 'Shopping Place': 'Wal-Mart' },
            { 1: 'Grape', 2: 'Fruit', 3: 4, 'Shopping Place': 'Sun Store' }
        ];
        this.columnWidth = 100;
    }
    render() {
        return (
            <div>
                <SpreadSheets backColor={this.spreadBackColor} hostStyle={this.hostStyle}>
                    <Worksheet name={this.sheetName} dataSource={this.data}>
                        <Column dataField='1' width={300}></Column>
                        <Column dataField='2' width={this.columnWidth}></Column>
                        <Column dataField='3' width={this.columnWidth}
                            formatter="$#.00"></Column>
                        <Column dataField='4' width={this.columnWidth}></Column>
                        <Column dataField='5' width={this.columnWidth}></Column>
                    </Worksheet>
                </SpreadSheets>
            </div>
        )
    }
}
export default APP
