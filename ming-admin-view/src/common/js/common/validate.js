import Vue from 'vue';


// 校验列名字
Vue.prototype.validateColumnName = (rule, value, callback) => {
  if (!value) {
    callback(new Error('请输入列名字'));
  }
  callback();
};

// 校验列代码
Vue.prototype.validateColumnCode = (rule, value, callback) => {
  if (!value) {
    callback(new Error('请输入列代码'));
  }
  callback();
};
// 校验参数名字
Vue.prototype.validateConditionName = (rule, value, callback) => {
  if (!value) {
    callback(new Error('请输入参数名字'));
  }
  callback();
};

// 校验参数代码
Vue.prototype.validateConditionCode = (rule, value, callback) => {
  if (!value) {
    callback(new Error('请输入参数代码'));
  }
  callback();
};


// 非空校验
Vue.prototype.validateIsNotNull = (rule, value, callback) => {
  if (!value) {
    callback(new Error('该参数不能为空'));
  }
  callback();
};

// 非空校验
Vue.prototype.validateShowOrder = (rule, value, callback) => {
  let pattern = /^[0-9]{1,6}$/;
  if (!value) {
    return callback(new Error('序号不能为空'));
  } else {
    if (!pattern.test(value)) {
      callback(new Error('请输入1-6位数字'));
    }
    callback();
  };
};

// 非空校验
Vue.prototype.validatePageSize = (rule, value, callback) => {
  let pattern = /^[1-9][0-9]{0,2}$/;
  if (!value) {
    return callback(new Error('该参数不能为空'));
  } else {
    if (!pattern.test(value)) {
      callback(new Error('请输入1-3位数字'));
    }
    callback();
  };
};

// 非空校验
Vue.prototype.validateExportMaxSize = (rule, value, callback) => {
  let pattern = /^[1-9][0-9]{0,5}$/;
  if (!value) {
    return callback(new Error('该参数不能为空'));
  } else {
    if (!pattern.test(value)) {
      callback(new Error('请输入1-5位数字'));
    }
    callback();
  };
};
















// 校验手机号
Vue.prototype.validateMobile = (rule, value, callback) => {
	let pattern = /1\d{10}/;
	if (!value) {
	    return callback(new Error('请输入手机号'));
	} else {
	if (!pattern.test(value)) {
	    callback(new Error('手机号格式不正确'));
	}
	    callback();
	};
};
// 校验身份证号码
Vue.prototype.validateCertificateNo = (rule, value, callback) => {
	// 18位和15位身份证校验
	let pattern = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}$)/;
    if (!value) {
        return callback(new Error('请输入身份证号'));
    } else {
    if (!pattern.test(value)) {
        callback(new Error('身份证号格式不正确'));
    }
        callback();
    };
};
// 校验申请额度
Vue.prototype.validateAmount = (rule, value, callback) => {
	let pattern = /^[1-9][0-9]{0,10}$/;
    if (!value) {
        return callback(new Error('请输入申请额度'));
    } else {
    if (!pattern.test(value)) {
        callback(new Error('申请额度格式不正确'));
    }
        callback();
    };
};
// 校验保险金额
Vue.prototype.validateInsurance = (rule, value, callback) => {
	let pattern = /^[1-9][0-9]{0,10}$/;
    if (value && !pattern.test(value)) {
    	callback(new Error('保险金额格式不正确'));
    }
    callback();
};
// 校验保单号
Vue.prototype.validatePolicyNo = (rule, value, callback) => {
	let pattern = /^[0-9]{22}$/;
    if (value && !pattern.test(value)) {
    	callback(new Error('保单号格式不正确'));
    }
    callback();
};
// 校验区域代码
Vue.prototype.validateAreaCode = (rule, value, callback) => {
	let pattern = /^\d{6}/;
    if (!value) {
        return callback(new Error('请选择所属省'));
    } else {
    if (!pattern.test(value)) {
        callback(new Error('所属地区号格式不正确'));
    }
        callback();
    };
};
// 校验搜索时的身份证号码
Vue.prototype.validateSearchCertificateNo = (rule, value, callback) => {
	let pattern = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}$)/;
    if (value && !pattern.test(value)) {
        callback(new Error('身份证号格式不正确'));
    }
    callback();
};
// 校验搜索时的手机号
Vue.prototype.validateSearchMobile = (rule, value, callback) => {
	let pattern = /1\d{10}/;
    if (value && !pattern.test(value)) {
        callback(new Error('手机号格式不正确'));
    }
    callback();
};
// 校验搜索时的申请额度
Vue.prototype.validateSearchLimit = (rule, value, callback) => {
	let pattern = /^[1-9][0-9]{0,10}$/;
    if (value && !pattern.test(value)) {
        callback(new Error('申请额度格式不正确'));
    }
    callback();
};
// 校验渠道不能为空
Vue.prototype.validateBranch = (rule, value, callback) => {
    if (!value) {
        callback(new Error('请选择国寿渠道'));
    }
    callback();
};
// 校验证件类型不能为空
Vue.prototype.validateCertificateType = (rule, value, callback) => {
    if (!value) {
        callback(new Error('请选择证件类型'));
    }
    callback();
};
// 校验产品集不能为空
Vue.prototype.validateGroupId = (rule, value, callback) => {
    if (!value) {
        callback(new Error('请选择产品集'));
    }
    callback();
};
//校验产品不能为空
Vue.prototype.validateProductId = (rule, value, callback) => {
	  if (!value) {
	    callback(new Error('请选择产品'));
	  }
	  callback();
	};
