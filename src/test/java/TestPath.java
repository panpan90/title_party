import java.util.ArrayList;

import com.sohu.mrd.util.IOUtils;


public class TestPath {
	public static void main(String[] args) {
		TestPath.class.getResourceAsStream("");
		ArrayList<String>  al=IOUtils.readFile2List("svm_files/titles");
		System.out.println(al.toString());
	}
}
