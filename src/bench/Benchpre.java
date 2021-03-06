package bench;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import bench_threads.Threadone;
import bench_threads.Threadtwo;
import inform.Inform;

public class Benchpre {

    public void trysocket () throws IOException{         //尝试连接目标网站
        Socket soctry = new Socket();
        try {
            soctry.connect(new InetSocketAddress("www.baidu.com", 80), 3000);
            System.out.println("尝试连接成功！");
        } catch (ConnectException e) {
            System.out.println("Error:连接失败");
        } catch (SocketTimeoutException e) {
            System.out.println("Error:Socket连接失败");
        } finally {
            soctry.close();
            System.out.println("尝试连接的socket已关闭。");
        }
    }

    public void new_threads(){           //开线程

        Threadone[] childs = new Threadone[Inform.clinetnum];
        for(int i = 0; i < Inform.clinetnum; i++) {
            childs[i] = new Threadone();
            childs[i].start();
        }
    }
}
