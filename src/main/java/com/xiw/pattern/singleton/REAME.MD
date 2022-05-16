# 单例设计模式
## 设计思想
(1)不允许其他程序用new对象。
因为new就是开辟新的空间，在这里更改数据只是更改的所创建的对象的数据，如果可以new的话，每一次new都产生一个对象，这样肯定保证不了对象的唯一性。

(2)在该类中创建对象
因为不允许其他程序new对象，所以这里的对象需要在本类中new出来

(3)对外提供一个可以让其他程序获取该对象的方法
因为对象是在本类中创建的，所以需要提供一个方法让其它的类获取这个对象。

## 实现方法

(1)私有化该类的构造函数

(2)通过new在本类中创建一个本类对象

(3)定义一个公有的方法，将在该类中所创建的对象返回

## 写法
经过3中的分析我们理解了单例所解决的问题以及它的实现思想，接着来看看它的实现代码，单例模式的写法大的方面可以分为5种五种：

①懒汉式
- SingletonLazy
- SingletonLazyPlus

②饿汉式
- SingletonSlow
- SingletonThreadNotSafe: 有多个线程去调用getInstance方法来获取Singleton的实例，那么就有可能发生这样一种情况当第一个线程在执行if(instance==null)这个语句时，此时instance是为null的进入语句。在还没有执行instance=new Singleton()时(此时instance是为null的)第二个线程也进入if(instance==null)这个语句，因为之前进入这个语句的线程中还没有执行instance=new Singleton()，所以它会执行instance=new Singleton()来实例化Singleton对象，因为第二个线程也进入了if语句所以它也会实例化Singleton对象。这样就导致了实例化了两个Singleton对象。
- SingletonThreadNotSafePlus: 当一个线程还没有实例化Singleton时另一个线程执行到if(instance==null)这个判断语句时就会进入if语句，虽然加了锁，但是等到第一个线程执行完instance=new Singleton()跳出这个锁时，另一个进入if语句的线程同样会实例化另外一个Singleton对象，线程不安全的原理跟4.3类似。

③双重校验锁
- SingletonDoubleCheck: Double-Check概念对于多线程开发者来说不会陌生，如代码中所示，我们进行了两次if (instance== null)检查，这样就可以保    证线程安全了。这样，实例化代码只用执行一次，后面再次访问时，判断if (instance== null)，直接return实例化对象。
  - 优点：
    - 线程安全；
    - 延迟加载；
    - 效率较高。

④静态内部类
- SingletonInnerClass: 这种方式跟饿汉式方式采用的机制类似，但又有不同。两者都是采用了类装载的机制来保证初始化实例时只有一个线程。不同 的地方在饿汉式方式是只要Singleton类被装载就会实例化，没有Lazy-Loading的作用，而静态内部类方式在Singleton类被装载时 并不会立即实例化，而是**在需要实例化时，调用getInstance方法，才会装载SingletonHolder类**，从而完成Singleton的实例化。 类的静态属性只会在第一次加载类的时候初始化，所以在这里，**JVM帮助我们保证了线程的安全性**，在类进行初始化时，别的线程是无法进入的。
  - 优点：
    - 避免了线程不安全
    - 延迟加载
    - 效率高。

⑤枚举。