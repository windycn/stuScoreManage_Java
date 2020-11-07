package score;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import course.Course;
import student.Student;
import stuScoreManage.RorW;
import stuScoreManage.color;

/**
 * @类名 成绩类
 * @说明 对成绩的增删改查，以及统计信息
 * @author 王晔
 */
public class Score implements Serializable {
	// 用于判断序列化的版本号
	private static final long serialVersionUID = 1L;
	private int stuID;// 学生学号（必须在学生信息中存在）
	private String classID;// 课程编码（必须在课程信息中存在）
	private double grade;// 成绩（非负数）
	// 成绩信息保存文件
	private static String scorePath = "score.data";

	public Score() {
	}
	
	/** 有参数构造函数 */
	public Score(int stuID, String classID, double grade) {
		super();
		this.stuID = stuID;
		this.classID = classID;
		this.grade = grade;
	}
	
	/** 利用[Source > Generate Getters and Setters...]自动生成 */
	public int getStuID() {
		return stuID;
	}

	public void setStuID(int stuID) {
		this.stuID = stuID;
	}

	public String getClassID() {
		return classID;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getScorePath() {
		return scorePath;
	}

	/** 添加成绩 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void addScore() {
		Scanner sc = new Scanner(System.in);
		File f = new File(Score.scorePath);
		File sf = new File(Student.getStuPath());
		File cf = new File(Course.getClassPath());
		Map<Integer, Student> mis = null;
		Map<String, Course> msc = null;
		List<Score> ls = null;
		Score s = new Score();
		if (!f.exists()) {
			ls = new ArrayList<Score>();
		} else {
			ls = (List<Score>) RorW.readFromFile(Score.scorePath);
		}
		if (!sf.exists()) {
			System.err.println("☹当前系统中没有学生信息，请添加...");
		} else {
			if (!cf.exists()) {
				System.err.println("☹当前系统中没有课程信息，请添加...");
			} else {

				mis = (Map<Integer, Student>) RorW.readFromFile(Student.getStuPath());
				msc = (Map<String, Course>) RorW.readFromFile(Course.getClassPath());
				System.out.println("✍请输入要录入的学生成绩！");
				System.out.println("1、请输入学生学号：");
				while (true) {
					s.setStuID(sc.nextInt());
					if (!mis.containsKey(s.getStuID())) {
						System.err.println("✘输入的学生学号不存在！\n\n✍请重新输入：");
						continue;
					} else {
						break;
					}
				}
				System.out.println("2、请输入课程编号：");
				while (true) {
					s.setClassID(sc.next());
					if (!msc.containsKey(s.getClassID())) {
						System.err.println("✘输入的课程编号不存在！\n\n✍请重新输入：");
						continue;
					} else {
						break;
					}
				}
				System.out.println("3、请输入成绩：");
				while (true) {
					s.setGrade(sc.nextDouble());
					if (s.getGrade() < 0) {
						System.err.println("✘成绩不能为负数，\n\n✍请重新输入：");
						continue;
					} else {
						break;
					}
				}
				ls.add(s);
				RorW.writeToFile(getScorePath(), ls);
				color.printlnc("✔成绩录入成功！",color.GREEN);
			}
		}

	}

	/** 修改成绩 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void editScore() {
		Scanner sc = new Scanner(System.in);
		File f = new File(Score.scorePath);
		List<Score> ls = null;
		boolean flag = true;
		int inId = 0;
		String inclassID = null;
		double inGrade = 0;
		int index = 0;
		if (!f.exists()) {
			System.err.println("☹当前系统中没有学生成绩信息，请添加...");
		} else {
			ls = (List<Score>) RorW.readFromFile(Score.scorePath);
			while (flag) {
				System.out.println("✍请输入要修改成绩的学生学号");
				inId = sc.nextInt();
				System.out.println("✍请输入要修改的课程编号：");
				inclassID = sc.next();
				for (Score s : ls) {
					if (s.getStuID() == inId && s.getClassID().equals(inclassID)) {
						index = ls.indexOf(s);
						flag = false;
						break;
					}
				}
				if (flag) {
					System.err.println("✘输入的在成绩列表中不存在，\n\n✍请重新输入！");
				}
			}
			System.out.println("✍请输入成绩：");
			while (true) {
				inGrade = sc.nextDouble();
				if (inGrade < 0) {
					System.err.println("✘成绩不能为负数，\n\n✍请重新输入");
				} else {
					break;
				}
			}
			ls.get(index).setGrade(inGrade);
			RorW.writeToFile(getScorePath(), ls);
		}
	}

	/** 删除学生成绩 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void delScore() {
		Scanner sc = new Scanner(System.in);
		File f = new File(getScorePath());
		List<Score> ls = null;
		int inId = 0;
		int index = 0;
		String inclassID = null;
		boolean flag = true;
		if (!f.exists()) {
			System.err.println("☹当前系统中没有学生成绩信息，请添加...");
		} else {
			ls = (List<Score>) RorW.readFromFile(getScorePath());
			while (flag) {
				getAllScore();
				System.out.println("✍请输入要删除成绩的学生学号");
				inId = sc.nextInt();
				System.out.println("✍请输入要删除的课程编号：");
				inclassID = sc.next();
				for (Score s : ls) {
					if (s.getStuID() == inId && s.getClassID().equals(inclassID)) {
						index = ls.indexOf(s);
						flag = false;
						break;
					}
				}
				if (flag) {
					System.err.println("✘输入的在成绩列表中不存在，\n\n✍请重新输入！");
				}
			}
			ls.remove(index);
			RorW.writeToFile(getScorePath(), ls);
			color.printlnc("✔删除成功！",color.GREEN);
		}
	}
	
	/** 查找学生成绩 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void getScore() {
		Scanner sc = new Scanner(System.in);
		File f = new File(getScorePath());
		Map<Integer, Student> mis = null;
		Map<String, Course> msc = null;
		List<Score> ls = null;
		int inId = 0;
		String inclassID = null;
		boolean flag=true;
		int index=0;
		if (!f.exists()) {
			System.err.println("☹当前系统中没有学生成绩信息，请添加...");
		} else {
			ls = (List<Score>) RorW.readFromFile(getScorePath());
			mis = (Map<Integer, Student>) RorW.readFromFile(Student.getStuPath());
			msc = (Map<String, Course>) RorW.readFromFile(Course.getClassPath());
			while (flag) {
				System.out.println("✍请输入要查询成绩的学号：");
				inId = sc.nextInt();
				System.out.println("✍请输入要查询的课程编号：");
				inclassID = sc.next();
				for (Score s : ls) {
					if (s.getStuID() == inId && s.getClassID().equals(inclassID)) {
						index=ls.indexOf(s);
						flag = false;
						break;
					}
				}
				if (flag) {
					System.err.println("✘输入的在成绩列表中不存在，\n\n✍请重新输入！");
				}
			}
			color.printlnc("\r\n查询结果如下：");
			System.out.println("=================================================================");
			System.out.println("学生学号\t\t姓名\t课程编号\t\t课程名称\t\t成绩");
			System.out.println("-----------------------------------------------------------------");
			System.out.println(String.format("%-10s",ls.get(index).getStuID())+"\t"+//获得学号
						mis.get(ls.get(index).getStuID()).getName()+"\t"+//通过学号获得姓名
						String.format("%-8s",ls.get(index).getClassID())+"\t"+//获得课程编号
						msc.get(ls.get(index).getClassID()).getClassName()+"\t"+//通过课程编号获得课程名称
						ls.get(index).getGrade());//获得成绩
			System.out.println("=================================================================");
					
		}
	}
	
	/** 学生登录账户查询自己的成绩（重载方法） */
	@SuppressWarnings("unchecked")
	public void getScore(int loginID) {
		File f = new File(getScorePath());
		Map<Integer, Student> mis = null;
		Map<String, Course> msc = null;
		List<Score> ls = null;
		List<Score> ls2=new ArrayList<Score>();
		if (!f.exists()) {
			System.err.println("☹当前系统中没有学生成绩信息，请联系管理员添加...");
		} else {
			ls = (List<Score>) RorW.readFromFile(getScorePath());
			mis = (Map<Integer, Student>) RorW.readFromFile(Student.getStuPath());
			msc = (Map<String, Course>) RorW.readFromFile(Course.getClassPath());
			for(Score s:ls){
				if(s.getStuID()==loginID){
					ls2.add(s);
				}
			}
			if(ls2.size()<=0){
				System.err.println("☹你还没有成绩记录，请联系管理员添加...");
			}else{
				System.out.println("\r◇学生"+mis.get(loginID).getName()+"的成绩信息如下：");
				System.out.println("======================================================");
				System.out.println(" 课程编号\t课程名称\t\t\t\t成绩");
				System.out.println("------------------------------------------------------");
				for(Score s:ls2){
					System.out.println(" "+String.format("%-8s",s.getClassID())+"\t"+String.format("%-13s",msc.get(s.getClassID()).getClassName())+"\t\t"+s.getGrade());
				}
				System.out.println("======================================================");
			}
					
		}
	}
	
	/** 学生登录账户查询自己的绩点（按照河师大GPA计算方法） */
	@SuppressWarnings("unchecked")
	public void getGPA(int loginID) {
		File f = new File(getScorePath());
		Map<Integer, Student> mis = null;
		Map<String, Course> msc = null;
		double gpa = 0;
		List<Score> ls = null;
		List<Score> ls2=new ArrayList<Score>();
		if (!f.exists()) {
			System.err.println("☹当前系统中没有学生成绩信息，请联系管理员添加...");
		} else {
			ls = (List<Score>) RorW.readFromFile(getScorePath());
			mis = (Map<Integer, Student>) RorW.readFromFile(Student.getStuPath());
			msc = (Map<String, Course>) RorW.readFromFile(Course.getClassPath());
			for(Score s:ls){
				if(s.getStuID()==loginID){
					ls2.add(s);
				}
			}
			if(ls2.size()<=0){
				System.err.println("☹你还没有成绩记录，请联系管理员添加...");
			}else{
				int sumCredit = 0;
				double sumGPA = 0;
				
				for(Score s:ls2){
					int credit = msc.get(s.getClassID()).getCredit();
					sumCredit += credit;
					if(s.getGrade()<60) {
						sumGPA += 0;
					} else {
						sumGPA += (1.5 + (s.getGrade() - 60)/10)*credit;
					}
				}
				gpa = sumGPA / sumCredit;
				System.out.println("◇学生"+mis.get(loginID).getName()+"的GPA为：" + String.format("%.2f", gpa));
				System.out.println("\n注：GPA计算方法为河南师范大学平均绩点计算方法：");
				System.out.println("满60分计1.5个绩点，每增加1分计0.1个绩点，因此");
				System.out.println("总平均绩点＝SUM(学科学分×绩点)/总学分");
			}
					
		}
	}
	
	/** 成绩统计打印 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void scCount(){
		Scanner sc =new Scanner(System.in);
		File f = new File(getScorePath());
		Map<Integer, Student> mis = null;
		Map<String, Course> msc = null;
		List<Score> ls = null;
		List<Score> countC=new ArrayList<Score>();//要统计的课程成绩集合
		String select=null;
		if(!f.exists()){
			System.err.println("☹当前系统中没有学生成绩信息，请添加...");
		}else{
			ls = (List<Score>) RorW.readFromFile(getScorePath());
			mis = (Map<Integer, Student>) RorW.readFromFile(Student.getStuPath());
			msc = (Map<String, Course>) RorW.readFromFile(Course.getClassPath());
			System.out.println("✍请输入要统计的课程编号：");
			while(true){
				select=sc.next();
				for(Score s:ls){
					if(s.getClassID().equals(select)){
						countC.add(s);//把符合要求的成绩类放入新的集合中
					}
				}
				if (countC.size()<=0) {
					System.err.println("✘输入的在成绩列表中不存在，请联系管理员录入！\n\n✍请重新输入：");
					continue;
				}else{
					break;
				}
			}
			int maxSid=countC.get(0).getStuID();//最高分的学号(默认第一个)
			int minSid=countC.get(0).getStuID();//最低分的学号(默认第一个)
			int fail=0;//不及格
			int excellent=0;//优秀
			double first=countC.get(0).getGrade();
			double temp1=first;//最高分
			double temp2=first;//最低分
			int n=countC.size();//课程的人数
			for(Score s:countC){
				if(s.getGrade()>temp1){
					temp1=s.getGrade();
					maxSid=s.getStuID();
				}
				if(s.getGrade()<temp2){
					temp2=s.getGrade();
					minSid=s.getStuID();
				}
				if(s.getGrade()>=90){
					excellent++;
				}
				if(s.getGrade()<60){
					fail++;
				}		
			}
			color.printlnc("查询的结果如下：");
			color.printlnc("◇课程名称："+msc.get(select).getClassName());
			System.out.println("====================================================");
			System.out.println(" 项目\t成绩\t学号\t\t姓名\t班级");
			System.out.println("----------------------------------------------------");
			System.out.println(" 最高分\t"+temp1+"\t"+String.format("%-10s",maxSid)+"\t"+mis.get(maxSid).getName()+"\t"+mis.get(maxSid).getClassName());
			System.out.println(" 最低分\t"+temp2+"\t"+String.format("%-10s",minSid)+"\t"+mis.get(minSid).getName()+"\t"+mis.get(minSid).getClassName());
			System.out.println(" 优秀率\t"+String.format("%.1f",(double)excellent/n*100)+"%");
			System.out.println("不及格率\t"+String.format("%.1f",(double)fail/n*100)+"%");
			System.out.println("====================================================");
		}	
	}
	
	
	/** 获取所以学生成绩记录 */
	@SuppressWarnings("unchecked")
	public void getAllScore() {
		List<Score> ls = null;
		if (new File(getScorePath()).exists()) {
			ls = (List<Score>) RorW.readFromFile(getScorePath());
			color.printlnc("▶▷当前系统中的所有学生成绩如下：");
			System.out.println("====================================================");
			System.out.println("学生学号\t\t课程编号\t\t成绩");
			System.out.println("----------------------------------------------------");
			for (Score s : ls) {
				System.out.println(s);
			}
			System.out.println("====================================================");
		} else {
			System.err.println("☹当前系统中没有学生成绩信息，请添加...");
		}
	}

	@Override
	public String toString() {
		return String.format("%-10s", stuID) + "\t" + String.format("%-8s", classID)  + "\t" + grade;
	}
}