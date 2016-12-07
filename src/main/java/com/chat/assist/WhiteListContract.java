package com.chat.assist;

/**
 * Created by xiangtianyu on 2016/11/21.
 */


import com.chat.util.Constrain;
import com.chat.util.Convert;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import com.chat.datamodel.domain.WhiteList;
import com.chat.datamodel.dao.WhiteListDao;

@Aspect
@Component
public class WhiteListContract {
    @Autowired
    private WhiteListDao whiteListDao;

    private static final Logger logger = LoggerFactory.getLogger("WhiteListLogger");
    private Map<String, Integer> redisTemplate=new HashMap<String,Integer>();
    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(wList)")
    public void requestLimit(final JoinPoint joinPoint, com.chat.assist.WhiteList wList) throws WhiteListException {
        try{
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof HttpServletRequest) {
                    request = (HttpServletRequest) args[i];
                    break;
                }
            }
            if (request == null) {
                throw new WhiteListException("方法中缺失HttpServletRequest参数");
            }

            String ip = Convert.getIpAddr(request);
            WhiteList whiteList = whiteListDao.findByIpAndValid(ip, 1);
            Date now = new Date();
            if (whiteList == null) {
                throw new WhiteListException("该ip不被允许访问");
            }
            else if (!(whiteList.getIsAuto() == 1) && !isDateInTime(now, whiteList.getsTime(),whiteList.geteTime())) {
                throw new WhiteListException("该ip在白名单中已过期");
            }
        }
        catch (WhiteListException e) {
            throw e;
        }
        catch (Exception e) {
            logger.error("发生异常: ", e);
        }
    }

    private boolean isDateInTime(Date date, String sTime, String eTime) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date t1 = sdf.parse(sTime);
        Date t2 = sdf.parse(eTime);

        if (t1.before(date) && t2.after(date)) {
            return true;
        }
        else {
            return false;
        }

    }
}
