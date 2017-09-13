package benchquest;

public class Benchquest {
    public static String req = "";
    public void build_request(String url) {
        req += "GET / HTTP/1.1\r\n";
        req += "Host: www.baidu.com\r\n";
        req += "\r\n";
    }
}