package com.ebpm.common.bean;

public class ResponseBean {
    private Integer status;   //状态码
    private String msg;       //返回信息
    private Object obj;       //数据
 
    private ResponseBean() {
    }
 
    public static ResponseBean build() {
        return new ResponseBean();
    }
 
    public static ResponseBean ok(String msg, Object obj) {
        return new ResponseBean(200, msg, obj);
    }
 
    public static ResponseBean ok(String msg) {
        return new ResponseBean(200, msg, null);
    }
 
    public static ResponseBean error(String msg, Object obj) {
        return new ResponseBean(500, msg, obj);
    }
 
    public static ResponseBean error(String msg) {
        return new ResponseBean(500, msg, null);
    }
 
    private ResponseBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }
 
    public Integer getStatus() {
 
        return status;
    }
 
    public ResponseBean setStatus(Integer status) {
        this.status = status;
        return this;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public ResponseBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }
 
    public Object getObj() {
        return obj;
    }
 
    public ResponseBean setObj(Object obj) {
        this.obj = obj;
        return this;
    }
}

//RespBean respBean = null;
//if (e instanceof BadCredentialsException ||
//    e instanceof UsernameNotFoundException) {
//    respBean = RespBean.error("账户名或者密码输入错误!");
//} else if (e instanceof LockedException) {
//    respBean = RespBean.error("账户被锁定，请联系管理员!");
//} else if (e instanceof CredentialsExpiredException) {
//    respBean = RespBean.error("密码过期，请联系管理员!");
//} else if (e instanceof AccountExpiredException) {
//    respBean = RespBean.error("账户过期，请联系管理员!");
//} else if (e instanceof DisabledException) {
//    respBean = RespBean.error("账户被禁用，请联系管理员!");
//} else {
//    respBean = RespBean.error("登录失败!");
//}
//resp.setStatus(401);
//ObjectMapper om = new ObjectMapper();
//PrintWriter out = resp.getWriter();
//out.write(om.writeValueAsString(respBean));
//out.flush();
//out.close();
