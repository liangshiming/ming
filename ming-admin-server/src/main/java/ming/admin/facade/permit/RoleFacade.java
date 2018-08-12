package ming.admin.facade.permit;

import ming.admin.facade.BaseFacade;
import ming.admin.vo.base.TransRes;
import ming.admin.vo.trans.RolePermitReq;
import ming.admin.vo.trans.RoleReq;
import ming.dto.permit.RoleDto;
import ming.dto.permit.RolePermitDto;
import ming.service.permit.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleFacade extends BaseFacade {

	@Resource
	private RoleService roleService;

	public TransRes roleAdd(RoleReq roleReq){
		RoleDto roleDto = buildRoleDto(roleReq);
		roleService.add(roleDto);
		return buildRes(roleReq);
	}

	public TransRes roleDelete(RoleReq roleReq){
		RoleDto roleDto = buildRoleDto(roleReq);
		roleService.delete(roleDto);
		return buildRes(roleReq);
	}

	public TransRes roleModify(RoleReq roleReq){
		RoleDto roleDto = buildRoleDto(roleReq);
		roleService.modify(roleDto);
		return buildRes(roleReq);
	}

	public TransRes rolePermitModify(RolePermitReq rolePermitReq){
		RolePermitDto rolePermitDto = buildRolePermitDto(rolePermitReq);
		roleService.modifyRolePermitList(rolePermitDto);
		return buildRes(rolePermitReq);
	}

	private RoleDto buildRoleDto(RoleReq roleReq) {
		RoleDto roleDto = new RoleDto();
		roleDto.setRoleId(roleReq.getRoleId());
		roleDto.setName(roleReq.getName());
		return roleDto;
	}

	private RolePermitDto buildRolePermitDto(RolePermitReq rolePermitReq) {
		RolePermitDto rolePermitDto = new RolePermitDto();
		rolePermitDto.setRoleId(rolePermitReq.getRoleId());
		rolePermitDto.setPermitList(rolePermitReq.getPermitList());
		return rolePermitDto;
	}

}
