package 高并发.CountDownLaunch;



import java.util.concurrent.TimeUnit;



/**

* 微信公众号：javacode2018，获取年薪50万课程

*/

public

class

Demo1{
public static class T extends Thread {

//休眠时间（秒）
int sleepSeconds;

public T(String name, int sleepSeconds)
{
    super(name);
    this.sleepSeconds = sleepSeconds;
}




@Override


public void run(){


Thread ct =

Thread.currentThread();


long startTime = System.currentTimeMillis();


System.out.println
(
    startTime + "," + ct.getName() + ",开始处理!"
);

try

{


//模拟耗时操作，休眠sleepSeconds秒


TimeUnit
.
SECONDS
.
sleep
(
this
.
sleepSeconds
);


}

catch

(
InterruptedException
e
)

{

        e
.
printStackTrace
();


}


long
endTime
=

System
.
currentTimeMillis
();


System
.
out
.
println
(
endTime 
+

","

+
ct
.
getName
()

+

",处理完毕,耗时:"

+

(
endTime 
-
startTime
));


}


}




public

static

void
main
(
String
[]
args
)

throws

InterruptedException

{


long
starTime
=

System
.
currentTimeMillis
();

T t1
=

new
T
(
"解析sheet1线程"
,

2
);

t1
.
start
();



T t2
=

new
T
(
"解析sheet2线程"
,

5
);

t2
.
start
();



t1
.
join
();

t2
.
join
();


long
endTime
=

System
.
currentTimeMillis
();


System
.
out
.
println
(
"总耗时:"

+

(
endTime 
-
starTime
));




}

}