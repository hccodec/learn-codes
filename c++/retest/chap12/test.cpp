#include <iostream>
#include <time.h>
using namespace std;

int main() {
    time_t now = time(0);

    char* dt;
    
    dt = asctime(localtime(&now));
    cout << "UTC 日期和时间：" << dt << endl;

    cout << "本地日期和时间：" << ctime(&now) << endl;

    dt = asctime(localtime(&now));
    cout << "UTC 日期和时间：" << dt << endl;

}