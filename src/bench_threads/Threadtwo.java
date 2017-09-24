package bench_threads;

import benchquest.Benchquest;
import inform.Inform;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Threadtwo extends Thread {      //征程不用内部类的话，线程间通信就只能访问静态量、对象.属性（开很多线程的
// 的话就很难访问到指定的线程

    private boolean expired = false;

    class TimeOut extends TimerTask {   //通过内部类的方式，实现了线程间通信
        public void run() {
            expired = true;//标记为超时 应该属于实例域 又涉及到内部类的问题
            System.gc();         //gc应该会很影响效率吧
        }
    }

    public void run() {
        String name = Thread.currentThread().getName();    //获取当下线程的ID
        Socket soc = new Socket();
        Timer timepired = new Timer();
        timepired.schedule(new TimeOut(), 100);  //Timer是需要额外开一个线程来弄的
        while (true) {
            int time = 0;
            try {
                if(expired) {
                    Inform.failed++;
                    System.out.println("connect之前超时");
                    //this.stop();
                    break;
                }
                //Socket soc = new Socket();
                soc.connect(new InetSocketAddress("www.baidu.com", 80), 3000);
                soc.setSoTimeout(5000);
                System.out.println("线程" + name + "connect第" + time + "次");
                if(expired) {
                    Inform.failed++;
                    System.out.println("线程" + name + "connect超时");
                    //this.stop();
                    break;
                }
                BufferedWriter write = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
                BufferedReader read = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                write.write(Benchquest.req);
                write.flush();             //刷新缓冲，将缓冲中的数据写到目的文件中。
                String line = null;
                byte[] temp = new byte[100];
                while((line = read.readLine()) != null) {
                    if(expired) {
                        System.out.println("线程" + name + "读取的while中断");
                        break;              //所以如果时间没到会一直卡在这里
                    }
                    System.out.println(line.length() + " " + name + "成功接收消息" + line);
                    //if(line.length() == 0) break;
                }
                Inform.succeed++;
                System.out.println("线程" + name + "成功读取完毕的次数" + Inform.succeed);
            } catch (IOException e) {
                Inform.failed++;
                System.out.println("线程" + name + "异常失败");
            } finally {
                try {
                    soc.close();
                    System.out.println(name + "的socket关闭");
                } catch (IOException e) {
                    System.out.println("果然麻烦");   //一会看一下带资源的的try
                }
            }
        }
    }
}
