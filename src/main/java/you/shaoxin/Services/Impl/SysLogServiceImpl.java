package you.shaoxin.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import you.shaoxin.Dao.SysLogDao;
import you.shaoxin.Services.SysLogService;
import you.shaoxin.domin.SysLog;

import java.util.List;

/**
 * 功能:
 * 创建时间: 2019-08-24 09:14 --游菜花
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    /*
     * 功能:保存日志信息
     **/
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    /*
     * 功能:查询所有日志
     **/
    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
