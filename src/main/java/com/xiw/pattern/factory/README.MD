# 工厂模式
## 产生原因
生产馍的种类和你的RoujiaMoStore耦合度太高了，如果增加几种风味，删除几种风味，你得一直修改sellRouJiaMo中的方法
## 静态工厂模式
类似于工具类，略过

## 简单工厂模式
不需要修改业务代码，业务代码只需要进行卖馍就可以了，后续不会进行修改

## 工厂方法模式
**定义**：定义一个创建对象的接口，但由子类决定要实例化的类是哪一个。工厂方法模式把类实例化的过程推迟到子类。

继续肉夹馍，由于使用了简单工厂模式，肉夹馍生意那个好啊，所以下载决定去西安开个分店，去北京开个分店。既然有分店了，那总店就是抽象的了：
## 抽象工厂模式

![](https://imgurlhangzhouxiwang.oss-cn-beijing.aliyuncs.com/20220520144824.png)
GUIFactory->RoujiaMoStore
WinFactory->XianRouJiaMoStore
RouJiaMO(Meat,YuanLiao)->Product(Button,Checkbox)
FreshMeat->WinButton
XianTeSeYuanLiao->WinCheckBox
