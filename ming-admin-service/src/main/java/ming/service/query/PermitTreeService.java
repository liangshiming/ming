package ming.service.query;

import ming.dao.query.QueryPermitMapper;
import ming.dto.query.PermitNode;
import ming.dto.query.QueryPermitTreeDto;
import ming.framework.util.ReflectUtil;
import ming.po.permit.Permit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PermitTreeService {
	@Resource
	private QueryPermitMapper queryPermitMapper;

	public void queryPermitListByUserId(QueryPermitTreeDto queryPermitTreeDto) {
		List<Permit> permitList = queryPermitMapper.selectPermitListByUserId(queryPermitTreeDto.getUserId());
		buildPermitTree(permitList, queryPermitTreeDto);
	}

	private void buildPermitTree(List<Permit> permitList, QueryPermitTreeDto queryPermitTreeDto) {
		List<PermitNode> permitNodeList = new ArrayList<>();
		for (Permit permit : permitList) {
			PermitNode permitTree = new PermitNode();
			ReflectUtil.copy(permit,permitTree);
			permitNodeList.add(permitTree);
		}
		List<PermitNode> permitTreeList = buildPermitTree(permitNodeList);
		queryPermitTreeDto.setPermitTreeList(permitTreeList);
	}

	private List<PermitNode> buildPermitTree(List<PermitNode> permitNodeList) {
		Map<String, PermitNode> permitNodeMap = ReflectUtil.reflectToMap(permitNodeList, "permitId");
		for (PermitNode permitNode : permitNodeMap.values()) {
			String permitParentId = permitNode.getParentPermitId();
			if (StringUtils.isEmpty(permitParentId)) {
				continue;
			}
			PermitNode permitNodeParent = permitNodeMap.get(permitParentId);
			addPermitNodeToParent(permitNode, permitNodeParent);
		}
		return getPermitTreeList(permitNodeMap);
	}

	private void addPermitNodeToParent(PermitNode permitNode, PermitNode permitNodeParent) {
		if (permitNodeParent == null) {
			return;
		}
		List<PermitNode> permitTreeList = permitNodeParent.getPermitTreeList();
		if (permitTreeList == null) {
			permitTreeList = new ArrayList<>();
		}
		permitTreeList.add(permitNode);
		permitNodeParent.setPermitTreeList(permitTreeList);
	}

	private List<PermitNode> getPermitTreeList(Map<String, PermitNode> permitNodeMap) {
		Collection<PermitNode> permitNodeCollection = permitNodeMap.values();
		List<PermitNode> permitTreeList = new ArrayList<>(permitNodeCollection);
		Iterator<PermitNode> iterator = permitTreeList.iterator();
		while (iterator.hasNext()) {
			PermitNode permitNode = iterator.next();
			if (StringUtils.isNotEmpty(permitNode.getParentPermitId())) {
				iterator.remove();
			}
		}
		return permitTreeList;
	}
}
