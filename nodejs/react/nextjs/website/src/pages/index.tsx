import React from 'react';
import styles from './index.css';
import { formatMessage } from 'umi-plugin-locale';
import { connect } from "dva/index";
import { Pagination, Spin, Alert } from 'antd';

@connect(({ loading }) => ({
  loading
}))
class Login extends React.Component {
  login() {
    // 调用login命名空间下的login方法
    this.props.dispatch({
      type: 'login/login',
      payload: {},
    });
  }
  render() {
    return (
      <div className={style.login}>
        <Spin spinning={this.props.loading.effects['login/login']}>
          <Alert
            message="Alert message title"
            description="Further details about the context of this alert."
            type="info"
          />
        </Spin>
        ...
        <button onClick={() => this.login()}>登陆</button>
      </div>
    );
  }
}

export default () => {
  return (
    <div className={styles.normal}>
      <div className={styles.welcome} />
      <ul className={styles.list}>
        <li>To get started, edit <code>src/pages/index.js</code> and save to reload.</li>
        <li>
          <a href="https://umijs.org/guide/getting-started.html">
            {formatMessage({ id: 'index.start' })}
            <br />
            {/* {this.props.loading} */}
          </a>
        </li>
      </ul>
    </div>
  );
}
