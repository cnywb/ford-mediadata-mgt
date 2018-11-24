var dbCityTreeOption = angular.module("dbCityTreeOption", ['ngResource']);
dbCityTreeOption.factory("dbCityTreeOption", ["$resource", "$http", DbCityTreeOption]);
function DbCityTreeOption($resource, $http) {
    var tree = [
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "北京",
    				"value": "北京"
    			},
    			{
    				"children": [
    				],
    				"name": "亦庄",
    				"value": "亦庄"
    			},
    			{
    				"children": [
    				],
    				"name": "顺义",
    				"value": "顺义"
    			},
    			{
    				"children": [
    				],
    				"name": "通州",
    				"value": "通州"
    			},
    			{
    				"children": [
    				],
    				"name": "石景山",
    				"value": "石景山"
    			},
    			{
    				"children": [
    				],
    				"name": "北京房山",
    				"value": "北京房山"
    			}
    		],
    		"name": "北京",
    		"value": "北京"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "重庆",
    				"value": "重庆"
    			},
    			{
    				"children": [
    				],
    				"name": "江津",
    				"value": "江津"
    			},
    			{
    				"children": [
    				],
    				"name": "永川",
    				"value": "永川"
    			},
    			{
    				"children": [
    				],
    				"name": "涪陵",
    				"value": "涪陵"
    			},
    			{
    				"children": [
    				],
    				"name": "万州",
    				"value": "万州"
    			},
    			{
    				"children": [
    				],
    				"name": "合川",
    				"value": "合川"
    			}
    		],
    		"name": "重庆",
    		"value": "重庆"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "伊宁",
    				"value": "伊宁"
    			},
    			{
    				"children": [
    				],
    				"name": "乌鲁木齐",
    				"value": "乌鲁木齐"
    			},
    			{
    				"children": [
    				],
    				"name": "阿克苏",
    				"value": "阿克苏"
    			},
    			{
    				"children": [
    				],
    				"name": "克拉玛依",
    				"value": "克拉玛依"
    			},
    			{
    				"children": [
    				],
    				"name": "昌吉",
    				"value": "昌吉"
    			},
    			{
    				"children": [
    				],
    				"name": "哈密",
    				"value": "哈密"
    			},
    			{
    				"children": [
    				],
    				"name": "库尔勒",
    				"value": "库尔勒"
    			},
    			{
    				"children": [
    				],
    				"name": "伊犁",
    				"value": "伊犁"
    			},
    			{
    				"children": [
    				],
    				"name": "奎屯",
    				"value": "奎屯"
    			}
    		],
    		"name": "新疆",
    		"value": "新疆"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "揭阳",
    				"value": "揭阳"
    			},
    			{
    				"children": [
    				],
    				"name": "里水",
    				"value": "里水"
    			},
    			{
    				"children": [
    				],
    				"name": "增城",
    				"value": "增城"
    			},
    			{
    				"children": [
    				],
    				"name": "江门",
    				"value": "江门"
    			},
    			{
    				"children": [
    				],
    				"name": "云浮",
    				"value": "云浮"
    			},
    			{
    				"children": [
    				],
    				"name": "阳江",
    				"value": "阳江"
    			},
    			{
    				"children": [
    				],
    				"name": "清远",
    				"value": "清远"
    			},
    			{
    				"children": [
    				],
    				"name": "顺德",
    				"value": "顺德"
    			},
    			{
    				"children": [
    				],
    				"name": "惠州",
    				"value": "惠州"
    			},
    			{
    				"children": [
    				],
    				"name": "深圳",
    				"value": "深圳"
    			},
    			{
    				"children": [
    				],
    				"name": "珠海",
    				"value": "珠海"
    			},
    			{
    				"children": [
    				],
    				"name": "番禺",
    				"value": "番禺"
    			},
    			{
    				"children": [
    				],
    				"name": "汕尾",
    				"value": "汕尾"
    			},
    			{
    				"children": [
    				],
    				"name": "佛山",
    				"value": "佛山"
    			},
    			{
    				"children": [
    				],
    				"name": "潮州",
    				"value": "潮州"
    			},
    			{
    				"children": [
    				],
    				"name": "河源",
    				"value": "河源"
    			},
    			{
    				"children": [
    				],
    				"name": "肇庆",
    				"value": "肇庆"
    			},
    			{
    				"children": [
    				],
    				"name": "广州",
    				"value": "广州"
    			},
    			{
    				"children": [
    				],
    				"name": "萝岗",
    				"value": "萝岗"
    			},
    			{
    				"children": [
    				],
    				"name": "中山",
    				"value": "中山"
    			},
    			{
    				"children": [
    				],
    				"name": "茂名",
    				"value": "茂名"
    			},
    			{
    				"children": [
    				],
    				"name": "湛江",
    				"value": "湛江"
    			},
    			{
    				"children": [
    				],
    				"name": "梅州",
    				"value": "梅州"
    			},
    			{
    				"children": [
    				],
    				"name": "韶关",
    				"value": "韶关"
    			},
    			{
    				"children": [
    				],
    				"name": "花都",
    				"value": "花都"
    			},
    			{
    				"children": [
    				],
    				"name": "东莞",
    				"value": "东莞"
    			},
    			{
    				"children": [
    				],
    				"name": "南海",
    				"value": "南海"
    			},
    			{
    				"children": [
    				],
    				"name": "汕头",
    				"value": "汕头"
    			}
    		],
    		"name": "广东",
    		"value": "广东"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "永康",
    				"value": "永康"
    			},
    			{
    				"children": [
    				],
    				"name": "临海",
    				"value": "临海"
    			},
    			{
    				"children": [
    				],
    				"name": "余姚",
    				"value": "余姚"
    			},
    			{
    				"children": [
    				],
    				"name": "海宁",
    				"value": "海宁"
    			},
    			{
    				"children": [
    				],
    				"name": "长兴",
    				"value": "长兴"
    			},
    			{
    				"children": [
    				],
    				"name": "瑞安",
    				"value": "瑞安"
    			},
    			{
    				"children": [
    				],
    				"name": "苍南",
    				"value": "苍南"
    			},
    			{
    				"children": [
    				],
    				"name": "柯桥",
    				"value": "柯桥"
    			},
    			{
    				"children": [
    				],
    				"name": "湖州",
    				"value": "湖州"
    			},
    			{
    				"children": [
    				],
    				"name": "富阳",
    				"value": "富阳"
    			},
    			{
    				"children": [
    				],
    				"name": "东阳",
    				"value": "东阳"
    			},
    			{
    				"children": [
    				],
    				"name": "丽水",
    				"value": "丽水"
    			},
    			{
    				"children": [
    				],
    				"name": "安吉",
    				"value": "安吉"
    			},
    			{
    				"children": [
    				],
    				"name": "嘉兴",
    				"value": "嘉兴"
    			},
    			{
    				"children": [
    				],
    				"name": "临安",
    				"value": "临安"
    			},
    			{
    				"children": [
    				],
    				"name": "杭州",
    				"value": "杭州"
    			},
    			{
    				"children": [
    				],
    				"name": "海盐",
    				"value": "海盐"
    			},
    			{
    				"children": [
    				],
    				"name": "金华",
    				"value": "金华"
    			},
    			{
    				"children": [
    				],
    				"name": "诸暨",
    				"value": "诸暨"
    			},
    			{
    				"children": [
    				],
    				"name": "温州",
    				"value": "温州"
    			},
    			{
    				"children": [
    				],
    				"name": "慈溪",
    				"value": "慈溪"
    			},
    			{
    				"children": [
    				],
    				"name": "宁波",
    				"value": "宁波"
    			},
    			{
    				"children": [
    				],
    				"name": "上虞",
    				"value": "上虞"
    			},
    			{
    				"children": [
    				],
    				"name": "嵊州",
    				"value": "嵊州"
    			},
    			{
    				"children": [
    				],
    				"name": "乐清",
    				"value": "乐清"
    			},
    			{
    				"children": [
    				],
    				"name": "平湖",
    				"value": "平湖"
    			},
    			{
    				"children": [
    				],
    				"name": "温岭",
    				"value": "温岭"
    			},
    			{
    				"children": [
    				],
    				"name": "绍兴",
    				"value": "绍兴"
    			},
    			{
    				"children": [
    				],
    				"name": "桐乡",
    				"value": "桐乡"
    			},
    			{
    				"children": [
    				],
    				"name": "义乌",
    				"value": "义乌"
    			},
    			{
    				"children": [
    				],
    				"name": "衢州",
    				"value": "衢州"
    			},
    			{
    				"children": [
    				],
    				"name": "台州",
    				"value": "台州"
    			},
    			{
    				"children": [
    				],
    				"name": "舟山",
    				"value": "舟山"
    			}
    		],
    		"name": "浙江",
    		"value": "浙江"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "静海",
    				"value": "静海"
    			},
    			{
    				"children": [
    				],
    				"name": "天津",
    				"value": "天津"
    			},
    			{
    				"children": [
    				],
    				"name": "塘沽",
    				"value": "塘沽"
    			}
    		],
    		"name": "天津",
    		"value": "天津"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "南宁",
    				"value": "南宁"
    			},
    			{
    				"children": [
    				],
    				"name": "贺州",
    				"value": "贺州"
    			},
    			{
    				"children": [
    				],
    				"name": "北海",
    				"value": "北海"
    			},
    			{
    				"children": [
    				],
    				"name": "河池",
    				"value": "河池"
    			},
    			{
    				"children": [
    				],
    				"name": "柳州",
    				"value": "柳州"
    			},
    			{
    				"children": [
    				],
    				"name": "贵港",
    				"value": "贵港"
    			},
    			{
    				"children": [
    				],
    				"name": "钦州",
    				"value": "钦州"
    			},
    			{
    				"children": [
    				],
    				"name": "百色",
    				"value": "百色"
    			},
    			{
    				"children": [
    				],
    				"name": "防城港",
    				"value": "防城港"
    			},
    			{
    				"children": [
    				],
    				"name": "梧州",
    				"value": "梧州"
    			},
    			{
    				"children": [
    				],
    				"name": "玉林",
    				"value": "玉林"
    			},
    			{
    				"children": [
    				],
    				"name": "桂林",
    				"value": "桂林"
    			}
    		],
    		"name": "广西",
    		"value": "广西"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "鄂尔多斯",
    				"value": "鄂尔多斯"
    			},
    			{
    				"children": [
    				],
    				"name": "赤峰",
    				"value": "赤峰"
    			},
    			{
    				"children": [
    				],
    				"name": "呼和浩特",
    				"value": "呼和浩特"
    			},
    			{
    				"children": [
    				],
    				"name": "包头",
    				"value": "包头"
    			},
    			{
    				"children": [
    				],
    				"name": "锡林郭勒",
    				"value": "锡林郭勒"
    			},
    			{
    				"children": [
    				],
    				"name": "乌海",
    				"value": "乌海"
    			},
    			{
    				"children": [
    				],
    				"name": "通辽",
    				"value": "通辽"
    			},
    			{
    				"children": [
    				],
    				"name": "巴彦淖尔盟",
    				"value": "巴彦淖尔盟"
    			},
    			{
    				"children": [
    				],
    				"name": "乌兰浩特",
    				"value": "乌兰浩特"
    			},
    			{
    				"children": [
    				],
    				"name": "呼伦贝尔",
    				"value": "呼伦贝尔"
    			}
    		],
    		"name": "内蒙古",
    		"value": "内蒙古"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "石嘴山",
    				"value": "石嘴山"
    			},
    			{
    				"children": [
    				],
    				"name": "中卫",
    				"value": "中卫"
    			},
    			{
    				"children": [
    				],
    				"name": "银川",
    				"value": "银川"
    			}
    		],
    		"name": "宁夏",
    		"value": "宁夏"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "上饶",
    				"value": "上饶"
    			},
    			{
    				"children": [
    				],
    				"name": "南康",
    				"value": "南康"
    			},
    			{
    				"children": [
    				],
    				"name": "景德镇",
    				"value": "景德镇"
    			},
    			{
    				"children": [
    				],
    				"name": "宜春",
    				"value": "宜春"
    			},
    			{
    				"children": [
    				],
    				"name": "新余",
    				"value": "新余"
    			},
    			{
    				"children": [
    				],
    				"name": "吉安",
    				"value": "吉安"
    			},
    			{
    				"children": [
    				],
    				"name": "抚州",
    				"value": "抚州"
    			},
    			{
    				"children": [
    				],
    				"name": "赣州",
    				"value": "赣州"
    			},
    			{
    				"children": [
    				],
    				"name": "南昌",
    				"value": "南昌"
    			},
    			{
    				"children": [
    				],
    				"name": "高安",
    				"value": "高安"
    			},
    			{
    				"children": [
    				],
    				"name": "九江",
    				"value": "九江"
    			}
    		],
    		"name": "江西",
    		"value": "江西"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "贵阳",
    				"value": "贵阳"
    			},
    			{
    				"children": [
    				],
    				"name": "凯里",
    				"value": "凯里"
    			},
    			{
    				"children": [
    				],
    				"name": "遵义",
    				"value": "遵义"
    			},
    			{
    				"children": [
    				],
    				"name": "安顺",
    				"value": "安顺"
    			},
    			{
    				"children": [
    				],
    				"name": "铜仁",
    				"value": "铜仁"
    			},
    			{
    				"children": [
    				],
    				"name": "六盘水",
    				"value": "六盘水"
    			},
    			{
    				"children": [
    				],
    				"name": "毕节",
    				"value": "毕节"
    			},
    			{
    				"children": [
    				],
    				"name": "兴义",
    				"value": "兴义"
    			}
    		],
    		"name": "贵州",
    		"value": "贵州"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "六安",
    				"value": "六安"
    			},
    			{
    				"children": [
    				],
    				"name": "蚌埠",
    				"value": "蚌埠"
    			},
    			{
    				"children": [
    				],
    				"name": "芜湖",
    				"value": "芜湖"
    			},
    			{
    				"children": [
    				],
    				"name": "池州",
    				"value": "池州"
    			},
    			{
    				"children": [
    				],
    				"name": "蒙城",
    				"value": "蒙城"
    			},
    			{
    				"children": [
    				],
    				"name": "安庆",
    				"value": "安庆"
    			},
    			{
    				"children": [
    				],
    				"name": "铜陵",
    				"value": "铜陵"
    			},
    			{
    				"children": [
    				],
    				"name": "黄山",
    				"value": "黄山"
    			},
    			{
    				"children": [
    				],
    				"name": "淮南",
    				"value": "淮南"
    			},
    			{
    				"children": [
    				],
    				"name": "亳州",
    				"value": "亳州"
    			},
    			{
    				"children": [
    				],
    				"name": "宿州",
    				"value": "宿州"
    			},
    			{
    				"children": [
    				],
    				"name": "巢湖",
    				"value": "巢湖"
    			},
    			{
    				"children": [
    				],
    				"name": "滁州",
    				"value": "滁州"
    			},
    			{
    				"children": [
    				],
    				"name": "包河",
    				"value": "包河"
    			},
    			{
    				"children": [
    				],
    				"name": "阜阳",
    				"value": "阜阳"
    			},
    			{
    				"children": [
    				],
    				"name": "淮北",
    				"value": "淮北"
    			},
    			{
    				"children": [
    				],
    				"name": "马鞍山",
    				"value": "马鞍山"
    			},
    			{
    				"children": [
    				],
    				"name": "合肥",
    				"value": "合肥"
    			},
    			{
    				"children": [
    				],
    				"name": "宣城",
    				"value": "宣城"
    			}
    		],
    		"name": "安徽",
    		"value": "安徽"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "西安",
    				"value": "西安"
    			},
    			{
    				"children": [
    				],
    				"name": "渭南",
    				"value": "渭南"
    			},
    			{
    				"children": [
    				],
    				"name": "延安",
    				"value": "延安"
    			},
    			{
    				"children": [
    				],
    				"name": "汉中",
    				"value": "汉中"
    			},
    			{
    				"children": [
    				],
    				"name": "咸阳",
    				"value": "咸阳"
    			},
    			{
    				"children": [
    				],
    				"name": "商洛",
    				"value": "商洛"
    			},
    			{
    				"children": [
    				],
    				"name": "宝鸡",
    				"value": "宝鸡"
    			},
    			{
    				"children": [
    				],
    				"name": "杨凌",
    				"value": "杨凌"
    			},
    			{
    				"children": [
    				],
    				"name": "榆林",
    				"value": "榆林"
    			},
    			{
    				"children": [
    				],
    				"name": "安康",
    				"value": "安康"
    			}
    		],
    		"name": "陕西",
    		"value": "陕西"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "锦州",
    				"value": "锦州"
    			},
    			{
    				"children": [
    				],
    				"name": "沈阳",
    				"value": "沈阳"
    			},
    			{
    				"children": [
    				],
    				"name": "营口",
    				"value": "营口"
    			},
    			{
    				"children": [
    				],
    				"name": "朝阳",
    				"value": "朝阳"
    			},
    			{
    				"children": [
    				],
    				"name": "抚顺",
    				"value": "抚顺"
    			},
    			{
    				"children": [
    				],
    				"name": "大连",
    				"value": "大连"
    			},
    			{
    				"children": [
    				],
    				"name": "辽阳",
    				"value": "辽阳"
    			},
    			{
    				"children": [
    				],
    				"name": "盘锦",
    				"value": "盘锦"
    			},
    			{
    				"children": [
    				],
    				"name": "丹东",
    				"value": "丹东"
    			},
    			{
    				"children": [
    				],
    				"name": "葫芦岛",
    				"value": "葫芦岛"
    			},
    			{
    				"children": [
    				],
    				"name": "鞍山",
    				"value": "鞍山"
    			},
    			{
    				"children": [
    				],
    				"name": "阜新",
    				"value": "阜新"
    			},
    			{
    				"children": [
    				],
    				"name": "铁岭",
    				"value": "铁岭"
    			}
    		],
    		"name": "辽宁",
    		"value": "辽宁"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "吕梁",
    				"value": "吕梁"
    			},
    			{
    				"children": [
    				],
    				"name": "晋中",
    				"value": "晋中"
    			},
    			{
    				"children": [
    				],
    				"name": "阳泉",
    				"value": "阳泉"
    			},
    			{
    				"children": [
    				],
    				"name": "忻州",
    				"value": "忻州"
    			},
    			{
    				"children": [
    				],
    				"name": "临汾",
    				"value": "临汾"
    			},
    			{
    				"children": [
    				],
    				"name": "太原",
    				"value": "太原"
    			},
    			{
    				"children": [
    				],
    				"name": "长治",
    				"value": "长治"
    			},
    			{
    				"children": [
    				],
    				"name": "运城",
    				"value": "运城"
    			},
    			{
    				"children": [
    				],
    				"name": "晋城",
    				"value": "晋城"
    			},
    			{
    				"children": [
    				],
    				"name": "大同",
    				"value": "大同"
    			}
    		],
    		"name": "山西",
    		"value": "山西"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "西宁",
    				"value": "西宁"
    			}
    		],
    		"name": "青海",
    		"value": "青海"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "遂宁",
    				"value": "遂宁"
    			},
    			{
    				"children": [
    				],
    				"name": "宜宾",
    				"value": "宜宾"
    			},
    			{
    				"children": [
    				],
    				"name": "乐山",
    				"value": "乐山"
    			},
    			{
    				"children": [
    				],
    				"name": "成都",
    				"value": "成都"
    			},
    			{
    				"children": [
    				],
    				"name": "攀枝花",
    				"value": "攀枝花"
    			},
    			{
    				"children": [
    				],
    				"name": "广安",
    				"value": "广安"
    			},
    			{
    				"children": [
    				],
    				"name": "眉山",
    				"value": "眉山"
    			},
    			{
    				"children": [
    				],
    				"name": "雅安",
    				"value": "雅安"
    			},
    			{
    				"children": [
    				],
    				"name": "西昌",
    				"value": "西昌"
    			},
    			{
    				"children": [
    				],
    				"name": "都江堰",
    				"value": "都江堰"
    			},
    			{
    				"children": [
    				],
    				"name": "泸州",
    				"value": "泸州"
    			},
    			{
    				"children": [
    				],
    				"name": "内江",
    				"value": "内江"
    			},
    			{
    				"children": [
    				],
    				"name": "自贡",
    				"value": "自贡"
    			},
    			{
    				"children": [
    				],
    				"name": "绵阳",
    				"value": "绵阳"
    			},
    			{
    				"children": [
    				],
    				"name": "达州",
    				"value": "达州"
    			},
    			{
    				"children": [
    				],
    				"name": "彭州",
    				"value": "彭州"
    			},
    			{
    				"children": [
    				],
    				"name": "德阳",
    				"value": "德阳"
    			},
    			{
    				"children": [
    				],
    				"name": "广元",
    				"value": "广元"
    			},
    			{
    				"children": [
    				],
    				"name": "南充",
    				"value": "南充"
    			},
    			{
    				"children": [
    				],
    				"name": "巴中",
    				"value": "巴中"
    			},
    			{
    				"children": [
    				],
    				"name": "资阳",
    				"value": "资阳"
    			}
    		],
    		"name": "四川",
    		"value": "四川"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "盐城",
    				"value": "盐城"
    			},
    			{
    				"children": [
    				],
    				"name": "溧阳",
    				"value": "溧阳"
    			},
    			{
    				"children": [
    				],
    				"name": "常熟",
    				"value": "常熟"
    			},
    			{
    				"children": [
    				],
    				"name": "姜堰",
    				"value": "姜堰"
    			},
    			{
    				"children": [
    				],
    				"name": "仪征",
    				"value": "仪征"
    			},
    			{
    				"children": [
    				],
    				"name": "东台",
    				"value": "东台"
    			},
    			{
    				"children": [
    				],
    				"name": "太仓",
    				"value": "太仓"
    			},
    			{
    				"children": [
    				],
    				"name": "泰州",
    				"value": "泰州"
    			},
    			{
    				"children": [
    				],
    				"name": "镇江",
    				"value": "镇江"
    			},
    			{
    				"children": [
    				],
    				"name": "江都",
    				"value": "江都"
    			},
    			{
    				"children": [
    				],
    				"name": "海安",
    				"value": "海安"
    			},
    			{
    				"children": [
    				],
    				"name": "江阴",
    				"value": "江阴"
    			},
    			{
    				"children": [
    				],
    				"name": "苏州",
    				"value": "苏州"
    			},
    			{
    				"children": [
    				],
    				"name": "海门",
    				"value": "海门"
    			},
    			{
    				"children": [
    				],
    				"name": "连云港",
    				"value": "连云港"
    			},
    			{
    				"children": [
    				],
    				"name": "张家港",
    				"value": "张家港"
    			},
    			{
    				"children": [
    				],
    				"name": "吴江",
    				"value": "吴江"
    			},
    			{
    				"children": [
    				],
    				"name": "丹阳",
    				"value": "丹阳"
    			},
    			{
    				"children": [
    				],
    				"name": "徐州",
    				"value": "徐州"
    			},
    			{
    				"children": [
    				],
    				"name": "江宁",
    				"value": "江宁"
    			},
    			{
    				"children": [
    				],
    				"name": "高邮",
    				"value": "高邮"
    			},
    			{
    				"children": [
    				],
    				"name": "无锡",
    				"value": "无锡"
    			},
    			{
    				"children": [
    				],
    				"name": "金坛",
    				"value": "金坛"
    			},
    			{
    				"children": [
    				],
    				"name": "启东",
    				"value": "启东"
    			},
    			{
    				"children": [
    				],
    				"name": "宜兴",
    				"value": "宜兴"
    			},
    			{
    				"children": [
    				],
    				"name": "扬州",
    				"value": "扬州"
    			},
    			{
    				"children": [
    				],
    				"name": "如东",
    				"value": "如东"
    			},
    			{
    				"children": [
    				],
    				"name": "兴化",
    				"value": "兴化"
    			},
    			{
    				"children": [
    				],
    				"name": "扬中",
    				"value": "扬中"
    			},
    			{
    				"children": [
    				],
    				"name": "淮安",
    				"value": "淮安"
    			},
    			{
    				"children": [
    				],
    				"name": "靖江",
    				"value": "靖江"
    			},
    			{
    				"children": [
    				],
    				"name": "昆山",
    				"value": "昆山"
    			},
    			{
    				"children": [
    				],
    				"name": "常州",
    				"value": "常州"
    			},
    			{
    				"children": [
    				],
    				"name": "南京",
    				"value": "南京"
    			},
    			{
    				"children": [
    				],
    				"name": "南通",
    				"value": "南通"
    			},
    			{
    				"children": [
    				],
    				"name": "如皋",
    				"value": "如皋"
    			},
    			{
    				"children": [
    				],
    				"name": "宿迁",
    				"value": "宿迁"
    			},
    			{
    				"children": [
    				],
    				"name": "沭阳",
    				"value": "沭阳"
    			},
    			{
    				"children": [
    				],
    				"name": "泰兴",
    				"value": "泰兴"
    			},
    			{
    				"children": [
    				],
    				"name": "浦口",
    				"value": "浦口"
    			},
    			{
    				"children": [
    				],
    				"name": "溧水",
    				"value": "溧水"
    			}
    		],
    		"name": "江苏",
    		"value": "江苏"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "张家口",
    				"value": "张家口"
    			},
    			{
    				"children": [
    				],
    				"name": "唐山",
    				"value": "唐山"
    			},
    			{
    				"children": [
    				],
    				"name": "廊坊",
    				"value": "廊坊"
    			},
    			{
    				"children": [
    				],
    				"name": "定州",
    				"value": "定州"
    			},
    			{
    				"children": [
    				],
    				"name": "秦皇岛",
    				"value": "秦皇岛"
    			},
    			{
    				"children": [
    				],
    				"name": "武安",
    				"value": "武安"
    			},
    			{
    				"children": [
    				],
    				"name": "保定",
    				"value": "保定"
    			},
    			{
    				"children": [
    				],
    				"name": "沧州",
    				"value": "沧州"
    			},
    			{
    				"children": [
    				],
    				"name": "衡水",
    				"value": "衡水"
    			},
    			{
    				"children": [
    				],
    				"name": "任丘",
    				"value": "任丘"
    			},
    			{
    				"children": [
    				],
    				"name": "邢台",
    				"value": "邢台"
    			},
    			{
    				"children": [
    				],
    				"name": "邯郸",
    				"value": "邯郸"
    			},
    			{
    				"children": [
    				],
    				"name": "涿州",
    				"value": "涿州"
    			},
    			{
    				"children": [
    				],
    				"name": "石家庄",
    				"value": "石家庄"
    			},
    			{
    				"children": [
    				],
    				"name": "承德",
    				"value": "承德"
    			},
    			{
    				"children": [
    				],
    				"name": "霸州",
    				"value": "霸州"
    			},
    			{
    				"children": [
    				],
    				"name": "迁安",
    				"value": "迁安"
    			}
    		],
    		"name": "河北",
    		"value": "河北"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "拉萨",
    				"value": "拉萨"
    			}
    		],
    		"name": "西藏",
    		"value": "西藏"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "漳州",
    				"value": "漳州"
    			},
    			{
    				"children": [
    				],
    				"name": "漳浦",
    				"value": "漳浦"
    			},
    			{
    				"children": [
    				],
    				"name": "福清",
    				"value": "福清"
    			},
    			{
    				"children": [
    				],
    				"name": "南平",
    				"value": "南平"
    			},
    			{
    				"children": [
    				],
    				"name": "宁德",
    				"value": "宁德"
    			},
    			{
    				"children": [
    				],
    				"name": "三明",
    				"value": "三明"
    			},
    			{
    				"children": [
    				],
    				"name": "泉州",
    				"value": "泉州"
    			},
    			{
    				"children": [
    				],
    				"name": "龙岩",
    				"value": "龙岩"
    			},
    			{
    				"children": [
    				],
    				"name": "晋江",
    				"value": "晋江"
    			},
    			{
    				"children": [
    				],
    				"name": "厦门",
    				"value": "厦门"
    			},
    			{
    				"children": [
    				],
    				"name": "莆田",
    				"value": "莆田"
    			},
    			{
    				"children": [
    				],
    				"name": "福州",
    				"value": "福州"
    			},
    			{
    				"children": [
    				],
    				"name": "安溪",
    				"value": "安溪"
    			}
    		],
    		"name": "福建",
    		"value": "福建"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "长春",
    				"value": "长春"
    			},
    			{
    				"children": [
    				],
    				"name": "松原",
    				"value": "松原"
    			},
    			{
    				"children": [
    				],
    				"name": "通化",
    				"value": "通化"
    			},
    			{
    				"children": [
    				],
    				"name": "延吉",
    				"value": "延吉"
    			},
    			{
    				"children": [
    				],
    				"name": "吉林",
    				"value": "吉林"
    			}
    		],
    		"name": "吉林",
    		"value": "吉林"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "普洱",
    				"value": "普洱"
    			},
    			{
    				"children": [
    				],
    				"name": "大理",
    				"value": "大理"
    			},
    			{
    				"children": [
    				],
    				"name": "丽江",
    				"value": "丽江"
    			},
    			{
    				"children": [
    				],
    				"name": "德宏",
    				"value": "德宏"
    			},
    			{
    				"children": [
    				],
    				"name": "玉溪",
    				"value": "玉溪"
    			},
    			{
    				"children": [
    				],
    				"name": "昆明",
    				"value": "昆明"
    			},
    			{
    				"children": [
    				],
    				"name": "安宁",
    				"value": "安宁"
    			},
    			{
    				"children": [
    				],
    				"name": "楚雄",
    				"value": "楚雄"
    			},
    			{
    				"children": [
    				],
    				"name": "昭通",
    				"value": "昭通"
    			},
    			{
    				"children": [
    				],
    				"name": "蒙自",
    				"value": "蒙自"
    			},
    			{
    				"children": [
    				],
    				"name": "文山",
    				"value": "文山"
    			},
    			{
    				"children": [
    				],
    				"name": "曲靖",
    				"value": "曲靖"
    			},
    			{
    				"children": [
    				],
    				"name": "景洪",
    				"value": "景洪"
    			},
    			{
    				"children": [
    				],
    				"name": "保山",
    				"value": "保山"
    			}
    		],
    		"name": "云南",
    		"value": "云南"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "襄阳",
    				"value": "襄阳"
    			},
    			{
    				"children": [
    				],
    				"name": "荆门",
    				"value": "荆门"
    			},
    			{
    				"children": [
    				],
    				"name": "随州",
    				"value": "随州"
    			},
    			{
    				"children": [
    				],
    				"name": "武汉",
    				"value": "武汉"
    			},
    			{
    				"children": [
    				],
    				"name": "十堰",
    				"value": "十堰"
    			},
    			{
    				"children": [
    				],
    				"name": "宜昌",
    				"value": "宜昌"
    			},
    			{
    				"children": [
    				],
    				"name": "孝感",
    				"value": "孝感"
    			},
    			{
    				"children": [
    				],
    				"name": "恩施",
    				"value": "恩施"
    			},
    			{
    				"children": [
    				],
    				"name": "咸宁",
    				"value": "咸宁"
    			},
    			{
    				"children": [
    				],
    				"name": "仙桃",
    				"value": "仙桃"
    			},
    			{
    				"children": [
    				],
    				"name": "荆州",
    				"value": "荆州"
    			},
    			{
    				"children": [
    				],
    				"name": "黄石",
    				"value": "黄石"
    			},
    			{
    				"children": [
    				],
    				"name": "黄冈",
    				"value": "黄冈"
    			}
    		],
    		"name": "湖北",
    		"value": "湖北"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "海口",
    				"value": "海口"
    			},
    			{
    				"children": [
    				],
    				"name": "三亚",
    				"value": "三亚"
    			}
    		],
    		"name": "海南",
    		"value": "海南"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "松江",
    				"value": "松江"
    			},
    			{
    				"children": [
    				],
    				"name": "嘉定",
    				"value": "嘉定"
    			},
    			{
    				"children": [
    				],
    				"name": "上海",
    				"value": "上海"
    			}
    		],
    		"name": "上海",
    		"value": "上海"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "天水",
    				"value": "天水"
    			},
    			{
    				"children": [
    				],
    				"name": "武威",
    				"value": "武威"
    			},
    			{
    				"children": [
    				],
    				"name": "嘉峪关",
    				"value": "嘉峪关"
    			},
    			{
    				"children": [
    				],
    				"name": "兰州",
    				"value": "兰州"
    			},
    			{
    				"children": [
    				],
    				"name": "张掖",
    				"value": "张掖"
    			},
    			{
    				"children": [
    				],
    				"name": "定西",
    				"value": "定西"
    			},
    			{
    				"children": [
    				],
    				"name": "白银",
    				"value": "白银"
    			},
    			{
    				"children": [
    				],
    				"name": "庆阳",
    				"value": "庆阳"
    			},
    			{
    				"children": [
    				],
    				"name": "平凉",
    				"value": "平凉"
    			}
    		],
    		"name": "甘肃",
    		"value": "甘肃"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "长沙",
    				"value": "长沙"
    			},
    			{
    				"children": [
    				],
    				"name": "株洲",
    				"value": "株洲"
    			},
    			{
    				"children": [
    				],
    				"name": "郴州",
    				"value": "郴州"
    			},
    			{
    				"children": [
    				],
    				"name": "永州",
    				"value": "永州"
    			},
    			{
    				"children": [
    				],
    				"name": "宁乡",
    				"value": "宁乡"
    			},
    			{
    				"children": [
    				],
    				"name": "张家界",
    				"value": "张家界"
    			},
    			{
    				"children": [
    				],
    				"name": "吉首",
    				"value": "吉首"
    			},
    			{
    				"children": [
    				],
    				"name": "攸县",
    				"value": "攸县"
    			},
    			{
    				"children": [
    				],
    				"name": "益阳",
    				"value": "益阳"
    			},
    			{
    				"children": [
    				],
    				"name": "怀化",
    				"value": "怀化"
    			},
    			{
    				"children": [
    				],
    				"name": "娄底",
    				"value": "娄底"
    			},
    			{
    				"children": [
    				],
    				"name": "岳阳",
    				"value": "岳阳"
    			},
    			{
    				"children": [
    				],
    				"name": "邵阳",
    				"value": "邵阳"
    			},
    			{
    				"children": [
    				],
    				"name": "浏阳",
    				"value": "浏阳"
    			},
    			{
    				"children": [
    				],
    				"name": "湘潭",
    				"value": "湘潭"
    			},
    			{
    				"children": [
    				],
    				"name": "衡阳",
    				"value": "衡阳"
    			},
    			{
    				"children": [
    				],
    				"name": "常德",
    				"value": "常德"
    			},
    			{
    				"children": [
    				],
    				"name": "湘西",
    				"value": "湘西"
    			}
    		],
    		"name": "湖南",
    		"value": "湖南"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "濮阳",
    				"value": "濮阳"
    			},
    			{
    				"children": [
    				],
    				"name": "永城",
    				"value": "永城"
    			},
    			{
    				"children": [
    				],
    				"name": "新乡",
    				"value": "新乡"
    			},
    			{
    				"children": [
    				],
    				"name": "许昌",
    				"value": "许昌"
    			},
    			{
    				"children": [
    				],
    				"name": "漯河",
    				"value": "漯河"
    			},
    			{
    				"children": [
    				],
    				"name": "周口",
    				"value": "周口"
    			},
    			{
    				"children": [
    				],
    				"name": "商丘",
    				"value": "商丘"
    			},
    			{
    				"children": [
    				],
    				"name": "三门峡",
    				"value": "三门峡"
    			},
    			{
    				"children": [
    				],
    				"name": "安阳",
    				"value": "安阳"
    			},
    			{
    				"children": [
    				],
    				"name": "新郑",
    				"value": "新郑"
    			},
    			{
    				"children": [
    				],
    				"name": "济源",
    				"value": "济源"
    			},
    			{
    				"children": [
    				],
    				"name": "巩义",
    				"value": "巩义"
    			},
    			{
    				"children": [
    				],
    				"name": "焦作",
    				"value": "焦作"
    			},
    			{
    				"children": [
    				],
    				"name": "平顶山",
    				"value": "平顶山"
    			},
    			{
    				"children": [
    				],
    				"name": "鹤壁",
    				"value": "鹤壁"
    			},
    			{
    				"children": [
    				],
    				"name": "驻马店",
    				"value": "驻马店"
    			},
    			{
    				"children": [
    				],
    				"name": "洛阳",
    				"value": "洛阳"
    			},
    			{
    				"children": [
    				],
    				"name": "禹州",
    				"value": "禹州"
    			},
    			{
    				"children": [
    				],
    				"name": "开封",
    				"value": "开封"
    			},
    			{
    				"children": [
    				],
    				"name": "郑州",
    				"value": "郑州"
    			},
    			{
    				"children": [
    				],
    				"name": "南阳",
    				"value": "南阳"
    			},
    			{
    				"children": [
    				],
    				"name": "信阳",
    				"value": "信阳"
    			}
    		],
    		"name": "河南",
    		"value": "河南"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "荷泽",
    				"value": "荷泽"
    			},
    			{
    				"children": [
    				],
    				"name": "高密",
    				"value": "高密"
    			},
    			{
    				"children": [
    				],
    				"name": "德州",
    				"value": "德州"
    			},
    			{
    				"children": [
    				],
    				"name": "滨州",
    				"value": "滨州"
    			},
    			{
    				"children": [
    				],
    				"name": "平度",
    				"value": "平度"
    			},
    			{
    				"children": [
    				],
    				"name": "邹平",
    				"value": "邹平"
    			},
    			{
    				"children": [
    				],
    				"name": "胶州",
    				"value": "胶州"
    			},
    			{
    				"children": [
    				],
    				"name": "青岛",
    				"value": "青岛"
    			},
    			{
    				"children": [
    				],
    				"name": "青州",
    				"value": "青州"
    			},
    			{
    				"children": [
    				],
    				"name": "临沂",
    				"value": "临沂"
    			},
    			{
    				"children": [
    				],
    				"name": "泰安",
    				"value": "泰安"
    			},
    			{
    				"children": [
    				],
    				"name": "淄博",
    				"value": "淄博"
    			},
    			{
    				"children": [
    				],
    				"name": "枣庄",
    				"value": "枣庄"
    			},
    			{
    				"children": [
    				],
    				"name": "胶南",
    				"value": "胶南"
    			},
    			{
    				"children": [
    				],
    				"name": "广饶",
    				"value": "广饶"
    			},
    			{
    				"children": [
    				],
    				"name": "莱阳",
    				"value": "莱阳"
    			},
    			{
    				"children": [
    				],
    				"name": "邹城",
    				"value": "邹城"
    			},
    			{
    				"children": [
    				],
    				"name": "即墨",
    				"value": "即墨"
    			},
    			{
    				"children": [
    				],
    				"name": "诸城",
    				"value": "诸城"
    			},
    			{
    				"children": [
    				],
    				"name": "章丘",
    				"value": "章丘"
    			},
    			{
    				"children": [
    				],
    				"name": "菏泽",
    				"value": "菏泽"
    			},
    			{
    				"children": [
    				],
    				"name": "寿光",
    				"value": "寿光"
    			},
    			{
    				"children": [
    				],
    				"name": "滕州",
    				"value": "滕州"
    			},
    			{
    				"children": [
    				],
    				"name": "烟台",
    				"value": "烟台"
    			},
    			{
    				"children": [
    				],
    				"name": "日照",
    				"value": "日照"
    			},
    			{
    				"children": [
    				],
    				"name": "东营",
    				"value": "东营"
    			},
    			{
    				"children": [
    				],
    				"name": "潍坊",
    				"value": "潍坊"
    			},
    			{
    				"children": [
    				],
    				"name": "济宁",
    				"value": "济宁"
    			},
    			{
    				"children": [
    				],
    				"name": "威海",
    				"value": "威海"
    			},
    			{
    				"children": [
    				],
    				"name": "龙口",
    				"value": "龙口"
    			},
    			{
    				"children": [
    				],
    				"name": "聊城",
    				"value": "聊城"
    			},
    			{
    				"children": [
    				],
    				"name": "济南",
    				"value": "济南"
    			},
    			{
    				"children": [
    				],
    				"name": "莱州",
    				"value": "莱州"
    			},
    			{
    				"children": [
    				],
    				"name": "莱芜",
    				"value": "莱芜"
    			},
    			{
    				"children": [
    				],
    				"name": "临汾",
    				"value": "临汾"
    			}
    		],
    		"name": "山东",
    		"value": "山东"
    	},
    	{
    		"children": [
    			{
    				"children": [
    				],
    				"name": "大庆",
    				"value": "大庆"
    			},
    			{
    				"children": [
    				],
    				"name": "双鸭山",
    				"value": "双鸭山"
    			},
    			{
    				"children": [
    				],
    				"name": "哈尔滨",
    				"value": "哈尔滨"
    			},
    			{
    				"children": [
    				],
    				"name": "齐齐哈尔",
    				"value": "齐齐哈尔"
    			},
    			{
    				"children": [
    				],
    				"name": "绥化",
    				"value": "绥化"
    			},
    			{
    				"children": [
    				],
    				"name": "牡丹江",
    				"value": "牡丹江"
    			},
    			{
    				"children": [
    				],
    				"name": "佳木斯",
    				"value": "佳木斯"
    			},
    			{
    				"children": [
    				],
    				"name": "北安",
    				"value": "北安"
    			}
    		],
    		"name": "黑龙江",
    		"value": "黑龙江"
    	}
    ];
    	
    return {
        get: function () {
        	return tree;
        }
    };
}