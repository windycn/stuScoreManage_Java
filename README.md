# Java学生成绩管理系统
完全利用Java写的学生成绩管理系统，不含数据库，命令行界面，具有权限管理、用户管理、学生管理、课程管理、成绩管理模块。

Completely using Java to write the student achievement management system, does not contain the database, the command line interface, has the authority management, the user management, the student management, the course management, the achievement management module.

---

目前系统中使用了表情符号:smirk:，所以文件保存格式为UTF-8，下载后打开乱码可以注意一下编码:computer:问题。

:alarm_clock:如果图片加载不出来，那是因为国内图片加载的连接被Ban了（外网可访问），可以直接查看本目录“imageCache”文件夹的图片。

 :bug:目前系统还有许多BUG有待改进，代码冗余性等等也需要进一步迭代，所以欢迎:clinking_glasses:提交你的代码到此仓库:house:！

:point_right:该系统用例图如下：

![成绩管理系统用例图](https://github.com/windycn/stuScoreManage_Java/blob/main/imageCache/成绩管理系统用例图.png?raw=true)

:point_right:该系统的系统架构图如下：

![成绩管理系统架构图](https://github.com/windycn/stuScoreManage_Java/blob/main/imageCache/成绩管理系统架构图.png?raw=true)

---

:exclamation:初始化项目注意事项：

1、目录下data文件:floppy_disk:仅供个人测试，可以删除，系统会重新进入初始化！

![1-初始化系统，添加管理员](https://github.com/windycn/stuScoreManage_Java/blob/main/imageCache/1-初始化系统，添加管理员.png?raw=true)

2、目前系统的测试用户列表如下：

| 测试账号   | 密码   | 账户类型 |
| ---------- | ------ | -------- |
| 10000      | 10000  | admin    |
| 19216811   | 123456 | admin    |
| 1828124026 | 123456 | student  |
| 1828124037 | 789456 | student  |
| 121115     | 123456 | teacher  |

---

:artificial_satellite:部分系统运行截图如下：

![3-管理员登录](https://github.com/windycn/stuScoreManage_Java/blob/main/imageCache/3-管理员登录.png?raw=true)![2-登录三次错误后退出系统](https://github.com/windycn/stuScoreManage_Java/blob/main/imageCache/2-登录三次错误后退出系统.png?raw=true)

---

:+1:当然还有实验报告中:page_with_curl: 对系统的阐述：

通过分析本次实验的要求，我认为需要把权限重新划分为管理员（*admin*）、学生（*student*）和教师（*teacher*）三个权限，如图所示为该学生成绩管理系统的用例图。

我们系统分为四个模块，分别为用户管理、学生管理、课程管理以及成绩管理，所以我们需要将三种权限的用户分配模块的不同功能。

其中管理员可以对用户、学生、课程以及成绩进行增删改查操作，涉及对系统完全信息的修改权限，但为了防止管理员操作出错，系统不支持删除当前登录用户，间接地保证了系统中有且至少有一个管理员用户，可以保障系统的正确运转。

学生用户只能查询和修改自己的用户信息，对其他用户信息操作完全隐藏。学生用户也仅仅只能查询自己的成绩以及绩点，绩点计算方式按照河南师范大学GPA计算方式进行计算。学生用户可以查询课程以及查询所有的课程，但不支持对课程进行任何其他操作。

教师用户也是只能查询和修改自己的用户信息，其他权限被屏蔽掉了。而教师用户不仅能够添加、查找、修改学生成绩，并且可以对某一门成绩进行统计，统计项目有最高分、最低分、及格率（大于60分）、优秀率（大于90分），同样在这里我们可以根据自己的需求自由发挥。但是教师用户不能删除学生成绩，也就是需要管理员权限才能删除。对于课程管理则和学生用户权限相同，只支持对课程的查询。

我们在设计学生管理、课程管理时候，都是采用*HashMap*存储对象到文件，而成绩管理利用的是*ArraryList*来存储对象。因为学生和课程管理具有唯一确定的索引码，并且需要快速查找，而成绩管理是联合学生ID和课程ID的联合索引码，可能是一对多，或者多对多的关系，所以 *ArrayList*进行对象序列化存储更为合适。



如图所示，学生成绩管理系统整体的脉络已经较为清晰了，我们再来看看我们该如何从Java高级程序设计语言的角度设计学生成绩管理系统。如图所示为学生成绩管理系统系统架构图，我们主要把系统自顶向下分为三种类文件，分别是启动及工具类、操控界面类和对象定义类。

其中启动及工具类里面是系统的启动类*start*，里面对系统启动的主菜单界面、系统的登录界面、系统登录校验以及权限处理和分配做出了定义，我们总是从这个主类的*main*方法启动学生成绩管理系统。并且在这种类文件中，我还封装了一些经常复用的工具方法类，比如*RorW*类中有对序列化对象的存储和读取的通用方法，而*color*类中重写了用于控制输出流字体样式的打印方法（*println*和*print*），用于我们展示更加具有美观的界面。

操控界面类，分别定义了对每个模块的函数调用以及对应的二级菜单界面，并且对于不同权限分门别类的将函数进行定义，并撰写了Java文档注释，可以后期快速帮助我们了解类及类方法所对应的权限及作用。

对象定义类作为学生成绩管理系统的核心文件，主要是将用户、学生、课程、成绩封装成一个完整的对象，然后直接利用Java序列化技术将对象转换为一个字节序列存入以“.data”为后缀结尾的对应类名文件中。这样充分利用了Java面向对象的程序设计思想，对系统进行了解耦合操作，通过分别控制、相互调用，来完成系统的一系列操作。



据此分析，整个系统整个流程已经被描述了出来，但是我们还需要添加许多细节之处，比如对错误的处理、对系统安全性的考虑等等，以提高系统的健壮性、稳定性和安全性。



工欲善其事，必先利其器。我们需要对整个项目具有一个较好的项目目录，而不是把所有的Java源文件杂乱堆放在一个包中。

![项目目录](https://github.com/windycn/stuScoreManage_Java/blob/main/imageCache/项目目录.png?raw=true)

如图所示，我们将整个系统划分为*course*、*score*、*student*、*user*和*stuScoreManage*包，分别将不同的对象定义类以及对应的操控界面类放入其中，这样子对项目我们能够很快速地进行代码搜寻。我们采用了自顶向下的系统架构设计，但是我们实际编写代码要采用自底向上进行一步步深入。

:joy: 总结部分就不放了......感谢能看到这的朋友！

:email:wy304379732@live.com 欢迎随时联系我！
