import Vue from "vue";
import Router from "vue-router";
import WhiteBoard from "@/components/WhiteBoard";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "WhiteBoard",
      component: WhiteBoard
    }
  ]
});
