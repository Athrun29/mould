package org.zuel.mould.util;

import com.github.pagehelper.Page;
import org.zuel.mould.constant.RespEnum;

public class RespUtil {

    public static BasePager getPager(Page page) {
        BasePager pager = new BasePager();
        pager.setPageNum(page.getPageNum());
        pager.setPageSize(page.getPageSize());
        pager.setTotalNum(page.getTotal());
        return pager;
    }

    public static RespMsg success(Page page, Object object) {
        if(page == null) {
            return success(object);
        } else {
            return success(getPager(page), object);
        }
    }

    public static RespMsg success(BasePager pager, Object object) {
        RespPager respPager = new RespPager();
        respPager.setPager(pager);
        respPager.setDatas(object);
        return success(respPager);
    }

    public static RespMsg success(){
        return success(null);
    }

    public static RespMsg success(Object object){
        return success(RespEnum.SUCCESS.getMsg(), object);
    }

    public static RespMsg success(String msg, Object object) {
        RespMsg respMsg = new RespMsg();
        respMsg.setCode(RespEnum.SUCCESS.getCode());
        respMsg.setMsg(msg);
        respMsg.setSuccess(1);
        respMsg.setData(object);
        return respMsg;
    }

    public static RespMsg error(Integer code, String msg){
        RespMsg respMsg = new RespMsg();
        respMsg.setCode(code);
        respMsg.setMsg(msg);
        respMsg.setSuccess(0);
        return respMsg;
    }
}
