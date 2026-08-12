package com.war.constant;

/**
 * 地图常量
 * @author TopTong
 *
 */
public class MapConstant {

	/** 地图宽度 */
	public static final int MAP_WIDTH = 400;
	/** 地图高度 */
	public static final int MAP_HEIGHT = 400;
	
	
	
	/** 每行每区域野地数量 */
	public static final int ROW_WILDLAND_NUM = 20;

	/** 地图区域最大玩家数量 */
	public static final int MAP_AREA_MAX_CITY_NUM = 7500;
	
	
	/** 城市 */
	public static final int CATEGORY_CITY = 1;
	/** 要塞 */
	public static final int CATEGORY_STRONG_HOLD = 2;
	
	/** 空地 */
	public static final int CATEGORY_BLANK_FIELD = 10;
	/** 林场 */
	public static final int CATEGORY_TIMBER_LAND = 11;
	/** 铁矿 */
	public static final int CATEGORY_IRON_MINE = 12;
	/** 油井 */
	public static final int CATEGORY_OIL_WELL = 13;
	/** 麦田 */
	public static final int CATEGORY_WHEAT_FIELD = 14;
	
	/** 海洋 */
	public static final int CATEGORY_SEA = 20;
	/** 海岸 */
	public static final int CATEGORY_COAST = 29;
	
	/** 野怪 */
	public static final int CATEGORY_MONSTER = 31;
	
	/** 据点 */
	public static final int CATEGORY_LODGMENT = 41;
	/** 特训基地 */
	public static final int CATEGORY_SPECIAL_TRAINING_BASE = 51;
	
	
	/** 城市类型 */
	public static final int TYPE_CITY = 1;
	/** 要塞类型 */
	public static final int TYPE_STRONG_HOLD = 2;
	
	/** 空地类型数组 */
	public static final int[] TYPE_BLANK_FIELD_ARRAY = {1001, 1002, 1003, 1004, 1005};
	/** 林场类型数组 */
	public static final int[] TYPE_TIMBER_LAND_ARRAY = {1101, 1102, 1103, 1104, 1105};
	/** 铁矿类型数组 */
	public static final int[] TYPE_IRON_MINE_ARRAY = {1201, 1202, 1203, 1204, 1205};
	/** 油井类型数组 */
	public static final int[] TYPE_OIL_WELL_ARRAY = {1301, 1302, 1303, 1304, 1305};
	/** 麦田类型数组 */
	public static final int[] TYPE_WHEAT_FIELD_ARRAY = {1401, 1402, 1403, 1404, 1405};
	
	/** 海洋类型数组 */
	public static final int[] TYPE_SEA_ARRAY = {2001, 2002, 2003, 2004, 2005};
	/** 上陆地下海洋类型数组 */
	public static final int[] TYPE_LAND_TOP_SEA_BOTTOM_ARRAY = {2911, 2912, 2913, 2914, 2915};
	/** 上海洋下陆地类型数组 */
	public static final int[] TYPE_SEA_TOP_LAND_BOTTOM_ARRAY = {2921, 2922, 2923, 2924, 2925};
	/** 左陆地右海洋类型数组 */
	public static final int[] TYPE_LAND_LEFT_SEA_RIGHT_ARRAY = {2931, 2932, 2933, 2934, 2935};
	/** 左海洋右陆地类型数组 */
	public static final int[] TYPE_SEA_LEFT_LAND_RIGHT_ARRAY = {2941, 2942, 2943, 2944, 2945};
	
	/** 岛屿左上拐角 */
	public static final int TYPE_ISLAND_TOP_LEFT_CORNER = 2951;
	/** 岛屿右上拐角 */
	public static final int TYPE_ISLAND_TOP_RIGHT_CORNER = 2952;
	/** 岛屿坐下拐角 */
	public static final int TYPE_ISLAND_BOTTOM_LEFT_CORNER = 2953;
	/** 岛屿右下拐角 */
	public static final int TYPE_ISLAND_BOTTOM_RIGHT_CORNER = 2954;
	
