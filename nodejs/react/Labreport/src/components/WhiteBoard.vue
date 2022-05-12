<template>
  <div>
    <canvas
      id="whiteboard"
      width="600"
      height="800"
      style="background-image: url(/static/report1.jpg);background-size:100% 100%;"
      v-on:mouseenter="mouseEnter('whiteboard')"
      v-on:mouseleave="mouseLeave('whiteboard')"
    ></canvas>
    <canvas
      id="whiteboard2"
      width="600"
      height="800"
      style="background-image: url(/static/report2.jpg);background-size:100% 100%;"
      @mouseenter="mouseEnter('whiteboard2')"
      @mouseleave="mouseLeave('whiteboard2')"
    ></canvas>
    <mainPanel></mainPanel>
    <!-- <div @click="openMask">打开弹窗</div> -->
    <dialog-bar v-model="sendVal" type="danger" title="我是标题" content="我是内容" v-on:cancel="clickCancel" @danger="clickDanger" @confirm="clickConfirm" dangerText="Delete"></dialog-bar>
  </div>
</template>

<script>
import paper, { PaperScope } from "paper";
import MainPanel from "./panel/MainPanel";
import NotationInput from "./NotationInput"
import dialogBar from './NotationInput'

import activeCurrentByCursor from "../../static/util.js";
// import colorPalette from "../../config/colorPalette.js";

export default {
  data() {
    return {
      screenWidth: "",
      screenHeight: "",
      scope1: "",
      scope2: "",
      sendVal: false,
    };
  },
  components: {
        'dialog-bar': dialogBar,
    mainPanel: MainPanel,
    notationInput: NotationInput
  },
  created() {
    // window.resizeTo(2500, 2700);
    paper.install(window);
    document.onkeydown = function(e) {
      var key = window.event.keyCode;
      // alert(key);
      switch (key) {
        case 90: // 'Z'
          this.nextPage();
          break;
        case 65: // 'A'
          this.prePage();
          break;
        case 86: // 'v'
          // this.$store.dispatch("setNotation", "4");
          this.openMask(0);
          break;
        case 88: // 'x'
          this.$store.dispatch("setNotation", "7");
          break;
        case 115: // 'f'
          document.getElementById("input-notation").focus();
          document.getElementById("input-notation").value = "成绩：";
          break;
      }
    }.bind(this);
  },
  watch: {
    screenWidth(val) {
      this.showMode2Pages();
    }
  },
  mounted() {
    paper.setup(document.getElementById("whiteboard"));
    paper.setup(document.getElementById("whiteboard2"));
    console.log(paper);
    this.showMode2Pages();
  },
  methods: {
    mouseEnter(canvasname) {
      activeCurrentByCursor(canvasname);
      paper.project.clear();
      var token = "_json_" + canvasname;
      paper.project.importJSON(window[token]);
    },
    mouseLeave(canvasname) {
      var token = "_json_" + canvasname;
      window[token] = paper.project.exportJSON();
    },
    showMode2Pages() {},
    nextPage() {
      document.getElementById("whiteboard").style.backgroundImage =
        "url(/static/report3.jpg)";
      document.getElementById("whiteboard2").style.backgroundImage =
        "url(/static/report4.jpg)";
    },
    prePage() {
      document.getElementById("whiteboard").style.backgroundImage =
        "url(/static/report1.jpg)";
      document.getElementById("whiteboard2").style.backgroundImage =
        "url(/static/report2.jpg)";
    },
    openMask(index){
            this.sendVal = true;
        },
        clickCancel(obj){
        },
        clickDanger(){
        },
        clickConfirm(info){
      this.$store.dispatch("setNotation",info );
        }
  }
};
</script>


<style lang="scss">
body {
  margin: 0;
  padding: 0;
  background: #ffffff;
}
canvas {
  background: #000000;
}

</style>
