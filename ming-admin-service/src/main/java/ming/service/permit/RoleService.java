package ming.service.permit;

import ming.constant.AdminSeqConst;
import ming.dao.permit.RoleMapper;
import ming.dao.permit.RolePermitMapper;
import ming.dto.permit.RoleDto;
import ming.dto.permit.RolePermitDto;
import ming.format.permit.PermitFormat;
import ming.format.permit.RoleFormat;
import ming.framework.core.dao.SeqMapper;
import ming.framework.util.CollectionUtil;
import ming.framework.util.TimeProvider;
import ming.po.permit.Permit;
import ming.po.permit.Role;
import ming.po.permit.RolePermit;
import ming.validate.permit.PermitValidate;
import ming.validate.permit.RoleValidate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleService {

	@Resource
	private TimeProvider timeProvider;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private SeqMapper seqMapper;
	@Resource
	private RolePermitMapper rolePermitMapper;
	@Resource
	private RoleValidate roleValidate;
	@Resource
	private PermitValidate permitValidate;

	@Transient
	public void add(RoleDto roleDto) {
		validateForAdd(roleDto);
		Role role = buildRoleForAdd(roleDto);
		roleMapper.insert(role);
	}

	@Transient
	public void delete(RoleDto roleDto) {
		validateForDelete(roleDto);
		rolePermitMapper.deleteByRoleId(roleDto.getRoleId());
		roleMapper.delete(roleDto.getRoleId());
	}

	@Transient
	public void modify(RoleDto roleDto) {
		validateForModify(roleDto);
		Role role = buildRoleForModify(roleDto);
		roleMapper.update(role);
	}

	@Transient
	public void modifyRolePermitList(RolePermitDto rolePermitDto) {
		validateForModifyRolePermitList(rolePermitDto);
		updateRolePermitList(rolePermitDto);
	}

	private void validateForAdd(RoleDto roleDto) {
		RoleFormat.validateRoleName(roleDto.getName());
		roleValidate.checkRoleIsNotExist(roleDto.getName());
	}

	private Role buildRoleForAdd(RoleDto roleDto) {
		Date currentTime = timeProvider.getCurrentTime();
		Role role = new Role();
		role.setRoleId(seqMapper.getSeq(AdminSeqConst.SEQ_PERMIT_ROLE));
		role.setName(roleDto.getName());
		role.setCreateTime(currentTime);
		role.setLastUpdateTime(currentTime);
		return role;
	}

	private void validateForDelete(RoleDto roleDto) {
		roleValidate.checkRoleIsExist(roleDto.getRoleId());
	}

	private void validateForModify(RoleDto roleDto) {
		RoleFormat.validateRoleName(roleDto.getName());
		roleValidate.checkRoleIsExist(roleDto.getRoleId());
	}

	private Role buildRoleForModify(RoleDto roleDto) {
		Date currentTime = timeProvider.getCurrentTime();
		Role role = roleMapper.selectById(roleDto.getRoleId());
		role.setName(roleDto.getName());
		role.setLastUpdateTime(currentTime);
		return role;
	}

	private void validateForModifyRolePermitList(RolePermitDto rolePermitDto) {
		RoleFormat.validateRoleId(rolePermitDto.getRoleId());
		roleValidate.checkRoleIsExist(rolePermitDto.getRoleId());
		validatePermitList(rolePermitDto.getPermitList());
	}

	private void validatePermitList(List<String> permitList) {
		if (CollectionUtil.isNotEmpty(permitList)) {
			for (String permitId : permitList) {
				PermitFormat.validatePermitId(permitId);
				permitValidate.checkPermitIsExist(permitId);
			}
		}
	}

	private void updateRolePermitList(RolePermitDto rolePermitDto) {
		rolePermitMapper.deleteByRoleId(rolePermitDto.getRoleId());
		if(CollectionUtil.isNotEmpty(rolePermitDto.getPermitList())) {
			List<RolePermit> rolePermitList = buildRolePermitList(rolePermitDto);
			rolePermitMapper.batchInsert(rolePermitList);
		}
	}

	private List<RolePermit> buildRolePermitList(RolePermitDto rolePermitDto) {
		String roleId = rolePermitDto.getRoleId();
		List<String> permitList = rolePermitDto.getPermitList();
		List<RolePermit> rolePermitList = new ArrayList<>();
		for (String permitId : permitList){
			RolePermit rolePermit = new RolePermit();
			rolePermit.setRoleId(roleId);
			rolePermit.setPermitId(permitId);
			rolePermitList.add(rolePermit);
		}
		return rolePermitList;
	}
}
