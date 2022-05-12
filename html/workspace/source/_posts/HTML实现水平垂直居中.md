---
title: HTML实现水平垂直居中
date: 2022-03-04 10:20:34
tags:
---
##CSS/CSS3 实现 居中（水平&垂直）

1. 水平居中：行内元素
    把行内元素放在一个属性块（display：block）元素中，然后设置父层元素属性居中：
    ```css
    .test {
        text-align:center;
    }
    ```

2. 水平居中：块状元素
    设置外边距
    ```css
    .test {
        margin: 100px auto;
    }
    ```
3. 水平居中：多个块状元素
    把块状元素属性（display：inline-block），然后设置父层元素属性居中：
    ```css
    .test {
        text-align:center;
    }
    ```
4. 水平居中：多个块状元素（flexbox布局实现）
    把块状元素的父元素属性 display:flex和justify-content:center，如下设置：
    ```css
    .test {
        text-align:center;
    }
5. 垂直居中：单行的行内元素
    设置height和line-height属性
    ```css
    .test {
        height: 100px;
        line-height:100px; 
    }
    ```
6. 垂直居中：多行的行内元素
    给要居中的父元素设置display:table-cell和vertical-align:middle属性
    ```css
    .test {
        background: red;
        width: 200px;
        height: 200px;
        /* 以下属性垂直居中 */
        display: table-cell;
        vertical-align:middle;
    }
    ```
7. 垂直居中：已知高度的块状元素
    给要居中的元素设置如下属性
    ```css
    .test {
        top: 50%;
        margin-top: -50px;  /* margin-top值为自身高度的一半 */
        position: absolute;
        padding:0;
    }
    ```
8. 水平垂直居中：已知高度和宽度的元素
    给要居中的元素设置如下属性
    （1）
    ```css
    .test {
        position: absolute;
        margin:auto;
        left:0;
        top:0;
        right:0;
        bottom:0;
    }
    ```
    （2）
    ```css
    .test{
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -75px;  /* 设置margin-left / margin-top 为自身高度的一半 */
        margin-left: -75px;
    }
    ```
9. 水平垂直居中：未知高度和宽度元素
    给要居中的元素设置如下属性
    ```css
    .test {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);  /* 使用css3的transform来实现 */
    }
    ```
10. 水平垂直居中：可用flex
    设置如下属性
    ```css
    .test {
        display: flex;
        justify-content:center;
        align-items: center;
        /* 注意这里需要设置高度来查看垂直居中效果 */
        background: #AAA;
        height: 300px;
    }
    ```


-----
参考链接:
1. https://segmentfault.com/a/1190000019666702