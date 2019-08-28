# AndroidKotlin
### 如果帮到您了请给个赞，赞多的话我会抽时间继续给大家更新支持的~
### 项目介绍
    AndroidKotlin：MVP+集成Retrofit+Rxjava+Okhttp网络框架，关于github的项目
    已升级更换为androidx
    
### 软件架构
    基于Retrofit+Rxjava+Okhttp封装的网络请求框架
    使用时请自行修改okhttp的公共参数（HeaderParam）
    1.HttpService请求的接口
    2.RetrofitFactory类：初始化并配置Retrofit和OkHttp
    3.OnSuccessAndFaultSub类：封装回调,项目中接收的是gzip压缩过的流
    4.各种Api类：根据业务模块划分，实现观察者和被观察者的订阅。
    
### 使用说明
    可直接使用
    
### 开源框架
com.github.cn-ljb:kotlin-mvp-lib:1.2.0
    

##### 接口不得以任何形式转载，项目可随意使用，注明出处。
