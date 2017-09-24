package bench_threads;

import benchquest.Benchquest;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Threadone extends Thread {
    public void run() {
        try {
            while(true) {
                Socket soc = new Socket();          //不会发生IOException的
                soc.connect(new InetSocketAddress("www.baidu.com", 80), 5000);    //会发生IOException


                soc.setSoTimeout(5000);

                BufferedReader read = new BufferedReader(new InputStreamReader(soc.getInputStream())); //IOE
                BufferedWriter write = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())); //IOE

                write.write(Benchquest.req);  //IOE
                write.flush();   //IOE

                /*---------------------readLine()是阻塞的-----------------------------*/
                String line = null;
                try {
                    while ((line = read.readLine()) != null) {       //效率上肯定就不过关，不停地改变String的值
                        System.out.println("成功读取到内容：" + line);
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("read超时");
                    soc.close();
                }
                /*-------------------------------------------------------------------*/

                soc.close();
            }
        } catch (IOException e) {
            System.out.println("IOE");
        }
    }
}