	/** 左上据点 */
	public static final int TYPE_LODGMENT_1_1 = 4111;
	/** 左下据点 */
	public static final int TYPE_LODGMENT_1_2 = 4121;
	/** 右上据点 */
	public static final int TYPE_LODGMENT_2_1 = 4131;
	/** 右下据点 */
	public static final int TYPE_LODGMENT_2_2 = 4141;
	/** 上岛屿据点 */
	public static final int TYPE_LODGMENT_ISLAND_1 = 4151;
	/** 下岛屿据点 */
	public static final int TYPE_LODGMENT_ISLAND_3 = 4161;
	/** 中岛屿据点 */
	public static final int TYPE_LODGMENT_ISLAND_2 = 4171;
	
	/** 特训基地 */
	public static final int TYPE_SPECIAL_TRAINING_BASE = 5101;
	
	
	/** 左上区域 */
	public static final int AREA_1_1 = 11;
	/** 左下区域 */
	public static final int AREA_1_2 = 12;
	/** 右上区域 */
	public static final int AREA_2_1 = 21;
	/** 右下区域 */
	public static final int AREA_2_2 = 22;
	
	
	/** 上岛屿 */
	public static final int ISLAND_1 = 31;
	/** 中岛屿 */
	public static final int ISLAND_2 = 32;
	/** 下岛屿 */
	public static final int ISLAND_3 = 33;
	/** 海洋 */
	public static final int SEA = 41;
	/** 海岸 */
	public static final int COAST = 42;
	
	/** 左区域 */
	public static final int[] AREA_1 = {11, 12};
	/** 右区域 */
	public static final int[] AREA_2 = {21, 22};
	
	
	/** 左上区域开始X坐标 */
	public static final int AREA_1_1_START_POSX = 1;
	/** 左上区域开始Y坐标 */
	public static final int AREA_1_1_START_POSY = 1;
	/** 左上区域结束X坐标 */
	public static final int AREA_1_1_END_POSX = 119;
	/** 左上区域结束Y坐标 */
	public static final int AREA_1_1_END_POSY = 200;
	
	/** 左下区域开始X坐标 */
	public static final int AREA_1_2_START_POSX = 1;
	/** 左下区域开始Y坐标 */
	public static final int AREA_1_2_START_POSY = 201;
	/** 左下区域结束X坐标 */
	public static final int AREA_1_2_END_POSX = 119;
	/** 左下区域结束Y坐标 */
	public static final int AREA_1_2_END_POSY = 400;
	
	/** 右上区域开始X坐标 */
	public static final int AREA_2_1_START_POSX = 282;
	/** 右上区域开始Y坐标 */
	public static final int AREA_2_1_START_POSY = 1;
	/** 右上区域结束X坐标 */
	public static final int AREA_2_1_END_POSX = 400;
	/** 右上区域结束Y坐标 */
	public static final int AREA_2_1_END_POSY = 200;
	
	/** 右下区域开始X坐标 */
	public static final int AREA_2_2_START_POSX = 282;
	/** 右下区域开始Y坐标 */
	public static final int AREA_2_2_START_POSY = 201;
	/** 右下区域结束X坐标 */
	public static final int AREA_2_2_END_POSX = 400;
	/** 右下区域结束Y坐标 */
	public static final int AREA_2_2_END_POSY = 400;
	
	/** 左区域开始X坐标 */
	public static final int AREA_1_START_POSX = 1;
	/** 左区域开始Y坐标 */
	public static final int AREA_1_START_POSY = 1;
	/** 左区域结束X坐标 */
	public static final int AREA_1_END_POSX = 119;
	/** 左区域结束Y坐标 */
	public static final int AREA_1_END_POSY = 400;
	
