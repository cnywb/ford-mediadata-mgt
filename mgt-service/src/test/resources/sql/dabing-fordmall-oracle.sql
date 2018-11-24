/*
 Navicat Premium Data Transfer

 Source Server         : 120.25.228.245
 Source Server Type    : Oracle
 Source Server Version : 112010
 Source Host           : 120.25.228.245
 Source Schema         : CRM

 Target Server Type    : Oracle
 Target Server Version : 112010
 File Encoding         : utf-8

 Date: 05/01/2016 23:29:57 PM
*/

-- ----------------------------
--  Table structure for mall_im_code_list
-- ----------------------------
DROP TABLE "CRM"."mall_im_code_list";
CREATE TABLE "mall_im_code_list" (   "ID" NUMBER(11,0) NOT NULL, "CODE" VARCHAR2(40CHAR), "TYPE_CODE" VARCHAR2(40CHAR), "NAME_CN" VARCHAR2(40CHAR), "NAME_EN" VARCHAR2(40CHAR), "SORT_NO" NUMBER(11,0), "SELECTED" NUMBER(11,0), "TENANT_ID" NUMBER(11,0), "LAST_MODIFIED" DATE, "FIRST_INSERT" DATE, "DELETED" NUMBER(11,0), "DELETE_TIME" DATE);
COMMENT ON COLUMN "mall_im_code_list"."CODE" IS '字典小类编码';
COMMENT ON COLUMN "mall_im_code_list"."NAME_CN" IS '编码中文名称';
COMMENT ON COLUMN "mall_im_code_list"."NAME_EN" IS '英文名称';
COMMENT ON COLUMN "mall_im_code_list"."SORT_NO" IS '排序';
COMMENT ON COLUMN "mall_im_code_list"."SELECTED" IS '默认选中 1是，0 否';
COMMENT ON COLUMN "mall_im_code_list"."TENANT_ID" IS '租户主键';zen
COMMENT ON COLUMN "mall_im_code_list"."LAST_MODIFIED" IS '最后修改时间';
COMMENT ON COLUMN "mall_im_code_list"."FIRST_INSERT" IS '首次插入时间';
COMMENT ON COLUMN "mall_im_code_list"."DELETED" IS '是否逻辑删除';

-- ----------------------------
--  Table structure for mall_im_code_type
-- ----------------------------
DROP TABLE "CRM"."mall_im_code_type";
CREATE TABLE "mall_im_code_type" (   "ID" NUMBER(11,0) NOT NULL, "CODE" VARCHAR2(40CHAR), "TYPE_CODE" VARCHAR2(40CHAR), "NAME_CN" VARCHAR2(40CHAR), "NAME_EN" VARCHAR2(40CHAR), "COMMENT" VARCHAR2(2000CHAR), "TENANT_ID" NUMBER(11,0), "ENABLE" NUMBER(11,0), "LAST_MODIFIED" DATE, "FIRST_INSERT" DATE, "DELETED" NUMBER(11,0), "DELETE_TIME" DATE);
COMMENT ON COLUMN "mall_im_code_type"."ID" IS '物理主键ID';
COMMENT ON COLUMN "mall_im_code_type"."CODE" IS '字典大类类别';
COMMENT ON COLUMN "mall_im_code_type"."TYPE_CODE" IS '字典类型代码';
COMMENT ON COLUMN "mall_im_code_type"."NAME_CN" IS '编码中文名称';
COMMENT ON COLUMN "mall_im_code_type"."NAME_EN" IS '英文名称';
COMMENT ON COLUMN "mall_im_code_type"."COMMENT" IS '描述';
COMMENT ON COLUMN "mall_im_code_type"."TENANT_ID" IS '租户主键';
COMMENT ON COLUMN "mall_im_code_type"."ENABLE" IS '是否可以被用户编辑，1可以，0 不可以';
COMMENT ON COLUMN "mall_im_code_type"."LAST_MODIFIED" IS '最后修改时间';
COMMENT ON COLUMN "mall_im_code_type"."FIRST_INSERT" IS '首次插入时间';
COMMENT ON COLUMN "mall_im_code_type"."DELETED" IS '是否逻辑删除';

