
package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/")
    String home() {
        return "		从科学思维上讲，我自身目前唯一能做的是好好宅在家里，远离病毒，避免成为“人传人”链条上的一员。这虽小，但也是每个普通百姓都能为国家贡献的绵薄之力。从科学思维的角度简单来讲，主要有以下几个方面。\n\n        1. 坚持防疫，保持良好的卫生习惯：\n        \n           1. 坚持勤洗手洗脸，坚持合理作息，提高免疫力。\n        \n           2. 出门要坚持戴口罩，回家要立刻全面消毒。\n        \n           3. 勤打扫室内卫生、勤通风，保持健康的室内生活环境。\n        \n        2. 理性防疫，对疫情保持正确认知：\n        \n           在信息化和全民防疫的社会时代背景下，我们实现宅在家中远程办公、远程交流。良莠不齐的网络信息更是广大群众主要的信息来源，如何正确分辨真假信息和过滤信息更是首要任务。\n        \n           1. 从官方获取信息来源，理性对待本地群发出的各种小道消息。\n        \n              相比各种小道消息，官方信息不仅来源可靠，更给予积极科学的舆论导向。而辩证来看，小道消息传播速度有时快于官媒、信息来源面与覆盖面都更加贴近自身周围的环境，也有一定参考价值。但同时由于信息发布方未知，会产生大量的虚假消息——（1.误报；2.社会不安定成分：煽动恐慌情绪、反动）等。\n        \n               相信祖国、相信政府相信党\n        \n              “无论你身在哪里，祖国永远是你坚强的后盾”\n        \n              防疫取得成效；众志成城做贡献";
    }

    /**
     * 本地访问内容地址 ：http://localhost:8080/hello
     * 
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public String helloHtml(@RequestParam("id") String aString) {
        return "返回相应结果：" + aString;
    }
}