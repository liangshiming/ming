import Vue from 'vue';
import store from '@/store'

Vue.filter('transformFilter', function(value, key) {
  // var b = '';
  // return Vue.prototype.getDictList(key,function (dictionary) {
  //   b = eachLabel(dictionary,value);
  //   return b;
  // })

  let storeValue = store.getters.dictList[key];
  return eachLabel(storeValue,value);
});

function eachLabel(dictionary, val){
	for(let i = 0; i < dictionary.length; i++) {
		if(dictionary[i].value == val) {
			return dictionary[i].label;
		}
	}
	return '--';
}

Vue.filter('dateStrFilter', function (value) {
  let reg = /([^\s]+)\s.*/;
  let date = value.replace(reg, "$1");
  return date;
});
Vue.filter('filterNull', function (value) {
  if (value && value == ".00") {
  	return "0.00";
  }
  if (value) {
  	return value;
  }
  return "--";
});
Vue.filter('combination', function (value, param) {
  const obj = value;
  const property = "obj." + param;
  if(!eval(property)){
  	return "--"
  }
  return eval(property);
});

Vue.filter('isNoFilter', function (value) {
  if(value === '0'){
    return '否';
  }else if(value === '1'){
    return '是';
  }else {
    return '--';
  }
});

Vue.filter('isVertical', function (value) {
  if(value === '0'){
    return '横向';
  }else if(value === '1'){
    return '纵向';
  }else {
    return '--';
  }
});
