package user;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import stuScoreManage.RorW;
import stuScoreManage.color;
import student.Student;

/**
 * @类名 用户类
 * @说明 对用户的增删改查
 * @author 王晔
 * @author windy
 */
public class User implements Serializable {
	// 用于判断序列化的版本号
	private static final long serialVersionUID =1L;
	private int userID = 0; // 用户名(纯数字，5~11位)
	private String name; // 用户昵称
	private String password; // 密码(4~8位)
	private String userType; // 用户类型（管理员admin,学生student,教师teacher）
	private String lastTime; // 记录最后登录时间(精确到秒)
	@SuppressWarnings("unused")
	private boolean isStudent = false; // 学生数据中是否存在该学生信息
	private static String path = "user.data";
	public User() {
	}
	/** 有参数构造函数 */
	public User(int userID, String name, String userType, String password, String sex, String lastTime) {
		super();
		this.userID = userID;
		this.name = name;
		this.password = password;
		this.userType = userType;
		this.lastTime = lastTime;

	}
	/** 利用[Source > Generate Getters and Setters...]自动生成 */
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		User.path = path;
	}
	

	/** 添加用户 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void addUser() {
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		User u = new User();
		boolean ut = false;// 用户类型判断
		Map<Integer, User> m = null;
		File f = new File(path);
		while (flag) {
			System.out.println("✍请输入要增加的用户信息");
			System.out.println("1、用户名(5~11位)：");
			while (flag) {
				u.setUserID(input.nextInt());
				if (f.exists()) {
					// 获取所有数据判定该用户名是否已经存在
					m = (Map<Integer, User>) RorW.readFromFile(path);
					if (m.containsKey(u.getUserID())) {
						System.err.println("✘用户名已经存在！\n\n✍请重新输入：");
						continue;
					}
				}
				if (u.getUserID() == 0) {
					System.err.println("✘用户名不能为空！\n\n✍请重新输入！");
					continue;
				}
				String sid=""+u.getUserID();
				if (sid.length() >= 11 && sid.length() <= 5) {
					System.err.println("✘用户名只能为5~11位字符！\n\n✍请重新输入：");
					continue;
				}
				break;
			}
			System.out.println("2、请输入密码(4~8位):");
			while (flag) {
				u.setPassword(input.next());
				if (u.getPassword().trim().length() < 4 || u.getPassword().trim().length() > 8) {
					System.err.println("✘密码只能为4~8位字符！\n\n✍请重新输入：");
					continue;
				}
				break;
			}
			System.out.println("3、请输入昵称：");
			while (flag) {
				u.setName(input.next());
				if (u.getName().trim().length() == 0) {
					System.err.println("✘用户名不能为空！\n\n✍请重新输入！");
					continue;
				}
				break;
			}
			if(f.exists()){
				System.out.println("4、请输入用户类别(admin/student/teacher):");
				while (flag) {
					u.setUserType(input.next());
					ut = "admin".equals(u.getUserType()) || "student".equals(u.getUserType()) || "teacher".equals(u.getUserType());
					if (!ut) {
						System.err.println("✘用户类型输入不正确(admin/student/teacher)！\n\n✍请重新输入");
						continue;
					} 
					break;
				}
			}else{
				// 添加第一个用户要添加为管理员
				color.printlnc("✔已经默认设置用户类型为管理员admin！",color.GREEN);
				u.setUserType("admin");
			}
			
			//添加用户为学生的时候判定学生类里是否有此学生，有此学生将isStudent置为true
			if(new File(Student.getStuPath()).exists()){
				Map<Integer,Student> mit=(Map<Integer, Student>) RorW.readFromFile(Student.getStuPath());
				for(Entry<Integer, Student> me:mit.entrySet()){
					int id = me.getValue().getStuID();
					if(id == u.userID){
						isStudent = true;
					}
				}
			}
			
			u.setLastTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			// 如果文件存在就用文件里的对象存放数据，不存在就新建对象存储
			if (f.exists()) {
				m = (Map<Integer, User>) RorW.readFromFile(path);
			} else {
				m = new HashMap<Integer, User>();
			}
			m.put(u.getUserID(), u);
			RorW.writeToFile(path, m);
			color.printlnc("✔用户添加成功！",color.GREEN) ;
			flag = false;
		}
	}

	/** 修改用户信息 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void editUser() {
		Scanner input = new Scanner(System.in);
		int num = 0;
		String oldPW = null;
		String temp = null;
		boolean flag = true;
		Map<Integer, User> userMap = null;
		System.out.println("✍请输入要修改的用户名：");
		int userID = input.nextInt();
		while (flag) {
			if (!new File(path).exists()) {// 如果用户文件不存在，不能进行修改操作
				System.err.println("☹当前系统中没有用户信息！");
				break;
			} else {
				userMap = (Map<Integer, User>) RorW.readFromFile(path);
			}
			if (!userMap.containsKey(userID)) {
				System.err.println("✘输入的用户名不存在！\n\n✍请重新输入");
				continue;
			} else {
				System.out.println("===================================================================");
				System.out.println("用户名\t\t昵称\t密码\t\t用户类型\t最后登录时间");
				System.out.println("-------------------------------------------------------------------");
				System.out.println(userMap.get(userID));// 获取输入用户名的信息
				System.out.println("===================================================================");
				color.printlnc("1>昵称 2>密码 3>用户类型 0>❈保存退出");
				System.out.println("✍请输入要修改的内容:");
				String choice = input.next();
				switch (choice) {
				case "1":// 更改昵称
					System.out.println("✍请输入新的昵称：");
					while (true) {
						
						temp = input.next();
						if (temp.trim().length() == 0) {
							System.err.println("✘输入的昵称不能为空！\n\n✍请重新输入");
						} else {
							userMap.get(userID).setName(temp);
							break;
						}
					}
					break;
				case "2":// 更改密码
					System.out.println("✍请输入原密码：");
					while (true) {
						// 设置三次试错机会
						num++;
						if (num > 3) {
							System.err.println("✘原密码三次输入不正确，返回上级菜单！");
							break;
						}
						oldPW = userMap.get(userID).getPassword();
						String passwordIn = input.next();
						if (oldPW.equals(passwordIn)) {
							System.out.println("✍请输入新密码：");
							userMap.get(userID).setPassword(input.next());
							color.printlnc("✔密码修改成功！",color.GREEN);
							break;
						} else {
							System.err.println("✘原密码输入不正确，\n\n✍请重新输入：");
						}
					}
					break;

				case "3":// 更改用户类型
					System.out.println("请输入更改的用户类型(admin/student/teacher):");
					while (true) {
						temp = input.next();
						if ("admin".equals(temp) || "student".equals(temp) || "teacher".equals(temp)) {
							userMap.get(userID).setUserType(temp);
							break;
						} else {
							System.err.println("✘用户类型输入不正确(admin/student/teacher)！\n\n✍请重新输入：");
						}
					}
					break;
				case "0":
					flag = false;
					break;
				}
				// 把修改的信息写入到文件
				RorW.writeToFile(path, userMap);
			}
			
		}
	}

	/** 教师和学生用户修改信息 */
	@SuppressWarnings( { "resource", "unchecked" })
	public void editUser2(int loginID) {
		Scanner input = new Scanner(System.in);
		int num = 0;
		String oldPW = null;
		String temp = null;
		boolean flag = true;
		Map<Integer, User> userMap = null;
		int userID = loginID;
		while (flag) {
			userMap = (Map<Integer, User>) RorW.readFromFile(path);
			System.out.println("\n===================================================================");
			System.out.println("用户名\t\t昵称\t密码\t\t用户类型\t最后登录时间");
			System.out.println("-------------------------------------------------------------------");
			System.out.println(userMap.get(userID));// 获取输入用户名的信息
			System.out.println("===================================================================");
			color.printlnc("1>昵称 2>密码  0>❈保存退出");
			System.out.println("✍请输入要修改的内容:");
			String choice = input.next();
			switch (choice) {
			case "1":// 更改昵称
				while (true) {
					System.out.println("✍请输入新的姓名：");
					temp = input.next();
					if (temp.trim().length() == 0) {
						System.err.println("✘新的姓名不能为空\n\n✍请重新输入：");
					} else {
						userMap.get(userID).setName(temp);
						break;
					}
				}
				break;
			case "2":// 更改密码
				while (true) {
					num++;
					if (num > 3) {
						System.err.println("✘原密码三次输入不正确，返回上级菜单！");
						break;
					}
					System.out.println("✍请输入原密码：");
					oldPW = userMap.get(userID).getPassword();
					String passwordIn = input.next();
					if (oldPW.equals(passwordIn)) {
						System.out.println("✍请输入新密码：");
						userMap.get(userID).setPassword(input.next());
						color.printlnc("✔密码修改成功！",color.GREEN);
						break;
					} else {
						System.err.println("✘原密码不正确，\n\n✍请重新输入：");
					}
				}
				break;
			case "0":
				flag = false;
				break;
			}
			RorW.writeToFile(path, userMap);// 把修改的信息写入到文件
		}
	}

	/** 删除用户（但是不能删除正在使用的用户）*/
	@SuppressWarnings({ "unchecked", "resource" })
	public void delUser(int loginID) {
		Scanner input = new Scanner(System.in);
		int userID = 0;
		Map<Integer, User> userMap = null;
		new User().getAllUser();
		if (!new File(path).exists()) {
			// 如果用户文件不存在，不能进行操作
			System.err.println("☹当前系统中没有用户信息！");
		} else {
			userMap = (Map<Integer, User>) RorW.readFromFile(path);
			System.out.println("✍请输入要删除的用户名：");
			userID = input.nextInt();
			if (loginID == userID) {
				System.err.println("✘当前用户已经登录,删除操作已取消！");
			} else {
				if (!userMap.containsKey(userID)) {
					System.err.println("✘你输入的用户名不存在！\n\n✍请重新输入：");
				} else {
					if (userMap.size() <= 1) {
						System.err.println("✘请确保本系统管理有管理员存在！");
					} else {
						System.err.println("▷确定要删除吗？(y/n)");
						String yn = input.next();
						if ("y".equals(yn)) {
							userMap.remove(userID);
							RorW.writeToFile(path, userMap);
							color.printlnc("✔用户" + userID + "已经被删除！\n",color.GREEN);
						} else {
							color.printlnc("✔删除操作已取消！\n",color.GREEN);
						}
					}
				}
			}
			new User().getAllUser();
		}
	}

	/** 查询用户 */
	@SuppressWarnings({ "unchecked" })
	public void getUser(int loginID) {
		if (!new File(path).exists()) {// 如果用户文件不存在，不能进行操作
			System.err.println("✘当前没有用户存在！");
		} else {
			Map<Integer, User> userMap = (Map<Integer, User>) RorW.readFromFile(path);
			Scanner input = null;
			int userID = 0;
			while (true) {
				// 如果登录的用户名非管理员只能查询自己的信息
				if (!"admin".equals(userMap.get(loginID).getUserType())) {
					System.out.println("===================================================================");
					System.out.println("用户名\t\t昵称\t密码\t\t用户类型\t最后登录时间");
					System.out.println("-------------------------------------------------------------------");
					System.out.println(userMap.get(loginID));// 获取输入用户名的信息
					System.out.println("===================================================================");
					break;
				} else {
					System.out.println("✍请输入要查询的用户名：");
					input = new Scanner(System.in);
					userID = input.nextInt();
					if (!userMap.containsKey(userID)) {
						System.err.println("✘输入的用户名不存在！\n\n✍请重新输入");
						continue;
					} else {
						System.out.println("===================================================================");
						System.out.println("用户名\t\t昵称\t密码\t\t用户类型\t最后登录时间");
						System.out.println("-------------------------------------------------------------------");
						System.out.println(userMap.get(userID));// 获取输入用户名的信息
						System.out.println("===================================================================");
						break;
					}
				}
			}
		}
	}

	/** 获取全部用户列表 */
	@SuppressWarnings({ "unchecked" })
	public void getAllUser() {
		Map<Integer, User> m = null;
		if (new File(path).exists()) {
			m = (Map<Integer, User>) RorW.readFromFile(path);
			color.printlnc("▶▷当前系统中的用户如下：");
			System.out.println("===================================================================");
			System.out.println("用户名\t\t昵称\t密码\t\t用户类型\t最后登录时间");
			System.out.println("-------------------------------------------------------------------");
			for (Map.Entry<Integer, User> me : m.entrySet()) {
				System.out.println(me.getValue());
			}
			System.out.println("===================================================================");
		} else {
			System.err.println("☹当前系统中没有用户信息！");
		}
	}

	@Override
	public String toString() {
		return String.format("%-10s",userID) + "\t" + name + "\t" + String.format("%-10s",password) + "\t" +  userType + "\t" + lastTime;
	}

}

