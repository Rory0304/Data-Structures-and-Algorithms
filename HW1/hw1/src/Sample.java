import java.io.File;

public class Sample {
    public static void main(String[] args){
        // ���� ������Ʈ ��� ��������
        File file = new File(".");
        File rootPath = file.getAbsoluteFile();
        System.out.println("���� ������Ʈ�� ��� : "+rootPath );

        // WEB-INF�� ��ο��� Json���� ��������
        // File file = new File("WebContent/WEB-INF/sample.json");
    }
}