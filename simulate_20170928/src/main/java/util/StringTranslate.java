package util;

import quantum.QuantumState;

public class StringTranslate {
    //int转为2进制
    public static  String intToBits(int p, int num)
    {
        StringBuilder sb=new StringBuilder();
        for(int n=p;n>0;n/=2)
            sb.append(n%2);
        for(;sb.length()<num;)//补足0
            sb.append(0);
        sb=sb.reverse();
        return sb.toString();
    }
    //量子态使用String表示
    public static String statesToString(QuantumState qs, double b){
        Complex[] st = qs.getState();
        int num = qs.getParticles();
        int length;
        boolean flag=false;
        length=st.length;
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<length;i++)
        {
            if(st[i].abs()>0.00000000000001) {
                if(flag == true)
                    sb.append("+");
                String str;
                str = intToBits(i, num);
                sb.append("(");
                //System.out.println(i);
                sb.append(st[i].times(b).toString());
                sb.append(")|");
                sb.append(str);
                sb.append(">");
                flag = true;
            }
        }
        return sb.toString();
    }
    //将String转为复数
    public static Complex stringToComplex(String s)
    {
        int length=s.length();
        double re=0,im=0;
        int mark=0;
        int sign=0;
        char c;
        for(int i=0;i<length;i++)
        {
            c=s.charAt(i);
            StringBuilder sb=new StringBuilder();
            int j;
            if(c=='+'||c=='-')
            {
                sign=1;
                j=i+1;
            }
            else if(c=='i')
            {
                re=0;
                im=1.0;
                break;
            }
            else
            {
                j=i;
            }

            for(;j<length;j++)
            {
                char ch = s.charAt(j);
                if (ch == 'i' || ch == '+' || ch == '-')
                {
                    if (ch == 'i')
                    {
                        mark = 1;
                        i=j;
                        if(s.charAt(j-1)=='+'||s.charAt(j-1)=='-')
                        {
                            sb.append("1");
                        }
                        break;
                    }

                    if(ch=='+'||ch=='-')
                    {
                        i=j-1;
                        break;
                    }
                }
                if (ch != 'i')
                {
                    sb.append(ch);
                    i=j;
                }

            }
            double a=Double.parseDouble(sb.toString());
            if(sign==1)
            {
                if(mark==1)
                {
                    if(c=='-')
                        im=-1.0*a;
                    else
                        im=a;
                }
                else
                {
                    if(c=='-')
                        re=-1.0*a;
                    else
                        re=a;
                }
                sign=0;
            }
            else
            {
                if(mark==1)
                {

                    im=a;
                    //System.out.println("im="+im);
                }
                else
                    re=a;
            }
            mark=0;
        }
        return  new Complex(re,im);
    }
    //将复数矩阵转换为String矩阵
    public static String[][] complexToString(Complex[][] complex){
        int row = complex.length;
        int col = complex[0].length;
        String[][] s = new String[row][col];
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                s[i][j] = complex[i][j].toString();
            }
        }
        return s;
    }
    //将int矩阵转为String矩阵
    public static String[][] intToString(int[][] num) {
        int row = num.length;
        int col = num[0].length;
        String[][] s = new String[row][col];
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                int t1 = (num[i][j]%4)/2;
                int t2 = num[i][j]%2;
                s[i][j] = "ψ" + String.valueOf(t1) + String.valueOf(t2);
            }
        }
        return s;
    }
}
