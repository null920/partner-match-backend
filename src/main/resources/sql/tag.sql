create table tag
(
    id          bigint auto_increment comment 'id'
        primary key,
    tag_name    varchar(255)                       null comment '标签名称',
    user_id     bigint                             null comment '用户 id',
    parent_id   bigint                             null comment '父标签 id',
    is_parent   tinyint                            null comment '是否是父标签 0-不是 1-是',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间（数据插入时间）',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间（数据更新时间）',
    deleted     tinyint  default 0                 null comment '是否删除 0 1（逻辑删除）',
    constraint tag_name_uindex
        unique (tag_name) comment '标签名唯一索引'
)
    comment '标签';

create index user_id_index
    on tag (user_id)
    comment '用户id索引';

