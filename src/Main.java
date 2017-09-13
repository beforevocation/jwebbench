import benchquest.Benchquest;
import bench.Benchpre;
import inform.Inform;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        Benchquest ben = new Benchquest();            //第一步：写request请求内容
        ben.build_request("www.baidu.com");
        System.out.println(Benchquest.req);

        Benchpre pre = new Benchpre();
        pre.trysocket();                              //第二步：尝试建立连接
        pre.new_threads();                            //第三步：建立子线程
        Inform.informUser();
    }
}
