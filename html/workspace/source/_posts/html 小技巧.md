---
title: html 小技巧
date: 2022-03-04 10:20:34
tags:
---
# HTML
## 页脚在最底部
1. 采用 flexbox 布局模型
    * 将 body 的 display 属性设置为 flex，然后将方向属性设置为列（纵向布局）；
    * 同时将 html 和 body 元素的高度设置为 100% 以使之充满整个屏幕
    ```html
    <!-- html代码 -->
    <div id="container">
        <header>HEADER</header>
        <section class="main">MAIN</section>
        <footer>FOOTER</footer>
    </div>
    ```
    ```css
    //css 代码
    * { margin: 0; padding: 0 }
    html, body { height: 100% }
    #container {
        display: flex;
        flex-direction: column;
        height: 100%
    }
    header { background: #999; flex: 0 0 auto }
    .main { background: orange; flex: 1 0 auto; }
    footer { background: #333; flex: 0 0 auto }
    ```