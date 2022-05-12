#include <windows.h>

LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wparam, LPARAM lparam);

int __stdcall WinMain(HINSTANCE hinstance, HINSTANCE hprevinstance, char *cmdline, int nshowcmd)
{

	WNDCLASS wc;
	wc.cbClsExtra = 0;
	wc.cbWndExtra = 0;
	wc.hbrBackground = (HBRUSH)GetStockObject(WHITE_BRUSH);
	wc.hCursor = LoadCursor(0, IDC_ARROW);
	wc.hIcon = NULL;
	wc.hInstance = hinstance;
	wc.lpfnWndProc = WndProc;
	wc.lpszClassName = "WND";
	wc.lpszMenuName = NULL;
	wc.style = CS_HREDRAW | CS_VREDRAW;
	RegisterClass(&wc);
	HWND hwnd = CreateWindow("WND", "主程序界面", WS_OVERLAPPEDWINDOW, 300, 100, 800, 500, 0, 0, hinstance, 0);

	ShowWindow(hwnd, SW_SHOW);
	UpdateWindow(hwnd);
	MSG msg;

	while (GetMessage(&msg, hwnd, 0, 0) > 0)
	{

		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}
	return msg.wParam;
}

LRESULT __stdcall WndProc(HWND hwnd, UINT msg, WPARAM wparam, LPARAM lparam)
{

	return DefWindowProc(hwnd, msg, wparam, lparam);
}