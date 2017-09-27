import benchquest.Benchquest;
import bench.Benchpre;
import inform.Inform;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) throws IOException{
        class TimerOut extends TimerTask {
            public void run() {
                System.exit(0);
            }
        }
        /*------------------初始化-----------------------------------------------------*/
        Inform.maintime = 20000;                      //主程序执行的时间
        Inform.benchtime = 10000;                     //每个线程执行的时间
        Inform.clinetnum = 10;                        //用户数／开的线程数
        Benchquest ben = new Benchquest();            //写request请求内容
        ben.build_request("www.baidu.com");       //改request的网址
        System.out.println(Benchquest.req);           //把request的信息打印出来
        /*----------------------------------------------------------------------------*/
        //Timer timer = new Timer();                  //main计时器，暂时不用
        //timer.schedule(new TimerOut(), 20000);
        Benchpre pre = new Benchpre();                //核心类：建立、开始 子线程

        pre.trysocket();                              //第二步：尝试建立连接
        pre.new_threads();                            //第三步：建立子线程
        Inform.informUser();                          //输出统计信息
    }
}
