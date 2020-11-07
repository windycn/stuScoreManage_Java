package student;

import java.util.Scanner;

import stuScoreManage.color;
/**
 * @类名 学生管理模块类
 * @说明 对于不同权限用户展示不同界面
 * @author 王晔
 */
public class studentManage {
		/** [管理员权限]学生管理模块 */
		@SuppressWarnings("resource")
		public static void studentMenu() {
			Scanner input = new Scanner(System.in);
			boolean flag = true;
			while (flag) {
				System.out.println("\r\n");
				color.printlnc("▶▷学生管理模块"); 
				color.printlnc("\r*******************************************************");
				color.printlnc("\r\n\t1)添加学生\t2)修改学生\t3)删除学生");
				color.printlnc("\r\n\t4)查询学生\t5)查询所有\t0)返回上级");
				color.printlnc("\r\n*******************************************************");
				System.out.println("✍请输入功能选项（0~5）：");
				String choice = input.next();
				System.out.println();
				switch (choice) {
				case "1":
					// 增加学生
					new Student().addStudent();
					break;
				case "2":
					// 修改学生信息，因为采用了重载，故0为管理员模式
					new Student().editStudent(0);
					break;
				case "3":
					// 删除学生
					new Student().delStudent();
					break;
				case "4":
					// 查询学生
					new Student().getStudent();
					break;
				case "5":
					// 查询全部
					new Student().getAllStu();
					break;
				case "0":
					// 返回上级菜单
					flag = false;
					break;
				default:
					System.err.println("※※※※※※※输入格式不正确，请重新输入！※※※※※※※");
					break;
				}
			}
		}
		
		/** [学生权限]学生管理模块 */
		@SuppressWarnings("resource")
		public static void studentMenu(int loginID) {
			// 利用了方法的重载
			Scanner input = new Scanner(System.in);
			boolean flag = true;
			while (flag) {
				System.out.println("\r\n");
				color.printlnc("▶▷学生管理模块");
				color.printlnc("\r*******************************************************");
				color.printlnc("\r\n\t1)信息查询\t2)信息修改\t0)返回上级");
				color.printlnc("\r\n*******************************************************");
				System.out.println("✍请输入功能选项（0~2）：");
				String choice = input.next();
				switch (choice) {
				case "1":
					new Student().getSelf(loginID);
					break;
				case "2":
					new Student().editStudent(loginID);
					break;
				case "0":
					flag = false;
					break;
				default:
					System.err.println("※※※※※※※输入格式不正确，请重新输入！※※※※※※※");
					break;
				}
			}
		}
		
		/** [教师权限]学生管理模块 */
		@SuppressWarnings("resource")
		public static void studentMenu2() {
			Scanner input = new Scanner(System.in);
			boolean flag = true;
			while (flag) {
				System.out.println("\r\n");
				color.printlnc("▶▷学生管理模块"); 
				color.printlnc("\r*******************************************************");
				color.printlnc("\r\n\t1)查询学生\t2)查询全部\t0)返回上级");
				color.printlnc("\r\n*******************************************************");
				System.out.println("✍请输入功能选项（0~2）：");
				String choice = input.next();
				System.out.println();
				switch (choice) {
				case "1":
					// 查询学生
					new Student().getStudent();
					break;
				case "2":
					// 查询全部
					new Student().getAllStu();
					break;
				case "0":
					// 返回上级菜单
					flag = false;
					break;
				default:
					System.err.println("※※※※※※※输入格式不正确，请重新输入！※※※※※※※");
					break;
				}
			}
		}

		/** 用于进行单元测试 */
		public static void main(String[] args) {
			// 学生权限
			//studentMenu(1828124037);
			// 管理员权限
			studentMenu();
			// 教师权限
			//studentMenu2();
		}
}
