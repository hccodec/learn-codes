import paper from "paper";
import store from "../../store/store";
import history from "../history";
import { createLayer } from "../shared";
import { DrawAction } from "../action";

let local = {
  path: null,
  group: null
};

function onMouseDown(event) {
  let layer = createLayer();
  var pointText = new PointText(event.point);
  pointText.fillColor = store.getters.shapeArgs.color;
  pointText.scale(store.getters.shapeArgs.size);

  pointText.strokeWidth = store.getters.shapeArgs.size;
  pointText.content = store.getters.notationArgs.notation; //"预置标注"; //window.textNotation;
  if (pointText.content === "4" || pointText.content === "7")
    pointText.fontFamily = "ZapfDingbats BT";
  else pointText.fontFamily = "华文行楷";

  layer.addChild(pointText);
}

export const tool = new paper.Tool();
tool.onMouseDown = onMouseDown;
