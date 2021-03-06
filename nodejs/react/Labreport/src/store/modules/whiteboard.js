import tools from "../../tools/tool/tools";

const state = {
  tool: null,
  toolArgs: {
    size: 2,
    color: "#FF0000"
  },
  eraserArgs: {
    size: 2,
    color: "#133337"
  },
  shapeArgs: {
    size: 2,
    color: "#FF0000"
  },
  notationArgs: {
    notation: "预置标注",
    size: 2,
    color: "#FF0000"
  }
};

const mutations = {
  //ToolArgs
  SET_TOOL_COLOR(state, toolColor) {
    state.toolArgs.color = toolColor;
  },
  SET_TOOL_SIZE(state, toolSize) {
    state.toolArgs.size = toolSize;
  },
  //EraserArgs
  SET_ERASER_SIZE(state, eraserSize) {
    state.eraserArgs.size = eraserSize;
  },
  SET_ERASER_COLOR(state, eraserColor) {
    state.eraserArgs.color = eraserColor;
  },
  //ShapesArgs
  SET_SHAPE_SIZE(state, shapeSize) {
    state.shapeArgs.size = shapeSize;
  },
  SET_SHAPE_COLOR(state, shapeColor) {
    state.shapeArgs.color = shapeColor;
  },
  SET_NOTATION(state, notation) {
    state.notationArgs.notation = notation;
  },

  //Tool
  SET_WHITEBOARD_TOOL(state, tool) {
    state.tool = tool;
    if (tools[tool]) {
      tools[tool].activate();
    }
  }
};

const actions = {
  //ToolArgs
  setToolColor: ({ commit }, toolColor) => {
    commit("SET_TOOL_COLOR", toolColor);
  },
  setToolSize: ({ commit }, toolSize) => {
    commit("SET_TOOL_SIZE", toolSize);
  },
  //Eraser
  setEraserSize: ({ commit }, eraserSize) => {
    commit("SET_ERASER_SIZE", eraserSize);
  },
  setEraserColor: ({ commit }, eraserColor) => {
    commit("SET_ERASER_COLOR", eraserColor);
  },
  //ShapeArgs
  setShapeSize: ({ commit }, shapeSize) => {
    commit("SET_SHAPE_SIZE", shapeSize);
  },
  setShapeColor: ({ commit }, shapeColor) => {
    commit("SET_SHAPE_COLOR", shapeColor);
  },
  setNotation: ({ commit }, notation) => {
    commit("SET_NOTATION", notation);
  },
  //Tool
  setWhiteboardTool: ({ commit }, tool) => {
    commit("SET_WHITEBOARD_TOOL", tool);
  }
};

const getters = {
  tool(state) {
    return state.tool;
  },
  toolArgs(state) {
    return state.toolArgs;
  },
  eraserArgs(state) {
    return state.eraserArgs;
  },
  shapeArgs(state) {
    return state.shapeArgs;
  },
  notationArgs(state) {
    return state.notationArgs;
  }
};

export default {
  state,
  mutations,
  actions,
  getters
};
