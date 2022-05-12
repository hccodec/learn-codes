import paper from "paper";
import store from "../../store/store";
import { createLayer } from "../shared";
import history from "../history";
import { DrawAction } from "../action";

// import activeCurrentByCursor from "./util";

let local = {
  path: null
};

function onMouseDrag(event) {
  if (local.path) {
    local.path.remove();
  }
  let point = event.point;

  if (Key.isDown("shift")) {
    point.y = event.downPoint.y;
  } else if (Key.isDown("control")) {
    point.x = event.downPoint.x;
  } else {
  }

  local.path = new paper.Path.Line({
    from: event.downPoint,
    to: point,
    strokeColor: "red"
  });
  local.path.strokeColor = store.getters.shapeArgs.color;
  local.path.strokeWidth = store.getters.shapeArgs.size;
}

function onMouseUp() {
  let layer = createLayer();
  layer.addChild(local.path);
  const action = new DrawAction({
    layer: local.path.layer.name,
    tool: store.getters.tool,
    points: local.path.segments.map(seg => {
      return {
        x: seg._point._x,
        y: seg._point._y
      };
    })
  });
  history.add(action);
  local.path = null;
}

export const tool = new paper.Tool();
tool.onMouseDrag = onMouseDrag;
tool.onMouseUp = onMouseUp;
