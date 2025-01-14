![github](https://img2018.cnblogs.com/blog/1216886/201909/1216886-20190922011540994-1036716499.png) 

## 装饰器模式（Decorator Pattern）
> 动态地将责任附加到对象上。若要扩展功能，装饰者提供了比继承更有弹性的替代方案。


允许向一个现有的对象添加新的功能，同时又不改变其结构。这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。

这种模式创建了一个装饰类，用来包装原有的类，并在保持类方法签名完整性的前提下，提供了额外的功能。

## 优缺点

　　- 优点：装饰类和被装饰类可以独立发展，不会相互耦合，装饰模式是继承的一个替代模式，装饰模式可以动态扩展一个实现类的功能。

　　- 缺点：多层装饰比较复杂。

## 使用场景： 

　　- 扩展一个类的功能。

　　- 动态增加功能，动态撤销

## 实现：
-我们要以咖啡为主体，然后在运行时以调料来“装饰”（coffee）饮料。比方说，如果顾客想要摩卡和奶泡深焙咖啡，那么，要做的是：
  
 ①、拿一个美式咖啡（AmericanCoffee）对象
 
 ②、以摩卡（Mocha）对象装饰它
 
 ③、以牛奶（Milk）对象装饰它
 
 ④、调用cost()方法，并依赖委托（delegate）将调料的价钱加上去。