// 校验营销员工号
Vue.prototype.validateSalerNo = (rule, value, callback) => {
	let pattern = /^[0-9]{14}$/;
    if (!value) {
        return callback(new Error('请输入营销员工号'));
    } else {
    if (!pattern.test(value)) {
        callback(new Error('营销员工号格式不正确'));
    }
        callback();
    };
};
// 校验营销员姓名
Vue.prototype.validateSalerName = (rule, value, callback) => {
    if (!value) {
        callback(new Error('请输入营销员姓名'));
    }
    callback();
};
// 校验搜索时的营销员工号validateSearchSalerNo
Vue.prototype.validateSearchSalerNo = (rule, value, callback) => {
	let pattern = /^[0-9]{14}$/;
    if (value && !pattern.test(value)) {
        callback(new Error('营销员工号格式不正确'));
    }
    callback();
};
//校验管理员状态
Vue.prototype.validateManagerStatus = (rule, value, callback) => {
  if (!value) {
    callback(new Error('请选择管理员状态'));
  }
  callback();
};
//校验管理员姓名
Vue.prototype.validateManagerName = (rule, value, callback) => {
  if (!value) {
    callback(new Error('请输入管理员姓名'));
  }
  callback();
};
// 校验产品集名称
Vue.prototype.validateProductGroupName = (rule, value, callback) => {
	let pattern = /[\s\S]{1,25}/;
    if (!value) {
        return callback(new Error('产品集名称不能为空'));
    } else {
    if (!pattern.test(value)) {
        callback(new Error('产品集名称格式不正确'));
    }
        callback();
    };
};
// 校验序列号格式
Vue.prototype.validateSerialNo = (rule, value, callback) => {
	let pattern = /^[0-9A-Z]{4,6}$/;
    if (!value) {
        return callback(new Error('序列号不能为空'));
    } else {
    if (!pattern.test(value)) {
        callback(new Error('序列号格式不正确'));
    }
        callback();
    };
};
// 校验搜索时的序列号
Vue.prototype.validateSearchSerialNo = (rule, value, callback) => {
	let pattern = /^[0-9A-Z]{4,6}$/;
    if (value && !pattern.test(value)) {
        callback(new Error('序列号格式不正确'));
    }
    callback();
};
