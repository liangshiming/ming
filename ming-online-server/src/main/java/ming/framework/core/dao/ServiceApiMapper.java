package ming.framework.core.dao;

import ming.framework.core.po.ServiceApi;

public interface ServiceApiMapper {
    int deleteByPrimaryKey(String apiId);

    int insert(ServiceApi record);

    int insertSelective(ServiceApi record);

    ServiceApi selectByPrimaryKey(String apiId);

    int updateByPrimaryKeySelective(ServiceApi record);

    int updateByPrimaryKey(ServiceApi record);
}