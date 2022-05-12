---
title: React-Native 定义事件基类 EventBus
date: 2022-03-04 10:20:34
tags:
---
# 定义事件基类

# EventBus

1. 定义事件基类

   ```java
   public class BaseEvent {
   }
   ```

   

2. 定义具体事件类

   ```java
   public class FavorEvent extends BaseEvent {
       
       private int did;
   
       public FavorEvent() {}
       
       public FavorEvent(int did) { this.did = did; }
       
       public int getDid() { return did; }
       
       public void setDid(int did) { this.did = did; }
   }
   ```

3. 在需要监听此事件的地方向 EventBus 注册事件监听器

   ```java
   EventBus.getDefault().register(this);
   ```

4. 当需要取消注册事件监听器时

   ```java
   EventBus.getDefault().unregister(this);
   ```

   