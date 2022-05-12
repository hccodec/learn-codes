using System;

namespace test
{
    class Testout
    {
        public int getParts(double n, out double frac)
        {
            int whole;

            whole = (int)n;

            frac = n - whole; //pass fractional小数 part back through frac 
            return whole;     //return integer portion 返回整数部分   
        }
    }

    class Useout
    {
        static void Main()
        {
            Testout Tout = new Testout();

            int i;
            double f;

            i = Tout.getParts(1234.56789, out f);

            Console.WriteLine("整数部分：" + i);
            Console.WriteLine("小数部分：{0:#.###}" , f);
            Console.WriteLine("小数部分：" + f);
            Console.ReadKey();　　//监听键盘事件，按任意键执行退出
        }
    }
}