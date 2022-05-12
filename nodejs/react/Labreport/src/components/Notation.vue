<template>
  <div class="Notation">
    <b-form-input id="input-notation" v-model="notation" placeholder="输入批注"></b-form-input>
    <div>
      <b-form-select v-model="notation" :options="options" :select-size="10"></b-form-select>
    </div>
    <div>
      <b-form-select v-model="notation" :options="options"></b-form-select>
    </div>
  </div>
</template>

<script>
export default {
  props: ["notation2", "onSelectNotation"],
  data() {
    return {
      notation: "",
      options: [
        "量纲错误",
        "计算误差偏大",
        "书写不规范",
        "作图不规范",
        "超长批注，展示不同显示效果"
      ]
    };
  },
  methods: {},
  watch: {
    notation(val) {
      this.onSelectNotation(val);
    }
  }
};
</script>


<style lang="scss">
.Notation {
  font-size: 6px;
}
</style>
