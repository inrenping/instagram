## Instagram

下载 instagram 照片到本地，并提供访问图片列表接口。

## todo

保存图片到对象存储。

## api
~~~

https://instagram.com/inrenping/?__a=1

https://instagram.com/graphql/query/?query_id=17888483320059182&id=3163835810&first=50&after=QVFEcTVySjEzOW1Ick4xTDQ3aXlZdXFKUUNhcWY0eTJnWTc1Ym84ZVE3S1N4MUprVU9ZZDBOVmQ0VjdTVlFRUjRxY19qb3ZuZG1lNFVtZXpKTi1odlc3WQ==

https://www.instagram.com/p/22CLWqkTbgbAV/?__a=1

https://instagram.com/graphql/query/?query_hash=d4e8ae69cb68f66329dcebe82fb69f6d&variables={%22shortcode%22:%22CLWqkTbgbAV%22}
~~~

## SQL
~~~
CREATE TABLE include.t_instagram_query (
  id bigint NOT NULL AUTO_INCREMENT,
  ins_id bigint NOT NULL,
  type_name varchar(255) DEFAULT NULL,
  short_code varchar(255) DEFAULT NULL,
  display_url varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  taken_at_timestamp bigint DEFAULT NULL,
  user_id bigint NOT NULL,
  file_id bigint DEFAULT NULL,
  mark varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  remark varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '重新备注',
  ischild int DEFAULT 0 COMMENT '是否是详情页',
  createtime datetime DEFAULT (SYSDATE()) COMMENT '创建时间',
  end_cursor varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '下一页',
  like_count int DEFAULT 0 COMMENT '点赞数',
  location_id bigint DEFAULT NULL,
  location_name varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;
~~~
~~~
CREATE TABLE include.t_log (
  id bigint NOT NULL AUTO_INCREMENT,
  ip varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  createtime datetime NOT NULL DEFAULT (SYSDATE()) COMMENT '创建时间',
  http_header varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  remark varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;
~~~

## 执行

~~~
http:127.0.0.1:8080/vultr/firstfetchquerywithupdate
http:127.0.0.1:8080/vultr/recursionfetchquerywithupdate?end_cursor=
~~~