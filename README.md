# **坦克大战**

游戏截图：

![初始状态](https://img-blog.csdnimg.cn/3bf84e9bdc1241ffb425f7f2355372d7.png)
![击中坦克](https://img-blog.csdnimg.cn/4c628080cd774c4489f4d45c72973da2.png)

## 操作坦克的方法：

WASD

J：为射出子弹

## 需求文档
```
/*
 * 		需求：
 * 			坦克大战:
 * 			功能：	
 * 				1.画出坦克，
 * 		
 * 		思路：
 * 			1.首先坦克想象由五个部件组成两个矩形，一个长方形或者正方形，一个圆
 * 				一条直线
 * 
 * 			2.画坦克的时候需要使用到画笔工具
 * 				必须在构造函数初始化使用画笔工具
 * 
 * 			3.在设置方向以及画出不同方向的坦克
 * 
 * 			4.敌方坦克画出来需要使用父类方法
 * 				敌方坦克的坐标需要设置，	
 * 				使用一个集合保存敌方坦克Vector集合便于删除和添加 
 * 
 * 			5.发射子弹是一个线程
 * 				具有线程的功能
 * 				另外线程对与子弹方向运动轨迹不同
 * 
 * 			6.需要把子弹画出来
 * 				在按下J键的时候发射子弹
 * 					实现连发使用集合存储
 * 				
 * 		升级：
 * 			1.让敌人能够发射子弹
				解决方法
					1.敌人发射子弹是一个多线程方法，应当在敌人的run函数当中实现
					2.坦克发射子弹和移动都是坦克本身具有的功能
 * 
 * 			思路：
 * 				1.在敌人类里面需要添加一个射击方法
 * 					与我方一样，但是敌人是自动射击或者说每过几秒射击一次
 * 
 * 				2.我方坦克子弹连发
 * 					使用一个集合保存建立的对象，画出子弹使用集合中的对象
 * 					我方坦克子弹连发过快，需要限定		
 * 			
 * 				3.
 * 					我方坦克击中敌人坦克之后，敌人坦克就要消失
 * 					需要获取到敌人的一个定点坐标，然后界定一个范围
 * 					写一个专门的函数判断是否击中敌人
 * 					
 * 					在哪里判断是否击中敌人
 * ·				因为每一颗子弹都要与所有的坦克匹配，并且每一次匹配都要
 * 					双重判断每次都要进行建立对象
 * 					图片问题没有得到解决
 * 
 * 		升级
 * 				1.需要实现敌人的坦克不断的移动使用多线程的手段实现
 * 
 * 				2.需要实现敌人能够发射子弹的功能
 * 				实现方法：
 * 					建立一个敌人的子弹集合
 * 					如何敌人何时发射子弹？
 * 					使用多重循环判断是否需要添加薪子弹
 * 
 * 				3.实现自己被子弹击中也会消失
 * 					对于摧毁坦克进行升级
 * 
 * 				4.
 * 					较难！
 * 					实现坦克不覆盖运动，
 * 					1.首先改判断在坦克类中实现
 * 					2.需要用到一个方法获取到生成的坦克类
 * 					3.对于地方其中一辆坦克的选择，都要循环与其他所有坦克进行比对
 * 						并且要事先判断是否为我方坦克
 * 					4.**对于点位的判断要判断两个点，才能够保证不会产生碰撞
 * 
 * 				5.实现选择关卡的功能
 * 					思路：
 * 						1.可以建立一个选择关卡的面板
 * 						2.暂时先实现不同的关卡敌人坦克的数量不同
 * 						3.实现闪烁功能，使用多线程的方法,注意线程的关闭
 * 						4.对于选项添加事件属性,添加事件
 * 
 * 				5.画出我方坦克击中了多少辆地方坦克
 * 					1.对于总体界面进行修改
 * 					2.显示敌人坦克的数量
 * 					扩展：
 * 						1.建立帮助文档
 * 					3.扩展:我方坦克的生命值，当生命值为0的时候游戏结束
 * 					4.记录我方击中了多少地方坦克
 * 						使用文件操作完成
 * 
 * 				6.实现重新开始的功能
 * 
 * 				7.实现存盘退出的功能
 * 					思路：
 * 						选在主界面增加两个按钮
 * 						1.记录所有坦克的坐标
 * 						
 * 				
 * 					
 * 
 * */
```