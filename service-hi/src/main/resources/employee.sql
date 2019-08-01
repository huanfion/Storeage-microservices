/*
 Navicat Premium Data Transfer

 Source Server         : postgres
 Source Server Type    : PostgreSQL
 Source Server Version : 110004
 Source Host           : localhost:5432
 Source Catalog        : mp
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 110004
 File Encoding         : 65001

 Date: 29/07/2019 11:49:10
*/


-- ----------------------------
-- Table structure for tbl_employee
-- ----------------------------
DROP TABLE IF EXISTS "public"."tbl_employee";
CREATE TABLE "public"."tbl_employee" (
  "id" int4 NOT NULL ,
  "last_name" varchar(50) COLLATE "pg_catalog"."default",
  "email" varchar(50) COLLATE "pg_catalog"."default",
  "gender" char(1) COLLATE "pg_catalog"."default",
  "age" int4,
	  "create_uid" int4,
  "create_time" timestamp(0),
  "update_uid" int4,
  "update_time" timestamp(0)
)
;



-- ----------------------------
-- Primary Key structure for table tbl_employee
-- ----------------------------
ALTER TABLE "public"."tbl_employee" ADD CONSTRAINT "tbl_employee_pkey" PRIMARY KEY ("id");


CREATE SEQUENCE employee_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

--将序列赋值给主键
alter table tbl_employee alter column id set default nextval('employee_id_seq');

--drop TABLE tbl_employee


--delete from tbl_employee

-- ----------------------------
-- Records of tbl_employee
-- ----------------------------
INSERT INTO "public"."tbl_employee" (last_name,email,gender,age,create_uid,create_time,update_uid,update_time) VALUES ('Tom', 'tom@atguigu.com', '1', 22,1,now(),NULL,NULL);
INSERT INTO "public"."tbl_employee" (last_name,email,gender,age,create_uid,create_time,update_uid,update_time)VALUES ('Jerry', 'jerry@atguigu.com', '0', 25,1,now(),NULL,NULL);
INSERT INTO "public"."tbl_employee" (last_name,email,gender,age,create_uid,create_time,update_uid,update_time)VALUES ('Black', 'black@atguigu.com', '1', 30,1,now(),NULL,NULL);
INSERT INTO "public"."tbl_employee" (last_name,email,gender,age,create_uid,create_time,update_uid,update_time)VALUES ('White', 'white@atguigu.com', '0', 35,1,now(),NULL,NULL);

select * from tbl_employee