	/** 右区域开始X坐标 */
	public static final int AREA_2_START_POSX = 282;
	/** 右区域开始Y坐标 */
	public static final int AREA_2_START_POSY = 1;
	/** 右区域结束X坐标 */
	public static final int AREA_2_END_POSX = 400;
	/** 右区域结束Y坐标 */
	public static final int AREA_2_END_POSY = 400;
	
	/** 岛屿1开始X坐标 */
	public static final int ISLAND_1_START_POSX = 151;
	/** 岛屿1开始Y坐标 */
	public static final int ISLAND_1_START_POSY = 1;
	/** 岛屿1结束X坐标 */
	public static final int ISLAND_1_END_POSX = 250;
	/** 岛屿1结束Y坐标 */
	public static final int ISLAND_1_END_POSY = 119;
	
	/** 岛屿2开始X坐标 */
	public static final int ISLAND_2_START_POSX = 151;
	/** 岛屿2开始Y坐标 */
	public static final int ISLAND_2_START_POSY = 151;
	/** 岛屿2结束X坐标 */
	public static final int ISLAND_2_END_POSX = 250;
	/** 岛屿2结束Y坐标 */
	public static final int ISLAND_2_END_POSY = 250;
	
	/** 岛屿3开始X坐标 */
	public static final int ISLAND_3_START_POSX = 151;
	/** 岛屿3开始Y坐标 */
	public static final int ISLAND_3_START_POSY = 281;
	/** 岛屿3结束X坐标 */
	public static final int ISLAND_3_END_POSX = 250;
	/** 岛屿3结束Y坐标 */
	public static final int ISLAND_3_END_POSY = 400;
	
	
	
	/** 左上可用区域开始X坐标 */
	public static final int AREA_1_1_AVAILABLE_START_POSX = 10;
	/** 左上可用区域开始Y坐标 */
	public static final int AREA_1_1_AVAILABLE_START_POSY = 10;
	/** 左上可用区域结束X坐标 */
	public static final int AREA_1_1_AVAILABLE_END_POSX = 110;
	/** 左上可用区域结束Y坐标 */
	public static final int AREA_1_1_AVAILABLE_END_POSY = 190;
	
	/** 左下可用区域开始X坐标 */
	public static final int AREA_1_2_AVAILABLE_START_POSX = 10;
	/** 左下可用区域开始Y坐标 */
	public static final int AREA_1_2_AVAILABLE_START_POSY = 210;
	/** 左下可用区域结束X坐标 */
	public static final int AREA_1_2_AVAILABLE_END_POSX = 110;
	/** 左下可用区域结束Y坐标 */
	public static final int AREA_1_2_AVAILABLE_END_POSY = 390;
	
	/** 右上可用区域开始X坐标 */
	public static final int AREA_2_1_AVAILABLE_START_POSX = 290;
	/** 右上可用区域开始Y坐标 */
	public static final int AREA_2_1_AVAILABLE_START_POSY = 10;
	/** 右上可用区域结束X坐标 */
	public static final int AREA_2_1_AVAILABLE_END_POSX = 390;
	/** 右上可用区域结束Y坐标 */
	public static final int AREA_2_1_AVAILABLE_END_POSY = 190;
	
	/** 右下可用区域开始X坐标 */
	public static final int AREA_2_2_AVAILABLE_START_POSX = 290;
	/** 右下可用区域开始Y坐标 */
	public static final int AREA_2_2_AVAILABLE_START_POSY = 210;
	/** 右下可用区域结束X坐标 */
	public static final int AREA_2_2_AVAILABLE_END_POSX = 390;
	/** 右下可用区域结束Y坐标 */
	public static final int AREA_2_2_AVAILABLE_END_POSY = 390;
	
	
	
	/** 纵向左海开始坐标 */
	public static final int V_SEA1_START = 120;
	/** 纵向左海结束坐标 */
	public static final int V_SEA1_END = 150+1;
	/** 纵向右海开始坐标 */
	public static final int V_SEA2_START = 250;
	/** 纵向右海结束坐标 */
	public static final int V_SEA2_END = 280+1;
	
