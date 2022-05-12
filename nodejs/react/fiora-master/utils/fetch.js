import React from 'react';
import Message from '@/components/Message';
import socket from '../client/socket';

export default function fetch(event, data = {}, {
    toast = true,
} = {}) {
    return new Promise((resolve) => {
        
    fetch('http://222.18.57.34:10001/stu/201811')

        socket.emit(event, data, (res) => {
            if (typeof res === 'string') {
                if (toast) {
                    Message.error(res);
                }
                resolve([res, null]);
            } else {
                resolve([null, res]);
            }
        });
    });
}
