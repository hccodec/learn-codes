#include <am.h>
#include <klib.h>
#include <klib-macros.h>
#include <stdarg.h>

#if !defined(__ISA_NATIVE__) || defined(__NATIVE_USE_KLIB__)
int m_pow(int x, int y);
int print(char *dest,const char *fmt, va_list args) {
//	const char* fmt = format;
//	char *d = *dst;
	int isdst = 0;
	if(dest == 0)
		isdst = 0;
	else
		isdst = 1;
//	char* ap = (char *)args;
	int val,val1;
	char ch;
	int count;
	int res = 0;
	char *other = NULL;
//	va_start(args,fmt);
	while(*fmt != '\0')
	{
		switch(*fmt)
		{
			case '%':
			fmt++;
			switch(*fmt)
			{
				case 'd':
					val = va_arg(args,int );
					val1 = val;
					count = 0;
					while(val1)
					{
						count++;
						val1 /= 10;
					}
					res += count;
					val1 = val;
					while(count)
					{
						ch = val1 / m_pow(10,count - 1)+ '0';
						val1 %= m_pow(10,count - 1);
						if(isdst == 0)
						{	putch(ch);}
						else
						{	*dest++ = ch;}
							count--;
						}fmt++;
					break;
				case 's':
						other = va_arg(args,char *);
						while(*other != '\0'){
						if(isdst == 0)
						{putch(*other);}
            else
						{ *dest++ = *other;}
			//			res += strlen(other);
							res++;
			//			printf("res = %d",res);			
						}fmt++;
						break;
				default :break;}
					break;
			default :
						if(isdst == 0)
						{ putch(*fmt);}
            else
						{  *dest++ = *fmt;}
						fmt++;
						res += 1;
				//		printf("res = %d",res);
						break;
				}
	//		fmt++;
	}
	va_end(args);
	return res;



}


int  m_pow(int x, int y)
{
		int sum = 1;
		while(y--)
		{
			sum *= x;
		}
		return sum;
}



int printf(const char *fmt, ...) {
	va_list args;
	va_start(args,fmt);
	return print(0,fmt,args);
}

int vsprintf(char *out, const char *fmt, va_list ap) {
//  va_start(ap,fmt);
	return print(out,fmt,ap);
}

int sprintf(char *out, const char *fmt, ...) {
	va_list args;
	va_start(args,fmt);
	return print(out, fmt,args);
}

int snprintf(char *out, size_t n, const char *fmt, ...) {
return 0;	



}

int vsnprintf(char *out, size_t n, const char *fmt, va_list ap) {

return 0;}








#endif
