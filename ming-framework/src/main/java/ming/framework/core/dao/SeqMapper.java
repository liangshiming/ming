package ming.framework.core.dao;

import org.apache.ibatis.annotations.Param;

public interface SeqMapper {
	String getSeq(@Param("seqName") String seqName);

	String getTimeSeq(@Param("seqName") String seqName);
}

