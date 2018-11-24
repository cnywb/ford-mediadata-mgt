package com.ford.mediadata.mgt.entity.security;

/**
 * Created by wanglijun on 16/12/2.
 */
public enum OperationType {

    USER_LOGIN ("登入","登录用户【{0}】成功!"),

    USER_LOGOUT ("退出","登出用户【{0}】成功! "),

    USER_PASSWORD_EXPIRED("密码过期","登录用户【{0}】失败,密码已过有效期!"),

    USER_CREATE ("新增用户","账号【{0}】"),

    USER_MODIFY ("修改用户","账号【{0}-->{1}】"),

    USER_PASSWORD_MODIFY ("修改密码","账号【{0}】新密码【{1}】"),

    USER_PASSWORD_RESET ("重置密码","账号【{0}】新密码【{1}】"),

    USER_REMOVE ("删除用户","账号【{0}】,密码【{0}】"),

    ROLE_CREATE ("新增角色","角色代码【{0}}】,角色名称【{1}】"),

    ROLE_MODIFY ("修改角色","角色代码【{0}——>{1}】,角色名称【{2}——>{3}】"),

    ROLE_REMOVE ("删除角色","角色代码【{0}】"),

    AUTH_ROLE_RESOURCE ("资源授权","角色代码【{0}】新增授权资源:【{1}】;删除授权资源:【{2}】"),

    AUTH_ROLE_USER_ADD ("授权用户新增","角色代码【{0}】用户【{1}】"),

    RESOURCE_CREATE("资源创建","资源代码【{0}}】,资源名称【{1}】"),

    RESOURCE_MODIFY("资源修改","资源代码【{0}——>{1}】,资源名称【{2}——>{3}】"),

    AUTH_ROLE_USER_REMOVE ("授权用户删除","角色代码【{0}】用户【{1}】");

    private String name;

    private String desc;

    OperationType(String name,String desc){
        this.name=name;
        this.desc=desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
