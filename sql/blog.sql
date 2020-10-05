drop database if exists blog;
create database blog default character set utf8 collate utf8_general_ci;
use  blog;

drop table if exists t_tag;
create table t_tag(
  id int(11) primary key not null auto_increment,
  name varchar(255) not null comment "标签"
);

insert into t_tag(id,name) values(1,'mysql'),(2,'spring'),(3,'mybatis'),(4,'redis'),(5,'zookeeper');

drop table if exists t_type;
create table t_type(
  id int(11) primary key not null auto_increment,
  name varchar(255) not null comment "分类"
);
insert into t_type(id,name) values(1,'java'),(2,'c'),(3,'go'),(4,'sql');

drop table if exists t_user;
create table t_user(
  id int(11) primary key not null auto_increment,
  username varchar(255) default null,
  password varchar(255) default null comment "密码",
  nickname varchar(255) default null comment "昵称",
  avatar varchar(255) default null comment "头像",
  create_time datetime default null comment "注册时间",
  email varchar(255) default null comment "邮箱",
  usertype int(11) default null comment "类型,admin"
);
insert into t_user(id,username,password,nickname,avatar,create_time,email,usertype) values('1', 'admin', 'password', '向志敏', 'https://allpassaway.oss-cn-shenzhen.aliyuncs.com/images/allpassaway.jpg', '2020-09-30 15:35:17', '3052720966@', '1');

drop table if exists t_blog;
create table t_blog(
  id int(11) primary key not null auto_increment,
  title varchar(255) default null comment "标题",
  user_id int(11) default null comment "发布者id",
  content longtext comment "内容",
  description varchar(255) default null comment "描述",
  first_picture varchar(255) default null comment "首图",
  flag varchar(255) default null comment "原创，转载，翻译",
  appreciation bit(1) default null comment "赞赏",
  commentabled bit(1) default null  comment "评论",
  recommend bit(1) default null  comment "推荐",
  share_statement bit(1) default null  comment "转载声明",
  published bit(1) default null  comment "发布/保存",
  create_time datetime default null comment "创建时间",
  update_time datetime default null comment "更新时间",
  views int(11) default null  comment "浏览量",
  type_id int(11) default null comment "类型id",
  foreign key(user_id) references t_user(id) on delete cascade on update cascade,
  foreign key(type_id) references t_type(id) on delete cascade on update cascade
);

/*一个博客有多个标签*/
drop table if exists t_blog_tag;
create table t_blog_tag(
  blog_id int(11) not null,
  tag_id int(11) not null,
  primary key (blog_id,tag_id),
  foreign key(blog_id) references t_blog(id) on delete cascade on update cascade,
  foreign key(tag_id) references t_tag(id) on delete cascade on update cascade
);

drop table if exists t_comment;
create table t_comment(
  id int(11) primary key not null auto_increment,
  admin_comment bit(1) default null comment "是否是博主",
  avatar varchar(255) default null comment "头像",
  content varchar(255) default null comment "评论内容",
  create_time datetime default null comment "评论时间",
  email varchar(255) default null comment "评论邮箱",
  nickname varchar(255) default null comment "评论昵称",
  blog_id int(11) default null comment "blog的id",
  foreign key(blog_id) references t_blog(id) on delete cascade on update cascade
);
