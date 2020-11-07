package stuScoreManage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import course.courseManage;
import score.scoreManage;
import student.studentManage;
import user.User;
import user.userManage;

/**
 * @类名 启动类
 * @说明 用于初始化界面、权限控制、启动管理系统
 * @author 王晔
 */
public class start {
	public static void main(String[] args) {
		loginMain();
	}

	/** 登录主界面 */
	@SuppressWarnings("resource")
	public static void loginMain() {
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			color.printlnc("\n▶▷学生成绩管理系统v1.1 By王晔");
			color.printlnc("\r\n*******************************************************");
			color.printlnc("\r\n\t\t1)登录\t\t0)退出系统");
			color.printlnc("\r\n*******************************************************");
			System.out.println("✍请选择操作：");
			String choice = input.next();
			switch (choice) {
			case "1":
				userLogin();
				break;
			case "0":// 退出系统
				color.printlnc("✔已成功退出系统，欢迎下次使用！", color.GREEN);
				System.exit(0);
				break;
			default:
				System.err.println("※※※※※※※输入格式不正确，请重新输入！※※※※※※※");
				break;
			}
		}
	}

	/** 验证登录界面 */
	@SuppressWarnings({ "unchecked", "resource"})
	public static void userLogin() {
		int userID = 0;
		String passWord = null;
		String loginUserType = null;
		Map<Integer, User> m = null;
		Scanner input = new Scanner(System.in);
		File f = new File(User.getPath());
		boolean flag = true;
		int count = 3;
		color.printlnc("*******************************************************");
		color.printlnc("▶▷学生成绩管理系统登录");
		color.printlnc("\r\n\t\t今天是" + new SimpleDateFormat("yyyy年MM月dd日  E").format(new Date()));
		color.printlnc("*******************************************************");
		while (flag) {
			count--;
			if (count < 0) {
				System.err.println("※※※※※※※※您已经输入三次用户信息，仍未登录成功将退出系统！※※※※※※※※\n");
				color.printlnc("✔已成功退出系统，欢迎下次使用！",color.GREEN);
				System.exit(0);
			}
			if (!f.exists()) {
				System.err.println("注：第一次登陆时，初始为账号：12580 密码：12580\n");
			}
			try {
				System.out.println("✍请输入登录用户名：");
				userID = input.nextInt();
				System.out.println("✍请输入登录密码：");
				passWord = input.next();
			} catch (Exception e) {
				System.err.println("✘用户名为纯数字，请检查后重新输入！\n");
				input = new Scanner(System.in);
				continue;
			}
			
			if (!f.exists()) {
				if (userID == 12580 && "12580".equals(passWord)) {
					color.printlnc("▶▷欢迎第一次登录学生成绩管理系统");
					System.err.println("\r\n--------------第一次初始化，请添加管理员用户！----------------\r\n");
					new User().addUser();
					flag = false;
					break;
				} else {
					System.err.println("※※※※※※※※用户名和密码输入错误，请重新输入！※※※※※※※※\n");
					continue;
				}
			} else {
				m = (Map<Integer, User>) RorW.readFromFile(User.getPath());
				if (m.containsKey(userID) && m.get(userID).getPassword().equals(passWord)) {
					// 更新最后登录时间
					m.get(userID).setLastTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					RorW.writeToFile(User.getPath(), m);
					// 登录用户判定
					loginUserType = m.get(userID).getUserType();
					switch (loginUserType) {
					case "admin":// a进入系统管理员界面
						color.printc("▶▷欢迎[" + m.get(userID).getName() + "]");
						color.printc("<管理员>",color.MAGENTA);
						color.printc("登录学生成绩管理系统\n");
						aMenu(userID);
						break;
					case "teacher":// t进入教师系统管理界面
						color.printc("▶▷欢迎[" + m.get(userID).getName() + "]");
						color.printc("<教师>",color.MAGENTA);
						color.printc("登录学生成绩管理系统\n");
						tMenu(userID);
						break;
					case "student":// s进去学生管理界面
						color.printc("▶▷欢迎[" + m.get(userID).getName() + "]");
						color.printc("<学生>",color.MAGENTA);
						color.printc("登录学生成绩管理系统\n");
						sMenu(userID);
						break;
					}
					flag = false;
					break;
				} else {
					System.err.println("※※※※※※※※用户名和密码输入错误，请重新输入！※※※※※※※※");
				}
			}
		}
	}

	/** 管理员界面 */
	@SuppressWarnings("resource")
	public static void aMenu(int loginID) {
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		while (flag) {

			color.printlnc("\r\n*******************************************************");
			color.printlnc("\r\n\t1)用户管理\t2)学生管理\t3)课程管理");
			color.printlnc("\r\n\t4)成绩管理\t\t\t0)退出登录");
			color.printlnc("\r\n*******************************************************");
			System.out.println("✍请输入选择项（0~4）:");
			String choice = input.next();
			switch (choice) {
			case "1":
				userManage.userMenu(loginID);
				break;
			case "2":
				studentManage.studentMenu();
				break;
			case "3":
				courseManage.courseMenu();
				break;
			case "4":
				scoreManage.scoreMenu();
				break;
			case "0":
				flag = false;// 退出登录
				color.printlnc("✔当前账户已经退出登录！\n",color.GREEN);
				break;
			default:
				System.err.println("※※※※※※※输入格式不正确，请重新输入！※※※※※※※\n");
				break;
			}
		}
	}

	/** 学生界面 */
	@SuppressWarnings("resource")
	public static void sMenu(int loginID) {
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			color.printlnc("\r\n*******************************************************");
			color.printlnc("\r\n\t1)用户管理\t2)学生管理\t3)课程管理");
			color.printlnc("\r\n\t4)成绩管理\t\t\t0)退出登录");
			color.printlnc("\r\n*******************************************************");
			System.out.println("✍请输入选择项（0~4）:");
			String choice = input.next();
			switch (choice) {
			case "1":
				userManage.userMenu2(loginID);
				break;
			case "2":
				studentManage.studentMenu(loginID);
				break;
			case "3":
				courseManage.courseMenu2();
				break;
			case "4":
				scoreManage.scoreMenu2(loginID);
				break;
			case "0":
				flag = false;// 退出登录
				color.printlnc("✔当前账户已经退出登录！\n",color.GREEN);
				break;
			default:
				System.err.println("※※※※※※※输入格式不正确，请重新输入！※※※※※※※\n");
				break;
			}
		}
	}

	/** 教师界面 */
	@SuppressWarnings("resource")
	public static void tMenu(int loginID) {
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			color.printlnc("\r\n*******************************************************");
			color.printlnc("\r\n\t1)用户管理\t2)学生管理\t3)课程管理");
			color.printlnc("\r\n\t4)成绩管理\t\t\t0)退出登录");
			color.printlnc("\r\n*******************************************************");
			System.out.println("✍请输入选择项（0~4）:");
			String choice = input.next();
			switch (choice) {
			case "1":
				userManage.userMenu2(loginID);
				break;
			case "2":
				studentManage.studentMenu2();
				break;
			case "3":
				courseManage.courseMenu2();
				break;
			case "4":
				scoreManage.scoreMenu3();
				break;
			case "0":
				flag = false;// 退出登录
				color.printlnc("✔当前账户已经退出登录！",color.GREEN);
				break;
			default:
				System.err.println("※※※※※※※输入格式不正确，请重新输入！※※※※※※※");
				break;
			}
		}
	}
}
