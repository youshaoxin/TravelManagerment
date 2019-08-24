package you.shaoxin.Services;

import you.shaoxin.domin.SysLog;

import java.util.List;

/**
 * 游菜花 -
 * 创建时间: 2019-08-24 09:14
 */
public interface SysLogService {

    /*
     * 功能:保存日志信息
    **/
    public void save(SysLog sysLog) throws Exception;

    /*
     * 功能:查询所有日志
    **/
    List<SysLog> findAll() throws Exception;
}
