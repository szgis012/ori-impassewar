package com.hifong.war.common
{
	
	import mx.collections.ArrayCollection;
	
	/**
	 * 报告信息
	 */ 
	 [Bindable]
	public class ReportInfo
	{
		/** 报告列表 */
		public var reportList:ArrayCollection;
		
		/** 当前报告类型 */
		public var currentReportType:int;
		/** 当前所在的页数*/
		public var currentPageNum:int;
		/** 总共的页数*/
		public var totalPageNum:int ;
		
		/** 一页显示的报告数量*/
		public const REPORT_COUNT:int = 10;
		
		
		public function ReportInfo()
		{
		}

	}
}