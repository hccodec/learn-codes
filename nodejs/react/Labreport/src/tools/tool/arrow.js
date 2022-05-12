import paper from "paper";
import store from "../../store/store";
import { createLayer } from "../shared";
import history from "../history";
import { DrawAction } from "../action";

// import activeCurrentByCursor from "./util";
var vectorStart, vector, vectorPrevious;
var vectorItem, items;
let local = {
  path: null,
  group: null
};
function drawVector(drag) {
  if (vectorItem) vectorItem.remove();
  items = [];

  var arrowVector = vector.normalize(20);
  var end = vectorStart.add(vector);

  vectorItem = new Group([
    new Path([vectorStart, end]),
    new Path([
      end.add(arrowVector.rotate(135)),
      end,
      end.add(arrowVector.rotate(-135))
    ])
  ]);

  vectorItem.strokeWidth = store.getters.shapeArgs.size / 2;
  vectorItem.strokeColor = store.getters.shapeArgs.color;
  local.group = vectorItem;
  // Display:
}

function processVector(event, drag) {
  vector = event.point;
  vector = vector.subtract(vectorStart);
  drawVector(drag);
}
function onMouseDown(event) {
  vectorStart = event.point;
  processVector(event, true);
}
function onMouseDrag(event) {
  processVector(event, event.modifiers.shift);
}

function onMouseUp(event) {
  processVector(event, false);

  vectorPrevious = vector;
  let layer = createLayer();
  layer.addChild(local.group);
  console.log(local.group);
  // const action = new DrawAction({
  //   layer: local.path.layer.name,
  //   tool: store.getters.tool
  // });

  local.group = null;
  vectorItem = null;
}

export const tool = new paper.Tool();
tool.onMouseDrag = onMouseDrag;
tool.onMouseUp = onMouseUp;
tool.onMouseDown = onMouseDown;
