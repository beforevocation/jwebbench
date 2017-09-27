package bench_threads;

import benchquest.Benchquest;
import inform.Inform;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Timer;
import java.util.TimerTask;

public class Threadone extends Thread {

    boolean running = true;

    public void run() {

        class TimerOut extends TimerTask {
            public void run () {
                running = false;
                Thread.currentThread().stop();            //原来计时器线程也要手动关闭的；stop方法已经被废弃。
            }
        }
        Timer timer = new Timer();
        timer.schedule(new TimerOut(), Inform.benchtime);

        String name = Thread.currentThread().getName();   //当前线程的名字
        try {
            while(running) {
                Socket soc = new Socket();          //不会发生IOException的
                soc.connect(new InetSocketAddress("www.baidu.com", 80), 5000);    //会发生IOException
                //上面的时间是建立连接时的时间，如果超过了，就抛出异常

                soc.setSoTimeout(5000);     //两次读的操作的时间间隔超过设定值，就认为是阻塞了

                BufferedReader read = new BufferedReader(new InputStreamReader(soc.getInputStream())); //IOE
                BufferedWriter write = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())); //IOE

                write.write(Benchquest.req);  //IOE 通过socket向服务器写
                write.flush();   //IOE 把缓存中剩余的数据写给服务器

                /*---------------------readLine()是阻塞的-----------------------------*/
                String line = null;
                try {
                    while ((line = read.readLine()) != null) {       //效率上肯定就不过关，不停地改变String的值
                        System.out.println("线程" + name + "成功读取到内容：" + line);
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("read超时线程：" + name);
                    soc.close();
                }
                /*-------------------------------------------------------------------*/

                //soc.close();
                System.out.println("顺利执行结束线程" + name);            //打印信息，证明线程顺利执行结束
            }
        } catch (IOException e) {
            System.out.println("IOE");
        }
    }
}
