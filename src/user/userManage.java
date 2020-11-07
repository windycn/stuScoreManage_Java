package user;

import java.util.Scanner;

import stuScoreManage.color;
/**
 * @类名 用户管理模块类
 * @说明 对于不同权限用户展示不同界面
 * @author 王晔
 */
public class userManage {
		/** [管理员权限]用户管理模块 */
		@SuppressWarnings("resource")
		public static void userMenu(int loginID) {
			Scanner input = new Scanner(System.in);
			boolean flag = true;
			while (flag) {
				System.out.println("\r\n");
				color.printlnc("▶▷用户管理模块"); 
				color.printlnc("\r*******************************************************");
				color.printlnc("\r\n\t1)添加用户\t2)修改用户\t3)删除用户");
				color.printlnc("\r\n\t4)查询用户\t5)查询所有\t0)返回上级");
				color.printlnc("\r\n*******************************************************");
				System.out.println("✍请输入功能选项（0~5）：");
				String choice = input.next();
				System.out.println();
				switch (choice) {
				case "1":
					new User().addUser();
					break;
				case "2":
					new User().editUser();
					break;
				case "3":
					new User().delUser(loginID);
					break;
				case "4":
					new User().getUser(loginID);
					break;
				case "5":
					new User().getAllUser();
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
		
		/** [学生/教师权限]用户管理模块 */
		@SuppressWarnings("resource")
		public static void userMenu2(int loginID) {
			// 利用了方法的重载
			Scanner input = new Scanner(System.in);
			boolean flag = true;
			while (flag) {
				System.out.println("\r\n");
				color.printlnc("▶▷用户管理模块");
				color.printlnc("\r*******************************************************");
				color.printlnc("\r\n\t1)信息查询\t2)信息修改\t0)返回上级");
				color.printlnc("\r\n*******************************************************");
				System.out.println("✍请输入功能选项（0~2）：");
				String choice = input.next();
				switch (choice) {
				case "1":
					new User().getUser(loginID);
					break;
				case "2":
					new User().editUser2(loginID);
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

		/** 用于进行单元测试 */
		public static void main(String[] args) {
			// 学生/教师权限
			 userMenu2(1828124037);
			// 管理员权限
			// userMenu(1828124026);
		}
}

