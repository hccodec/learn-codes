<template>
  <div class="mainPanel">
    <!-- Actions panel -->
    <div class="actionsPanel">
      <!-- Tool select -->
      <panelToolIcon
        @click.native="toggleToolSettings"
        :toolColor="toolColor"
        :isActive="tool === 'pencil' || tool === 'brush'"
        :icon="activeTool"
      />
      <!-- Eraser select -->
      <panelToolIcon
        @click.native="toggleEraserSettings(); setWhiteboardTool('eraser')"
        toolColor="#133337"
        :isActive="tool === 'eraser'"
        icon="eraser"
      />
      <!-- Shape select -->
      <panelToolIcon
        @click.native="toggleShapeSettings"
        :toolColor="shapeColor"
        :isActive="tool === 'circle' || tool === 'square' || tool === 'triangle' || tool === 'line' || tool === 'arrow'"
        :icon="activeShape"
      />
      <!-- Notation select -->
      <panelToolIcon
        @click.native="toggleNotationSettings(); setWhiteboardTool('notation')"
        :toolColor="shapeColor"
        :isActive="tool === 'notation'"
        icon="text-height"
      />
    </div>

    <!-- Action settings settings -->
    <div class="actionSettingsPanel">
      <!-- Tool settings -->
      <div v-on:mouseleave="toggleToolSettings">
        <panelToolSettings v-if="isToolSettingsOpened">
          <!-- SettingsActions -->
          <div class="settingsActions" slot="settingsActions">
            <!-- Pencil select -->
            <panelToolIcon
              @click.native="setWhiteboardTool('pencil')"
              :toolColor="toolColor"
              :isActive="tool === 'pencil'"
              icon="pencil-alt"
            />
            <!-- Brush select -->
            <panelToolIcon
              @click.native="setWhiteboardTool('brush')"
              :toolColor="toolColor"
              :isActive="tool === 'brush'"
              icon="paint-brush"
            />
          </div>
          <!-- ColorPicker -->
          <colorPicker
            :onSelectColor="setToolColor"
            class="settingsColorPicker"
            slot="settingsColorPicker"
            :colors="colors"
          />
          <!-- Slider -->
          <rangeSlider
            :onChange="setToolSize"
            :min="0"
            :max="10"
            :value="toolSize"
            class="settingsSlider"
            slot="slider"
          />
        </panelToolSettings>
      </div>
      <!-- Eraser settings -->
      <div v-on:mouseleave="toggleEraserSettings">
        <panelToolSettings v-if="isEraserSettingsOpened">
          <!-- Slider -->
          <rangeSlider
            :onChange="setEraserSize"
            :min="0"
            :max="24"
            :value="eraserSize"
            class="settingsSlider"
            slot="slider"
          />
        </panelToolSettings>
      </div>
      <!-- Shape settings -->
      <div v-on:mouseleave="toggleShapeSettings">
        <panelToolSettings v-if="isShapeSettingsOpened">
          <!-- SettingsActions -->
          <div class="settingsActions" slot="settingsActions">
            <!-- Circle select -->
            <panelToolIcon
              @click.native="setWhiteboardTool('circle')"
              :toolColor="shapeColor"
              :isActive="tool === 'circle'"
              icon="circle"
            />
            <!-- <panelToolIcon
            @click.native="setWhiteboardTool('text')"
            :toolColor="shapeColor"
            :isActive="tool === 'text'"
            icon="text-height"
            />-->
            <!-- Square select -->
            <panelToolIcon
              @click.native="setWhiteboardTool('square')"
              :toolColor="shapeColor"
              :isActive="tool === 'square'"
              icon="square"
            />
            <!-- Triangle select -->
            <panelToolIcon
              @click.native="setWhiteboardTool('triangle')"
              :toolColor="shapeColor"
              :isActive="tool === 'triangle'"
              icon="exclamation-triangle"
            />
            <!-- Line select -->
            <panelToolIcon
              @click.native="setWhiteboardTool('line')"
              :toolColor="shapeColor"
              :isActive="tool === 'line'"
              icon="slash"
            />
            <!-- Line select -->
            <panelToolIcon
              @click.native="setWhiteboardTool('arrow')"
              :toolColor="shapeColor"
              :isActive="tool === 'arrow'"
              icon="arrow-up"
            />
          </div>
          <!-- ColorPicker -->
          <colorPicker
            :onSelectColor="setShapeColor"
            class="settingsColorPicker"
            slot="settingsColorPicker"
            :colors="colors"
          />
          <!-- Slider -->
          <rangeSlider
            :onChange="setShapeSize"
            :min="0"
            :max="6"
            :value="shapeSize"
            class="settingsSlider"
            slot="slider"
          />
        </panelToolSettings>
      </div>
      <div v-on:mouseleave="toggleNotationSettings">
        <panelToolSettings v-if="isNotationSettingsOpened">
          <!-- SettingsActions -->
          <div class="settingsActions" slot="settingsActions">
            <!-- Circle select -->
            <panelToolIcon
              @click.native="setWhiteboardTool('notation')"
              :toolColor="shapeColor"
              :isActive="tool === 'notation'"
              icon="text-height"
            />
          </div>
          <!-- ColorPicker -->
          <colorPicker
            :onSelectColor="setShapeColor"
            class="settingsColorPicker"
            slot="settingsColorPicker"
            :colors="colors"
          />
          <!-- Slider -->
          <rangeSlider
            :onChange="setShapeSize"
            :min="0"
            :max="6"
            :value="shapeSize"
            class="settingsSlider"
            slot="slider"
          />
          <!-- Slider -->
          <notation slot="settingsNotation" :onSelectNotation="setNotation" :notation2="notation"/>
        </panelToolSettings>
      </div>
    </div>
  </div>
