package inform;

public class Inform {
    public static boolean timepired = false;
    public static int failed = 0;
    public static int succeed = 0;
    public static void informUser() {
        System.out.println("连接成功次数" + succeed);
        System.out.println("连接失败次数" + failed);
    }
}
