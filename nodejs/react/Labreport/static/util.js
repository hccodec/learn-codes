function activeCurrentByCursor(sname) {
  var view = paper.View._viewsById[sname];
  if (!view) return;
  view._project.activate();
}

export default activeCurrentByCursor;
