package course;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import stuScoreManage.RorW;
import stuScoreManage.color;

/**
 * @类名 课程类
 * @说明 对课程的增删改查
 * @author 王晔
 */
public class Course implements Serializable {
	// 用于判断序列化的版本号
	private static final long serialVersionUID = 1L;
	private String classID;// 课程编码
	private String className;// 课程名称
	private int credit;// 学分(1位数字)
	// 课程信息保存文件
	private static String classPath = "course.data";
	
	public Course() {
	}
	/** 有参数构造函数 */
	public Course(String classID, String className, int credit) {
		super();
		this.classID = classID;
		this.className = className;
		this.credit = credit;
	}
	/** 利用[Source > Generate Getters and Setters...]自动生成 */
	public String getClassID() {
		return classID;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public static String getClassPath() {
		return classPath;
	}

	/** 添加课程信息 */
	@SuppressWarnings({ "unchecked", "resource", "unused" })
	public void addCourse() {
		File file = new File(classPath);
		Course c = new Course();
		Map<String, Course> classMap = null;
		int temp = 0;
		boolean flag = true;
		Scanner input = new Scanner(System.in);
		if (!file.exists()) {
			classMap = new HashMap<String, Course>();
			flag=false;
		} else {
			classMap = (Map<String, Course>) RorW.readFromFile(classPath);
		}
		System.out.println("✍请输入要增加的课程信息：");
		System.out.println("1、请输入课程编号：");
		while (true) {
			c.setClassID(input.next());
			if (c.getClassID().length() == 0) {
				System.err.println("✘课程编号不能为空！\n\n✍请重新输入：");
				continue;
			} else {
				if (classMap.containsKey(c.getClassID())) {
					System.err.println("✘该课程编号已经存在！\n\n✍请重新输入：");
					continue;
				}
			}
			break;
		}

		System.out.println("2、请输入课程名：");
		while (true) {
			c.setClassName(input.next());
			if (c.getClassName().trim().length() == 0) {
				System.err.println("✘课程名不能为空！\n\n✍请重新输入：");
				continue;
			} else {
				for (Course gc : classMap.values()) {
					if (c.getClassName().equals(gc.getClassName())) {
						flag = true;// 存在跳出本for循环，继续while循环
						break;
					}else{
						flag = false;// for循环结束还不存在就结束while说明输入满足要求！
					}
				}
				if(flag){
					System.err.println("✘该课程名称已经存在！\n\n✍请重新输入：");
					continue;
				}else{
					break;
				}
				
			}
		}

		System.out.println("3、请输入学分：");
		while (true) {
			c.setCredit(input.nextInt());
			if (c.getCredit() < 0 && c.getCredit() > 9) {
				System.err.println("✘学分输入错误！\n\n✍请重新输入：");
				continue;
			}
			break;
		}
		classMap.put(c.getClassID(), c);
		RorW.writeToFile(Course.getClassPath(), classMap);
	}

	/** 修改课程信息 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void editCourse() {
		Scanner input = new Scanner(System.in);
		File file = new File(classPath);
		Map<String, Course> classMap = null;
		String temp = null;
		String id = null;
		boolean flag = true;
		boolean flag1 = true;
		int i = 0;
		String choice = null;
		if (!file.exists()) {
			System.err.println("☹当前系统中没有任何课程，请添加...");
		} else {
			classMap = (Map<String, Course>) RorW.readFromFile(classPath);

			System.out.println("\n\n✍请输入要修改的课程编号：");
			while (true) {
				id = input.next();
				if (classMap.containsKey(id)) {
					System.out.println("===============================================");
					System.out.println("课程编码\t\t课程名称\t\t\t学分");
					System.out.println("-----------------------------------------------");
					System.out.println(classMap.get(id));
					System.out.println("===============================================");
					break;
				} else {
					System.err.println("✘输入的课程编号不存在！\n\n✍请重新输入：");
					continue;
				}
			}
			while (flag) {
				color.printlnc("\n1>课程名称 2>学分  0>❈保存退出");
				System.out.println("✍请输入要修改的内容:");
				choice = input.next();
				switch (choice) {
				case "1":
					System.out.println("✍请输入课程名称：");
					while (flag1) {
						temp = input.next();
						if (temp.trim().length() == 0) {
							System.err.println("✘课程名称不能为空！\n\n✍请重新输入：");
							continue;
						} else {
							for (Course gc : classMap.values()) {
								if (temp.equals(gc.getClassName())) {
									System.err.println("✘该课程名称已经存在！\n\n✍请重新输入：");
									flag1 = true;// 存在跳出本for循环
									break;
								} else {
									flag1 = false;
								}
							}
							if(!flag) {
								classMap.get(id).setClassName(temp);// 不存在就存储在classMap对应得key下
							}
						}
					}
					break;
				case "2":
					System.out.println("✍请输入学分：");
					while (true) {
						i = input.nextInt();
						if (i < 0 && i > 9) {
							System.err.println("✘学分只能为1位数字！\n\n✍请重新输入：");
							continue;
						} else {
							classMap.get(id).setCredit(i);
							break;
						}
					}
					break;
				case "0":
					flag = false;
					break;
				}
				RorW.writeToFile(getClassPath(), classMap);
			}
		}

	}

	/** 以课程编码删除一个课程 */
	@SuppressWarnings({ "unchecked", "resource", "unlikely-arg-type" })
	public void delCourse() {
		Scanner input = new Scanner(System.in);
		File file = new File(getClassPath());
		Map<Integer, Course> classMap = null;
		if (!file.exists()) {
			System.err.println("☹当前系统中没有任何课程，请添加...");
		} else {
			getAllCourse();
			classMap = (Map<Integer, Course>) RorW.readFromFile(getClassPath());
			System.out.println("✍请输入要删除的课程编码：");
			String id = input.next();
			if (!classMap.containsKey(id)) {
				System.err.println("✘输入的课程编码不存在！");
			} else {
				// 删除前保存课程名称用于返回信息
				String name = classMap.get(id).getClassName();
				classMap.remove(id);
				RorW.writeToFile(getClassPath(), classMap);
				color.printlnc("✔课程" + name + "的信息已被删除！",color.GREEN);
			}
		}

	}

	/** 按照课程编码查询一个课程信息 */
	@SuppressWarnings({ "unchecked", "resource", "unlikely-arg-type" })
	public void getCourse() {
		Scanner input = new Scanner(System.in);
		Map<Integer, Course> classMap = null;
		File file = new File(getClassPath());
		if (!file.exists()) {
			System.err.println("☹当前系统中没有任何课程，请添加...");
		} else {
			classMap = (Map<Integer, Course>) RorW.readFromFile(getClassPath());
			System.out.println("✍请输入要查询的课程编码：");
			while (true) {
				String id = input.next();
				if (classMap.containsKey(id)) {
					System.out.println("===============================================");
					System.out.println("课程编码\t\t课程名称\t\t\t学分");
					System.out.println("-----------------------------------------------");
					System.out.println(classMap.get(id));
					System.out.println("===============================================");
					break;
				} else {
					System.err.println("✘输入的课程编码不存在！\n\n✍请重新输入：");
					continue;
				}
			}
		}

	}
	/** 查询所有课程信息 */
	@SuppressWarnings("unchecked")
	public void getAllCourse() {
		File file = new java.io.File(classPath);
		Map<Integer, Course> classMap = null;
		if (!file.exists()) {
			System.err.println("☹当前系统中没有任何课程，请添加...");
		} else {
			classMap = (Map<Integer, Course>) RorW.readFromFile(getClassPath());
			color.printlnc("▶▷当前系统中的课程如下：");
			System.out.println("===============================================");
			System.out.println("课程编码\t\t课程名称\t\t\t学分");
			System.out.println("-----------------------------------------------");
			for (Map.Entry<Integer, Course> cla : classMap.entrySet()) {
				System.out.println(cla.getValue());
			}
			System.out.println("===============================================");
		}

	}

	@Override
	public String toString() {
		return String.format("%-8s", classID) + "\t" + String.format("%-14s", className) + "\t" + credit;
	}

}
