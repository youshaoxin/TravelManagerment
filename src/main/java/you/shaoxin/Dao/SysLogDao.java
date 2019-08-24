package you.shaoxin.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import you.shaoxin.domin.SysLog;

import java.util.List;

/**
 * 游菜花 -
 * 创建时间: 2019-08-24 09:15
 */
public interface SysLogDao {

    /*
     * 功能:保存日志信息
     **/
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog) throws Exception;

    /*
     * 功能:查询所有日志
     **/
    @Select("select * from syslog")
    List<SysLog> findAll() throws Exception;
}
