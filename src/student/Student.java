package student;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import stuScoreManage.RorW;
import stuScoreManage.color;

/**
 * @类名 学生类
 * @说明 对学生进行增删改查
 * @author 王晔
 */
public class Student implements Serializable {
	// 用于判断序列化的版本号
	private static final long serialVersionUID = 1L;
	private int stuID = 0; // 学号
	private String name; // 姓名
	private String sex; // 性别
	private int grade = 0; // 年级
	private String major; // 专业
	private String className; // 班级
	// 学生信息保存文件
	private static String stuPath = "student.data";

	public Student() {
	}

	/** 有参数构造函数 */
	public Student(int stuID, String name, String sex, String major, String className) {
		this.stuID = stuID;
		this.name = name;
		this.sex = sex;
		this.major = major;
		this.className = className;
	}

	/** 利用[Source > Generate Getters and Setters...]自动生成 */
	public int getStuID() {
		return stuID;
	}

	public void setStuID(int stuID) {
		this.stuID = stuID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public static String getStuPath() {
		return stuPath;
	}

	/** 添加学生 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void addStudent() {
		File file = new java.io.File(stuPath);
		Student stu = new Student();
		// 存储学生对象
		Map<Integer, Student> stuMap = null;
		Scanner input = new Scanner(System.in);
		// 先判断是否存在student.txt文件
		if (!file.exists())
			stuMap = new HashMap<Integer, Student>();
		else
			stuMap = (Map<Integer, Student>) RorW.readFromFile(stuPath);
		System.out.println("✍请输入要添加的学生信息：");
		System.out.println("1、请输入姓名：");
		while (true) {
			stu.setName(input.next());
			if (stu.getName().trim().length() == 0) {
				System.err.println("✘学生姓名不能为空！\n\n✍请重新输入：");
				continue;
			}
			break;
		}
		System.out.println("2、请输入学号：");
		while (true) {
			stu.setStuID(input.nextInt());
			if (stu.getStuID() == 0) {
				System.err.println("✘学生学号不能为空！\n\n✍请重新输入：");
				continue;
			}
			if (stuMap.containsKey(stu.getStuID())) {
				System.err.println("✘输入的学生学号已存在！\n\n✍请重新输入：");
				continue;
			}
			break;
		}
		System.out.println("3、请输入性别（男/女）：");
		while (true) {
			stu.setSex(input.next());
			if (!"男".equals(stu.getSex()) && !"女".equals(stu.getSex())) {
				System.err.println("✘性别输入不正确，请输入男/女！\n\n✍请重新输入：");
				continue;
			}
			break;
		}
		System.out.println("4、请输入年级：");
		while (true) {
			stu.setGrade(input.nextInt());
			if (stu.getGrade() == 0) {
				System.err.println("✘学生年级不能为空！\n\n✍请重新输入：");
				continue;
			}
			break;
		}
		System.out.println("5、请输入专业：");
		while (true) {
			stu.setMajor(input.next());
			if (stu.getMajor().trim().length() == 0) {
				System.err.println("✘学生专业不能为空！\n ✍请重新输入：");
				continue;
			}
			break;
		}
		System.out.println("6、请输入班级：");
		while (true) {
			stu.setClassName(input.next());
			if (stu.getClassName().trim().length() == 0) {
				System.err.println("✘学生班级不能为空！\n ✍请重新输入：");
				continue;
			}
			break;
		}
		// 将学生信息写入文件，以学号为索引key
		stuMap.put(stu.getStuID(),stu);
		RorW.writeToFile(Student.getStuPath(), stuMap);
	}

	/** 按照学号删除学生 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void delStudent() {
		File file = new java.io.File(stuPath);
		Map<Integer, Student> stuMap = null;
		Scanner input = new Scanner(System.in);
		// 先判断是否存在student.txt文件
		if (!file.exists()) {
			System.err.println("☹当前系统中没有学生信息，请添加...");
		} else {
			// getAllStu();
			stuMap = (Map<Integer, Student>) RorW.readFromFile(getStuPath());
			System.out.println("✍请输入要删除的学生学号：");
			int id = input.nextInt();
			if (!stuMap.containsKey(id)) {
				System.err.println("✘输入的学生学号不存在！");
			} else {
				String name = stuMap.get(id).getName();
				stuMap.remove(id);
				RorW.writeToFile(getStuPath(), stuMap);
				color.printlnc("✔学生" + name + "的信息已被删除！",color.GREEN);
			}
		}
	}

	/** 按照学号修改学生信息 */
	@SuppressWarnings({"unchecked","resource"})
	public void editStudent(int loginID) {
		File file = new java.io.File(stuPath);
		Map<Integer, Student> stuMap = null;
		Scanner input = new Scanner(System.in);
		int id; // 修改学生信息的学生id
		stuMap = (Map<Integer, Student>) RorW.readFromFile(getStuPath());
		// 先判断是否存在student.txt文件
		if (!file.exists()) {
			if (loginID == 0)
				System.err.println("☹当前系统中没有学生信息，请添加...");
		} else {
			// 先判断权限是否为管理员
			if (loginID != 0) { // 学生身份
				id = loginID;
				getSelf(loginID);
			} else { // 管理员身份
				getAllStu();
				System.out.println("\n\n✍请输入要修改的学生学号：");
				while (true) {
					id = input.nextInt();
					if (stuMap.containsKey(id)) {
						System.out.println("===================================================================");
						System.out.println("学号\t姓名\t性别\t年级\t专业\t\t班级");
						System.out.println("-------------------------------------------------------------------");
						System.out.println(stuMap.get(id));
						System.out.println("===================================================================");
						break;
					} else {
						System.err.println("✘输入的学生学号不存在！\n\n✍请重新输入：");
						continue;
					}
				}
			}
			
			// 进入修改程序段
			boolean isGoOn = true; // 判断用户是否选择退出
			String choice = null; // 存储用户的选项
			while (isGoOn) {
				color.printlnc("\n1>姓名 2>性别 3>年级 4>专业 5>班级  0>❈保存退出");
				System.out.println("✍请输入要修改的内容:");
				choice = input.next();
				switch (choice) {
				case "1":
					System.out.println("✍请输入修改后的姓名：");
					while (true) {
						String name = input.next();
						if (name.trim().length() == 0) {
							System.err.println("✘学生姓名不能为空！\n\n✍请重新输入：");
							continue;
						}
						else {
							stuMap.get(id).setName(name);
							break;
						}
					}
					break;
				case "2":
					System.out.println("✍请输入修改后的性别（男/女）：");
					while (true) {
						String sex = input.next();
						if (!"男".equals(sex) && !"女".equals(sex)) {
							System.err.println("✘性别输入不正确，请输入男/女！\n\n✍请重新输入：");
							continue;
						}
						else {
							stuMap.get(id).setName(sex);
							break;
						}
					}
					break;
				case "3":
					System.out.println("✍请输入修改后的年级：");
					while (true) {
						int grade = input.nextInt();
						if (grade == 0) {
							System.err.println("✘学生年级不能为空！\n\n✍请重新输入：");
							continue;
						}
						else {
							stuMap.get(id).setGrade(grade);
							break;
						}
					}
					break;
				case "4":
					System.out.println("✍请输入修改后的专业：");
					while (true) {
						String major = input.next();
						if (major.trim().length() == 0) {
							System.err.println("✘学生专业不能为空！\n\n✍请重新输入：");
							continue;
						}
						else {
							stuMap.get(id).setMajor(major);
							break;
						}
					}
					break;
				case "5":
					System.out.println("✍请输入修改后的班级：");
					while (true) {
						String className = input.next();
						if (className.trim().length() == 0) {
							System.err.println("✘学生班级不能为空！\n\n✍请重新输入：");
							continue;
						}
						else {
							stuMap.get(id).setClassName(className);
							break;
						}
					}
					break;
				case "0":
					isGoOn = false;
					break;
				}
				// 将修改后的学生信息写入文件
				RorW.writeToFile(Student.getStuPath(), stuMap);
			}
		}
	}
	
	/** 按照学号查询一个学生信息 */
	@SuppressWarnings({"unchecked","resource"})
	public void getStudent() {
		File file = new java.io.File(stuPath);
		Map<Integer, Student> stuMap = null;
		Scanner input = new Scanner(System.in);
		// 先判断是否存在student.txt文件
		if (!file.exists()) {
			System.err.println("☹当前系统中没有学生信息，请添加...");
		} else {
			stuMap = (Map<Integer, Student>) RorW.readFromFile(getStuPath());
			System.out.println("✍请输入要查询的学生学号：");
			int id;
			while (true) {
				id = input.nextInt();
				if (stuMap.containsKey(id)) {
					System.out.println("===================================================================");
					System.out.println("学号\t\t姓名\t性别\t年级\t专业\t\t班级");
					System.out.println("-------------------------------------------------------------------");
					System.out.println(stuMap.get(id));
					System.out.println("===================================================================");
					break;
				} else {
					System.err.println("✘输入的学生学号不存在！\n\n✍请重新输入：");
					continue;
				}
			}
		}
	}
	
	/** 查询所有学生信息 */
	@SuppressWarnings("unchecked")
	public void getAllStu() {
		File file = new java.io.File(stuPath);
		Map<Integer, Student> stuMap = null;
		// 先判断是否存在student.txt文件
		if (!file.exists()) {
			System.err.println("☹当前系统中没有学生信息，请添加...");
		} else {
			stuMap = (Map<Integer, Student>) RorW.readFromFile(getStuPath());
			color.printlnc("▶▷当前系统中的学生如下：");
			System.out.println("===================================================================");
			System.out.println("学号\t\t姓名\t性别\t年级\t专业\t\t班级");
			System.out.println("-------------------------------------------------------------------");
			// 打印所有学生的信息
			for(Map.Entry<Integer, Student> stu : stuMap.entrySet()) {
				System.out.println(stu.getValue());
			}
			System.out.println("===================================================================");
		}
	}
	
	/** 查询当前登录的学生用户自己的信息 */
	@SuppressWarnings("unchecked")
	public void getSelf(int loginID) {
		File file = new java.io.File(stuPath);
		Map<Integer, Student> stuMap = null;
		// 先判断是否存在student.txt文件
		if (!file.exists()) {
			System.err.println("☹当前系统中没有当前登录者的学生信息，请联系管理员添加...");
		} else {
			stuMap = (Map<Integer, Student>) RorW.readFromFile(getStuPath());
			// 打印当前登录学生的信息
			if (stuMap.containsKey(loginID)) {
				System.out.println("===================================================================");
				System.out.println("学号\t\t姓名\t性别\t年级\t专业\t\t班级");
				System.out.println("-------------------------------------------------------------------");
				System.out.println(stuMap.get(loginID));
				System.out.println("===================================================================");
			} else {
				System.err.println("☹当前系统中没有当前登录者的学生信息，请联系管理员添加...");
			}
		}
	}
	
	@Override
	public String toString() {
		return  String.format("%-10s", stuID)+ "\t" + name + "\t" + sex + "\t" + grade + "\t" + String.format("%-8s", major) + "\t" + className;
	}
}
