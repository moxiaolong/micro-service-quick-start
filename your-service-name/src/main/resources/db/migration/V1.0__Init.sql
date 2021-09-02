create table if not exists _template
(
    id                 bigint auto_increment comment '主键'
        primary key,
    create_time        datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time        datetime default CURRENT_TIMESTAMP not null comment '修改时间',
    create_operator_id bigint   default 0                 not null comment '创建人id',
    update_operator_id bigint   default 0                 not null comment '更新人id',
    is_delete          tinyint                            null comment '逻辑删除 0 未删除，1 已删除'
)
    comment '建表模板';

create table if not exists user
(
    id bigint auto_increment comment '主键' primary key
)
    comment '测试user表'