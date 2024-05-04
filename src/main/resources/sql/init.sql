-- 用户表
create table user
(
    id            bigint auto_increment comment 'id' primary key,
    username      varchar(255)                       null comment '用户昵称',
    user_account  varchar(255)                       null comment '账号',
    avatar_url    varchar(1024)                      null comment '头像',
    gender        tinyint                            null comment '性别',
    user_password varchar(512)                       not null comment '密码',
    phone         varchar(128)                       null comment '电话',
    email         varchar(512)                       null comment '邮箱',
    user_status   int      default 0                 null comment '用户状态 0-正常',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间（数据插入时间）',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间（数据更新时间）',
    deleted       tinyint  default 0                 not null comment '是否删除 0 1（逻辑删除）',
    user_role     tinyint  default 0                 not null comment '用户角色 0-普通用户 1-管理员',
    tags          varchar(1024)                      null comment '标签列表'
)
    comment '用户';

-- 标签表（标签字段已放入用户表中，不用再创建标签表）
# create table tag
# (
#     id          bigint auto_increment comment 'id' primary key,
#     tag_name    varchar(255)                       null comment '标签名称',
#     user_id     bigint                             null comment '用户 id',
#     parent_id   bigint                             null comment '父标签 id',
#     is_parent   tinyint                            null comment '是否是父标签 0-不是 1-是',
#     create_time datetime default CURRENT_TIMESTAMP null comment '创建时间（数据插入时间）',
#     update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间（数据更新时间）',
#     deleted     tinyint  default 0                 not null comment '是否删除 0 1（逻辑删除）',
#     constraint tag_name_uindex
#         unique (tag_name) comment '标签名唯一索引'
# )
#     comment '标签';

# create index user_id_index
#     on tag (user_id)
#     comment '用户id索引';

-- 队伍表
create table team
(
    id          bigint auto_increment comment 'id' primary key,
    team_name   varchar(255)                       not null comment '队伍名称',
    description varchar(1024)                      null comment '描述',
    max_num     int      default 1                 not null comment '最大人数',
    expire_time datetime                           null comment '过期时间',
    user_id     bigint comment '创建者id',
    status      int      default 0                 not null comment '队伍状态 0-公开，1-私有，2-加密',
    password    varchar(512)                       null comment '密码',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间（数据插入时间）',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间（数据更新时间）',
    deleted     tinyint  default 0                 not null comment '是否删除 0 1（逻辑删除）'
)
    comment '队伍';

-- 用户队伍关系表
create table user_team
(
    id          bigint auto_increment comment 'id' primary key,
    user_id     bigint comment '用户id',
    team_id     bigint comment '队伍id',
    join_time   datetime                           null comment '加入时间',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间（数据插入时间）',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间（数据更新时间）',
    deleted     tinyint  default 0                 not null comment '是否删除 0 1（逻辑删除）'
)
    comment '用户队伍关系';

