package edu.nju.frs.util;

public enum ApproveState {
	WaitHostPass,//等待项目主持人审核
	WaitFEPass,//等待财务审核
	FENotPass,//财务审查打回
	Finish,//已报销
}
