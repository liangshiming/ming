package ming.service.permit;

import ming.dao.permit.PermitMapper;
import ming.dto.permit.PermitDto;
import ming.format.permit.PermitFormat;
import ming.framework.util.TimeProvider;
import ming.po.permit.Permit;
import ming.validate.permit.PermitValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.Date;

@Service
public class PermitService {

	@Resource
	private TimeProvider timeProvider;
	@Resource
	private PermitMapper permitMapper;
	@Resource
	private PermitValidate permitValidate;

	@Transient
	public void add(PermitDto permitDto) {
		validateForAdd(permitDto);
		Permit permit = buildPermitForAdd(permitDto);
		permitMapper.insert(permit);
	}

	@Transient
	public void delete(PermitDto permitDto) {
		validateForDelete(permitDto);
		permitMapper.delete(permitDto.getPermitId());
	}

	@Transient
	public void modify(PermitDto permitDto) {
		validateForModify(permitDto);
		Permit permit = buildPermitForModify(permitDto);
		permitMapper.update(permit);
	}

	private void validateForAdd(PermitDto permitDto) {
		PermitFormat.validatePermitId(permitDto.getPermitId());
		permitValidate.checkPermitIsNotExist(permitDto.getPermitId());
	}

	private Permit buildPermitForAdd(PermitDto permitDto) {
		Date currentTime = timeProvider.getCurrentTime();
		Permit permit = new Permit();
		permit.setPermitId(permitDto.getPermitId());
		permit.setParentPermitId(permitDto.getParentPermitId());
		permit.setName(permitDto.getName());
		permit.setUrl(permitDto.getUrl());
		permit.setIcon(permitDto.getIcon());
		permit.setStatus(permitDto.getStatus());
		permit.setCreateTime(currentTime);
		permit.setLastUpdateTime(currentTime);
		return permit;
	}

	private void validateForDelete(PermitDto permitDto) {
		PermitFormat.validatePermitId(permitDto.getPermitId());
		permitValidate.checkPermitIsExist(permitDto.getPermitId());
	}

	private void validateForModify(PermitDto permitDto) {
		PermitFormat.validatePermitId(permitDto.getPermitId());
		permitValidate.checkPermitIsExist(permitDto.getPermitId());
	}

	private Permit buildPermitForModify(PermitDto permitDto) {
		Date currentTime = timeProvider.getCurrentTime();
		Permit permit = permitMapper.selectById(permitDto.getPermitId());
		if (StringUtils.isNotBlank(permitDto.getParentPermitId())) {
			permit.setParentPermitId(permitDto.getParentPermitId());
		}
		if (StringUtils.isNotBlank(permitDto.getName())) {
			permit.setName(permitDto.getName());
		}
		if (StringUtils.isNotBlank(permitDto.getIcon())) {
			permit.setIcon(permitDto.getIcon());
		}
		if (StringUtils.isNotBlank(permitDto.getUrl())) {
			permit.setUrl(permitDto.getUrl());
		}
		if (StringUtils.isNotBlank(permitDto.getStatus())) {
			permit.setStatus(permitDto.getStatus());
		}
		permit.setLastUpdateTime(currentTime);
		return permit;
	}
}
