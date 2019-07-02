### 软件架构
1. SpringBoot + MybatisPlus核心框架
2. PageHelper插件 + 通用Mapper插件
3. 自定义token实现登陆状态控制
4. Redis(Jedis)缓存框架
5. swaggger2+ui超级好看的接口文档生成
6. 自定义模版生成mybatis-plus AR模式的各种CRUD代码
7. 加入Ddos、Xss防护
8. 增加Cors跨域支持
9. 集成微信公众号WxJava-mp
10.集成微信小程序WxJava-miniapp（微信运动获取步数）
11.集成微信支付WxJava-pay
### 项目相关
1. 接口文档 http://localhost:8080/docs.html
#### 关于将Jedis工具类与SpringBoot整合
```txt
本来是直接将RedisUtil注入为Bean，每次使用直接@Autowired注入使用即可
```

### 软件架构

1. SpringBoot + Mybatisplus核心框架
2. PageHelper插件 + 通用Mapper插件
3. Redis(Jedis)缓存框架

### 安装教程

1. 数据库帐号密码默认为root，如有修改，请自行修改配置文件application.yml
2. mal_plus脚本创建数据库和表
3. Redis需要自行安装Redis服务，端口密码默认
4. SpringBoot直接启动即可，测试工具PostMan

### api测试脚本（test->apitest文件夹下）
1. 登陆地址：微信授权登陆
2. 获取到token
3. 放入Header  格式：Authorization  上面token
```txt
进行请求访问，请求访问成功
```
```txt
点击查看Header信息的Authorization属性即是Token字段
```
```txt
访问需要权限的请求将Token字段放在Header信息的Authorization属性访问即可
```
```txt
Token的自动刷新也是在Token失效时返回新的Token在Header信息的Authorization属性
```