-- ----------------------------
--  Table structure for mall_image_store
-- ----------------------------
DROP TABLE "CRM"."mall_image_store";
CREATE TABLE "mall_image_store" (   "id" NUMBER(11,0) NOT NULL, "FIRST_INSERT" DATE, "LAST_MODIFIED" DATE, "DELETED" NUMBER(11,0), "DELETE_TIME" DATE, "TYPE" VARCHAR2(30CHAR), "SUBTYPE" VARCHAR2(30CHAR), "URL" VARCHAR2(255CHAR), "REFID" NUMBER(11,0), "BUCKET" VARCHAR2(30CHAR), "FILENAME" VARCHAR2(100CHAR));
COMMENT ON COLUMN "mall_image_store"."id" IS '主键ID，生成策略，自动';
COMMENT ON COLUMN "mall_image_store"."FIRST_INSERT" IS '记录创建时间';
COMMENT ON COLUMN "mall_image_store"."LAST_MODIFIED" IS '最后一次修改时间';
COMMENT ON COLUMN "mall_image_store"."DELETED" IS '是否逻辑删除';
COMMENT ON COLUMN "mall_image_store"."DELETE_TIME" IS '逻辑删除时间';
COMMENT ON COLUMN "mall_image_store"."TYPE" IS '类型:人员影像|合同影像';
COMMENT ON COLUMN "mall_image_store"."SUBTYPE" IS '子类型:影像件业务归属类型';
COMMENT ON COLUMN "mall_image_store"."URL" IS '影像件地址:直接是OSS的外网地址';
COMMENT ON COLUMN "mall_image_store"."REFID" IS '业务引用关系ID:人员或合同对象的ID';
COMMENT ON COLUMN "mall_image_store"."BUCKET" IS '阿里云OSS上文件所属bucket值';
COMMENT ON COLUMN "mall_image_store"."FILENAME" IS '阿里云OSS上文件key值';

-- ----------------------------
--  Table structure for mall_permission
-- ----------------------------
DROP TABLE "CRM"."mall_permission";
CREATE TABLE "mall_permission" (   "ID" NUMBER(11,0) NOT NULL, "ROLE_CODE" VARCHAR2(50CHAR), "RESOURCE_CODE" VARCHAR2(20CHAR), "FIRST_INSERT" DATE, "DELETED" NUMBER(11,0), "DELETE_TIME" DATE, "LAST_MODIFIED" DATE);
COMMENT ON COLUMN "mall_permission"."DELETED" IS '是否逻辑删除';

-- ----------------------------
--  Table structure for mall_resource
-- ----------------------------
DROP TABLE "CRM"."mall_resource";
CREATE TABLE "mall_resource" (   "ID" NUMBER(11,0) NOT NULL, "CODE" VARCHAR2(20CHAR), "NAME" VARCHAR2(50CHAR), "TYPE" VARCHAR2(10CHAR), "IS_MENU" VARCHAR2(20CHAR), "PERMISSION" VARCHAR2(60CHAR), "PARENT_CODE" VARCHAR2(20CHAR), "PARENT_NAME" VARCHAR2(200CHAR), "SORT_NO" NUMBER(11,0), "LAST_MODIFIED" DATE, "FIRST_INSERT" DATE, "DELETED" NUMBER(11,0), "DELETE_TIME" DATE);
COMMENT ON COLUMN "mall_resource"."DELETED" IS '是否逻辑删除';

-- ----------------------------
--  Table structure for mall_role
-- ----------------------------
DROP TABLE "CRM"."mall_role";
CREATE TABLE "mall_role" (   "ID" NUMBER(11,0) NOT NULL, "ROLE_CODE" VARCHAR2(50CHAR), "ROLE_NAME" VARCHAR2(100CHAR), "REMARK" VARCHAR2(100CHAR), "LAST_MODIFIED" DATE, "FIRST_INSERT" DATE, "DELETED" NUMBER(11,0), "DELETE_TIME" DATE);
COMMENT ON COLUMN "mall_role"."DELETED" IS '是否逻辑删除';

-- ----------------------------
--  Table structure for mall_table_sequence
-- ----------------------------
DROP TABLE "CRM"."mall_table_sequence";
CREATE TABLE "mall_table_sequence" (   "id" NUMBER(11,0) NOT NULL, "FIRST_INSERT" DATE, "LAST_MODIFIED" DATE, "DELETED" NUMBER(11,0), "DELETE_TIME" DATE, "TABLE_NAME" VARCHAR2(100CHAR), "SEQ_VALUE" NUMBER(11,0), "DESC_" VARCHAR2(100CHAR));
COMMENT ON COLUMN "mall_table_sequence"."id" IS '主键ID，生成策略，自动';
COMMENT ON COLUMN "mall_table_sequence"."FIRST_INSERT" IS '记录创建时间';
COMMENT ON COLUMN "mall_table_sequence"."LAST_MODIFIED" IS '最后一次修改时间';
COMMENT ON COLUMN "mall_table_sequence"."DELETED" IS '是否逻辑删除';
COMMENT ON COLUMN "mall_table_sequence"."DELETE_TIME" IS '逻辑删除时间';
COMMENT ON COLUMN "mall_table_sequence"."TABLE_NAME" IS '表名';
COMMENT ON COLUMN "mall_table_sequence"."SEQ_VALUE" IS 'SEQ 值';
COMMENT ON COLUMN "mall_table_sequence"."DESC_" IS '业务描述';

