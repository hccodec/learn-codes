import React from 'react';
import styles from './index.css';
import { formatMessage } from "umi-plugin-locale";

const BasicLayout: React.FC = props => {
  return (
    <div className={styles.normal}>
      <h1 className={styles.title}>{formatMessage({ id: 'index.welcome' })}</h1>
      {props.children}
    </div>
  );
};

export default BasicLayout;
