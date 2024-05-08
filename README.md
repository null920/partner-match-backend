# 数字灯塔-伙伴匹配系统（后端）

为你找到合适的伙伴

基于 Vue 3 + Spring Boot 2 的移动端网站，实现了用户管理（复用另一个自己开发的用户中心）、根据标签
检索用户、随机匹配相似队友、组队等功能。

前端地址：[数字灯塔-伙伴匹配系统（前端）](https://github.com/null920/partner-match-frontend)

## 技术栈

* Java 8
* SpringBoot 2.6.x 框架
* MyBatis-Plus ORM框架
* MySQL 数据库
* Redis 缓存
* Redisson 分布式锁
* Spring Scheduler 定时任务
* Swagger + knife4j 接口文档
* Gson JSON解析
* 编辑距离算法

## 快速开始

1. 配置数据库连接信息
2. 运行 classpath:/sql/init.sql 初始化数据库
3. 运行

## 项目部署

### Docker打包上线

1. 安装 Docker https://docs.docker.com/get-docker/

2. 拉取 MySQL 镜像并运行容器

```bash
docker pull mysql
```

```bash
docker run --privileged=true --name mysql8.0.27 -d -p 3306:3306 -v /home/MySQL/conf:/etc/mysql/ -v /home/MySQL/data:
/var/lib/mysql -e MYSQL_ROOT_PASSWORD=password mysql/mysql-server
```

3. 拉取 Redis 镜像并运行容器

```bash
docker pull redis
```

```bash
docker run -d -p 6379:6379 -v /home/Redis/conf/redis.conf:/etc/redis/redis.conf -v /home/Redis/data:/data --name
redis redis-server /etc/redis/redis.conf
```

4. 编辑 application.yml-prod 文件
5. 上传项目 jar 包和 Dockerfile 到服务器
6. 构建 Docker 镜像

```bash
docker build -t partner-match-backend:v0.0.1 .
```

7. 运行 Docker 容器

```bash
docker run -p 8080:8080 -d partner-match-backend:v0.0.1
```