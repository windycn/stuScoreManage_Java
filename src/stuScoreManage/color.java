package stuScoreManage;
/**
 * @���� ����̨��ɫ/�����ϸ������
 * @˵�� ���ڿ��ƿ���̨�����ɫ�������ϸ
 * @Eclipse˵�� ��Ҫ��װ���Ansi Console
 * ���߰�װ��ַhttp://www.mihai-nita.net/eclipse
 * @author ����
 * 
 */
public class color {
	 /** ���ڵ�Ԫ���� */
    public static void main(String[] args) {
        color.printc("��ɫ", color.BLACK);
        color.printc("��ɫ", color.WHITE);
        color.printc("��ɫ", color.RED);
        color.printc("��ɫ", color.GREEN);
        color.printc("��ɫ", color.YELLOW);
        color.printc("��ɫ", color.BLUE);
        color.printc("Ʒ��", color.MAGENTA);
        color.printc("����", color.CYAN);
        color.printc("�׵׺���", color.BLACK, color.BLACK_BACKGROUND);
        color.printc("�ڵװ���", color.WHITE, color.WHITE_BACKGROUND);
        color.printc("���׺���", color.RED, color.BLUE_BACKGROUND);
        // Eclipse����̨���Ҳ�����
        color.printc("�Ӵ���б", color.BOLD, color.ITATIC);
        color.printc("�Ƶװ����»���", color.WHITE, color.YELLOW_BACKGROUND, color.UNDERLINE);
        color.printc("������ɫ��ת", color.RED, color.REVERSE);
    }
 
    public static final int WHITE = 37;             // ��ɫ
    public static final int WHITE_BACKGROUND = 40;  // ��ɫ����
    public static final int RED = 31;               // ��ɫ
    public static final int RED_BACKGROUND = 41;    // ��ɫ����
    public static final int GREEN = 32;             // ��ɫ
    public static final int GREEN_BACKGROUND = 42;  // ��ɫ����
    public static final int YELLOW = 33;            // ��ɫ
    public static final int YELLOW_BACKGROUND = 43; // ��ɫ����
    public static final int BLUE = 34;              // ��ɫ
    public static final int BLUE_BACKGROUND = 44;   // ��ɫ����
    public static final int MAGENTA = 35;           // Ʒ�죨��죩
    public static final int MAGENTA_BACKGROUND = 45;// Ʒ�챳��
    public static final int CYAN = 36;              // ����
    public static final int CYAN_BACKGROUND = 46;   // ���̱���
    public static final int BLACK = 30;             // ��ɫ
    public static final int BLACK_BACKGROUND = 47;  // ��ɫ����
 
    public static final int BOLD = 1;       // ����
    public static final int ITATIC = 3;     // б��
    public static final int UNDERLINE = 4;  // �»���
    public static final int REVERSE = 7;    // ��ת
 
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
 
    /** ��ӡ������ */
    public static void printc(String txt, int... codes) {
        System.out.print(formatTrans(txt, codes));
    }
 
    /** ��ӡ������ */
    public static void printlnc(String txt, int... codes) {
        System.out.println(formatTrans(txt, codes));
    }
 
    /** Ĭ�ϻ��д�ӡ��ɫ���֣���ɫ����ֱ�ӵ���System.err.println(xxx)*/
    public static void printlnc(String txt) {
        System.out.println(formatTrans(txt, new int[]{BLUE}));
    }
    
    /** Ĭ�ϲ����д�ӡ��ɫ���֣���ɫ����ֱ�ӵ���System.err.print(xxx)*/
    public static void printc(String txt) {
        System.out.print(formatTrans(txt, new int[]{BLUE}));
    }
}