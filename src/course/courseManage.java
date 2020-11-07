package course;

import java.util.Scanner;
import stuScoreManage.color;

/**
 * @类名 课程管理模块类
 * @说明 对于不同权限用户展示不同界面
 * @author 王晔
 */
public class courseManage {
		/** [管理员权限]课程管理模块 */
		@SuppressWarnings("resource")
		public static void courseMenu() {
			Scanner input = new Scanner(System.in);
			boolean flag = true;
			while (flag) {
				System.out.println("\r\n");
				color.printlnc("▶▷课程管理模块"); 
				color.printlnc("\r*******************************************************");
				color.printlnc("\r\n\t1)添加课程\t2)修改课程\t3)课程删除");
				color.printlnc("\r\n\t4)查询课程\t5)查询所有\t0)返回上级");
				color.printlnc("\r\n*******************************************************");
				System.out.println("✍请输入功能选项（0~5）：");
				String choice = input.next();
				System.out.println();
				switch (choice) {
				case "1":
					// 增加课程
					new Course().addCourse();
					break;
				case "2":
					// 修改课程信息
					new Course().editCourse();
					break;
				case "3":
					// 删除课程
					new Course().delCourse();
					break;
				case "4":
					// 查询课程
					new Course().getCourse();
					break;
				case "5":
					// 查询全部
					new Course().getAllCourse();
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
		
		/** [学生/教师权限]课程管理模块 */
		@SuppressWarnings({ "resource", "unused" })
		public static void courseMenu2() {
			// 利用了方法的重载
			Scanner input = new Scanner(System.in);
			boolean flag = true;
			while (flag) {
				System.out.println("\r\n");
				color.printlnc("▶▷课程管理模块");
				color.printlnc("\r*******************************************************");
				color.printlnc("\r\n\t1)查询课程\t2)查询全部\t0)返回上级");
				color.printlnc("\r\n*******************************************************");
				System.out.println("✍请输入功能选项（0~2）：");
				String choice = input.next();
				switch (choice) {
				case "1":
					new Course().getCourse();
					break;
				case "2":
					new Course().getAllCourse();
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
			//courseMenu2();
			// 管理员权限
			courseMenu();
		}
}
