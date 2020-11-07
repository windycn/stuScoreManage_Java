package score;

import java.util.Scanner;
import stuScoreManage.color;

/**
 * 类名：成绩管理模块类
 * 说明：对于不同权限用户展示不同界面
 * @author 王晔
 */
public class scoreManage {
		/** [管理员权限]成绩管理模块 */
		@SuppressWarnings("resource")
		public static void scoreMenu() {
			Scanner input = new Scanner(System.in);
			boolean flag = true;
			while (flag) {
				System.out.println("\r\n");
				color.printlnc("▶▷成绩管理模块"); 
				color.printlnc("\r*******************************************************");
				color.printlnc("\r\n\t1)添加成绩\t2)修改成绩\t3)成绩删除");
				color.printlnc("\r\n\t4)查询成绩\t5)查询所有\t0)返回上级");
				color.printlnc("\r\n*******************************************************");
				System.out.println("✍请输入功能选项（0~5）：");
				String choice = input.next();
				System.out.println();
				switch (choice) {
				case "1":
					// 添加成绩
					new Score().addScore();
					break;
				case "2":
					// 修改成绩记录
					new Score().editScore();
					break;
				case "3":
					// 删除成绩
					new Score().delScore();
					break;
				case "4":
					// 查询成绩
					new Score().getScore();
					break;
				case "5":
					// 查询全部
					new Score().getAllScore();
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
		
		/** [学生权限]成绩管理模块 */
		@SuppressWarnings("resource")
		public static void scoreMenu2(int loginID) {
			// 利用了方法的重载
			Scanner input = new Scanner(System.in);
			boolean flag = true;
			while (flag) {
				System.out.println("\r\n");
				color.printlnc("▶▷成绩管理模块");
				color.printlnc("\r*******************************************************");
				color.printlnc("\r\n\t1)成绩查询\t2)绩点查询\t0)返回上级");
				color.printlnc("\r\n*******************************************************");
				System.out.println("✍请输入功能选项（0~2）：");
				String choice = input.next();
				switch (choice) {
				case "1":
					new Score().getScore(loginID);
					break;
				case "2":
					new Score().getGPA(loginID);
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
		
		/** [教师权限]成绩管理模块（不具有删除权限，但具有统计权限） */
		@SuppressWarnings("resource")
		public static void scoreMenu3() {
			Scanner input = new Scanner(System.in);
			boolean flag = true;
			while (flag) {
				System.out.println("\r\n");
				color.printlnc("▶▷成绩管理模块"); 
				color.printlnc("\r*******************************************************");
				color.printlnc("\r\n\t1)添加成绩\t2)修改成绩\t3)成绩统计");
				color.printlnc("\r\n\t4)查询成绩\t5)查询全部\t0)返回上级");
				color.printlnc("\r\n*******************************************************");
				System.out.println("✍请输入功能选项（0~5）：");
				String choice = input.next();
				System.out.println();
				switch (choice) {
				case "1":
					// 添加成绩
					new Score().addScore();
					break;
				case "2":
					// 修改成绩记录
					new Score().editScore();
					break;
				case "3":
					// 成绩统计
					new Score().scCount();
					break;
				case "4":
					// 查询成绩
					new Score().getScore();
					break;
				case "5":
					// 查询全部
					new Score().getAllScore();
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
			//scoreMenu2(1828124037);
			// 管理员权限
			//scoreMenu();
			// 教师权限
			scoreMenu3();
		}
}
