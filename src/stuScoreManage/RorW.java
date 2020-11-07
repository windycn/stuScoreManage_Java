package stuScoreManage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * @类名 文件读写类
 * @说明 通过路径，读写文件，减少代码冗余
 * @author 王晔
 */
public class RorW {
	/** 从文件读取对象 */
	public static Object readFromFile(String path) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Object obj = null;
		try {
			fis = new FileInputStream(new File(path));
			ois = new ObjectInputStream(fis);
			obj = ois.readObject();
		} catch (Exception e) {
			System.err.println("\n✘文件读取异常：" + e.getMessage());
		} 
		finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (fis != null) {
					fis.close();
				}

			} catch (IOException e) {
			}
		}
		return obj;
	}

	/** 写入对象到文件 */
	public static void writeToFile(String path, Object obj) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(new File(path));
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (oos != null) {
					// 刷新缓冲区
					oos.flush();
				}
				if (oos != null) {
					oos.close();
				}
				if (fos != null) {
					fos.close();
				}
				color.printlnc("\n✔数据更新成功！",color.GREEN);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
