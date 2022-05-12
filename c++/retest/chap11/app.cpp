#include "windows.h"
#include <iostream>
using namespace std;


//6��������Ϣ�����ڹ��̣�
    //CALLBACK����__stdcall��������˳��  ��������������ջ�������ں�������ǰ�Զ���ն�ջ����
LRESULT CALLBACK WindowProc(
    HWND hwnd,              //��Ϣ�������ھ��
    UINT uMsg,               //������Ϣ���ƣ�WM_XXX
    WPARAM wparam,           //���̸�����Ϣ
    LPARAM lparam                //��긽����Ϣ
)
{

    switch (uMsg)
    {
    case WM_CLOSE:
        DestroyWindow(hwnd);                //����WM_DESTROY
        break;
    case WM_DESTROY:
        PostQuitMessage(0);                  //�����˳���Ϣ
        break;
    case WM_LBUTTONDOWN:                      //������������Ϣ
    {
        int xPos;
        int yPos;
        xPos = LOWORD(lparam);
        yPos = HIWORD(lparam);
        //��ӡ������ͨ��ͬMessageBox
        TCHAR buf[1024];
        cout<<"CodePage:"<<GetConsoleOutputCP()<<endl;
        wsprintf(buf, TEXT("x=%d,y=%d"), xPos, yPos);
        //�������ڡ���ʾ���ݡ��������ݡ�������������
        MessageBox(hwnd, buf, TEXT("��갴��"), MB_OK);
        break;
    }
    case WM_KEYDOWN:       //������Ϣ
        MessageBox(hwnd, TEXT("���̰���"), TEXT("������Ϣ"), MB_OK);
        break;
    case WM_PAINT:     //��ͼ
    {
        PAINTSTRUCT ps;             //��ͼ�ṹ��
        HDC hdc = BeginPaint(hwnd, &ps);
        //��������
        TextOut(hdc, 100, 100, TEXT("ѧ����Ϣ"), strlen("��������"));
        EndPaint(hwnd, &ps);
        break;
    }
    default:
        break;
    }
    //Ĭ�Ϸ�ʽ����
    return DefWindowProc(hwnd, uMsg, wparam, lparam);
}


int WINAPI WinMain(
    HINSTANCE hInstance,           //Ӧ�ó���ʵ�����
    HINSTANCE hPrevInstance,       //ǰһ��Ӧ�ó���ʵ���������win32�����»���ֵNULL����������
    LPSTR lpCmdLine,             //char *argv[]  �����в�������
    int nShowCmd)                //������ʾ��������С��
{
    //ʵ�ֵײ㴰��  6����
    //1����ƴ�����
    //2��ע�ᴰ����
    //3������������
    //4����ʾ�͸��´���
    //5��ͨ��ѭ��ȡ��Ϣ
    //6��������Ϣ�����ڹ��̣�

    



    //1����ƴ���
    WNDCLASS wc;
    wc.cbClsExtra = 0;//�������ڴ棬ͨ��Ϊ��
    wc.cbWndExtra = 0;//���ڶ�����ڴ棬ͨ��Ϊ��
    wc.hbrBackground = (HBRUSH)GetStockObject(WHITE_BRUSH);//���ñ���
    wc.hCursor = LoadCursor(NULL, IDC_HAND);//���ù�꣬�����һ������ΪNULL������ʹ��ϵͳ�ṩ��Ĭ�Ϲ��
    wc.hIcon = LoadIcon(NULL, IDI_WARNING);
    wc.hInstance = hInstance;//��ǰʵ�������WinMain�������βμ���
    wc.lpfnWndProc = WindowProc;//���ڹ��̺������ص����������ƿ��������
    wc.lpszClassName = TEXT("WINDOW");//ָ����������
    wc.lpszMenuName = NULL;//�˵�����û����NULL
    wc.style = 0;//0����Ĭ�Ϸ��

    //2��ע�ᴰ����
    RegisterClass(&wc);

    //3����������
    /*
    lpClassName,    //����
    lpWindowName,    //������
    dwStyle,        //��ʾ���  WS_OVERLAPPEDWINDOW
    x, y,\    //�������ڵ���ʼ����  CW_USEDEFAULT
    nWidth,nHeight,    //��������Ĭ�Ͽ��
    hWndParent,        //�����ڣ�NULL
    hMenu,       �˵�  NULL
    hInstance,    ʵ�������hInstance
    lpParam       ��������   NULL
    */
    HWND hwnd = CreateWindow(wc.lpszClassName, TEXT("TEXT WINDOW"), WS_OVERLAPPEDWINDOW,
        CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT ,
        NULL, NULL, hInstance, NULL);
    //4����ʾ�͸���
    ShowWindow(hwnd, SW_SHOWNORMAL);
    UpdateWindow(hwnd);
    //5��ͨ��ѭ��ȡ��Ϣ

    /*
    HWND        hwnd;                  //������
    UINT        message;               //��Ϣ����   WM_XXXXX��WINDOW MESSAGE)
    WPARAM      wParam;                //������Ϣ    ����
    LPARAM      lParam;                //������Ϣ    ������Ҽ�
    DWORD       time;                  //��Ϣ����ʱ�䣬
    POINT       pt;                    //������Ϣ   ���   x��y����
    */
    MSG msg;
    while(1)
    {
        /*
        ����
        _Out_ LPMSG lpMsg,               ��Ϣ�ṹ��
        _In_opt_ HWND hWnd,            NULL  �������д�����Ϣ
        _In_ UINT wMsgFilterMin,            ������С��Ϣ����
        _In_ UINT wMsgFilterMax             ���������Ϣ����       ��д0������������Ϣ
        */
        if (GetMessage(&msg, NULL,0,0) == FALSE)
        {
            break;
        }
        TranslateMessage(&msg);
        DispatchMessage(&msg);
    }
    return 0;
}