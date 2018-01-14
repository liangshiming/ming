package ming.framework.core.dao;

import ming.framework.core.po.ServiceApi;

import java.util.List;

/**
 * Created by Jrick on 2018/1/11.
 */
public interface ServiceApiMapper {

    List<ServiceApi> selectAll();
}