</template>

<script>
import PanelToolIcon from "./PanelToolIcon";
import PanelToolSettings from "./PanelToolSettings";
import ColorPicker from "../ColorPicker";
import Notation from "../Notation";
import RangeSlider from "../RangeSlider";
import colorPalette from "../../config/colorPalette.js";
export default {
  components: {
    panelToolIcon: PanelToolIcon,
    panelToolSettings: PanelToolSettings,
    rangeSlider: RangeSlider,
    colorPicker: ColorPicker,
    notation: Notation
  },
  data() {
    return {
      isToolSettingsOpened: false,
      isEraserSettingsOpened: false,
      isShapeSettingsOpened: false,
      isNotationSettingsOpened: false,
      colors: colorPalette
    };
  },
  methods: {
    // Toggle

    onmouseleave: function() {
      this.toggleToolSettings();
    },
    toggleToolSettings() {
      this.isToolSettingsOpened = !this.isToolSettingsOpened;
      this.isEraserSettingsOpened = false;
      this.isShapeSettingsOpened = false;
      this.isNotationSettingsOpened = false;
    },
    toggleEraserSettings() {
      this.isEraserSettingsOpened = !this.isEraserSettingsOpened;
      this.isToolSettingsOpened = false;
      this.isShapeSettingsOpened = false;
      this.isNotationSettingsOpened = false;
    },
    toggleShapeSettings() {
      this.isShapeSettingsOpened = !this.isShapeSettingsOpened;
      this.isEraserSettingsOpened = false;
      this.isToolSettingsOpened = false;
      this.isNotationSettingsOpened = false;
    },
    toggleNotationSettings() {
      this.isNotationSettingsOpened = !this.isNotationSettingsOpened;
      this.isEraserSettingsOpened = false;
      this.isToolSettingsOpened = false;
      this.isShapeSettingsOpened = false;
      console.log(this.isNotationSettingsOpened);
    },
    // Set Color
    setToolColor(color) {
      this.$store.dispatch("setToolColor", color);
    },
    setShapeColor(color) {
      this.$store.dispatch("setShapeColor", color);
    },
    setNotation(notation) {
      this.$store.dispatch("setNotation", notation);
    },
    // Set size
    setToolSize(size) {
      this.$store.dispatch("setToolSize", size);
    },
    setEraserSize(size) {
      this.$store.dispatch("setEraserSize", size);
    },
    setShapeSize(size) {
      this.$store.dispatch("setShapeSize", size);
    },
    // Set tool
    setWhiteboardTool(tool) {
      this.$store.dispatch("setWhiteboardTool", tool);
    }
  },
  computed: {
    // Acitve
    activeTool: function() {
      if (this.tool === "pencil") {
        return "pencil-alt";
      } else if (this.tool === "brush") {
        return "paint-brush";
      } else {
        return "pencil-alt";
      }
    },
    activeShape: function() {
      if (this.tool === "cricle") {
        return "circle";
      } else if (this.tool === "square") {
        return "square";
      } else if (this.tool === "triangle") {
        return "exclamation-triangle";
      } else if (this.tool === "line") {
        return "slash";
      } else if (this.tool === "arrow") {
        return "arrow-up";
      } else {
        return "circle";
      }
    },
    tool: function() {
      return this.$store.getters.tool;
    },
    // Color
    toolColor: function() {
      return this.$store.getters.toolArgs.color;
    },
    shapeColor: function() {
      return this.$store.getters.shapeArgs.color;
    },
    // Size
    toolSize: function() {
      return this.$store.getters.toolArgs.size;
    },
    eraserSize: function() {
      return this.$store.getters.eraserArgs.size;
    },
    shapeSize: function() {
      return this.$store.getters.shapeArgs.size;
    },
    notation: function() {
      return this.$store.getters.notationArgs.notation;
    }
  },
  mounted() {
    this.$store.dispatch("setWhiteboardTool", "pencil");
  }
};
</script>


<style lang="scss">
.mainPanel {
  position: fixed;
  display: flex;
  flex-direction: column;
  top: 50px;
  left: 30px;
  padding: 10px;
  background: #fff;
  border-radius: 5px;
  box-shadow: 0px 10px 30px 8px rgba(0, 0, 0, 0.4);
  .actionsPanel {
    .tool:not(:last-child) {
      margin-bottom: 5px;
    }
  }
}
</style>