	/** 横向上海开始坐标 */
	public static final int H_SEA1_START = 120;
	/** 横向上海结束坐标 */
	public static final int H_SEA1_END = 150+1;
	/** 横向下海开始坐标 */
	public static final int H_SEA2_START = 250;
	/** 横向下海结束坐标 */
	public static final int H_SEA2_END = 280+1;
	
	/** 左上据点X坐标 */
	public static final int LODGMENT_1_1_POSX = 60;
	/** 左上据点Y坐标 */
	public static final int LODGMENT_1_1_POSY = 100;
	
	/** 左下据点X坐标 */
	public static final int LODGMENT_1_2_POSX = 60;
	/** 左下据点Y坐标 */
	public static final int LODGMENT_1_2_POSY = 300;
	
	/** 右上据点X坐标 */
	public static final int LODGMENT_2_1_POSX = 340;
	/** 右上据点Y坐标 */
	public static final int LODGMENT_2_1_POSY = 100;
	
	/** 右下据点X坐标 */
	public static final int LODGMENT_2_2_POSX = 340;
	/** 右下据点Y坐标 */
	public static final int LODGMENT_2_2_POSY = 300;
	
	/** 上岛屿据点X坐标 */
	public static final int LODGMENT_ISLAND_1_POSX = 200;
	/** 上岛屿据点Y坐标 */
	public static final int LODGMENT_ISLAND_1_POSY = 60;
	
	/** 下岛屿据点X坐标 */
	public static final int LODGMENT_ISLAND_3_POSX = 200;
	/** 下岛屿据点Y坐标 */
	public static final int LODGMENT_ISLAND_3_POSY = 340;
	
	/** 中岛屿据点X坐标 */
	public static final int LODGMENT_ISLAND_2_POSX = 200;
	/** 中岛屿据点Y坐标 */
	public static final int LODGMENT_ISLAND_2_POSY = 200;
	
	/** 特训基地X坐标数组 */
	public static final int[] SPECIAL_TRAINING_BASE_POSX_ARRAY = {30, 60, 90, 30, 60, 90, 30, 60, 90, 30, 60, 90, 30, 60, 90, 30, 60, 90, 30, 60, 90, 30, 60, 90, 310, 340, 370, 310, 340, 370, 310, 340, 370, 310, 340, 370, 310, 340, 370, 310, 340, 370, 310, 340, 370, 310, 340, 370};
	/** 特训基地Y坐标数组 */
	public static final int[] SPECIAL_TRAINING_BASE_POSY_ARRAY = {40, 40, 40, 80, 80, 80, 120, 120, 120, 160, 160, 160, 240, 240, 240, 280, 280, 280, 320, 320, 320, 360, 360, 360, 40, 40, 40, 80, 80, 80, 120, 120, 120, 160, 160, 160, 240, 240, 240, 280, 280, 280, 320, 320, 320, 360, 360, 360};
	/** 特训基地X坐标数组 */
	public static final int[] SPECIAL_TRAINING_BASE_POSX_ARRAY_LODGMENT = {60, 60, 55, 65, 60, 60, 55, 65, 200, 200, 195, 205, 200, 200, 195, 205, 340, 340, 335, 345, 340, 340, 335, 345};
	/** 特训基地Y坐标数组 */
	public static final int[] SPECIAL_TRAINING_BASE_POSY_ARRAY_LODGMENT = {95, 105, 100, 100, 295, 305, 300, 300, 55, 65, 60, 60, 335, 345, 340, 340, 95, 105, 100, 100, 295, 305, 300, 300};
	
	/** 状态正常 */
	public static final int STATE_NORMAL = 1;
	/** 状态战斗中 */
	public static final int STATE_FIGHTING = 2;
}
