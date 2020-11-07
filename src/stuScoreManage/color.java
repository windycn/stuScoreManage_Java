package stuScoreManage;
/**
 * @类名 控制台颜色/字体粗细控制类
 * @说明 用于控制控制台输出颜色和字体粗细
 * @Eclipse说明 需要安装插件Ansi Console
 * 在线安装地址http://www.mihai-nita.net/eclipse
 * @author 王晔
 * 
 */
public class color {
	 /** 用于单元测试 */
    public static void main(String[] args) {
        color.printc("黑色", color.BLACK);
        color.printc("白色", color.WHITE);
        color.printc("红色", color.RED);
        color.printc("绿色", color.GREEN);
        color.printc("黄色", color.YELLOW);
        color.printc("蓝色", color.BLUE);
        color.printc("品红", color.MAGENTA);
        color.printc("蓝绿", color.CYAN);
        color.printc("白底黑字", color.BLACK, color.BLACK_BACKGROUND);
        color.printc("黑底白字", color.WHITE, color.WHITE_BACKGROUND);
        color.printc("蓝底红字", color.RED, color.BLUE_BACKGROUND);
        // Eclipse控制台暂且不管用
        color.printc("加粗倾斜", color.BOLD, color.ITATIC);
        color.printc("黄底白字下划线", color.WHITE, color.YELLOW_BACKGROUND, color.UNDERLINE);
        color.printc("红字颜色反转", color.RED, color.REVERSE);
    }
 
    public static final int WHITE = 37;             // 白色
    public static final int WHITE_BACKGROUND = 40;  // 白色背景
    public static final int RED = 31;               // 红色
    public static final int RED_BACKGROUND = 41;    // 红色背景
    public static final int GREEN = 32;             // 绿色
    public static final int GREEN_BACKGROUND = 42;  // 绿色背景
    public static final int YELLOW = 33;            // 黄色
    public static final int YELLOW_BACKGROUND = 43; // 黄色背景
    public static final int BLUE = 34;              // 蓝色
    public static final int BLUE_BACKGROUND = 44;   // 蓝色背景
    public static final int MAGENTA = 35;           // 品红（洋红）
    public static final int MAGENTA_BACKGROUND = 45;// 品红背景
    public static final int CYAN = 36;              // 蓝绿
    public static final int CYAN_BACKGROUND = 46;   // 蓝绿背景
    public static final int BLACK = 30;             // 黑色
    public static final int BLACK_BACKGROUND = 47;  // 黑色背景
 
    public static final int BOLD = 1;       // 粗体
    public static final int ITATIC = 3;     // 斜体
    public static final int UNDERLINE = 4;  // 下划线
    public static final int REVERSE = 7;    // 反转
 
    private static String formatTrans(String txt, int... codes) {
        StringBuffer sb = new StringBuffer();
        for (int code : codes) {
            sb.append(code + ";");
        }
        String _code = sb.toString();
        if (_code.endsWith(";")) {
            _code = _code.substring(0, _code.length() - 1);
        }
        return (char) 27 + "[" + _code + "m" + txt + (char) 27 + "[0m";
    }
 
    /** 打印不换行 */
    public static void printc(String txt, int... codes) {
        System.out.print(formatTrans(txt, codes));
    }
 
    /** 打印并换行 */
    public static void printlnc(String txt, int... codes) {
        System.out.println(formatTrans(txt, codes));
    }
 
    /** 默认换行打印蓝色文字，红色可以直接调用System.err.println(xxx)*/
    public static void printlnc(String txt) {
        System.out.println(formatTrans(txt, new int[]{BLUE}));
    }
    
    /** 默认不换行打印蓝色文字，红色可以直接调用System.err.print(xxx)*/
    public static void printc(String txt) {
        System.out.print(formatTrans(txt, new int[]{BLUE}));
    }
}