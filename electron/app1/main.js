const { app, BrowserWindow } = require('electron')
const path = require('path');

function newWindow() {
    const mywindow = new BrowserWindow({
        width: 1280,
        height: 720,
        webPreferences: {
            preload: path.join(__dirname, 'preload.js')
        }
    });
    mywindow.loadFile('html/index.html')
    mywindow.on('closed', () => {
        mywindow = null
    })
}

app.whenReady().then(() => {
    newWindow()

    app.on('activate', () => {
        if (BrowserWindow.getAllWindows().length == 0) newWindow()
    })
})
app.on('window-all-closed', () => { if (process.platform != 'darwin') app.quit(); })