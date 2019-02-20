# Intruduce

集成项目开发中常用的框架和工具，加快开发速度，具体包括如下：  
BaseActivity  
通用dialog弹窗  
加载菊花转  
RecyclerViewHolder，InterfaceItemClick  
网络库  
观察者registrant  
Activity生命周期回调实现  
Drawable工具  
Fragment切换工具  
Json  
Application  
implementation中导入的第三方库  

todo: ViewModel+json的封装  
Rxjava的一些高级用法请看https://github.com/hrdzkj/RxJavaSamples的develop分支   

*************************待封装内容**************************
1)封装空view，errorview  showStateView(error/empty)
ViewFlipper (or FrameLayout  ViewSwitcher  ViewAnimator
一个ViewSwitcher有且只能有两个子View,而且同时只能有一个View显示给用户
https://stackoverflow.com/questions/4018772/calling-setcontentview-multiple-times
/**
 * 将页面添加进ViewFlipper
 */
private void addViews() {
    View itemView;
    for (int viewId : viewIds) {
        itemView = View.inflate(this, viewId, null);
        viewFlipper.addView(itemView);
    }
}
    
viewFlipper.setInAnimation(MainActivity.this, R.anim.right_in);
viewFlipper.setOutAnimation(MainActivity.this, R.anim.left_out);
viewFlipper.showNext();

viewFlipper.bringChildToFront();


如何保留标题---viewFlipper写在各个的布局文件里面,baseActivity封装切换方法，这样和LYW写的emptyview也差不多
              参考上拉刷新下拉加载组件（另外对外提供接口）
--------------
2）注解的方式进行权限验证，会改变流程吗，可行吗？
********************************************************************************

——-------------
https://github.com/JessYanCoding/MVPArms

支付宝使用了 aspectj okio,nannohttpd  里面有好多动画

AOP则是针对业务处理过程中的切面进行提取,从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率.

用AspectJ，把用来修饰方法的注解作为一个切点，在执行该方法时，触发该切点所对应的逻辑。
-----------
todo:
加入登录页面；