-- ----------------------------
--  Table structure for mall_user
-- ----------------------------
DROP TABLE "CRM"."mall_user";
CREATE TABLE "mall_user" (   "ID" NUMBER(11,0) NOT NULL, "LOGIN_NAME" NVARCHAR2(40), "PASSWORD" NVARCHAR2(40), "NAME" NVARCHAR2(40), "MOBILE" NVARCHAR2(20), "EMAIL" NVARCHAR2(50), "ORG_ID" NUMBER(11,0), "ORG_CODE" NVARCHAR2(50), "ORG_NAME" NVARCHAR2(100), "FIRST_INSERT" DATE, "LAST_MODIFIED" DATE, "DELETED" NUMBER(11,0), "DELETE_TIME" DATE);
COMMENT ON COLUMN "mall_user"."ID" IS '物理主键';
COMMENT ON COLUMN "mall_user"."LOGIN_NAME" IS '登陆账号';
COMMENT ON COLUMN "mall_user"."PASSWORD" IS '登陆密码';
COMMENT ON COLUMN "mall_user"."NAME" IS '姓名';
COMMENT ON COLUMN "mall_user"."MOBILE" IS '联系方式';
COMMENT ON COLUMN "mall_user"."FIRST_INSERT" IS '首次插入时间';
COMMENT ON COLUMN "mall_user"."LAST_MODIFIED" IS '最后修改时间';
COMMENT ON COLUMN "mall_user"."DELETED" IS '是否逻辑删除';

-- ----------------------------
--  Table structure for mall_user_role
-- ----------------------------
DROP TABLE "CRM"."mall_user_role";
CREATE TABLE "mall_user_role" (   "ID" NUMBER(11,0) NOT NULL, "ROLE_ID" NUMBER(11,0), "USER_ID" NUMBER(11,0), "ROLE_CODE" VARCHAR2(50CHAR), "TENANT_ID" NUMBER(11,0), "LAST_MODIFIED" DATE, "FIRST_INSERT" DATE, "DELETED" NUMBER(11,0), "DELETE_TIME" DATE);
COMMENT ON COLUMN "mall_user_role"."TENANT_ID" IS '租户主键';
COMMENT ON COLUMN "mall_user_role"."LAST_MODIFIED" IS '最后修改时间';
COMMENT ON COLUMN "mall_user_role"."FIRST_INSERT" IS '首次插入时间';
COMMENT ON COLUMN "mall_user_role"."DELETED" IS '是否逻辑删除';

-- ----------------------------
--  Primary key structure for table mall_im_code_list
-- ----------------------------
ALTER TABLE "CRM"."mall_im_code_list" ADD CONSTRAINT "SYS_C0019246" PRIMARY KEY("ID");

-- ----------------------------
--  Primary key structure for table mall_im_code_type
-- ----------------------------
ALTER TABLE "CRM"."mall_im_code_type" ADD CONSTRAINT "SYS_C0019247" PRIMARY KEY("ID");

-- ----------------------------
--  Primary key structure for table mall_image_store
-- ----------------------------
ALTER TABLE "CRM"."mall_image_store" ADD CONSTRAINT "SYS_C0019248" PRIMARY KEY("id");

-- ----------------------------
--  Primary key structure for table mall_permission
-- ----------------------------
ALTER TABLE "CRM"."mall_permission" ADD CONSTRAINT "SYS_C0019249" PRIMARY KEY("ID");

-- ----------------------------
--  Primary key structure for table mall_resource
-- ----------------------------
ALTER TABLE "CRM"."mall_resource" ADD CONSTRAINT "SYS_C0019250" PRIMARY KEY("ID");

-- ----------------------------
--  Primary key structure for table mall_role
-- ----------------------------
ALTER TABLE "CRM"."mall_role" ADD CONSTRAINT "SYS_C0019251" PRIMARY KEY("ID");

-- ----------------------------
--  Primary key structure for table mall_table_sequence
-- ----------------------------
ALTER TABLE "CRM"."mall_table_sequence" ADD CONSTRAINT "SYS_C0019252" PRIMARY KEY("id");

-- ----------------------------
--  Primary key structure for table mall_user
-- ----------------------------
ALTER TABLE "CRM"."mall_user" ADD CONSTRAINT "SYS_C0019253" PRIMARY KEY("ID");

-- ----------------------------
--  Primary key structure for table mall_user_role
-- ----------------------------
ALTER TABLE "CRM"."mall_user_role" ADD CONSTRAINT "SYS_C0019254" PRIMARY KEY("ID");



CREATE SEQUENCE  "CRM"."SEQ_MALL_IM_CODE_LIST"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "CRM"."SEQ_MALL_IM_CODE_TYPE"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "CRM"."SEQ_MALL_IMAGE_STORE"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "CRM"."SEQ_MALL_PERMISSION"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "CRM"."SEQ_MALL_RESOURCE"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "CRM"."SEQ_MALL_ROLE"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "CRM"."SEQ_MALL_USER"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "CRM"."SEQ_MALL_TABLE_SEQUENCE"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;


