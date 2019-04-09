import java.io.File;

public class Sample {
    public static void main(String[] args){
        // 현재 프로젝트 경로 가져오기
        File file = new File(".");
        File rootPath = file.getAbsoluteFile();
        System.out.println("현재 프로젝트의 경로 : "+rootPath );

        // WEB-INF의 경로에서 Json파일 가져오기
        // File file = new File("WebContent/WEB-INF/sample.json");
    }
}